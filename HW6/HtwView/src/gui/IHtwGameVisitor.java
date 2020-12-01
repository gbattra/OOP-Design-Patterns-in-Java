package gui;

import java.util.List;

import htw.game.IHtwPlayer;
import htw.level.IHtwMaze;

public interface IHtwGameVisitor<R> {
  R visitGame(List<IHtwPlayer> players, IHtwMaze maze);
}
