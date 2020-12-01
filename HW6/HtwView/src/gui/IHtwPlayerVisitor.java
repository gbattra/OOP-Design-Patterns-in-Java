package gui;

import htw.game.IHtwPlayer;

public interface IHtwPlayerVisitor<R> {
  R visitPlayer(int playerId, int arrowCount, boolean isAlive);
}
