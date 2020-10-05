package rpg.models;

import java.util.List;
import java.util.Optional;

import rpg.enums.GearType;
import rpg.interfaces.ICombinable;
import rpg.interfaces.IGear;

/**
 * Abstract class for objects implementing IGear. Contains common functionality such as
 * basic getters and aggregators.
 */
public abstract class AbstractGear implements IGear {
  private static final int COMBINED_COUNT = 2;

  protected final GearType type;
  protected final int attack;
  protected final int defense;
  protected final String adjective;
  protected final String noun;
  protected final List<IGear> combinedWith;
  protected final boolean isCombined;

  /**
   * Constructor for when gear is not combined.
   *
   * @param type GearType of this gear instance
   * @param attack int the base attack value for this gear
   * @param defense int the base defense value for this gear
   * @param adjective String the adjective for this gear
   * @param noun String the noun for this gear
   * @throws IllegalArgumentException when attack or def < 0, adj or noun empty
   */
  public AbstractGear(
          GearType type,
          int attack,
          int defense,
          String adjective,
          String noun) throws IllegalArgumentException {
    if (attack < 0 || defense < 0) {
      throw new IllegalArgumentException("Attack and defense values must be non-negative.");
    }

    if (adjective.isEmpty() || noun.isEmpty()) {
      throw new IllegalArgumentException("Adjective and noun must not be empty.");
    }
    this.type = type;
    this.attack = attack;
    this.defense = defense;
    this.adjective = adjective;
    this.noun = noun;
    this.isCombined = false;
    this.combinedWith = null;
  }

  /**
   * Constructor for when gear is combined.
   *
   * @param type GearType of this gear instance
   * @param attack int the base attack value for this gear
   * @param defense int the base defense value for this gear
   * @param adjective String the adjective for this gear
   * @param noun String the noun for this gear
   * @param combinedWith List of gears combined to form this gear
   */
  public AbstractGear(
          GearType type,
          int attack,
          int defense,
          String adjective,
          String noun,
          List<IGear> combinedWith) throws IllegalArgumentException {
    if (combinedWith.stream().anyMatch(gear -> gear.getType().gearClass != type.gearClass)) {
      throw new IllegalArgumentException(
              "Cannot created combined gear with gears of different types.");
    }

    if (attack < 0 || defense < 0) {
      throw new IllegalArgumentException("Attack and defense values must be non-negative.");
    }

    if (adjective.isEmpty() || noun.isEmpty()) {
      throw new IllegalArgumentException("Adjective and noun must not be empty.");
    }

    if (combinedWith.size() != COMBINED_COUNT) {
      throw new IllegalArgumentException("Invalid combinedWith list. Must have exactly 2 items.");
    }

    this.type = type;
    this.attack = attack;
    this.defense = defense;
    this.adjective = adjective;
    this.noun = noun;
    this.combinedWith = combinedWith;
    this.isCombined = true;
  }

  /**
   * Abstract method for combining one gear to another.
   *
   * @param gear T type of gear instance to merge with this
   * @return a new IGear instance
   */
  public abstract IGear combine(IGear gear);

  /**
   * Getter for the combinedGear property.
   *
   * @return the IGear instance that this is combined with
   */
  public Optional<List<IGear>> combinedWith() {
    return Optional.ofNullable(this.combinedWith);
  }

  /**
   * Boolean for if this gear is combined with another.
   *
   * @return is this a combined item
   */
  public boolean isCombined() {
    return this.isCombined;
  }

  /**
   * Returns the gear type.
   *
   * @return GearType enum value
   */
  public GearType getType() {
    return this.type;
  }

  /**
   * Returns the attack value for this gear.
   *
   * @return the int attack property value
   */
  public int getAttack() {
    return this.attack;
  }

  /**
   * Returns the defense value for this gear.
   *
   * @return the int defense property value
   */
  public int getDefense() {
    return this.defense;
  }

  /**
   * Returns the adjective describing this gear.
   *
   * @return the String adjective property value
   */
  public String getAdjective() {
    return this.adjective;
  }

  /**
   * Returns the noun describing this gear.
   *
   * @return the String noun property value
   */
  public String getNoun() {
    return this.noun;
  }

  /**
   * Returns the name of this gear.
   *
   * @return String combination of adjective and noun properties
   */
  public String getName() {
    return String.format("%s %s", this.adjective, this.noun);
  }

  /**
   * Overrides toString() method.
   *
   * @return the string representation of this instance
   */
  @Override
  public String toString() {
    String description = String.format(
            "Gear - Type: %s, Name: %s, Attack: %s, Defense: %s.",
            this.type.toString(),
            this.getName(),
            this.attack,
            this.defense);
    if (this.isCombined) {
      description += " Combined with: ";
      for (IGear gear : this.combinedWith) {
        description += String.format("%s", gear.toString());
      }
    }

    return description;
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

    if (other instanceof IGear) {
      IGear gear = (IGear) other;
      return gear.hashCode() == this.hashCode();
    }

    return false;
  }
}
