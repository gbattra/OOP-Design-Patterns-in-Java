package models;

import java.util.List;
import java.util.Optional;

import interfaces.IGear;

/**
 * Abstract class for objects implementing IGear. Contains common functionality such as
 * basic getters and aggregators.
 * @param <T> Interface for the gear type represented by whatever extends this abstract class
 */
public abstract class AbstractGear<T> implements IGear<T> {
  protected final int attack;
  protected final int defense;
  protected final String adjective;
  protected final String noun;
  protected final List<IGear<T>> combinedWith;
  protected final boolean isCombined;

  /**
   * Constructor for when gear is not combined.
   *
   * @param attack int the base attack value for this gear
   * @param defense int the base defense value for this gear
   * @param adjective String the adjective for this gear
   * @param noun String the noun for this gear
   */
  public AbstractGear(
          int attack,
          int defense,
          String adjective,
          String noun) {
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
   * @param attack int the base attack value for this gear
   * @param defense int the base defense value for this gear
   * @param adjective String the adjective for this gear
   * @param noun String the noun for this gear
   * @param combinedGears List of gears combined to form this gear
   */
  public AbstractGear(
          int attack,
          int defense,
          String adjective,
          String noun,
          List<IGear<T>> combinedGears) {
    this.attack = attack;
    this.defense = defense;
    this.adjective = adjective;
    this.noun = noun;
    this.combinedWith = combinedGears;
    this.isCombined = true;
  }

  /**
   * Abstract method for combining one gear to another.
   *
   * @param gear T type of gear instance to merge with this
   * @return a new IGear instance
   */
  public abstract T combine(T gear);

  /**
   * Getter for the combinedGear property.
   *
   * @return the IGear instance that this is combined with
   */
  public Optional<List<IGear<T>>> combinedWith() {
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
}
