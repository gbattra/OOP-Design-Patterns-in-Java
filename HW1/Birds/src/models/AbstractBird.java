package models;

import java.util.ArrayList;

import enums.BirdClassification;
import enums.BirdDiet;
import enums.BirdType;
import interfaces.IBird;

/**
 * Abstract class for the IBird interface. Handles some basic constructor validation and the
 * default describe() implementation. It also implements the accessor methods described in IBird.
 */
public abstract class AbstractBird implements IBird {
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
  protected final ArrayList<BirdDiet> diet;

  /**
   * The constructor for AbstractBird class. Enforces constraints on inputs, such as diet length,
   * wing count, and ensuring the provided type belongs to a permitted classification.
   *
   * @param name String the name of the bird
   * @param type BirdType the type of the bird
   * @param diet ArrayList<BirdDiet> the diet of the bird
   * @param wingCount int how many wings the bird has
   * @throws IllegalArgumentException when the provided inputs violate any constraints
   */
  public AbstractBird(
          String name,
          BirdType type,
          ArrayList<BirdDiet> diet,
          int wingCount) throws IllegalArgumentException {
    this.name = name;
    this.type = type;
    this.diet = diet;
    this.wingCount = wingCount;

    if (diet.size() < 2 || diet.size() > 4) {
      throw new IllegalArgumentException(
              "Diet list length must be at least 2 and no greater than 4.");
    }

    if (wingCount < 0 || wingCount > 2) {
      throw new IllegalArgumentException(
              "Wing count must be non-negative and less than or equal to 2.");
    }

    // enforces constraint that the provided BirdType belongs to a permissible classification
    if (!this.getPermissibleBirdClassifications().contains(this.type.classification)) {
      throw new IllegalArgumentException(
              String.format(
                "Provided bird type must belong to a permissible classification." +
                "Provided bird type classification: %s. Permissible bird type classifications: %s",
                this.type.classification.label,
                this.getPermissibleBirdClassifications().stream().map(
                  birdClassification -> birdClassification.label)));
    }
  }

  /**
   * Implementing classes will use this method to provide the list of permissible bird
   * classifications.
   *
   * @return ArrayList<BirdClassification> the list of permissible bird classifications
   */
  protected abstract ArrayList<BirdClassification> getPermissibleBirdClassifications();

  /**
   * Default descriptor method which generates a string based on instance attributes.
   *
   * @return String the instance description
   */
  public String describe() {
    return String.format(
            "This bird's name is %s. %s is a %s, which belongs to the classification %s. %s" +
            "%s likes to eat %s.",
            this.name,
            this.name,
            this.type.label,
            this.type.classification.label,
            this.type.classification.description,
            this.name,
            this.diet.stream().map((birdDiet -> birdDiet.label)));
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
   * Accessor for the bird instance extinct attribute
   *
   * @return boolean is bird extinct?
   */
  public boolean isExtinct() {
    return this.type.isExtinct;
  }

  /**
   * Accessor for the bird instance diet list.
   *
   * @return ArrayList<BirdDiet> the list of BirdDiet types consumed by this bird instance
   */
  public ArrayList<BirdDiet> getDiet() {
    return this.diet;
  }
}