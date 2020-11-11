package htw;

import maze.components.nodes.Node;
import maze.utils.Direction;

public interface MazeNode extends Node {
  MazeNode enter(Direction from);
  MazeNode promote();
}
