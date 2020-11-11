package htw;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import maze.components.Coordinates;
import maze.components.nodes.AbstractRoomNode;
import maze.components.nodes.Node;
import maze.utils.Direction;

public class Tunnel extends AbstractRoomNode implements MazeNode {
  public Tunnel(Coordinates coordinates) {
    super(coordinates, 0, 0);
  }

  @Override
  public MazeNode enter(Direction from) {
    List<Direction> exits = new ArrayList<>(
            Arrays.asList(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST));
    while (exits.size() > 0) {
      Direction exit = exits.get(0);
      if (exit.equals(from)) {
        continue;
      }

      try {
        MazeNode node = (MazeNode) this.getNode(exit);
        exits.remove(0);
        return node.enter(from);
      } catch (Exception ignored) {
      }
    }

    throw new IllegalStateException("Could not enter tunnel. No valid exits found.");
  }

  @Override
  public int loot(int gold) {
    return 0;
  }

  @Override
  public void setNode(Node node, Direction dir) throws IllegalArgumentException {
    if (!(node instanceof MazeNode)) {
      throw new IllegalArgumentException("Provided node is not an instance of HTWNode.");
    }
    super.setNode(node, dir);
  }

  @Override
  public MazeNode promote() {
    MazeNode promoted = new Cave(this.coordinates);
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
}
