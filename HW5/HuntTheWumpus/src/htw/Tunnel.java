package htw;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import maze.components.Coordinates;
import maze.components.nodes.AbstractRoomNode;
import maze.components.nodes.Node;
import maze.utils.Direction;

public class Tunnel extends AbstractCaveNode implements HTWNode {
  public Tunnel(Coordinates coordinates) {
    super(coordinates);
    this.north = new DeadEnd();
    this.south = new DeadEnd();
    this.east = new DeadEnd();
    this.west = new DeadEnd();
  }

  @Override
  public HTWNode enter(Direction from) {
    List<Direction> exits = new ArrayList<>(
            Arrays.asList(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST));
    while (exits.size() > 0) {
      Direction exit = exits.get(0);
      exits.remove(0);

      if (exit.equals(from)) {
        continue;
      }

      try {
        HTWNode node = (HTWNode) this.getNode(exit);
        return node.enter(from);
      } catch (Exception ignored) {
      }
    }

    throw new IllegalStateException("Could not enter tunnel. No valid exits found.");
  }

  @Override
  public HTWNode promote() {
    HTWNode promoted = new Cave(this.coordinates);
    promoted.setNode(this.north, Direction.NORTH);
    promoted.setNode(this.east, Direction.EAST);
    promoted.setNode(this.south, Direction.SOUTH);
    promoted.setNode(this.west, Direction.WEST);

    this.north.setNode(promoted, Direction.SOUTH);
    this.south.setNode(promoted, Direction.NORTH);
    this.east.setNode(promoted, Direction.WEST);
    this.west.setNode(promoted, Direction.EAST);

    return promoted;
  }

  @Override
  public boolean shoot(Direction direction, int count) {
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
      hit |= ((HTWNode) this.getNode(exit)).shoot(exit, count);
    }

    return hit;
  }
}
