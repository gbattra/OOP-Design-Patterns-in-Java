package birds.models;

import java.util.Hashtable;
import java.util.List;
import java.util.stream.Collectors;

import birds.enums.BirdClassification;
import birds.enums.BirdDiet;
import birds.enums.BirdType;
import birds.interfaces.IAviary;
import birds.interfaces.IBird;

/**
 * Aviaries are medium-sized structures that can house up to 5 birds. Some birds cannot be mixed
 * with others depending on their type. An aviary resides in a sector which is identified by an
 * int. Extinct birds cannot be added to an aviary.
 */
public class Aviary implements IAviary {
  /**
   * Attribute holding the birds housed in this aviary.
   */
  private final List<IBird> birds;

  /**
   * Sector id where the aviary is located.
   */
  private final int sector;

  /**
   * Constructor for the Aviary.
   *
   * @param birds  List<IBird> list of birds housed in the aviary
   * @param sector int the sector id where the aviary is located
   * @throws IllegalArgumentException when any state constraint is violated by constructor args
   */
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

  /**
   * Accessor for the birds in this aviary.
   *
   * @return List<IBird> the list of birds in the aviary
   */
  public List<IBird> getBirds() {
    return this.birds;
  }

  /**
   * Accessor for the aviary's sector id.
   *
   * @return int the sector id
   */
  public int getSector() {
    return this.sector;
  }

  /**
   * Accessor for the types of birds in the aviary.
   *
   * @return List<BirdType> the list of bird types in the aviary
   */
  public List<BirdType> getBirdTypes() {
    return this.birds.stream().map(IBird::getType).collect(Collectors.toList());
  }

  /**
   * Adds a bird to the aviary.
   *
   * @param bird IBird to add to the aviary
   * @return IAviary new Aviary instance with updated birds list
   * @throws IllegalArgumentException when any constructor constraint is violated
   */
  public IAviary addBird(IBird bird) throws IllegalArgumentException {
    List<IBird> birds = this.birds;
    birds.add(bird);
    return new Aviary(
            birds,
            this.sector);
  }

  /**
   * Returns a list of daily food requirements based on the birds housed in the aviary. Assumes
   * each bird needs one of each of its diet per day.
   *
   * @return Hashtable<BirdDiet, Integer> the list of food requirements
   */
  public Hashtable<BirdDiet, Integer> getFoodRequirements()
  {
    Hashtable<BirdDiet, Integer> requirements = new Hashtable<>();

    for (IBird bird : this.birds) {
      for (BirdDiet birdDiet : bird.getDiet()) {
        if (requirements.containsKey(birdDiet)) {
          requirements.put(birdDiet, requirements.get(birdDiet) + 1);
        } else {
          requirements.put(birdDiet, 1);
        }
      }
    }

    return requirements;
  }

  /**
   * Describes the aviaries birds and location in a human-readable way.
   *
   * @return String the description of the aviary
   */
  public String describe() {
    String description = String.format(
            "The aviary in sector %s houses %s birds of types %s. " +
            "Below are descriptions of each bird living in this aviary:\n",
            this.sector,
            this.birds.size(),
            this.birds.stream()
                      .map(bird -> bird.getType().label)
                      .collect(Collectors.joining(", ")));

    for (IBird bird : this.birds) {
      description += String.format(
              "- %s\n",
              bird.describe());
    }

    return description;
  }
}
