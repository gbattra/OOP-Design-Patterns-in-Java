package maze;

/**
 * A player in the game. Navigates a maze and loots the rooms for gold, or gets robbed
 * by thieves.
 */
public class MazePlayer implements IMazePlayer {
  private final String name;
  private final int goldCount;

  /**
   * Standard constructor for a player object.
   *
   * @param name the player's name (cannot be empty)
   * @throws IllegalArgumentException if the name is empty
   */
  public MazePlayer(String name) throws IllegalArgumentException {
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("Name cannot be empty.");
    }
    this.name = name;
    this.goldCount = 0;
  }

  private MazePlayer(
          String name,
          int goldCount) throws IllegalArgumentException {
    if (name.isEmpty()) {
      throw new IllegalArgumentException("Name cannot be empty.");
    }
    if (goldCount < 0) {
      throw new IllegalArgumentException("Gold count cannot be negative.");
    }

    this.name = name;
    this.goldCount = goldCount;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public int getGold() {
    return this.goldCount;
  }

  @Override
  public IMazePlayer loot(Node node) {
    return new MazePlayer(
            this.name,
            node.loot(this.goldCount));
  }
}
