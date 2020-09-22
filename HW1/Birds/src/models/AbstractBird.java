package models;

import java.util.ArrayList;

import enums.BirdClassification;
import enums.BirdDiet;
import enums.BirdType;
import interfaces.IBird;

public abstract class AbstractBird implements IBird {
  protected final String name;
  protected final int wingCount;
  protected final BirdType type;
  protected final ArrayList<BirdDiet> diet;

  public AbstractBird(
          String name,
          BirdType type,
          ArrayList<BirdDiet> diet,
          int wingCount) {
    this.name = name;
    this.type = type;
    this.diet = diet;
    this.wingCount = wingCount;

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

  protected abstract ArrayList<BirdClassification> getPermissibleBirdClassifications();

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

  public String getName() {
    return this.name;
  }

  public BirdType getType() {
    return this.type;
  }

  public BirdClassification getClassification() {
    return this.type.classification;
  }

  public int getWingCount() {
    return this.wingCount;
  }

  public boolean isExtinct() {
    return this.type.isExtinct;
  }

  public ArrayList<BirdDiet> getDiet() {
    return this.diet;
  }
}