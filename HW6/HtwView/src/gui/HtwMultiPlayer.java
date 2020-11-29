package gui;

import htw.game.HtwPlayer;

public class HtwMultiPlayer extends HtwPlayer {
  private final int number;

  public HtwMultiPlayer(String name, int number, int arrowCount) {
    super(name, arrowCount);
    this.number = number;
  }

  @Override
  public int number() {
    return this.number;
  }
}
