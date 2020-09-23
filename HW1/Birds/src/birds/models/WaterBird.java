package birds.models;

import java.util.ArrayList;
import java.util.Arrays;

import birds.enums.BirdClassification;
import birds.enums.BirdDiet;
import birds.enums.BirdType;
import birds.interfaces.IWaterbird;

/**
 * Class representing type of bird that lives near water.
 */
public class WaterBird extends AbstractBird implements IWaterbird {
  /**
   * The list of permissible bird classifications. Used to validate BirdType passed into
   * constructor.
   */
  protected static final ArrayList<BirdClassification> PERMISSIBLE_CLASSIFICATIONS =
          new ArrayList<>(Arrays.asList(
                  BirdClassification.WATERFOWL,
                  BirdClassification.SHOREBIRD));

  /**
   * Water body closest to the bird's dwelling.
   */
  private final String nearestWaterBody;

  /**
   * Constructor for the WaterBird class. Passes some args up to AbstractBird class for validation.
   *
   * @param name String the name of the bird
   * @param type BirdType the type of the bird
   * @param diet ArrayList<BirdDiet> the diet of the bird
   * @param wingCount int how many wings the bird has
   * @param nearestWaterBody String the name of the water body nearest this bird's habitat
   * @throws IllegalArgumentException when the provided inputs violate any constraints
   */
  public WaterBird(
          String name,
          BirdType type,
          ArrayList<BirdDiet> diet,
          int wingCount,
          String nearestWaterBody) throws IllegalArgumentException {
    super(name, type, diet, wingCount);
    this.nearestWaterBody = nearestWaterBody;

    if (this.nearestWaterBody.isEmpty()) {
      throw new IllegalArgumentException("nearestWaterBody input cannot be empty.");
    }

    // enforces constraint that the provided BirdType belongs to a permissible classification
    if (!WaterBird.PERMISSIBLE_CLASSIFICATIONS.contains(this.type.classification)) {
      throw new IllegalArgumentException(
              String.format(
                "Provided bird type must belong to a permissible classification." +
                "Provided bird type classification: %s. Permissible bird type classifications: %s",
                this.type.classification.label,
                WaterBird.PERMISSIBLE_CLASSIFICATIONS.stream().map(
                        birdClassification -> birdClassification.label)));
    }
  }

  /**
   * Accessor for the bird instance nearest water body.
   *
   * @return String name of the nearest water body
   */
  public String getNearestWaterBody() {
    return this.nearestWaterBody;
  }

  /**
   * Overrides the default descriptor method to incorporate unique attributes (nearestWaterBody).
   *
   * @return String the instance description
   */
  @Override
  public String describe() {
    String description = super.describe();
    description += String.format(
            "%s lives near the water body %s",
            this.name,
            this.nearestWaterBody);
    return description;
  }
}
