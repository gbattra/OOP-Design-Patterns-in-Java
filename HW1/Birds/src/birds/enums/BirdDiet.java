package birds.enums;

/**
 * Enum representing each type of food a bird may consume.
 */
public enum BirdDiet {
  BERRIES("Berries"),
  SEEDS("Seeds"),
  FRUIT("Fruit"),
  INSECTS("Insects"),
  OTHER_BIRDS("Other birds"),
  EGGS("Eggs"),
  SMALL_MAMMALS("Small mammals"),
  FISH("Fish"),
  BUDS("Buds"),
  LARVAE("Larvae"),
  AQUATIC_INVERTEBRATES("Aquatic invertebrates"),
  NUTS("Nuts"),
  VEGETATION("Vegetation");

  /**
   * A string descriptor of the food type.
   */
  public final String label;

  /**
   * Private constructor for the BirdDiet enum.
   *
   * @param label the string descriptor of the food type
   */
  private BirdDiet(String label) {
    this.label = label;
  }
}
