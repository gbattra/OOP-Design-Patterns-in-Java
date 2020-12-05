package htw;

/**
 * Player object used in a multiplayer game. Has a unique player number.
 */
public class HtwMultiPlayer extends HtwPlayer {
  private final int number;

  /**
   * Constructor for the multiplayer object.
   *
   * @param name the name of the player
   * @param number the player number
   * @param arrowCount starting arrow count
   */
  public HtwMultiPlayer(String name, int number, int arrowCount) {
    super(name, arrowCount);
    this.number = number;
  }

  @Override
  public int number() {
    return this.number;
  }
}
