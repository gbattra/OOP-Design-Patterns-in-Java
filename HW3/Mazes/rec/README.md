# Mazes

### Building the maze

The `mazes` package is a Java library for instantiating 2D mazes. It implements a linked-list
approach to representing mazes.

There are four types of mazes that can be constructed by this library:
- *perfect maze* - where there are no circular paths
- *room maze* - where there are circular paths
- *non-wrapping maze* - where the every path stays within the specified borders
- *wrapping maze* - where the maze paths may wrap around to the other axis

In this implementation, these differences, along with other customizable features
(such as `columnCount`, `rowCount`, `startCoordinates`, `goalCoordinates`, etc...)
are captured in a `Configuration` object. This will be useful for guiding how the node's
build out the maze.

A `Maze` is composed of `Nodes`. Each node has four `exits` also of type `Node`. Some
exits are `DeadEndNodes` which mirror the function of empty nodes in a linked-list.
All other nodes are extensions of the abstract class `AbstractRoomNode`. This abstract 
representation of a `Node` interface implements the bulk of the functionality related to
building the maze, which is done recursively. There are three classes which extend this
class:
- `StandardRoomNode` - Has no gold and is not a thief. Effectively an empty room
- `GoldRoomNode` - This node contains gold which a player may pick up
- `ThiefRoomNode` - This node contains a thief which steals a percentage of the player's gold


While there exists a builder class to streamline the processes of creating a maze,
here is how it would be done manually:
- Build a `Configuration` object capturing any customizable features of the maze
- Instantiate a `start` node, typically at `Coordinate(0, 0)`
- Call that node's `grow(Configuration config)` method, passing in the custom configurations

The algorithm for recursively growing the maze is a mix of "Rat in a Maze" / Backtracking
and Kruskal's. The program uses "Rat in a Maze" to generate a perfect maze. If the configs require
a room maze, Kruskal's algorithm is applied to reach the user-specified `targetEdgeCount`.

The "Rat in a Maze" algorithm works as follows:
```
1. In a node, mark the node as `visited`
2. randomly pick an exit
3. Attempt to move to the node at that exit
    a. If we have already visited that node randomly select a remaining exit
    b. Else move into that node and call that node to continue the recursion
```

This implementation differs in some respects. Below, I'll walk through the function
responsible for this:
```
interface Node {
    Configuration grow(Configuration config);
```

The implementation resides in the `AbstractRoomNode` class:
```
@Override
  public Configuration grow(Configuration configuration) {
    // add this to the visited list
    configuration.addVisited(this);
```
The first thing to occur upon entering the function is to mark the node as
`visited` by setting a reference to it in the `config`'s `Node[][] visited` property,
where the first index is the node's `Coordinate` `y` and the second coordinate `x`.

```
// get exit candidates
    List<Direction> exits = this.getPotentialExits(configuration);
```

Then, it compiles a list of potential directions to attempt to "grow into". It
does this using a `Configuration` object which helps the node understand its
coordinates relative to the rest of the maze. For example, if the node resides
on the border of a non-wrapping maze, it would the direction to that border from
the list of exit candidates.

```
while (!exits.isEmpty()) {
      // randomly pick exit
      int exitIndex = exits.size() > 1 ? configuration.random().nextInt(exits.size()) : 0;
      Direction exit = exits.get(exitIndex);
      exits.remove(exitIndex);
      Coordinates c = this.coordinatesAt(exit, configuration);
```
While there still remain un-tried exits, it samples randomly from the list of exits
and removes the one chosen from the list.

```
// check if node where exit points has been visited
      Node other = configuration.visited()[c.getY()][c.getX()];
```
It checks if there is a reference to a node at the `node`'s coordinates
in the `configuration`'s `visited` array.

```
if (other != null) {
        // if has been visited, add an edge
        configuration.addEdge(
                this.getCoordinates(),
                other.getCoordinates(),
                DirectionHelper.oppositeOf(exit), exit);
}
```
If a reference does exist, a new `Edge` object is added to the `List<Edges>`.

```
else {
    // if has not been visited, instantiate new node and grow
    Node room = configuration.generateRoom(c);
    this.setNode(room, exit);
    room.setNode(this, DirectionHelper.oppositeOf(exit));

    // recursively call new node's grow to continue building out the maze
    configuration = room.grow(configuration);
}
```

Otherwise, instantiate a new `node` and set it at the current node's chosen `exit`.
Then call into that new node's `grow()` method to continue building the maze.

Once the list of `exits` has been exhausted, the `while` loop will exit, and the function
will return the updated `configuration` object.

The node which "kicked off" the whole process will be set as the `start` node
of the `Maze` object. A `Maze` object is a light-weight ADT wrapper around the `Node`
functionality. A `Maze` holds a reference to both the `start` and `goal` nodes of the maze.
It also has a "pointer" to another node `current`, which can be moved by calling
the `move(Direction dir)`, which will set the pointer at whatever node reside at the
specified direction relative to `current`'s position. When a maze is initialized,
`current` is set to `start`.

As mentioned, there exists a `Builder` object which handles setting up the configuration
objected with default values, exposing functionality to overwrite those defaults.

```
Builder builder = new Maze2dBuilder()
        .setColumnCount(7)
        .setRowCount(7)
        .setStart(0, 0)
        .setGoal(6, 6)
        .setGoldFrequency(0.2)
        .setThiefFrequency(0.1);
Maze perfectMaze = builder.build();
```

### Traversing the maze
Nodes also traverse the maze recursively. A given `node` is able to determine
if it `canReach()` another node given the coordinates for that node. It can `get()`
a node at a given coordinate set. A node can also `exploreTo()` a coordinate set, entering
every room the maze before terminating. A node can create a random `pathTo()` a node at a coordinate set. And
a node can find the `wealthiestPathTo()` a node.

Similar algorithms to "Rat in a Maze" are used here. For example, here is the `exploreTo()`
implementation:
```
@Override
public Path exploreTo(Coordinates coordinates) {
    return this.exploreHelper(new MazePath(coordinates));
}

@Override
public Path exploreHelper(Path path) {
    if (path.getCoordinatesTraversed().contains(this.coordinates)) {
      return path;
    }

    path = path.enter(this);

    path = this.north.exploreHelper(path);
    path = this.south.exploreHelper(path);
    path = this.east.exploreHelper(path);
    path = this.west.exploreHelper(path);

    return path;
}
```

As you can see, some of these functions require traversing the
entire maze recursively, which a shortcoming of this implementation, as at some point,
`StackOverflow` is unavoidable. However, with this implementation, I was able to construct
mazes of up to `100x100` dimensions and perform each of these operations successfully.
Of course, that is on my machine, so results will vary.

### Playing the game

Finally, to put it all together, a `Game` object is used to facilitate navigating
a `Player` instance through the maze, collecting gold (or losing it) and handling
when the player enters the `goal` node, thus ending the game.

A `Game` node is instantiated with a reference to the `Maze` and a `Player` instance.
The two major methods of the `Game` object are:
- `void start()` <br>
Moves the player into the `start` node of the `Maze`.

- `boolean movePlayer(Direction direction)` <br>
Attempts to move the player from the maze's `current` node to the node
at the specified direction, relative to the `current` node. Returns false the `maze`
rejects this move (either because it is a border / `DeadEndNode` or because the
player has already visited that node). Otherwise, moves the player into that node,
`loot()`s it and checks if the entered node is the `goal` node. If yes, sets `gameOver`
to `true`.

### Interactive Program
To try out a sample program running this maze library, execute the `JAR` file: `InteractiveDemo`
found in the `rec` folder.

You will be promped to enter your name and to provide basic configurations
for the maze:
```
System.out.print("What is your name?\n");
String name = scanner.nextLine();
Player player = new MazePlayer(name);
System.out.printf("Hello, %s!\n", player.getName());

System.out.print("Configure your maze...\n");
Builder builder = new Maze2dBuilder();
int columnCount = readColumnCount();
int rowCount = readRowCount();
builder = builder.setColumnCount(columnCount).setRowCount(rowCount);
...
```
```
----------------------------------------------------
This is an INTERACTIVE demo of the maze program.
----------------------------------------------------
What is your name?
> Greg
Hello, Greg!
Configure your maze...
Enter the number of columns:
> 10
Enter the number of rows:
> 10
Enter the start row index (starting at zero):
> 0
Enter the start column index (starting at zero):
> 0
Enter the goal column index (starting at zero):
...
```
Once finished, the program will build a `maze` and setup a `game`. Then a loop
while wait for user input while the `!game.isOver()`:
```
System.out.print("Building the maze...\n");
Maze maze = builder.build();
System.out.print("Setting up the game...\n");
Game game = new MazeGame(player, maze);
System.out.print("Ready to play!\n");
game.start();

while (!game.isOver()) {
...
```
```
MAZE SUMMARY:
10 rows X 10 columns
Start: row 0, column 0
Goal: row 9, column 0
Is not a room maze
Is not a wrapping maze
----------------------------------------------------
Building the maze...
Setting up the game...
Ready to play!
----------------------------------------------------
Player (Greg): location - (0, 0), gold count - 0
----------------------------------------------------
Move the player (type 'north', 'south', 'east', or 'west'):
```
To navigate, type either `north`, `east`, `south`, or `west`.

After each move, the program will indicate:
- whether the move was successful, if not why
- if a gold node was entered, how much gold was looted
- if a thief node was entered, how much gold was taken
- the player's new location and updated gold count

```
System.out.print("Move the player (type 'north', 'south', 'east', or 'west'):\n");
Direction direction = readDirectionInput();
    System.out.printf("Moving %s\n", direction.toString());

if (!game.movePlayer(direction)) {
    System.out.printf(
        "Cannot move %s. Wall blocking the player!\n", direction.toString());
    System.out.print(playerState(game));
    continue;
}

Node current = game.getMaze().getCurrent();

if (current.isThiefRoom()) {
    System.out.print("Player encountered a thief!\n");
    System.out.printf("Gold count is now: %s gold pieces\n", game.getPlayer().getGold());
}
if (current.isGoldRoom()) {
    System.out.print("Player entered a room with gold!\n");
    System.out.printf("Gold count is now: %s gold pieces\n", game.getPlayer().getGold());
}
if (current.isGoal()) {
    System.out.print("Player reached the goal node of the maze!\n");
}
System.out.print(playerState(game));
```
```
----------------------------------------------------
Move the player (type 'north', 'south', 'east', or 'west'):
> east
Moving EAST
Cannot move EAST. Wall blocking the player!
Player (Greg): location - (1, 7), gold count - 0
----------------------------------------------------
Move the player (type 'north', 'south', 'east', or 'west'):
> north
Moving NORTH
Player entered a room with gold!
Gold count is now: 10 gold pieces
Player (Greg): location - (0, 7), gold count - 10
----------------------------------------------------
Move the player (type 'north', 'south', 'east', or 'west'):
> east
Moving EAST
Player encountered a thief!
Gold count is now: 9 gold pieces
Player (Greg): location - (0, 8), gold count - 9
```

### Demos
There are four other `JAR` files in `rec/JAR`. Each is a hardcoded run of the maze
program:
- `ExploreDemo` - This demo builds a simple maze and has the player explore every
room in the maze. To do this, it calls the `maze`'s `explore()` method, which returns
a `Path` object outlining a path through each node.
- `PerfectMazeDemo` - This demo builds a perfect maze and has the player navigate to
goal node.
- `WealthiestPathDemo` - This demo builds a simple maze and finds the `Path` from
the `start` to the `goal` of the maze which yields the highest `goldCount` for the
player.
- `WrappingMazeDemo` - This demo constructs a wrapping maze and finds a random path
to the goal node.

The player then moves through each node, with its `goldCount` and `coordinates`
printed out at each step, until it reaches the `goal` node and the loop exists.

As these demos is not a user-driven interaction, the `Game` object was not used for these demos.
Instead, I implemented a simple loop through the `Path`'s `coordinatesTraversed` to print out the
computed path.
