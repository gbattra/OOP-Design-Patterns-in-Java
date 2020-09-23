package birds.models;

import java.util.ArrayList;
import java.util.Arrays;

import birds.enums.BirdClassification;
import birds.enums.BirdDiet;
import birds.enums.BirdType;

public class StandardBird extends Bird {
  /**
   * The list of permissible bird classifications. Used to validate BirdType passed into
   * constructor.
   */
  protected static final ArrayList<BirdClassification> PERMISSIBLE_CLASSIFICATIONS =
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
  public StandardBird(
          String name,
          BirdType type,
          ArrayList<BirdDiet> diet,
          int wingCount) throws IllegalArgumentException {
    super(name, type, diet, wingCount);

    // enforces constraint that the provided BirdType belongs to a permissible classification
    if (!StandardBird.PERMISSIBLE_CLASSIFICATIONS.contains(this.type.classification)) {
      throw new IllegalArgumentException(
              String.format(
                "Provided bird type must belong to a permissible classification." +
                "Provided bird type classification: %s. Permissible bird type classifications: %s",
                this.type.classification.label,
                StandardBird.PERMISSIBLE_CLASSIFICATIONS.stream().map(
                        birdClassification -> birdClassification.label)));
    }
  }
}
