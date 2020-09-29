package rpg.models;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import rpg.interfaces.IFootGear;
import rpg.interfaces.IGear;
import rpg.interfaces.IHandGear;
import rpg.interfaces.IHeadGear;
import rpg.interfaces.IPlayer;

/**
 * Class representing a player in the RPG
 */
public class Player implements IPlayer {
  private static final int HEAD_GEAR_COUNT = 1;
  private static final int HAND_GEAR_COUNT = 2;
  private static final int FOOT_GEAR_COUNT = 2;

  private final int number;
  private final int attack;
  private final int defense;
  private final List<IHeadGear> headGears;
  private final List<IHandGear> handGears;
  private final List<IFootGear> footGears;

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
    this.headGears = new ArrayList<>();
    this.handGears = new ArrayList<>();
    this.footGears = new ArrayList<>();
  }

  /**
   * Player constructor with gear.
   *
   * @param number the player number / id
   * @param attack the player's initial attack strength
   * @param defense the palyer's initial defense strenght
   * @param headGears the headgears worn by the player
   * @param handGears the handgears worn by the player
   * @param footGears the footgears worn by the player
   * @throws IllegalArgumentException when number, attack or defense < 0, or invalid gear types
   */
  public Player(
          int number,
          int attack,
          int defense,
          List<IHeadGear> headGears,
          List<IHandGear> handGears,
          List<IFootGear> footGears) throws IllegalArgumentException {
    if (number < 0 || attack < 0 || defense < 0) {
      throw new IllegalArgumentException("Number, attack and defense must be non-negative");
    }

    if (headGears.size() > HEAD_GEAR_COUNT) {
      throw new IllegalStateException(
              String.format("Too many head gear items provided. Max: %s", HEAD_GEAR_COUNT));
    }

    if (handGears.size() > HAND_GEAR_COUNT) {
      throw new IllegalStateException(
              String.format("Too many hand gear items provided. Max: %s", HAND_GEAR_COUNT));
    }

    if (footGears.size() > FOOT_GEAR_COUNT) {
      throw new IllegalStateException(
              String.format("Too many foot gear items provided. Max: %s", FOOT_GEAR_COUNT));
    }

    this.number = number;
    this.attack = attack;
    this.defense = defense;
    this.headGears = headGears;
    this.handGears = handGears;
    this.footGears = footGears;
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
    List<IGear> gears = new ArrayList<>();
    Stream.of(this.headGears, this.handGears, this.footGears).forEach(gears::addAll);
    for (IGear gear : gears) {
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
    List<IGear> gears = new ArrayList<>();
    Stream.of(this.headGears, this.handGears, this.footGears).forEach(gears::addAll);
    for (IGear gear : gears) {
      aggregateDefense += gear.getDefense();
    }
    return aggregateDefense;
  }

  /**
   * Adds a headgear item to this player.
   *
   * @param gear the gear to add
   * @return a new player instance with updated attire
   * @throws IllegalStateException when geartype is invalid or player has no more room
   */
  public IPlayer addHeadGear(IHeadGear gear) throws IllegalStateException {
    List<IHeadGear> headGearsCopy = new ArrayList<>(this.headGears);
    if (headGearsCopy.size() < HEAD_GEAR_COUNT) {
      headGearsCopy.add(gear);
      IPlayer player = new Player(
              this.number,
              this.attack,
              this.defense,
              headGearsCopy,
              this.handGears,
              this.footGears);
      return player;
    }

    boolean combined = false;
    List<IHeadGear> newHeadGears = new ArrayList<>(this.headGears);
    for (int i = 0; i < this.headGears.size(); i++) {
      try {
        IHeadGear newGear = this.headGears.get(i).combine(gear);
        newHeadGears.set(i, newGear);
        combined = true;
        break;
      } catch (IllegalStateException e) {
        newHeadGears.set(i, this.headGears.get(i));
      }
    }

    if (!combined) {
      throw new IllegalStateException(
              "Failed to add head gear. No remaining un-combined head gears.");
    }

    IPlayer player = new Player(
            this.number,
            this.attack,
            this.defense,
            newHeadGears,
            this.handGears,
            this.footGears);

    return player;
  }

  /**
   * Adds a handgear item to this player.
   *
   * @param gear the gear to add
   * @return a new player instance with updated attire
   * @throws IllegalStateException when geartype is invalid or player has no more room
   */
  public IPlayer addHandGear(IHandGear gear) throws IllegalStateException {
    List<IHandGear> handGearsCopy = new ArrayList<>(this.handGears);
    if (handGearsCopy.size() < HAND_GEAR_COUNT) {
      handGearsCopy.add(gear);
      IPlayer player = new Player(
              this.number,
              this.attack,
              this.defense,
              this.headGears,
              handGearsCopy,
              this.footGears);
      return player;
    }

    boolean combined = false;
    List<IHandGear> newHandGears = new ArrayList<>(this.handGears);
    for (int i = 0; i < this.handGears.size(); i++) {
      try {
        IHandGear newGear = this.handGears.get(i).combine(gear);
        newHandGears.set(i, newGear);
        combined = true;
        break;
      } catch (IllegalStateException e) {
        newHandGears.set(i, this.handGears.get(i));
      }
    }

    if (!combined) {
      throw new IllegalStateException(
              "Failed to add hand gear. No remaining un-combined hand gears.");
    }

    IPlayer player = new Player(
            this.number,
            this.attack,
            this.defense,
            this.headGears,
            newHandGears,
            this.footGears);

    return player;
  }

  /**
   * Adds a footgear item to this player.
   *
   * @param gear the gear to add
   * @return a new player instance with updated attire
   * @throws IllegalStateException when geartype is invalid or player has no more room
   */
  public IPlayer addFootGear(IFootGear gear) throws IllegalStateException {
    List<IFootGear> footGearsCopy = new ArrayList<>(this.footGears);
    if (footGearsCopy.size() < FOOT_GEAR_COUNT) {
      footGearsCopy.add(gear);
      IPlayer player = new Player(
              this.number,
              this.attack,
              this.defense,
              this.headGears,
              this.handGears,
              footGearsCopy);
      return player;
    }

    boolean combined = false;
    List<IFootGear> newFootGears = new ArrayList<>(this.footGears);
    for (int i = 0; i < this.footGears.size(); i++) {
      try {
        IFootGear newGear = this.footGears.get(i).combine(gear);
        newFootGears.set(i, newGear);
        combined = true;
        break;
      } catch (IllegalStateException e) {
        newFootGears.set(i, this.footGears.get(i));
      }
    }

    if (!combined) {
      throw new IllegalStateException(
              "Failed to add foot gear. No remaining un-combined foot gears.");
    }

    IPlayer player = new Player(
            this.number,
            this.attack,
            this.defense,
            this.headGears,
            this.handGears,
            newFootGears);

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
            "Player %s: Attack: %s, Defense: %s, HeadGear: %s, Handgear: %s, Footgear: %s",
            this.number,
            this.attack,
            this.defense,
            this.headGears.stream().map(IHeadGear::toString).collect(Collectors.joining("; ")),
            this.handGears.stream().map(IHandGear::toString).collect(Collectors.joining("; ")),
            this.footGears.stream().map(IFootGear::toString).collect(Collectors.joining("; ")));
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
      return player.hashCode() == other.hashCode();
    }

    return false;
  }
}
