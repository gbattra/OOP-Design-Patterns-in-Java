package rpg.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rpg.interfaces.IHeadGear;

/**
 * Type of IGear specifically for head gear. Has defense value but no attack value.
 */
public class HeadGear extends AbstractGear<IHeadGear> implements IHeadGear {
  /**
   * Constructor for when this gear is not combined.
   *
   * @param defense int the base defense value for this gear
   * @param adjective String the adjective for this gear
   * @param noun String the noun for this gear
   * @throws IllegalArgumentException when attack or defense < 0, adjective or noun empty
   */
  public HeadGear(
          int defense,
          String adjective,
          String noun) throws IllegalArgumentException {
    super(0, defense, adjective, noun);
  }

  /**
   * Constructor for when this gear is combined.
   *
   * @param defense int the base defense value for this gear
   * @param adjective String the adjective for this gear
   * @param noun String the noun for this gear
   * @param combinedGears List of gears combined to form this gear
   * @throws IllegalArgumentException when attack or defense < 0, adjective or noun empty,
   * combinedGears != 2
   */
  public HeadGear(
          int defense,
          String adjective,
          String noun,
          List<IHeadGear> combinedGears) throws IllegalArgumentException {
    super(0, defense, adjective, noun, combinedGears);
  }

  /**
   * Combines this gear with another and returns a new instance with the merged values.
   *
   * @param gear IHeadGear instance to merge with this
   * @return the new combined gear instance
   * @throws IllegalStateException when either this or the gear provided in is already combined
   */
  public IHeadGear combine(IHeadGear gear) throws IllegalStateException {
    if (this.isCombined) {
      throw new IllegalStateException(
              "Cannot combine self to gear. Self is already combined with another gear.");
    }

    if (gear.isCombined()) {
      throw new IllegalStateException(
              "Cannot combine self to gear. Gear is already combined with another gear.");
    }

    IHeadGear newGear = new HeadGear(
            this.defense + gear.getDefense(),
            String.format("%s, %s", this.adjective, gear.getAdjective()),
            this.noun,
            new ArrayList<>(Arrays.asList(this, gear)));

    return newGear;
  }
}
