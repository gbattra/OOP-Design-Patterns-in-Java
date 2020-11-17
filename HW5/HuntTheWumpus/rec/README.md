# Implementation
### Refactoring the `Maze` package
There was no major refactor required to support the functionality required by the
Hunt the Wumpus game. The most significant change was moving the functionality to build the
maze out of the `Node` itself and into a `MazeBuilder` class.  I did this for two reasons:

1) The `grow()` method, which is the core of the maze building process, is a temporary
method used only once to build the maze. It seemed like a waste of real-estate to have such
a significant one-off method reside in a class which did not need to support that functionality
for the end-user. Instead, it made more sense to wrap this method in a utility class, in this case
a `MazeBuilder` object.

2) As it was, the `Configuration` object had mixed roles: both holding the configuration
for the maze as well as the logic for building the maze with said configuration. By separating
these two roles into separate objects, I was able to create smoother extensibility when
implementing Hunt the Wumpus.

I also factored out the segment within the `grow()` method which generates the next `Node` to
insert into the maze into its own method. This allows extending classes to override the node-
generation piece of the over-all algorithm.

**Before:**
```
// MazeBuilder.java
...
else {
    // if has not been visited, instantiate new node and grow
    Node room = this.generateRoom(c);
    node.setNode(room, exit);
    room.setNode(node, exit.opposite());
    
    // recursively call new node's grow to continue building out the maze
    this.grow(room);
}
```

**After**
```
// MazeBuilder.java
...
      else {
        this.spawnAndGrow(node, exit);
      }
    }
}

protected void spawnAndGrow(Node node, Direction exit) {
    // if has not been visited, instantiate new node and grow
    Node room = this.generateRoom(this.coordinatesAt(node, exit));
    node.setNode(room, exit);
    room.setNode(node, exit.opposite());
    
    // recursively call new node's grow to continue building out the maze
    this.grow(room);
}
```
Using the Decorator pattern in the maze builder for Hunt the Wumpus, I overrode the
`spawnAndGrow()` method to upgrade `Tunnel` nodes to `Cave` nodes when the number of
exits for that node exceeded two.
```
@Override
protected void spawnAndGrow(Node node, Direction exit) {
    if (this.currExitCount > 1) {
      ((IHtwNode) node).setStrategy(this.generateStrategy((IHtwConfiguration) this.config));
    }

    super.spawnAndGrow(node, exit);
}
```

Unfortunately, a downside of extension is that many overridden operations require casting the
returned object to the child interface.

### `IHtwNode`
The key components of the maze are its node. The `IHtwNode` interface extends the `Node` interface
from the `maze` package to support functionality required for the game Hunt the Wumpus, such
as `shoot()`ing and arrow through tunnels and caves.


### `IHtwNodeStrategy`
Each new `IHtwNode` is instantiated with an `IHtwNodeStrategy`, which can be dynamically
changed at runtime. The types of strategies are `Tunnel`, `Standard`, `Bat`, `Pit` and `Wumpus`.
The `Bat` strategy wraps around another `IHtwNodeStrategy`. This allows us to have caves
that have superbats and something else, like a pit or the Wumpus. Bats could also occupy an
"empty" cave, represented by the `Standard` strategy.

Each `Strategy` supports the following operations:
- `enter(Diretion from, IHtwNode curr)` <br>
With the introduction of `Tunnels`, moving through the maze became more complex than is
supported by the `maze` package. Specifically, movement through tunnels is automatic and
immediate, carrying the player from one cave to another, regardless of how many tunnel nodes
are in between. This new behavior is handled by `enter()`. The `Standard` strategy simply returns
the node entered. This strategy is implemented by the `Pit` and `Wumpus` strategies, but
overridden by the `Bat` and `Tunnel` strategies.

In the `Tunnel` strategy, the player is simply passed through to the node at the next exit.
In the `Bat` strategy, a call to `enter()` will result a 50% chance that another random
node in the maze is selected and the player dropped into that node instead.

```
@Override
public IHtwNode enter(Direction from, IHtwNode curr) throws IOException {
    if (this.random.nextDouble() <= 0.5) {
      curr.logger().append("Snatch! You are grabbed by superbats and dropped in another cave!\n");
      int row = this.random.nextInt(this.rowCount);
      int column = this.random.nextInt(this.columnCount);
      ICoordinates coordinates = new Coordinates(column, row);
      return ((IHtwNode) curr.get(coordinates)).enter(from);
    }
    
    curr.logger().append("Close one! You dodge the claws of the superbats.\n");
    return this.parent.enter(from, curr);
}
```

- `shoot(Direction dir, int count, IHtwNode curr)` <br>
Similarly to `enter()` arrows can pass through tunnels without decrementing the specified
number of caves to traverse (the `count` arg). If `count == 0`, the current strategy will return
`false` if it is a `Pit`,`Standard`, or `Bat` strategy, and `true` if it is a `Wumpus` strategy.

- `receive(IHtwPlayer player, IHtwNode curr)` <br>
This method operates on the `IHtwPlayer` instance. A `Wumpus` or `Pit` strategy will `kill()`
the players while other strategies do nothing.

- `smelly()` & `drafty()`<br>
These methods are used to provide sensor data to the player. `Wumpus` strategies will return `true`
for `smell()` while `Pit` strategies will return `true` for `drafty()`.

- `adjacent(List<ICoordinates> coords)`<br>
Returns a `Map` of `Direction -> node id` pairs for all caves connected
directly to this node.

### `IHtwMaze`
An `IHtwMaze` object acts as an ADT around the root `Node` of the maze itself. This protects the
state of the maze. It exposes methods to `shoot()` and `move()` either by `Direction` or node `id`.

### `IHtwGame`
This is the top-level model for the `htw` package, in that it is the model with which the
`Controller` interacts. It holds an `IHtwPlayer` object and `IHtwMaze` object and facilitates
the interactions between the two. It also exposes a `gameOver()` method which determines the
state of the game through player and maze state (i.e. player has no arrows, or Wumpus is slain).

### `Controller`
The `Controller` takes user input and feeds it to the appropriate `IHtwGame` model methods.
It uses the Command Pattern to streamline and condense this functionality.

The controller implements the Java `Runnable` interface. Here is a breakdown of that
implementation:

```
@Override
  public void run() {
    // initialize the game
    if (this.game == null) {
      this.init();
    }
```

We start by checking if the model has been initialized. If not, we execute the `"restart"`
command, which prompts the user for player and maze configurations.
```
Function<Scanner, ICommand<IHtwGame>> entry = commands.get("restart");
ICommand<IHtwGame> cmd = entry.apply(this.scanner);
this.game = cmd.execute(this.game);
```
Once initialized, the game is ready to be played. The controller loops, at each iteration
prompting the user for an action. If `q` or `quit` is entered, it exits.
```
while (!this.game.isOver()) {
      try {
        this.out.append("\n").append(this.game.status(strategy));
        this.out.append("\n'shoot' or 'move'? ");
        String next = this.scanner.next();
        if (next.equalsIgnoreCase("q") || next.equalsIgnoreCase("quit")) {
          this.out.append("Quitting...");
          break;
        }
```
Once the action has been read by `Scanner`, the controller attemps to fetch the command
from its `Map` of `String -> ICommand` functions.
```
Function<Scanner, ICommand<IHtwGame>> entry = commands.get(next);
if (entry == null) {
  this.out.append("Command not found. Try again.");
  continue;
}

ICommand<IHtwGame> cmd = entry.apply(this.scanner);
this.game = cmd.execute(this.game);
```
The list of commands is generated by an injected `IHtwCommandMapFactory`. This dependency
injection allows us to swap out command implementations as well as support mocks for testing.
The implementation used in the `htw` package returns the following map:
```
return new HashMap<>() {{
      put("restart", s -> new StartGameCommand(
              s,
              out,
              new NewConfigCommand(
                      s,
                      out,
                      new CustomConfigCommand(s, out),
                      new StandardConfigCommand())));
      put("move", s -> new MoveCommand(s, out, strategy));
      put("shoot", s -> new ShootCommand(s, out, strategy));
    }};
```
- `StartGameCommand`<br>
This command sets up a new Hunt the Wumpus game, promting the user for player and maze
configuration. In addition to a `Scanner` and `Appendable` reference, it takes as
constructor args another `ICommand<T>` instance responsible for generating the
configuration for the maze to be generated.

- `MoveCommand`<br>
Moves the player given user input. Takes an `IActionStrategy` which allows different modes
of navigation: by `Direction` or by node `id`.

- `ShootCommand`<br>
Shoots an arrow from the current cave given user input. Takes an `IActionStrategy`
which allows different modes of shooting: by `Direction` or by node `id`.

# Demo

### Interactive Demo
The interactive demo driver consists of the follow `main()` method:
```
System.out.print("Controller type ([dir], 'id'): ");
String type = new Scanner(System.in).nextLine().split(" ")[0];
IActionStrategy strategy = new ActionByDirStrategy();
if (type.equalsIgnoreCase("id")) {
  strategy = new ActionByIdStrategy();
}

Runnable controller = new HtwController(
        new Scanner(System.in),
        System.out,
        strategy,
        new HtwCommandMapFactory());
controller.run();
```
The initial block is simply asking the user which `mode` they would prefer when playing the game:
`standard` means moving and shooting using the Cardinal directions, `id` means using the
adjacent node `id`s. This should be extracted from the `String[] args` param, but for ease
of use I have the driver prompting for this input.

All that's left from there is to instantiate the controller and `run()` it.

### Controls
- **move** -> `move <dir>` or `move <id>`
- **shoot** -> `shoot <dir> <count>` or `shoot <id> <count>`

i.e.:
```
move e
move 13

shoot n 4
shoot 16 4
```