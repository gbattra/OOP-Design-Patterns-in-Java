package birds.models;

import java.util.List;
import java.util.stream.Collectors;

import birds.enums.BirdClassification;
import birds.enums.BirdDiet;
import birds.enums.BirdType;
import birds.interfaces.IBird;

/**
 * Default / Base class for AbstractBird.
 */
public class Bird implements IBird {
  /**
   * The minimum number of BirdDiet items to pass constructor.
   */
  private static final int DIET_COUNT_MIN = 2;

  /**
   * The maximum number of BirdDiet items to pass constructor.
   */
  private static final int DIET_COUNT_MAX = 4;

  /**
   * The minimum number of wings a bird can have.
   */
  private static final int WING_COUNT_MAX = 4;

  /**
   * The maximum number of wings a bird can have.
   */
  private static final int WING_COUNT_MIN = 2;

  /**
   * The bird's name.
   */
  protected final String name;

  /**
   * The bird's wing count. Greater than or equal to zero, less than or equal to two.
   */
  protected final int wingCount;

  /**
   * The bird's type.
   */
  protected final BirdType type;

  /**
   * The bird's diet. Length is greater than or equal to two, less than or equal to four.
   */
  protected final List<BirdDiet> diet;

  /**
   * The constructor for AbstractBird class. Enforces constraints on inputs, such as diet length,
   * wing count, and ensuring the provided type belongs to a permitted classification.
   *
   * @param name String the name of the bird
   * @param type BirdType the type of the bird
   * @param diet the diet of the bird
   * @param wingCount int how many wings the bird has
   * @throws IllegalArgumentException when the provided inputs violate any constraints
   */
  public Bird(
          String name,
          BirdType type,
          List<BirdDiet> diet,
          int wingCount) throws IllegalArgumentException {
    this.name = name;
    this.type = type;
    this.diet = diet;
    this.wingCount = wingCount;

    if (this.name.isEmpty()) {
      throw new IllegalArgumentException("Bird must be given a name.");
    }

    if (diet.size() < Bird.DIET_COUNT_MIN || diet.size() > Bird.DIET_COUNT_MAX) {
      throw new IllegalArgumentException(
              "Diet list length must be at least 2 and no greater than 4.");
    }

    if (wingCount < Bird.WING_COUNT_MIN || wingCount > Bird.WING_COUNT_MAX) {
      throw new IllegalArgumentException(
              "Wing count must be non-negative and less than or equal to 2.");
    }
  }

  /**
   * Default descriptor method which generates a string based on instance attributes.
   *
   * @return String the instance description
   */
  public String describe() {
    return String.format(
            "%s is a %s, which belongs to the classification %s. %s "
            + "%s likes to eat %s.",
            this.name,
            this.type.label,
            this.type.classification.label,
            this.type.classification.description,
            this.name,
            this.diet.stream().map((birdDiet -> birdDiet.label)).collect(Collectors.joining(", ")));
  }

  /**
   * Accessor method for the instance name attribute.
   *
   * @return String the instance name
   */
  public String getName() {
    return this.name;
  }

  /**
   * Accessor for the bird instance type.
   *
   * @return BirdType the type of the bird
   */
  public BirdType getType() {
    return this.type;
  }

  /**
   * Accessor for the bird instance classification. Determined by type.
   *
   * @return BirdClassification the bird instance classification
   */
  public BirdClassification getClassification() {
    return this.type.classification;
  }

  /**
   * Accessor for the bird instance wing count.
   *
   * @return int the bird instance wing count
   */
  public int getWingCount() {
    return this.wingCount;
  }

  /**
   * Accessor for the bird instance extinct attribute.
   *
   * @return boolean is bird extinct?
   */
  public boolean isExtinct() {
    return this.type.isExtinct;
  }

  /**
   * Accessor for the bird instance diet list.
   *
   * @return the list of BirdDiet types consumed by this bird instance
   */
  public List<BirdDiet> getDiet() {
    return this.diet;
  }

  /**
   * Computes a string representation of this bird instance.
   *
   * @return the string representation
   */
  @Override
  public String toString() {
    return String.format(
            "%s_%s_%s_%s",
            this.name,
            this.type.label,
            this.wingCount,
            this.diet.stream().map(diet -> diet.label).collect(Collectors.joining("_")));
  }

  /**
   * Compares one object to this instance.
   *
   * @param other the object to compare
   * @return true/false if they are equal
   */
  @Override
  public boolean equals(Object other) {
    if (!(other instanceof Bird)) {
      return false;
    }

    Bird bird = (Bird) other;
    return bird.hashCode() == other.hashCode();
  }

  /**
   * Computes a int encoding of this bird instance.
   *
   * @return the int encoding
   */
  @Override
  public int hashCode() {
    return this.toString().hashCode();
  }
}
