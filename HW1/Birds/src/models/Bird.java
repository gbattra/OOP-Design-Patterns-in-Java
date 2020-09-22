package models;

import java.util.ArrayList;
import java.util.Arrays;

import enums.BirdClassification;
import enums.BirdDiet;
import enums.BirdType;

public class Bird extends AbstractBird {
  protected static final ArrayList<BirdClassification> permissibleBirdClassifications =
          new ArrayList<>(
                  Arrays.asList(
                    BirdClassification.BIRD_OF_PREY,
                    BirdClassification.FLIGHTLESS_BIRD,
                    BirdClassification.OWL,
                    BirdClassification.PIGEON));

  public Bird(
          String name,
          BirdType type,
          ArrayList<BirdDiet> diet,
          int wingCount) {
    super(name, type, diet, wingCount);
  }
}
