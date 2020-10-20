package dungeon;

/**
 * Class representing monsters in our dungeon.
 */
public class Monster {

  private final String name;
  private final String description;
  private final int hitpoints;

  /**
   * Constructor for a monster.
   * 
   * @param name        the name of the monster
   * @param description the description of the monster
   * @param hitpoints   the number of hit points the monsters has
   */
  public Monster(String name, String description, int hitpoints) {
    this.name = name;
    this.description = description;
    this.hitpoints = hitpoints;
  }

  /**
   * Accessor for the name of the monster.
   * 
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * Accessor for the description of the monster.
   * 
   * @return the description
   */
  public String getDescription() {
    return description;
  }

  /**
   * Accessor for the number of hit points this monster has.
   * 
   * @return the hit points
   */
  public int getHitpoints() {
    return hitpoints;
  }

  @Override
  public String toString() {
    return String.format("%s (hitpoints = %d) is a %s", name, hitpoints, description);
  }
}
