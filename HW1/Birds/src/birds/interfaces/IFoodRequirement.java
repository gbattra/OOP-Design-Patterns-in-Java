package birds.interfaces;

/**
 * Interface for a FoodRequirement object. Food requirements keep track of what food is required
 * and in what quantity.
 *
 * @param <T> the type of food being tracked
 */
public interface IFoodRequirement<T> {
  /**
   * Accessor for the food property.
   *
   * @return BirdDiet the food being tracked
   */
  T getFood();

  /**
   * Accessor for the count property.
   *
   * @return int the quantity required
   */
  int getCount();
}
