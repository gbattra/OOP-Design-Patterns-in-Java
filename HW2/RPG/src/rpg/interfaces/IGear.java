package rpg.interfaces;

import rpg.enums.GearType;

/**
 * Interface for gear objects.
 */
public interface IGear {
  /**
   * Returns the gear type.
   *
   * @return GearType enum value
   */
  GearType getType();

  /**
   * Returns the attack value for this gear.
   *
   * @return the int attack property value
   */
  int getAttack();

  /**
   * Returns the defense value for this gear.
   *
   * @return the int defense property value
   */
  int getDefense();

  /**
   * Returns the adjective describing this gear.
   *
   * @return the String adjective property value
   */
  String getAdjective();

  /**
   * Returns the noun describing this gear.
   *
   * @return the String noun property value
   */
  String getNoun();

  /**
   * Returns the name of this gear.
   *
   * @return String combination of adjective and noun properties
   */
  String getName();
}
