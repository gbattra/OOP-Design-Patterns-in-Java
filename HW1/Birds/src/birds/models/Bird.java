package birds.models;

import java.util.ArrayList;
import java.util.Arrays;

import birds.enums.BirdClassification;
import birds.enums.BirdDiet;
import birds.enums.BirdType;

/**
 * Default / Base class for AbstractBird.
 */
public class Bird extends AbstractBird {
  /**
   * Constructor for the Bird class. Passes all args up to AbstractBird class for validation.
   *
   * @param name String the name of the bird
   * @param type BirdType the type of the bird
   * @param diet ArrayList<BirdDiet> the diet of the bird
   * @param wingCount int how many wings the bird has
   * @throws IllegalArgumentException when the provided inputs violate any constraints
   */
  public Bird(
          String name,
          BirdType type,
          ArrayList<BirdDiet> diet,
          int wingCount) throws IllegalArgumentException {
    super(name, type, diet, wingCount);
  }
}
