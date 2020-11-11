package maze.models;

import maze.enums.Direction;
import maze.interfaces.Configuration;
import maze.interfaces.Coordinates;
import maze.interfaces.Node;
import maze.interfaces.Path;

/**
 * Concrete representation of a dead end node. Used for terminating paths in a maze.
 */
public class DeadEndNode implements Node {
  @Override
  public Node get(Coordinates coordinates) {
    return this.getHelper(new MazePath(coordinates));
  }

  @Override
  public Node getHelper(Path path) {
    return this;
  }

  @Override
  public int getGoldCount() {
    return 0;
  }

  @Override
  public boolean isDeadEnd() {
    return true;
  }

  @Override
  public boolean isThiefRoom() {
    return false;
  }

  @Override
  public double getThiefPenalty() {
    return 0;
  }

  @Override
  public Coordinates getCoordinates() {
    return new MazeCoordinates(0, 0);
  }

  @Override
  public int loot(int gold) {
    return gold;
  }

  @Override
  public void setNode(Node node, Direction dir) throws IllegalStateException {
    throw new IllegalStateException("Cannot set node on a deadend node.");
  }

  @Override
  public Node getNode(Direction dir) {
    return this;
  }

  @Override
  public boolean canReach(Coordinates coordinates) throws IllegalArgumentException {
    return this.canReachHelper(new MazePath(coordinates));
  }

  @Override
  public boolean canReachHelper(Path path) {
    return false;
  }

  @Override
  public Path wealthiestPathTo(Coordinates coordinates) throws IllegalArgumentException {
    return new MazePath(coordinates);
  }

  @Override
  public Path wealthiestPathToHelper(Path path) throws IllegalArgumentException {
    return path;
  }

  @Override
  public Path exploreTo(Coordinates coordinates) {
    return this.exploreHelper(new MazePath(coordinates));
  }

  @Override
  public Path exploreHelper(Path path) {
    return path;
  }

  @Override
  public Path pathTo(Coordinates coordinates) {
    return this.pathToHelper(new MazePath(coordinates));
  }

  @Override
  public Path pathToHelper(Path path) {
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

  @Override
  public boolean isGoldRoom() {
    return false;
  }
}
