package maze.components;

import maze.components.nodes.DeadEndNode;
import maze.components.nodes.Node;
import maze.utils.Direction;

/**
 * A maze ADT class which wraps Node functionality. Has start and goal node references. Also
 * has a pointer 'current' which can be moved using move().
 */
public class Maze implements IMaze {
  private final Node start;
  private final Node goal;

  private Node current;

  /**
   * ADT wrapper for the maze nodes. Tracks start, goal and current nodes. Navigate the maze pointer
   * by calling move(Direction dir). Wrappers for wealthiestPathTo, exploreTo and pathTo provided.
   *
   * @param start the start node
   * @param goal the goal node
   */
  public Maze(
          Node start,
          Node goal) {
    this.start = start;
    this.goal = goal;
    this.current = start;
  }

  @Override
  public Node getStart() {
    return this.start;
  }

  @Override
  public Node getCurrent() {
    return this.current;
  }

  @Override
  public Node getGoal() {
    return this.goal;
  }

  @Override
  public boolean move(Direction dir) {
    Node next = this.current.getNode(dir);
    if (next instanceof DeadEndNode) {
      return false;
    }
    this.current = next;

    return true;
  }

  @Override
  public IPath wealthiestPathTo(ICoordinates coordinates) throws IllegalArgumentException {
    return this.current.wealthiestPathTo(coordinates);
  }

  @Override
  public IPath pathTo(ICoordinates coordinates) throws IllegalArgumentException {
    return this.current.pathTo(coordinates);
  }

  @Override
  public IPath exploreTo(ICoordinates coordinates) {
    return this.current.exploreTo(coordinates);
  }

  @Override
  public Node get(ICoordinates coordinates) {
    return this.current.get(coordinates);
  }

  @Override
  public boolean canReach(ICoordinates coordinates) throws IllegalArgumentException {
    return this.current.canReach(coordinates);
  }
}
