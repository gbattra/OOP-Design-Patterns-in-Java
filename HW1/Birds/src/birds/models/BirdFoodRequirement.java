package birds.models;

import birds.enums.BirdDiet;
import birds.interfaces.IFoodRequirement;

/**
 * Class representing a food requirement for an aviary or conservatory. Food requirements keep
 * track of what food is required and in what quantity.
 */
public class BirdFoodRequirement implements IFoodRequirement<BirdDiet> {
  /**
   * What type of food does this requirement track
   */
  private final BirdDiet food;

  /**
   * The quantity of food required.
   */
  private final int count;

  /**
   * Constructor for the BirdFoodRequirement class.
   *
   * @param food BirdDiet the food to track
   * @param count int the quantity required
   * @throws IllegalArgumentException when quantity is < 0
   */
  public BirdFoodRequirement(
          BirdDiet food,
          int count) {
    this.food = food;
    this.count = count;

    if (this.count < 0) {
      throw new IllegalArgumentException("Count cannot be negative.");
    }
  }

  /**
   * Accessor for the count property.
   *
   * @return int the quantity required
   */
  public int getCount() {
    return this.count;
  }

  /**
   * Accessor for the food property.
   *
   * @return BirdDiet the food being tracked
   */
  public BirdDiet getFood() {
    return this.food;
  }
}
