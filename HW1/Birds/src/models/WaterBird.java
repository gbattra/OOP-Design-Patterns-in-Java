package models;

import java.util.ArrayList;
import java.util.Arrays;

import enums.BirdClassification;
import enums.BirdDiet;
import enums.BirdType;
import interfaces.IWaterbird;

public class WaterBird extends Bird implements IWaterbird {
  protected static final ArrayList<BirdClassification> permissibleBirdClassifications =
          new ArrayList<>(Arrays.asList(
                  BirdClassification.WATERFOWL,
                  BirdClassification.SHOREBIRD));

  private final String nearestWaterBody;

  public WaterBird(
          String name,
          BirdType type,
          ArrayList<BirdDiet> diet,
          int wingCount,
          String nearestWaterBody) {
    super(name, type, diet, wingCount);
    this.nearestWaterBody = nearestWaterBody;
  }

  public String getNearestWaterBody() {
    return this.nearestWaterBody;
  }

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
