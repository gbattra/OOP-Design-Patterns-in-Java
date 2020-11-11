package htw.strategies;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import htw.nodes.HTWNode;
import maze.utils.Direction;

public class TunnelStrategy extends StandardStrategy implements Strategy {
  @Override
  public boolean shoot(Direction direction, int count, HTWNode curr) {
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
      hit |= ((HTWNode) curr.getNode(exit)).shoot(exit, count);
    }

    return hit;
  }

  @Override
  public HTWNode enter(Direction from, HTWNode curr) {
      List<Direction> exits = new ArrayList<>(
            Arrays.asList(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST));
    while (exits.size() > 0) {
      Direction exit = exits.get(0);
      exits.remove(0);

      if (exit.equals(from)) {
        continue;
      }

      try {
        HTWNode node = (HTWNode) curr.getNode(exit);
        return node.enter(from);
      } catch (Exception ignored) {
      }
    }

    throw new IllegalStateException("Could not enter tunnel. No valid exits found.");
  }
}
