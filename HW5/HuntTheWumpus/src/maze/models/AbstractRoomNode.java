package maze.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import maze.enums.Direction;
import maze.interfaces.Configuration;
import maze.interfaces.Coordinates;
import maze.interfaces.Node;
import maze.interfaces.Path;

/**
 * Concrete representation of a room in the maze. Has exits North, South, East, West and a gold
 * count.
 */
public abstract class AbstractRoomNode implements Node {
  protected Node north = new DeadEndNode();
  protected Node south = new DeadEndNode();
  protected Node east = new DeadEndNode();
  protected Node west = new DeadEndNode();

  protected final Coordinates coordinates;
  protected final int goldCount;
  protected final boolean isGoal;
  protected final double thiefPenalty;

  /**
   * First constructor for RoomNode. Use when room contains gold for the player.
   *
   * @param coordinates the coordinates where this cell resides in the maze
   * @param goldCount the amount of gold for the player
   * @param thiefPenalty what percentage of gold should be stolen by thief
   * @throws IllegalArgumentException when goldCount is negative
   */
  public AbstractRoomNode(
          Coordinates coordinates,
          int goldCount,
          double thiefPenalty) throws IllegalArgumentException {
    if (goldCount < 0 || thiefPenalty < 0) {
      throw new IllegalArgumentException("Gold count and thief penalty cannot be negative.");
    }
    this.coordinates = coordinates;
    this.goldCount = goldCount;
    this.thiefPenalty = thiefPenalty;
    this.isGoal = false;
  }

  /**
   * Second constructor for RoomNode. Use to set isExit true.
   *
   * @param coordinates the coordinates where this cell resides in the maze
   * @param goldCount the amount of gold for the player
   * @param thiefPenalty what percentage of gold should be stolen by thief
   * @param isGoal true if this is the exit node
   * @throws IllegalArgumentException when goldCount is negative
   */
  public AbstractRoomNode(
          Coordinates coordinates,
          int goldCount,
          double thiefPenalty,
          boolean isGoal) throws IllegalArgumentException {
    if (goldCount < 0 || thiefPenalty < 0) {
      throw new IllegalArgumentException("Gold count and thief penalty cannot be negative.");
    }
    this.coordinates = coordinates;
    this.goldCount = goldCount;
    this.thiefPenalty = thiefPenalty;
    this.isGoal = isGoal;
  }

  @Override
  public abstract boolean isGoldRoom();

  @Override
  public abstract boolean isThiefRoom();

  @Override
  public boolean isDeadEnd() {
    return false;
  }

  @Override
  public boolean isGoal() {
    return this.isGoal;
  }

  @Override
  public int getGoldCount() {
    return this.goldCount;
  }

  @Override
  public double getThiefPenalty() {
    return this.thiefPenalty;
  }

  @Override
  public Coordinates getCoordinates() {
    return this.coordinates;
  }

  @Override
  public abstract int loot(int gold);

  @Override
  public Node get(Coordinates coordinates) {
    return this.getHelper(new MazePath(coordinates));
  }

  @Override
  public Node getHelper(Path path) {
    if (path.getCoordinatesTraversed().contains(this.coordinates)) {
      return new DeadEndNode();
    }

    path = path.enter(this);

    if (path.reachesTarget()) {
      return this;
    }

    Node target = new DeadEndNode();
    List<Direction> exits = new ArrayList<>(
            Arrays.asList(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST));
    while (exits.size() > 0 && target.isDeadEnd()) {
      Node node = this.getNode(exits.get(0));
      exits.remove(0);
      target = node.getHelper(path);
    }

    return target;
  }

  @Override
  public Configuration grow(Configuration configuration) {
    // add this to the visited list
    configuration.addVisited(this);

    // get exit candidates
    List<Direction> exits = this.getPotentialExits(configuration);

    while (!exits.isEmpty()) {
      // randomly pick exit
      int exitIndex = exits.size() > 1 ? configuration.random().nextInt(exits.size()) : 0;
      Direction exit = exits.get(exitIndex);
      exits.remove(exitIndex);
      Coordinates c = this.coordinatesAt(exit, configuration);

      // check if node where exit points has been visited
      Node other = configuration.visited()[c.getY()][c.getX()];

      if (other != null) {
        // if has been visited, add an edge
        configuration.addEdge(
                this.getCoordinates(),
                other.getCoordinates(),
                Direction.oppositeOf(exit), exit);
      } else {
        // if has not been visited, instantiate new node and grow
        Node room = configuration.generateRoom(c);
        this.setNode(room, exit);
        room.setNode(this, Direction.oppositeOf(exit));

        // recursively call new node's grow to continue building out the maze
        configuration = room.grow(configuration);
      }
    }

    return configuration;
  }

  @Override
  public void setNode(Node node, Direction dir) throws IllegalArgumentException {
    if (dir  == Direction.NORTH) {
      this.north = node;
    }
    if (dir  == Direction.SOUTH) {
      this.south = node;
    }
    if (dir  == Direction.EAST) {
      this.east = node;
    }
    if (dir  == Direction.WEST) {
      this.west = node;
    }
  }

  @Override
  public Node getNode(Direction dir) throws IllegalArgumentException {
    if (dir  == Direction.NORTH) {
      return this.north;
    }
    if (dir  == Direction.SOUTH) {
      return this.south;
    }
    if (dir  == Direction.EAST) {
      return  this.east;
    }
    if (dir  == Direction.WEST) {
      return  this.west;
    }
    throw new IllegalArgumentException("Invalid direction provided.");
  }

  @Override
  public boolean canReach(Coordinates coordinates) throws IllegalArgumentException {
    return this.canReachHelper(new MazePath(coordinates));
  }

  @Override
  public boolean canReachHelper(Path path) {
    if (path.getCoordinatesTraversed().contains(this.coordinates)) {
      return false;
    }

    path = path.enter(this);

    if (path.reachesTarget()) {
      return true;
    }

    return this.north.canReachHelper(path)
        || this.south.canReachHelper(path)
        || this.east.canReachHelper(path)
        || this.west.canReachHelper(path);
  }

  @Override
  public Path pathTo(Coordinates coordinates) {
    return this.pathToHelper(new MazePath(coordinates));
  }

  @Override
  public Path pathToHelper(Path path) {
    if (path.getCoordinatesTraversed().contains(this.coordinates)) {
      return path;
    }

    path = path.addCoordinates(this.coordinates);

    if (this.coordinates.equals(path.getTarget())) {
      return path.setReachesTarget(true);
    }

    Path north = this.north.pathToHelper(path);
    if (north.reachesTarget()) {
      return north;
    }

    Path south = this.south.pathToHelper(path);
    if (south.reachesTarget()) {
      return south;
    }

    Path east = this.east.pathToHelper(path);
    if (east.reachesTarget()) {
      return east;
    }

    Path west = this.west.pathToHelper(path);
    if (west.reachesTarget()) {
      return west;
    }

    return path;
  }

  @Override
  public Path wealthiestPathTo(Coordinates coordinates) throws IllegalArgumentException {
    return this.wealthiestPathToHelper(new MazePath(coordinates));
  }

  @Override
  public Path wealthiestPathToHelper(Path path) throws IllegalArgumentException {
    if (path.getCoordinatesTraversed().contains(this.coordinates)) {
      return path;
    }

    path = path.enter(this);

    if (path.reachesTarget()) {
      return path;
    }

    List<Path> paths = new ArrayList<>();
    paths.add(this.north.wealthiestPathToHelper(path));
    paths.add(this.south.wealthiestPathToHelper(path));
    paths.add(this.east.wealthiestPathToHelper(path));
    paths.add(this.west.wealthiestPathToHelper(path));

    paths = paths.stream().filter(Path::reachesTarget).collect(Collectors.toList());
    if (paths.isEmpty()) {
      return path;
    }

    return paths.stream().max(Comparator.comparingInt(Path::totalGold)).get();
  }

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

  @Override
  public String toString() {
    return String.format("Node: %s", this.coordinates.toString());
  }

  @Override
  public int hashCode() {
    return this.toString().hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (obj instanceof Node) {
      Node o = (Node) obj;
      return o.getCoordinates().equals(this.getCoordinates());
    }

    return false;
  }

  protected List<Direction> getPotentialExits(Configuration configuration) {
    List<Direction> exits = new ArrayList<>(Arrays.asList(
            Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST));
    if ((this.coordinates.getX() == 0 && !configuration.isWrappingMaze())
        || !(this.west.isDeadEnd())) {
      exits.remove(Direction.WEST);
    }
    if ((this.coordinates.getX() == configuration.columnCount() - 1
        && !configuration.isWrappingMaze())
        || !(this.east.isDeadEnd())) {
      exits.remove(Direction.EAST);
    }
    if ((this.coordinates.getY() == 0 && !configuration.isWrappingMaze())
        || !(this.north.isDeadEnd())) {
      exits.remove(Direction.NORTH);
    }
    if ((this.coordinates.getY() == configuration.rowCount() - 1
        && !configuration.isWrappingMaze())
        || !(this.south.isDeadEnd())) {
      exits.remove(Direction.SOUTH);
    }

    return exits;
  }

  protected Coordinates coordinatesAt(
          Direction dir, Configuration configuration) throws IllegalArgumentException {
    int x = 0;
    int y = 0;
    if (dir == Direction.NORTH) {
      if (this.coordinates.getY() == 0) {
        y = configuration.rowCount() - 1;
      } else {
        y = this.coordinates.getY() - 1;
      }
      x = this.coordinates.getX();
    }
    if (dir == Direction.SOUTH) {
      if (this.coordinates.getY() == configuration.rowCount() - 1) {
        y = 0;
      } else {
        y = this.coordinates.getY() + 1;
      }
      x = this.coordinates.getX();
    }
    if (dir == Direction.WEST) {
      if (this.coordinates.getX() == 0) {
        x = configuration.columnCount() - 1;
      } else {
        x = this.coordinates.getX() - 1;
      }
      y = this.coordinates.getY();
    }
    if (dir == Direction.EAST) {
      if (this.coordinates.getX() == configuration.columnCount() - 1) {
        x = 0;
      } else {
        x = this.coordinates.getX() + 1;
      }
      y = this.coordinates.getY();
    }

    return new MazeCoordinates(x, y);
  }
}
