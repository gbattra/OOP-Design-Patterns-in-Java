package htw.level;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import gui.IHtwNodeVisitor;
import maze.components.ICoordinates;
import maze.Direction;

/**
 * Strategy for a tunnel node. A tunnel has only two exits.
 */
public class TunnelStrategy extends StandardStrategy implements IHtwNodeStrategy {
  /**
   * When a player enters the tunnel, the player is moved through the tunnel to the next node in
   * the maze. The `from` param in enter() ensures the tunnel does not return the player to the
   * node from which they entered.
   *
   * @param from the direction from which the node is entered
   * @param curr the node itself
   * @return the node where the tunnel leads
   */
  @Override
  public IHtwNode enter(Direction from, IHtwNode curr) throws IOException {
    List<Direction> exits = new ArrayList<>(
          Arrays.asList(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST));
    while (exits.size() > 0) {
      Direction exit = exits.get(0);
      exits.remove(0);

      if (exit.equals(from)) {
        continue;
      }

      try {
        IHtwNode node = (IHtwNode) curr.getNode(exit);
        return node.enter(exit.opposite());
      } catch (Exception ignored) {
        // do nothing
      }
    }

    throw new IllegalStateException("Could not enter tunnel. No valid exits found.");
  }

  /**
   * In the case of tunnels, an arrow passes through the tunnel without decrementing the count.
   *
   * @param direction the direction to shoot the arrow
   * @param count how many caves the arrow should traverse
   * @param curr the node itself
   * @return true if the wumpus is struck
   */
  @Override
  public boolean shoot(Direction direction, int count, IHtwNode curr) {
    if (count < 0) {
      throw new IllegalArgumentException("Count cannot be negative.");
    }
    boolean hit = false;
    List<Direction> exits = new ArrayList<>(
            Arrays.asList(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST));
    for (Direction exit : exits) {
      if (exit == direction.opposite()) {
        continue;
      }
      hit |= ((IHtwNode) curr.getNode(exit)).shoot(exit, count);
    }

    return hit;
  }

  @Override
  public boolean smelly(Direction from, IHtwNode curr) {
    boolean smelly = false;
    List<Direction> exits = new ArrayList<>(
            Arrays.asList(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST));
    for (Direction exit : exits) {
      if (exit.equals(from)) {
        continue;
      }
      smelly |= ((IHtwNode) curr.getNode(exit)).smelly(exit.opposite());
    }

    return smelly;
  }

  @Override
  public boolean drafty(Direction from, IHtwNode curr) {
    boolean drafty = false;
    List<Direction> exits = new ArrayList<>(
            Arrays.asList(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST));
    for (Direction exit : exits) {
      if (exit.equals(from)) {
        continue;
      }
      drafty |= ((IHtwNode) curr.getNode(exit)).drafty(exit.opposite());
    }

    return drafty;
  }

  @Override
  public IHtwNode adjacent(List<ICoordinates> traversed, IHtwNode curr) {
    if (traversed.contains(curr.getCoordinates())) {
      throw new IllegalStateException("Already traversed this node when finding adjacent.");
    }

    traversed.add(curr.getCoordinates());

    List<Direction> exits = new ArrayList<>(
            Arrays.asList(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST));
    for (Direction exit : exits) {
      try {
        return ((IHtwNode) curr.getNode(exit)).adjacent(traversed);
      } catch (Exception ignored) {
        // do nothing
      }
    }

    throw new IllegalStateException("No available nodes to enter.");
  }

  @Override
  public <R> R receive(IHtwNodeVisitor<R> visitor, IHtwNode curr) {
    return visitor.visitTunnel(curr);
  }

  @Override
  public String toString() {
    return "Tunnel";
  }
}
