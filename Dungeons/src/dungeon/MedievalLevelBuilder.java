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
          int numberOfTreasures) {
    this.level = new Level(levelNumber);
    this.numberOfRooms = numberOfRooms;
    this.numberOfMonsters = numberOfMonsters;
    this.numberOfTreasures = numberOfTreasures;
  }

  public ILevelBuilder addRoom(String description) throws IllegalStateException {
    if (this.roomCount == numberOfRooms) {
      throw new IllegalStateException(
              String.format("Level cannot have more than %s rooms.", this.numberOfRooms));
    }
    this.roomCount++;
    this.level.addRoom(description);
    return this;
  }

  public ILevelBuilder addGoblin(int roomNumber, int count) {
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

  public ILevelBuilder addOrc(int roomNumber, int count) {
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

  public ILevelBuilder addOgre(int roomNumber, int count) {
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

  public ILevelBuilder addHuman(
          int roomNumber,
          String name,
          String description,
          int hitPoints) {
    if (roomNumber < 0) {
      throw new IllegalArgumentException("Invalid room number. Must be non-negative.");
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

  public ILevelBuilder addPotion(int roomNumber)
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

  public ILevelBuilder addGold(int roomNumber, int value) {
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
    Treasure treasure = new Treasure("Gold", value);
    this.level.addTreasure(roomNumber, treasure);

    return this;
  }

  public ILevelBuilder addWeapon(int roomNumber, String description) {
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

  public ILevelBuilder addSpecial(
          int roomNumber,
          String description,
          int value) {
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
    Treasure treasure = new Treasure(description, value);
    this.level.addTreasure(roomNumber, treasure);

    return this;
  }
}
