package htw;

import maze.components.Coordinates;
import maze.components.nodes.AbstractRoomNode;
import maze.components.nodes.Node;
import maze.utils.Direction;

public class Cave extends AbstractRoomNode implements MazeNode {
  public Cave(Coordinates coordinates) {
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
    return this;
  }
}
