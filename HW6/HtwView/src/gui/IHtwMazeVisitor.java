package gui;

import java.awt.*;

import htw.level.IHtwNode;

public interface IHtwMazeVisitor<R> {
  R visitMaze(IHtwNode root, Dimension dimension);
}
