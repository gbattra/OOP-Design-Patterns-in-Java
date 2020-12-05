# Implementation

### `GuiController`

This is a separate controller from the one used for the Hunt the Wumpus console implementation.
That controller was **syncronous**, while this controller is **asyncronous**.

The `GuiController` implements the `IViewFeatures` interface, which represents the various
ways the view "hooks" into the controller and prompts it to handle user feedback. These methods
include:
* `onRestart(RestartRequest request)`
* `onShoot(int id, int count)`
* `onMove(int id)`
* `onMove(Direction dir)`

When a user takes an action, that action is routed to the corresponding feature. I.e.: right-clicking
a node in the maze trigger the `onShoot` method.

Any errors thrown by the model will be caught by the controller, which will prompt the view to
alert the users to the error:

```
try {
  boolean move = game.move(direction);
  this.view.populate(game);
} catch (IllegalArgumentException | IllegalStateException | IOException e) {
  this.view.alert(e.getMessage());
}
```

To initialize the game, the driver instantiates the controller and calls its `startNew()` method.
This builds a standard game using default configurations and passes that `HtwGame` model to the
view for display.

```
configurationBuilder = ((IHtwConfigurationBuilder) new HtwConfigurationBuilder()
              .setLogger(view)
              .setNumPlayers(2)
              .setRandomSeed(this.random.nextInt(1000)));
game = new HtwGameBuilder(configurationBuilder.build()).build();
game.start();
this.view.populate(game);
```

### `GuiView`

The `GuiView` object is what interfaces with the controller. A view instance is passed to
the `GuiController` on instantiation. The controller sets itself as that view object's
`features` delegate, hooking in to view events:
```
public GuiController(IView view) throws IllegalArgumentException {
    if (view == null) {
      throw new IllegalArgumentException("Cannot instantiate GuiController. View is null.");
    }
    
    this.view = view;
    this.view.setFeatures(this);
}
```

The `GuiView` object itself does not inherit from any Java Swing base class. It acts as a
functional wrapper around those views, capturing events that bubble up from the component
parts underneath.

When the controller tells the view to update itself by passing the updated `HtwGame` model
to its `populate()` method, the view "visits" the various pieces of the model using the visitor
pattern:
```
@Override
public void populate(IHtwGame game) {
    game.receive(this);
}

@Override
public Void visitGame(List<IHtwPlayer> players, IHtwMaze maze, int activePlayerNumber) {
    if (this.container != null) {
      frame.remove(this.container);
    }

    this.container = new Container(this, players, maze, activePlayerNumber);
    ...
```

The view removes its old `Container` component and instantiates a new one with the updated
values passed from the game model. **Note:** this implementation of the visitor pattern does
not pass the entire game model to the visitor and expose its `private` members through
getters. Instead it directly passes the values that the visitor cares about.\

This approach has some draw backs, but also benefits.
The main draw back is that future visitors may need
other attributes from the model object, which would mean updating the method to include
those properties, thus requiring some refactoring of unrelated code. However, I felt that
exposing getters to sensitive data that is otherwise protected from use elsewhere in the codebase,
simply for the purposes of a view, was an avoidable casualty. To add getters to these properties
meant they could be accessed and manipulated anywhere in the codebase. In this way, only
visitors are "trusted" with the data.

-----

**Note**: all objects discussed from here on out inherit from `JPanel`.
### `Container`

This is the main view component which wraps all the other view components. It is a
`KeyListener` which allows the user to navigate using the arrow keys:
```
@Override
public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_UP) {
      this.features.onMove(Direction.NORTH);
    } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
      this.features.onMove(Direction.SOUTH);
    } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
      this.features.onMove(Direction.EAST);
    } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
      this.features.onMove(Direction.WEST);
    }
}
```

When the `GuiView` visits the `HtwGame` model, it basically passes all of the data
from the model directly to the `Container` constructor, letting it do the heavy lifting of
populating the view.

Similarly to the `GuiView`, the container has a `features` object which hooks in to events
that bubble up to it. For example, in the code sample above, the container maps an arrow
key to a `Direction` and triggers the `features` `move(Direction)` callback.

Other components use a similar pattern to bubble up events all the way to the `GuiView` and
`GuiController`.

### `ButtonBar`

The first component underneath the `Container` is the `ButtonBar` which wraps around the
buttons "Quit" and "Restart". As their names would suggest, these buttons will either quit
the application, or restart the game.

On restart, a popup form prompts the user for configurations to use for the new game, such as
row and column size, starting arrow count, player count, and pit / bat frequency. Additionally,
it provides the option to use the same game as the current.

### `PlayerBar` & `PlayerView`

The `PlayerBar` wraps around `PlayerView` which displays player stats such as the current arrow
count and whether that player is alive. The `PlayerView` implements the `IHtwPlayerVisitor<R>`
interface, so that it may visit `HtwPlayer` objects to populate itself.

### `MazeView`

The `MazeView` wraps around a grid layout of `NodeView` components. When building the grid,
the maze view tracks each node view in a 2D array corresponding the node's position in the maze:It is also an
```
this.nodeViews = new NodeView[dimension.height][dimension.width];
for (int r = 0; r < dimension.height; r++) {
  for (int c = 0; c < dimension.width; c++) {
    IHtwNode node = (IHtwNode) root.get(new Coordinates(c, r));
    NodeView nodeView = new NodeView(node, this);
    nodeGrid.add(nodeView);
    this.nodeViews[r][c] = nodeView;
  }
}
```

`IHtwPlayerVisitor<R>`. It visits the player objects in the game in order to reflect their
current position in the maze:
```
@Override
public Void visitPlayer(IHtwPlayer player) {
    NodeView nodeView =
            this.nodeViews[player.currentPosition().getY()][player.currentPosition().getX()];

    nodeView.setOccupied(player.number());
}
```

### `NodeView`

The `NodeView` displays a given node in the maze. This could be a `BatCave`, `PitCave`,
`StandardCave`, `Tunnel` or `WumpusCave`. It uses the visitor pattern and double dispatch
to accomplish this. The `IHtwNodeVisitor<R>` interface has methods for visiting each of these
types of nodes:
* `visitBat(node)`
* `visitPit(node)`
* `visitStandard(node)`
* `visitTunnel(node)`
* `visitWumpus(node)`

As each node has its own strategy, each strategy will call into the
corresponding visitor method.

Each node view has an image icon which displays the cave itself as well as its exits,
a green fog if the player can smell the Wumpus while in that node, a gray fog if the
player can feel a breeze from a bottomless pit while in that node, or a bat if the node
is a Bat cave.

Each `HtwNode` object has a `visited` property, which is initialized to `false`. When a player
enters that node or passes through, that property is toggled to `true` and will stay `true`
throughout the entirety of the game. The node view uses this property to determine if it
should be visible to the players. As the game progresses, more node views become visible and
the maze begins to take shape.

### `LoggerView`

The logger view is a static text area which displays text output from the model. The output seen
here will correspond to the output you would see in the console version of the game. I.e.:
```
The cave is empty
You enter the cave and...
You smell a Wumpus
You enter the cave and...
You smell a Wumpus
The cave is empty
You enter the cave and...
Chomp chomp! You are eaten by the Wumpus!
You enter the cave and...
You smell a Wumpus
Nice shot! You've slain the Wumpus! VICTORY!
```

# Driver

To **play** the game is (hopefully) straightforward.

To **start**, open a command prompt and navigate to the directory containing the `htw.jar` file.

There are two **modes** with which to run the JAR:
* `java -jar htw.jar --text` <br>
    This will run the console version of the game. To use node id's instead of cardinal directions,
    add `--id` after the `--text` argument
    
* `java -jar htw.jar --gui` <br>
    This runs the game using the `GuiView` and `GuiController`.
    
The program will start with a game ready to go using the standard configuration. To change
these settings and start a new game, click the `Restart` button at the top right. You will
be prompted with a form of configuration options to customize the new game.
* `Use same maze?` - Use the existing maze configurations for the new game
* `Is multiplayer?` - Select for two-player mode, deselect for single player mode
* `Arrow count` - The number of arrows each player starts with
* `Row count` & `Column count` - The dimensions of the maze
* `Is room maze?` - Select if the maze should not be a perfect maze
* `Target edge count` - The desired number of walls for a room maze

**Note:** Betwen the `ButtonBar` and the `PlayerBar` you will see a small colored bar running across
the width of the view. This bar will be either `Magenta` or `Green`. These colors correspond
to the color of one of the two `PlayerView` components. It signals to the user which player
is currently taking their turn / action.

To **move** a player in the maze you may either:
* **Left-click** the space directly next to the player's current position, or **left-click** an
already-visited cave that is adjacent to the current position. For example, if a player
wants to move North, they would click the white space directly "north" of their current
node.

* Use an **arrow key**, with each key corresponding to the Cardinal directions North, South, East, West.

To **shoot** an arrow:
* **Right-click** the space in the direction you wish to shoot the arrow. For example, if a player
wants to shoot to the East from their current node, the would click the space directly left
of their current position. A pop-up will appear prompting you to enter the "distance" (in
number of caves) for the arrow to traverse. Tunnels will not count against this number.

If your arrow strikes true and slays the Wumpus, the logger view will display a message
indicating such and a pop-up will alert you that the game is over.
