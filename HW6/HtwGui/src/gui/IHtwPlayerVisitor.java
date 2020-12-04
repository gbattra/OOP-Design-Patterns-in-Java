package gui;

import htw.game.IHtwPlayer;

public interface IHtwPlayerVisitor<R> {
  R visitPlayer(IHtwPlayer player);
}
