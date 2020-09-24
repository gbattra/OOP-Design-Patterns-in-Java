package birds.models;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import birds.enums.BirdClassification;
import birds.enums.BirdDiet;
import birds.enums.BirdType;
import birds.interfaces.IAviary;
import birds.interfaces.IBird;
import birds.interfaces.IFoodRequirement;

public class Aviary implements IAviary {
  private final List<IBird> birds;
  private final int sector;

  public Aviary(
          List<IBird> birds,
          int sector) throws IllegalArgumentException {
    this.birds = birds;
    this.sector = sector;

    if (this.birds.size() > 5) {
      throw new IllegalStateException("Aviary cannot house more than 5 birds at a time.");
    }

    if (this.sector <= 0) {
      throw new IllegalStateException("Aviary must have valid sector number.");
    }

    if (this.birds.stream().anyMatch(bird -> bird.isExtinct())) {
      throw new IllegalArgumentException("Extinct birds cannot be added to an aviary.");
    }

    List<BirdClassification> currentBirdClasses = this.birds.stream()
                                                .map(bird -> bird.getType().classification)
                                                .distinct()
                                                .collect(Collectors.toList());

    if ((currentBirdClasses.contains(BirdClassification.BIRD_OF_PREY)
        || currentBirdClasses.contains(BirdClassification.FLIGHTLESS_BIRD)
        || currentBirdClasses.contains(BirdClassification.WATERFOWL))
        && currentBirdClasses.size() > 1) {
      throw new IllegalArgumentException(
              String.format(
                      "%s, %s, and %s cannot be mixed with other bird types.",
                      BirdClassification.BIRD_OF_PREY.label,
                      BirdClassification.FLIGHTLESS_BIRD.label,
                      BirdClassification.WATERFOWL.label));
    }
  }

  public List<IBird> getBirds() {
    return this.birds;
  }

  public int getSector() {
    return this.sector;
  }

  public List<BirdType> getBirdTypes() {
    return this.birds.stream().map(IBird::getType).collect(Collectors.toList());
  }

  public IAviary addBird(IBird bird) throws IllegalArgumentException {
    List<IBird> birds = this.birds;
    birds.add(bird);
    return new Aviary(
            birds,
            this.sector);
  }

  public List<IFoodRequirement<BirdDiet>> getFoodRequirements()
  {
    return new ArrayList<>();
  }

  public String describe() {
    String description = String.format(
            "The aviary in sector %s houses %s birds of types %s. " +
            "Below are descriptions of each bird living in this aviary:\n",
            this.sector,
            this.birds.size(),
            this.birds.stream()
                      .map(bird -> bird.getType().label)
                      .collect(Collectors.joining(", ")));

    for (int i = 0; i < this.birds.size(); i++) {
      description += String.format(
              "- %s\n",
              this.birds.get(i).describe());
    }

    return description;
  }
}
