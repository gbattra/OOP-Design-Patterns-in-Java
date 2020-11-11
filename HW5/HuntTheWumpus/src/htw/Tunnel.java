package htw;

import maze.components.Coordinates;
import maze.components.nodes.AbstractRoomNode;
import maze.components.nodes.Node;
import maze.utils.Direction;

public class Tunnel extends AbstractRoomNode implements MazeNode {
  public Tunnel(Coordinates coordinates) {
    super(coordinates, 0, 0);
  }

  @Override
  public MazeNode move(Direction dir) throws IllegalStateException {
    return ((MazeNode) this.getNode(dir)).enter(dir.opposite());
  }

  @Override
  public MazeNode enter(Direction from) {
    return this;
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
