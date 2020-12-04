package gui;

import java.awt.*;

import htw.level.IHtwNode;

/**
 * Interface for a visitor of an HtwMaze object.
 *
 * @param <R> the return type of the function
 */
public interface IHtwMazeVisitor<R> {
  /**
   * Callback which the maze will call on the visitor.
   *
   * @param root the root node of the maze
   * @param dimension the dimensions of the maze (rows x cols)
   * @return R
   */
  R visitMaze(IHtwNode root, Dimension dimension);
}
