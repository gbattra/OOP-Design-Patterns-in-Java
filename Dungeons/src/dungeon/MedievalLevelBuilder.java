package dungeon;

public class MedievalLevelBuilder implements ILevelBuilder {
  private final int numberOfRooms;
  private final int numberOfMonsters;
  private final int numberOfTreasures;

  private final Level level;

  private int roomCount;
  private int monsterCount;
  private int treasureCount;

  public MedievalLevelBuilder(
          int levelNumber,
          int numberOfRooms,
          int numberOfMonsters,
          int numberOfTreasures) throws IllegalArgumentException {
    if (levelNumber < 0
        || numberOfRooms < 0
        || numberOfMonsters < 0
        || numberOfTreasures < 0) {
      throw new IllegalArgumentException(
              "Level number, number of rooms, number of monsters and number of treasures"
              + "must all be non-negative.");
    }
    this.level = new Level(levelNumber);
    this.numberOfRooms = numberOfRooms;
    this.numberOfMonsters = numberOfMonsters;
    this.numberOfTreasures = numberOfTreasures;
  }

  /**
   * Add a room to the level.
   *
   * @param description the description of the room
   * @return the level builder instance with updated level data
   * @throws IllegalStateException if too many rooms are added to the level
   */
  public ILevelBuilder addRoom(String description) throws IllegalStateException {
    if (this.roomCount == numberOfRooms) {
      throw new IllegalStateException(
              String.format("Level cannot have more than %s rooms.", this.numberOfRooms));
    }
    this.roomCount++;
    this.level.addRoom(description);
    return this;
  }

  /**
   * Add goblin monsters to a specified room.
   *
   * @param roomNumber the room to which the monster is added
   * @param count the number of goblins to add
   * @return the level builder instance with updated level data
   * @throws IllegalArgumentException when specified room has not yet been added
   * @throws IllegalStateException when too many monsters are added to the level
   */
  public ILevelBuilder addGoblin(
          int roomNumber,
          int count) throws IllegalStateException, IllegalArgumentException {
    if (roomNumber < 0) {
      throw new IllegalArgumentException("Invalid room number. Must be non-negative.");
    }
    if (this.roomCount - 1 < roomNumber) {
      throw new IllegalArgumentException(
              String.format("Room number %s has not yet been set.", roomNumber));
    }
    if (this.monsterCount + count > this.numberOfMonsters) {
      throw new IllegalStateException(
              String.format("Total number of monsters cannot exceed %s", this.numberOfMonsters));
    }

    for (int i = 0; i < count; i++) {
      this.monsterCount++;
      Monster monster = new Monster(
              MonsterType.GOBLIN.name,
              MonsterType.GOBLIN.description,
              MonsterType.GOBLIN.hitPoints);
      this.level.addMonster(roomNumber, monster);
    }

    return this;
  }

  /**
   * Add orc monsters to a specified room.
   *
   * @param roomNumber the room to which the monster is added
   * @param count the number of orcs to add
   * @return the level builder instance with updated level data
   * @throws IllegalArgumentException when specified room has not yet been added
   * @throws IllegalStateException when too many monsters are added to the level
   */
  public ILevelBuilder addOrc(
          int roomNumber,
          int count) throws IllegalStateException, IllegalArgumentException {
    if (roomNumber < 0) {
      throw new IllegalArgumentException("Invalid room number. Must be non-negative.");
    }
    if (this.roomCount - 1 < roomNumber) {
      throw new IllegalArgumentException(
              String.format("Room number %s has not yet been set.", roomNumber));
    }
    if (this.monsterCount + count > this.numberOfMonsters) {
      throw new IllegalStateException(
              String.format("Total number of monsters cannot exceed %s", this.numberOfMonsters));
    }

    for (int i = 0; i < count; i++) {
      this.monsterCount++;
      Monster monster = new Monster(
              MonsterType.ORC.name,
              MonsterType.ORC.description,
              MonsterType.ORC.hitPoints);
      this.level.addMonster(roomNumber, monster);
    }

    return this;
  }

  /**
   * Add ogre monsters to a specified.
   *
   * @param roomNumber the room to which the monster is added
   * @param count the number of ogres to add
   * @return the level builder instance with updated level data
   * @throws IllegalArgumentException when specified room has not yet been added
   * @throws IllegalStateException when too many monsters are added to the level
   */
  public ILevelBuilder addOgre(
          int roomNumber,
          int count) throws IllegalStateException, IllegalArgumentException{
    if (roomNumber < 0) {
      throw new IllegalArgumentException("Invalid room number. Must be non-negative.");
    }
    if (this.roomCount - 1 < roomNumber) {
      throw new IllegalArgumentException(
              String.format("Room number %s has not yet been set.", roomNumber));
    }
    if (this.monsterCount + count > this.numberOfMonsters) {
      throw new IllegalStateException(
              String.format("Total number of monsters cannot exceed %s", this.numberOfMonsters));
    }

    for (int i = 0; i < count; i++) {
      this.monsterCount++;
      Monster monster = new Monster(
              MonsterType.OGRE.name,
              MonsterType.OGRE.description,
              MonsterType.OGRE.hitPoints);
      this.level.addMonster(roomNumber, monster);
    }

    return this;
  }

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
  public ILevelBuilder addHuman(
          int roomNumber,
          String name,
          String description,
          int hitPoints) throws IllegalStateException, IllegalArgumentException {
    if (roomNumber < 0) {
      throw new IllegalArgumentException("Invalid room number. Must be non-negative.");
    }
    if (hitPoints <= 0) {
      throw new IllegalArgumentException("Hit points must be great than zero.");
    }
    if (this.roomCount - 1 < roomNumber) {
      throw new IllegalArgumentException(
              String.format("Room number %s has not yet been set.", roomNumber));
    }
    if (this.monsterCount + 1 > this.numberOfMonsters) {
      throw new IllegalStateException(
              String.format("Total number of monsters cannot exceed %s", this.numberOfMonsters));
    }

    this.monsterCount++;
    Monster monster = new Monster(name, description, hitPoints);
    this.level.addMonster(roomNumber, monster);

    return this;
  }

  /**
   * Adds a potion to the specified room.
   *
   * @param roomNumber the room to which the potion is added
   * @return the level builder instance with updated level data
   * @throws IllegalStateException when the specified room has not been set
   * @throws IllegalStateException when too many treasures are added to a level
   */
  public ILevelBuilder addPotion(
          int roomNumber) throws IllegalStateException, IllegalArgumentException
  {
    if (roomNumber < 0) {
      throw new IllegalArgumentException("Invalid room number. Must be non-negative.");
    }
    if (this.roomCount - 1 < roomNumber) {
      throw new IllegalArgumentException(
              String.format("Room number %s has not yet been set.", roomNumber));
    }
    if (this.treasureCount + 1 > this.numberOfTreasures) {
      throw new IllegalStateException(
              String.format("Total number of treasures cannot exceed %s", this.numberOfTreasures));
    }

    this.treasureCount++;
    Treasure treasure = new Treasure("A healing potion", 1);
    this.level.addTreasure(roomNumber, treasure);

    return this;
  }

  /**
   * Adds gold to a room in the level.
   *
   * @param roomNumber the room to which the gold is added
   * @param value the value of the gold added
   * @return the level builder instance with updated level data
   * @throws IllegalStateException when the specified room has not been set or value is <= 0
   * @throws IllegalArgumentException when too many treasures are added to a level
   */
  public ILevelBuilder addGold(
          int roomNumber,
          int value) throws IllegalStateException, IllegalArgumentException {
    if (roomNumber < 0) {
      throw new IllegalArgumentException("Invalid room number. Must be non-negative.");
    }
    if (value <= 0) {
      throw new IllegalArgumentException("Gold must have a value greater than zero.");
    }
    if (this.roomCount - 1 < roomNumber) {
      throw new IllegalArgumentException(
              String.format("Room number %s has not yet been set.", roomNumber));
    }
    if (this.treasureCount + 1 > this.numberOfTreasures) {
      throw new IllegalStateException(
              String.format("Total number of treasures cannot exceed %s", this.numberOfTreasures));
    }

    this.treasureCount++;
    Treasure treasure = new Treasure("Gold", value);
    this.level.addTreasure(roomNumber, treasure);

    return this;
  }

  /**
   * Adds a weapon to a room in the level.
   *
   * @param roomNumber the room to which the weapon is added
   * @param description the level builder instance with updated level data
   * @return the level builder instance with updated level data
   * @throws IllegalStateException when the specified room has not been set
   * @throws IllegalArgumentException when too many treasures are added to a level
   */
  public ILevelBuilder addWeapon(
          int roomNumber,
          String description) throws IllegalStateException, IllegalArgumentException {
    if (roomNumber < 0) {
      throw new IllegalArgumentException("Invalid room number. Must be non-negative.");
    }
    if (this.roomCount - 1 < roomNumber) {
      throw new IllegalArgumentException(
              String.format("Room number %s has not yet been set.", roomNumber));
    }
    if (this.treasureCount + 1 > this.numberOfTreasures) {
      throw new IllegalStateException(
              String.format("Total number of treasures cannot exceed %s", this.numberOfTreasures));
    }

    this.treasureCount++;
    Treasure treasure = new Treasure(description, 10);
    this.level.addTreasure(roomNumber, treasure);

    return this;
  }

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
  public ILevelBuilder addSpecialItem(
          int roomNumber,
          String description,
          int value) throws IllegalStateException, IllegalArgumentException {
    if (roomNumber < 0) {
      throw new IllegalArgumentException("Invalid room number. Must be non-negative.");
    }
    if (value <= 0) {
      throw new IllegalArgumentException("Gold must have a value greater than zero.");
    }
    if (this.roomCount - 1 < roomNumber) {
      throw new IllegalArgumentException(
              String.format("Room number %s has not yet been set.", roomNumber));
    }
    if (this.treasureCount + 1 > this.numberOfTreasures) {
      throw new IllegalStateException(
              String.format("Total number of treasures cannot exceed %s", this.numberOfTreasures));
    }

    this.treasureCount++;
    Treasure treasure = new Treasure(description, value);
    this.level.addTreasure(roomNumber, treasure);

    return this;
  }

  /**
   * Builds and returns the Level instance.
   *
   * @return the level instance
   * @throws IllegalStateException when the level has not yet been fully built
   */
  public Level build() throws IllegalStateException{
    if (this.treasureCount < this.numberOfTreasures) {
      throw new IllegalStateException("Required number of treasures not met.");
    }
    if (this.monsterCount < this.numberOfMonsters) {
      throw new IllegalStateException("Required number of monsters not met.");
    }
    if (this.roomCount < this.numberOfRooms) {
      throw new IllegalStateException("Required number of rooms not met.");
    }

    return this.level;
  }
}
