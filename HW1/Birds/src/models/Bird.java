package models;

import java.util.ArrayList;
import java.util.Arrays;

import enums.BirdClassification;
import enums.BirdDiet;
import enums.BirdType;

/**
 * Default / Base class for AbstractBird.
 */
public class Bird extends AbstractBird {
  /**
   * The list of permissible bird classifications. Used to validate BirdType passed into
   * constructor.
   */
  protected final ArrayList<BirdClassification> permissibleBirdClassifications =
          new ArrayList<>(Arrays.asList(
                  BirdClassification.BIRD_OF_PREY,
                  BirdClassification.FLIGHTLESS_BIRD,
                  BirdClassification.OWL,
                  BirdClassification.PIGEON));

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

  /**
   * Passes the list of permissible bird classifications to the abstract class for validation
   * during instantiation.
   *
   * @return ArrayList<BirdClassification> the list of permissible bird classifications
   */
  @Override
  protected ArrayList<BirdClassification> getPermissibleBirdClassifications() {
    return this.permissibleBirdClassifications;
  }
}
