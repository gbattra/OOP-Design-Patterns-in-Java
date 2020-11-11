package maze.components.nodes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import maze.components.Coordinates;
import maze.components.MazePath;
import maze.components.Path;
import maze.utils.Direction;

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
   * Second constructor for RoomNode. Use to set isGoal true.
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
  public abstract int loot(int gold);

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
    while (exits.size() > 0 && target instanceof DeadEndNode) {
      Node node = this.getNode(exits.get(0));
      exits.remove(0);
      target = node.getHelper(path);
    }

    return target;
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
}
