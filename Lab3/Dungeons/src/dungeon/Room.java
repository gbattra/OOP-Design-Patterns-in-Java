package dungeon;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a room in a dungeon.
 */
public class Room {

  private final String description;
  private final List<Monster> monsters;
  private final List<Treasure> treasures;

  /**
   * Constructor for a Room. It starts out initially empty.
   * 
   * @param description the description
   */
  public Room(String description) {
    this.description = description;
    monsters = new ArrayList<>();
    treasures = new ArrayList<>();
  }

  /**
   * Add a monster to this room.
   * 
   * @param m the monster to add
   */
  public void addMonster(Monster m) {
    monsters.add(m);
  }

  /**
   * Remove a monster from this room.
   * 
   * @param m the monster to remove
   */
  public void removeMonster(Monster m) {
    if (!monsters.contains(m)) {
      throw new IllegalStateException("Trying to remove a monster from a room they are not in.");
    }
    monsters.remove(m);
  }

  /**
   * Get the monsters in this room so the character can fight.
   * 
   * @return the monsters
   */
  public Monster[] getMonsters() {
    return monsters.toArray(new Monster[0]);
  }

  /**
   * Add a treasure to this room.
   * 
   * @param m the treasure to add
   */
  public void addTreasure(Treasure m) {
    treasures.add(m);
  }

  /**
   * Remove a treasure from this room.
   * 
   * @param m the treasure to remove
   */
  public void removeTreasure(Treasure m) {
    if (!treasures.contains(m)) {
      throw new IllegalStateException("Trying to remove a monster from a room they are not in.");
    }
    treasures.remove(m);
  }

  /**
   * Get the treasure in this room.
   * 
   * @return the treasures
   */
  public Treasure[] getTreasures() {
    return treasures.toArray(new Treasure[0]);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder(description + "\n");
    sb.append("Monsters:\n");
    if (monsters.size() == 0) {
      sb.append("\tNone\n");
    }
    else {
      for (Monster m : monsters) {
        sb.append("\t" + m + "\n");
      }
    }
    sb.append("Treasures:\n");
    if (treasures.size() == 0) {
      sb.append("\tNone\n");
    }
    else {
      for (Treasure t : treasures) {
        sb.append("\t" + t + "\n");
      }
    }
    return sb.toString();
  }
}
