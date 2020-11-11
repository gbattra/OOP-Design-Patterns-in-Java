package htw;

import maze.components.Coordinates;
import maze.components.nodes.AbstractRoomNode;
import maze.components.nodes.Node;
import maze.utils.Direction;

public class CaveNode extends AbstractRoomNode implements HTWNode {
  public CaveNode(Coordinates coordinates) {
    super(coordinates, 0, 0);
  }

  @Override
  public int loot(int gold) {
    return 0;
  }

  @Override
  public void setNode(Node node, Direction dir) throws IllegalArgumentException {
    if (!(node instanceof HTWNode)) {
      throw new IllegalArgumentException("Provided node is not an instance of HTWNode.");
    }
    super.setNode(node, dir);
  }
}
