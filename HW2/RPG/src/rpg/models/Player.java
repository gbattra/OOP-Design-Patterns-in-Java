package rpg.models;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import rpg.enums.GearClass;
import rpg.interfaces.IGear;
import rpg.interfaces.IPlayer;

/**
 * Class representing a player in the RPG. Players have a base defense and attack, and can wear
 * gear that augments their attack and defense values.
 */
public class Player implements IPlayer {
  private final int number;
  private final int attack;
  private final int defense;
  private final List<IGear> gears;

  /**
   * Basic player constructor.
   *
   * @param number the player number / id
   * @param attack the player's initial attack strength
   * @param defense the palyer's initial defense strenght
   * @throws IllegalArgumentException when number, attack or defense < 0
   */
  public Player(
          int number,
          int attack,
          int defense) throws IllegalArgumentException {
    if (number < 0 || attack < 0 || defense < 0) {
      throw new IllegalArgumentException("Number, attack and defense must be non-negative");
    }

    this.number = number;
    this.attack = attack;
    this.defense = defense;
    this.gears = new ArrayList<>();
  }

  /**
   * Player constructor with gear.
   *
   * @param number the player number / id
   * @param attack the player's initial attack strength
   * @param defense the palyer's initial defense strenght
   * @param gears the gear worn by the player
   * @throws IllegalArgumentException when number, attack or defense < 0, or invalid gear types
   */
  public Player(
          int number,
          int attack,
          int defense,
          List<IGear> gears) throws IllegalArgumentException {
    if (number < 0 || attack < 0 || defense < 0) {
      throw new IllegalArgumentException("Number, attack and defense must be non-negative");
    }

    if (gears.stream().filter(gear -> gear.getType().gearClass == GearClass.HEADGEAR).count()
        > GearClass.HEADGEAR.count) {
      throw new IllegalStateException(
              String.format(
                      "Too many head gear items provided. Max: %s",
                      GearClass.HEADGEAR.count));
    }

    if (gears.stream().filter(gear -> gear.getType().gearClass == GearClass.HANDGEAR).count()
        > GearClass.HANDGEAR.count) {
      throw new IllegalStateException(
              String.format(
                      "Too many hand gear items provided. Max: %s",
                      GearClass.HANDGEAR.count));
    }

    if (gears.stream().filter(gear -> gear.getType().gearClass == GearClass.FOOTGEAR).count()
        > GearClass.FOOTGEAR.count) {
      throw new IllegalStateException(
              String.format(
                      "Too many foot gear items provided. Max: %s",
                      GearClass.FOOTGEAR.count));
    }

    this.number = number;
    this.attack = attack;
    this.defense = defense;
    this.gears = gears;
  }

  /**
   * Getter for this player's number.
   *
   * @return int the player number
   */
  public int getNumber() {
    return this.number;
  }

  /**
   * Aggregates the attack values of player attack plus all its gears' attack and returns the
   * total.
   *
   * @return the aggregated attack value
   */
  public int getAttack() {
    int aggregateAttack = this.attack;
    for (IGear gear : this.gears) {
      aggregateAttack += gear.getAttack();
    }
    return aggregateAttack;
  }

  /**
   * Aggregates the defense values of player defense plus all its gears' defense and returns the
   * total.
   *
   * @return the aggregated defense value
   */
  public int getDefense() {
    int aggregateDefense = this.defense;
    for (IGear gear : this.gears) {
      aggregateDefense += gear.getDefense();
    }
    return aggregateDefense;
  }

  /**
   * Returns the list of gear worn by the player instace.
   *
   * @return the list of gear worn by the player
   */
  public List<IGear> getGear() {
    return this.gears;
  }

  /**
   * Factory method which takes any IGear instance and adds it to the appropriate list. Returns
   * updated player instance.
   *
   * @param gear the IGear instance to add
   * @return the updated player with gear added
   * @throws IllegalStateException when player state does not permit adding the gear
   */
  public IPlayer addGear(IGear gear) throws IllegalStateException, IllegalArgumentException {
    List<IGear> gearsFromClass = this.gears
            .stream()
            .filter(g -> g.getType().gearClass == gear.getType().gearClass)
            .collect(Collectors.toList());
    List<IGear> gearsNotFromClass = this.gears
            .stream()
            .filter(g -> g.getType().gearClass != gear.getType().gearClass)
            .collect(Collectors.toList());

    if (gearsFromClass.size() < gear.getType().gearClass.count) {
      gearsFromClass.add(gear);
    } else {
      boolean combined = false;

      for (int i = 0; i < gearsFromClass.size(); i++) {
        try {
          IGear newGear = gearsFromClass.get(i).combine(gear);
          gearsFromClass.set(i, newGear);
          combined = true;
          break;
        } catch (Exception e) {
          gearsFromClass.set(i, gearsFromClass.get(i));
        }
      }

      if (!combined) {
        throw new IllegalStateException(
                String.format(
                        "Failed to add head gear. No remaining un-combined gear of class: %s",
                        gear.getType().gearClass.toString()));
      }
    }

    List<IGear> newGearList = new ArrayList<>();
    newGearList.addAll(gearsFromClass);
    newGearList.addAll(gearsNotFromClass);

    IPlayer player = new Player(
            this.number,
            this.attack,
            this.defense,
            newGearList);

    return player;
  }

  /**
   * Overrides toString() method.
   *
   * @return the string representation of this instance
   */
  @Override
  public String toString() {
    return String.format(
            "Player %s:\n"
            + "- Total Attack: %s,\n- Total Defense: %s,\n- Base Attack: %s,\n- Base Defense: %s,"
            + "\n- HeadGear: %s,\n- Handgear: %s,\n- Footgear: %s",
            this.number,
            this.getAttack(),
            this.getDefense(),
            this.attack,
            this.defense,
            this.gears.stream().filter(gear -> gear.getType().gearClass == GearClass.HEADGEAR)
                      .map(IGear::toString).collect(Collectors.joining("; ")),
            this.gears.stream().filter(gear -> gear.getType().gearClass == GearClass.HANDGEAR)
                      .map(IGear::toString).collect(Collectors.joining("; ")),
            this.gears.stream().filter(gear -> gear.getType().gearClass == GearClass.FOOTGEAR)
                      .map(IGear::toString).collect(Collectors.joining("; ")));
  }

  /**
   * Overrides the hashCode() method.
   *
   * @return int the hashcode
   */
  @Override
  public int hashCode() {
    return this.toString().hashCode();
  }

  /**
   * Override equals() method.
   *
   * @param other the object to compare
   * @return boolean is this object equal to the other
   */
  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }

    if (other instanceof IPlayer) {
      IPlayer player = (IPlayer) other;
      return player.hashCode() == this.hashCode();
    }

    return false;
  }
}
