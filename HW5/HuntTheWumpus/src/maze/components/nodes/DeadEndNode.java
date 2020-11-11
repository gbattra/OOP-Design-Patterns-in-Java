package maze.components.nodes;

import maze.components.ICoordinates;
import maze.components.MazeCoordinates;
import maze.components.MazePath;
import maze.components.IPath;
import maze.utils.Direction;

/**
 * Concrete representation of a dead end node. Used for terminating paths in a maze.
 */
public class DeadEndNode implements Node {
  @Override
  public Node get(ICoordinates coordinates) {
    return this.getHelper(new MazePath(coordinates));
  }

  @Override
  public Node getHelper(IPath path) {
    return this;
  }

  @Override
  public int getGoldCount() {
    return 0;
  }

  @Override
  public double getThiefPenalty() {
    return 0;
  }

  @Override
  public ICoordinates getCoordinates() {
    return new MazeCoordinates(0, 0);
  }

  @Override
  public int loot(int gold) {
    return gold;
  }

  @Override
  public void setNode(Node node, Direction dir) throws IllegalStateException {
    // do nothing
  }

  @Override
  public Node getNode(Direction dir) {
    return this;
  }

  @Override
  public boolean canReach(ICoordinates coordinates) throws IllegalArgumentException {
    return this.canReachHelper(new MazePath(coordinates));
  }

  @Override
  public boolean canReachHelper(IPath path) {
    return false;
  }

  @Override
  public IPath wealthiestPathTo(ICoordinates coordinates) throws IllegalArgumentException {
    return new MazePath(coordinates);
  }

  @Override
  public IPath wealthiestPathToHelper(IPath path) throws IllegalArgumentException {
    return path;
  }

  @Override
  public IPath exploreTo(ICoordinates coordinates) {
    return this.exploreHelper(new MazePath(coordinates));
  }

  @Override
  public IPath exploreHelper(IPath path) {
    return path;
  }

  @Override
  public IPath pathTo(ICoordinates coordinates) {
    return this.pathToHelper(new MazePath(coordinates));
  }

  @Override
  public IPath pathToHelper(IPath path) {
    return path;
  }

  @Override
  public String toString() {
    return "Dead End Node";
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

  @Override
  public boolean isGoal() {
    return false;
  }
}
