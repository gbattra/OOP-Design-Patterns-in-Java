# Mazes


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




