package dungeon;

/**
 * Interface for a level builder object. Adds rooms, monsters, treasure and items to a level.
 */
public interface ILevelBuilder {
  /**
   * Add a room to the level.
   *
   * @param description the description of the room
   * @return the level builder instance with updated level data
   * @throws IllegalStateException if too many rooms are added to the level
   */
  ILevelBuilder addRoom(String description) throws IllegalStateException;

  /**
   * Add goblin monsters to a specified room.
   *
   * @param roomNumber the room to which the monster is added
   * @param count the number of goblins to add
   * @return the level builder instance with updated level data
   * @throws IllegalArgumentException when specified room has not yet been added
   * @throws IllegalStateException when too many monsters are added to the level
   */
  ILevelBuilder addGoblin(int roomNumber, int count)
          throws IllegalStateException, IllegalArgumentException;

  /**
   * Add orc monsters to a specified room.
   *
   * @param roomNumber the room to which the monster is added
   * @param count the number of orcs to add
   * @return the level builder instance with updated level data
   * @throws IllegalArgumentException when specified room has not yet been added
   * @throws IllegalStateException when too many monsters are added to the level
   */
  ILevelBuilder addOrc(int roomNumber, int count)
          throws IllegalStateException, IllegalArgumentException;

  /**
   * Add org monsters to a specified.
   *
   * @param roomNumber the room to which the monster is added
   * @param count the number of ogres to add
   * @return the level builder instance with updated level data
   * @throws IllegalArgumentException when specified room has not yet been added
   * @throws IllegalStateException when too many monsters are added to the level
   */
  ILevelBuilder addOgre(int roomNumber, int count)
          throws IllegalStateException, IllegalArgumentException;

  /**
   * Adds a human to the level.
   *
   * @param roomNumber the number to which the human is added
   * @param name the name of the human
   * @param description a description of the human
   * @param hitPoints the hitpoints for the human
   * @return the level builder instance with updated level data
   * @throws IllegalArgumentException when the room has not been added or hitPoint is negative
   * @throws IllegalStateException when too many monsters are added to the level
   */
  ILevelBuilder addHuman(int roomNumber,
                          String name,
                          String description,
                          int hitPoints)
          throws IllegalStateException, IllegalArgumentException;

  /**
   * Adds a potion to the specified room.
   *
   * @param roomNumber the room to which the potion is added
   * @return the level builder instance with updated level data
   * @throws IllegalStateException when the specified room has not been set
   * @throws IllegalStateException when too many treasures are added to a level
   */
  ILevelBuilder addPotion(int roomNumber)
          throws IllegalStateException, IllegalArgumentException;

  /**
   * Adds gold to a room in the level.
   *
   * @param roomNumber the room to which the gold is added
   * @param value the value of the gold added
   * @return the level builder instance with updated level data
   * @throws IllegalStateException when the specified room has not been set or value is <= 0
   * @throws IllegalArgumentException when too many treasures are added to a level
   */
  ILevelBuilder addGold(int roomNumber, int value)
          throws IllegalStateException, IllegalArgumentException;

  /**
   * Adds a weapon to a room in the level.
   *
   * @param roomNumber the room to which the weapon is added
   * @param description the level builder instance with updated level data
   * @return the level builder instance with updated level data
   * @throws IllegalStateException when the specified room has not been set
   * @throws IllegalArgumentException when too many treasures are added to a level
   */
  ILevelBuilder addWeapon(int roomNumber, String description)
          throws IllegalStateException, IllegalArgumentException;

  /**
   * Adds a special item to a room in the level.
   *
   * @param roomNumber the room to which the item is added
   * @param description a description of the item
   * @param value the value of the item
   * @return the level builder instance with updated level data
   * @throws IllegalStateException when the specified room has not been set or value <= 0
   * @throws IllegalArgumentException when too many treasures are added to a level
   */
  ILevelBuilder addSpecialItem(int roomNumber, String description, int value)
          throws IllegalStateException, IllegalArgumentException;

  /**
   * Builds and returns the Level instance.
   *
   * @return the level instance
   * @throws IllegalStateException when the level has not yet been fully built
   */
  Level build() throws IllegalStateException;
}
