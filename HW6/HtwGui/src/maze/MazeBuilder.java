package maze;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Builder class for the Maze2d class. Contains default configs to make customizing easier.
 */
public class MazeBuilder implements IMazeBuilder {
  protected final IConfiguration config;

  protected Node[][] visited;
  protected List<IEdge> edges;
  protected int totalExitCount = 0;
  protected int goldNodeCount;
  protected int thiefNodeCount;

  /**
   * Constructor for the maze builder.
   *
   * @param configuration config to use when building the maze
   * @throws IllegalArgumentException if config is null
   */
  public MazeBuilder(IConfiguration configuration) throws IllegalArgumentException {
    if (configuration == null) {
      throw new IllegalArgumentException("Configuration cannot be null.");
    }

    this.config = configuration;

    this.visited = new Node[configuration.rowCount()][configuration.columnCount()];
    this.edges = new ArrayList<>();
  }

  @Override
  public Node[][] visited() {
    return this.visited;
  }

  @Override
  public List<IEdge> edges() {
    return this.edges;
  }

  @Override
  public boolean isPerfect() {
    return this.config.perfectExitCount() == this.totalExitCount;
  }

  @Override
  public IMaze build() {
    Node start = new StandardRoomNode(this.config.start());
    this.grow(start);
    if (this.config.isRoomMaze()) {
      this.tearDownWalls();
    }

    return new Maze(
            this.visited[this.config.start().getY()][this.config.start().getX()],
            this.visited[this.config.goal().getY()][this.config.goal().getX()]);
  }

  @Override
  public Node generateRoom(ICoordinates c) {
    this.totalExitCount++;
    boolean isThief = this.config.random().nextDouble() <= this.config.thiefFrequency();
    if (isThief) {
      this.thiefNodeCount++;
      return new ThiefRoomNode(c, this.config.thiefPenalty(), this.config.goal().equals(c));
    }
    boolean isGold = this.config.random().nextDouble() <= this.config.goldFrequency();
    if (isGold) {
      this.goldNodeCount++;
      return new GoldRoomNode(c, this.config.goldAmount(), this.config.goal().equals(c));
    }

    return new StandardRoomNode(c, this.config.goal().equals(c));
  }

  @Override
  public void addVisited(Node node) {
    this.visited[node.getCoordinates().getY()][node.getCoordinates().getX()] = node;
  }

  @Override
  public void addEdge(ICoordinates one, ICoordinates two, Direction tail, Direction head) {
    IEdge edge = new Edge(one, two, tail, head);
    if (!this.edges.contains(edge)) {
      this.edges.add(edge);
    }
  }

  protected void grow(Node node) {
    // add this to the visited list
    this.addVisited(node);

    // get exit candidates
    List<Direction> exits = this.getPotentialExits(node);

    int currExitCount = 0;
    while (!exits.isEmpty()) {
      // randomly pick exit
      int exitIndex = exits.size() > 1 ? this.config.random().nextInt(exits.size()) : 0;
      Direction exit = exits.get(exitIndex);
      exits.remove(exitIndex);
      ICoordinates c = this.coordinatesAt(node, exit);

      // check if node where exit points has been visited
      Node other = this.visited[c.getY()][c.getX()];

      if (other != null) {
        // if has been visited, add an edge
        this.addEdge(
                node.getCoordinates(),
                other.getCoordinates(),
                exit.opposite(), exit);
      } else {
        currExitCount++;
        this.spawnAndGrow(node, exit, currExitCount);
      }
    }
  }

  protected void spawnAndGrow(Node node, Direction exit, int currExitCount) {
    // if has not been visited, instantiate new node and grow
    Node room = this.generateRoom(this.coordinatesAt(node, exit));
    node.setNode(room, exit);
    room.setNode(node, exit.opposite());

    // recursively call new node's grow to continue building out the maze
    this.grow(room);
  }

  protected void tearDownWalls() {
    while (edges.size() > this.config.targetEdgeCount()) {
      int index = this.config.random().nextInt(edges.size());
      IEdge edge = this.edges.get(index);
      edges.remove(index);
      this.totalExitCount++;
      Node tail = this.visited[edge.getTail().getY()][edge.getTail().getX()];
      Node head = this.visited[edge.getHead().getY()][edge.getHead().getX()];
      tail.setNode(head, edge.getHeadDirection());
      head.setNode(tail, edge.getTailDirection());
    }
  }

  protected List<Direction> getPotentialExits(Node node) {
    List<Direction> exits = new ArrayList<>(Arrays.asList(
            Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST));
    if ((node.getCoordinates().getX() == 0 && !this.config.isWrappingMaze())
            || !(node.getNode(Direction.WEST) instanceof DeadEndNode)) {
      exits.remove(Direction.WEST);
    }
    if ((node.getCoordinates().getX() == this.config.columnCount() - 1
            && !this.config.isWrappingMaze())
            || !(node.getNode(Direction.EAST) instanceof DeadEndNode)) {
      exits.remove(Direction.EAST);
    }
    if ((node.getCoordinates().getY() == 0 && !this.config.isWrappingMaze())
            || !(node.getNode(Direction.NORTH) instanceof DeadEndNode)) {
      exits.remove(Direction.NORTH);
    }
    if ((node.getCoordinates().getY() == this.config.rowCount() - 1
            && !this.config.isWrappingMaze())
            || !(node.getNode(Direction.SOUTH) instanceof DeadEndNode)) {
      exits.remove(Direction.SOUTH);
    }

    return exits;
  }

  protected ICoordinates coordinatesAt(Node node, Direction dir) throws IllegalArgumentException {
    int x = 0;
    int y = 0;
    if (dir == Direction.NORTH) {
      if (node.getCoordinates().getY() == 0) {
        y = this.config.rowCount() - 1;
      } else {
        y = node.getCoordinates().getY() - 1;
      }
      x = node.getCoordinates().getX();
    }
    if (dir == Direction.SOUTH) {
      if (node.getCoordinates().getY() == this.config.rowCount() - 1) {
        y = 0;
      } else {
        y = node.getCoordinates().getY() + 1;
      }
      x = node.getCoordinates().getX();
    }
    if (dir == Direction.WEST) {
      if (node.getCoordinates().getX() == 0) {
        x = this.config.columnCount() - 1;
      } else {
        x = node.getCoordinates().getX() - 1;
      }
      y = node.getCoordinates().getY();
    }
    if (dir == Direction.EAST) {
      if (node.getCoordinates().getX() == this.config.columnCount() - 1) {
        x = 0;
      } else {
        x = node.getCoordinates().getX() + 1;
      }
      y = node.getCoordinates().getY();
    }

    return new Coordinates(x, y);
  }
}
