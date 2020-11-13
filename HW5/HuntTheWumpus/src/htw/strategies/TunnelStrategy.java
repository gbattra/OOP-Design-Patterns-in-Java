package htw.strategies;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import htw.nodes.INode;
import maze.utils.Direction;

/**
 * Strategy for a tunnel node. A tunnel has only two exits.
 */
public class TunnelStrategy extends StandardStrategy implements INodeStrategy {
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
  public INode enter(Direction from, INode curr) {
      List<Direction> exits = new ArrayList<>(
            Arrays.asList(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST));
    while (exits.size() > 0) {
      Direction exit = exits.get(0);
      exits.remove(0);

      if (exit.equals(from)) {
        continue;
      }

      try {
        INode node = (INode) curr.getNode(exit);
        return node.enter(from);
      } catch (Exception ignored) {
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
  public boolean shoot(Direction direction, int count, INode curr) {
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
      hit |= ((INode) curr.getNode(exit)).shoot(exit, count);
    }

    return hit;
  }

  @Override
  public String toString() {
    return "Tunnel";
  }
}
