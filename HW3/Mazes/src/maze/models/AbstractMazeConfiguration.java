package maze.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import maze.enums.Direction;
import maze.interfaces.Configuration;
import maze.interfaces.Coordinates;
import maze.interfaces.Edge;
import maze.interfaces.Node;

public abstract class AbstractMazeConfiguration implements Configuration {
  protected final int columnCount;
  protected final int rowCount;
  protected final Coordinates start;
  protected final Coordinates goal;
  protected final int randomSeed;
  protected final Random random;
  protected final double thiefPenalty;
  protected final double thiefFrequency;
  protected final double goldFrequency;
  protected final int goldAmount;
  protected final boolean isWrappingMaze;
  protected final boolean isRoomMaze;
  protected final int perfectExitCount;
  protected final int targetEdgeCount;

  protected Node[][] visited;
  protected List<Edge> edges;
  protected int exitCount = 0;
  protected int goldNodeCount;
  protected int thiefNodeCount;

  /**
   * Constructor for the AbstractMazeConfiguration.
   *
   * @param rowCount the row count of the maze
   * @param columnCount the column count of the maze
   * @param start the start coordinates of the maze
   * @param goal the goal coordinates of the maze
   * @param thiefPenalty the thief penalty for a thief node
   * @param thiefFrequency the frequency of thief nodes in the maze
   * @param goldFrequency the frequency of gold nodes in the maze
   * @param goldAmount the amount of gold in a gold node
   * @param isWrappingMaze is this maze a wrapping maze?
   * @param isRoomMaze is this maze a room maze?
   * @param targetEdgeCount the desired target edge count after the maze is built
   *                        (used for Kruskals)
   * @param randomSeed the random seed to use when building the maze
   * @throws IllegalArgumentException when values are negative or out of bounds
   */
  public AbstractMazeConfiguration(
          int rowCount,
          int columnCount,
          Coordinates start,
          Coordinates goal,
          double thiefPenalty,
          double thiefFrequency,
          double goldFrequency,
          int goldAmount,
          boolean isWrappingMaze,
          boolean isRoomMaze,
          int targetEdgeCount,
          int randomSeed) throws IllegalArgumentException {
    if (goldAmount < 0 || targetEdgeCount < 0) {
      throw new IllegalArgumentException(
              "goldAmount and targetEdgeCount must not be negative.");
    }

    if (thiefPenalty < 0 || thiefPenalty > 1
        || thiefFrequency < 0 || thiefFrequency > 1
        || goldFrequency < 0 || goldFrequency > 1) {
      throw new IllegalArgumentException(
              "thiefPenalty, thiefFrequency, and goldFrequency must be between 0 and 1 inclusive.");
    }

    if (columnCount <= 0 || rowCount <= 0) {
      throw new IllegalArgumentException(
              "columnCount and rowCount must be greater than zero.");
    }


    this.columnCount = columnCount;
    this.rowCount = rowCount;
    this.start = start;
    this.goal = goal;
    this.thiefPenalty = thiefPenalty;
    this.thiefFrequency = thiefFrequency;
    this.goldFrequency = goldFrequency;
    this.goldAmount = goldAmount;
    this.perfectExitCount = (rowCount * columnCount) - 1;
    this.targetEdgeCount = targetEdgeCount;
    this.isRoomMaze = isRoomMaze;
    this.isWrappingMaze = isWrappingMaze;
    this.randomSeed = randomSeed;
    this.random = randomSeed > 0 ? new Random(randomSeed) : new Random();
    this.visited = new Node[rowCount][columnCount];
    this.edges = new ArrayList<>();
  }

  @Override
  public Configuration growMaze() {
    Node start = this.generateStart();
    start.grow(this);
    if (this.isRoomMaze) {
      this.applyKruskals();
    }

    return this;
  }

  @Override
  public int columnCount() {
    return this.columnCount;
  }

  @Override
  public int rowCount() {
    return this.rowCount;
  }

  @Override
  public double thiefPenalty() {
    return this.thiefPenalty;
  }

  @Override
  public double thiefFrequency() {
    return this.thiefFrequency;
  }

  @Override
  public double goldFrequency() {
    return this.goldFrequency;
  }

  @Override
  public int goldAmount() {
    return this.goldAmount;
  }

  @Override
  public Random random() {
    return this.random;
  }

  @Override
  public int randomSeed() {
    return this.randomSeed;
  }

  @Override
  public int targetEdgeCount() {
    return this.targetEdgeCount;
  }

  @Override
  public int exitCount() {
    return this.exitCount;
  }

  @Override
  public boolean isRoomMaze() {
    return this.isRoomMaze;
  }

  @Override
  public boolean isWrappingMaze() {
    return this.isWrappingMaze;
  }

  @Override
  public Node[][] visited() {
    return this.visited;
  }

  @Override
  public List<Edge> edges() {
    return this.edges;
  }

  @Override
  public Coordinates startCoordinates() {
    return this.start;
  }

  @Override
  public Coordinates goalCoordinates() {
    return this.goal;
  }

  @Override
  public void addVisited(Node node) {
    this.visited[node.getCoordinates().getY()][node.getCoordinates().getX()] = node;
  }

  @Override
  public Node generateRoom(Coordinates c) {
    this.exitCount++;
    boolean isThief = this.random().nextDouble() <= this.thiefFrequency();
    if (isThief) {
      this.thiefNodeCount++;
      return new ThiefRoomNode(c, this.thiefPenalty(), this.goal.equals(c));
    }
    boolean isGold = this.random().nextDouble() <= this.goldFrequency();
    if (isGold) {
      this.goldNodeCount++;
      return new GoldRoomNode(c, this.goldAmount(), this.goal.equals(c));
    }

    return new StandardRoomNode(c, this.goal.equals(c));
  }

  @Override
  public Node generateStart() {
    return new StandardRoomNode(this.start);
  }

  @Override
  public void addEdge(Coordinates one, Coordinates two, Direction tail, Direction head) {
    Edge edge = new MazeEdge(one, two, tail, head);
    if (!this.edges.contains(edge)) {
      this.edges.add(edge);
    }
  }

  @Override
  public boolean isPerfect() {
    return this.perfectExitCount == this.exitCount;
  }

  protected void applyKruskals() {
    while (edges.size() > this.targetEdgeCount) {
      int index = this.random.nextInt(edges.size());
      Edge edge = this.edges.get(index);
      edges.remove(index);
      this.exitCount++;
      Node tail = this.visited[edge.getTail().getY()][edge.getTail().getX()];
      Node head = this.visited[edge.getHead().getY()][edge.getHead().getX()];
      tail.setNode(head, edge.getHeadDirection());
      head.setNode(tail, edge.getTailDirection());
    }
  }
}
