package birds.models;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import birds.enums.BirdDiet;
import birds.enums.BirdType;
import birds.interfaces.IAviary;
import birds.interfaces.IBird;
import birds.interfaces.IConservatory;
import birds.interfaces.IConservatoryDirectory;
import birds.interfaces.IConservatoryIndex;

/**
 * Class for a conservatory. Conservatories are made up of a collection of Aviaries, which each
 * house up to 5 birds. Conservatories can provide information about the birds it houses, such as
 * the sector or aviary where a bird is located, as well as human-readable descriptions.
 */
public class Conservatory implements IConservatory {
  /**
   * The max number of aviaries the conservatory can hold.
   */
  private static final int AVIARY_LIMIT = 20;

  /**
   * The list of aviaries within the conservatory.
   */
  private final List<IAviary> aviaries;

  /**
   * Constructor for the Conservatory class.
   */
  public Conservatory() {
    this.aviaries = new ArrayList<>();
  }

  /**
   * Constructor for the Conservatory class
   * @param aviaries the list of aviaries in the conservatory
   * @throws IllegalArgumentException if list of aviaries is too big or has invalid sectors
   */
  public Conservatory(
          List<IAviary> aviaries) throws IllegalArgumentException {
    this.aviaries = aviaries;

    if (aviaries.stream().anyMatch(aviary -> aviary.getSector() > AVIARY_LIMIT)) {
      throw new IllegalArgumentException("Sector value out of bounds.");
    }

    if (aviaries.size() !=
        aviaries.stream().map(IAviary::getSector).distinct().count()) {
      throw new IllegalArgumentException("Each aviary must reside in unique sector.");
    }

    if (this.aviaries.size() > Conservatory.AVIARY_LIMIT) {
      throw new IllegalArgumentException("Conservatory cannot have more than 20 aviaries.");
    }
  }

  /**
   * Adds an aviary to the list of aviaries.
   *
   * @param aviary IAviary the aviary to add
   * @return new Conservatory instance with updated aviary list
   * @throws IllegalStateException if aviary addition breaks any constructor constraints
   */
  public IConservatory addAviary(IAviary aviary) throws IllegalStateException {
    List<IAviary> aviaries = new ArrayList<>(this.aviaries);
    aviaries.add(aviary);
    try {
      return new Conservatory(aviaries);
    } catch (IllegalArgumentException e)
    {
      throw new IllegalStateException(e.getMessage());
    }
  }

  /**
   * Accessor for the aviary list
   * @return List<IAviary> the list of aviaries
   */
  public List<IAviary> getAviaries() {
    return this.aviaries;
  }

  /**
   * Builds and returns a directory object tracking aviaries and their sectors.
   *
   * @return the directory object
   * @throws IllegalArgumentException if the directory state constraints are violated
   */
  public IConservatoryDirectory getDirectory() throws IllegalArgumentException {
    Hashtable<Integer, IAviary> directory = new Hashtable<>();
    for (IAviary aviary : this.aviaries) {
      directory.put(aviary.getSector(), aviary);
    }

    return new ConservatoryDirectory(directory);
  }

  /**
   * Builds and returns a index object tracking birds and their sectors.
   *
   * @return the index object
   * @throws IllegalArgumentException if the index state constraints are violated
   */
  public IConservatoryIndex getIndex() throws IllegalArgumentException {
    Hashtable<IBird, Integer> index = new Hashtable<>();
    for (IAviary aviary : this.aviaries) {
      for (IBird bird : aviary.getBirds()) {
        index.put(bird, aviary.getSector());
      }
    }

    return new ConservatoryIndex(index);
  }

  /**
   * Finds the aviary housing a bird. Searches by name, type, diet, and wing count.
   *
   * @param bird the birds to search by
   * @return the aviary with that bird
   */
  public Optional<IAviary> getAviaryWithBird(IBird bird) {
    Optional<IAviary> aviary = this.aviaries.stream()
            .filter(a -> a.getBirds().stream().anyMatch(b -> b.equals(bird)))
            .findFirst();

    return aviary;
  }

  /**
   * Finds the aviary located at a given sector.
   *
   * @param sector the sector to search by
   * @return the aviary at that sector
   */
  public Optional<IAviary> getAviaryAtSector(int sector) {
    Optional<IAviary> aviary = this.aviaries.stream()
            .filter(a -> a.getSector() == sector)
            .findFirst();

    return aviary;
  }

  /**
   * Computes the daily required food quantities for the conservatory. Aggregates the food requirements
   * for each of its aviaries. Food quantity calculations assume each bird needs 1 of each food
   * it consumes per day.
   *
   * @return a Hashtable tracking each BirdDiet and the required quantity
   */
  public Hashtable<BirdDiet, Integer> getFoodRequirements() {
    Hashtable<BirdDiet, Integer> requirements = new Hashtable<>();

    for (IAviary aviary : this.aviaries) {
      Hashtable<BirdDiet, Integer> aviaryFoodRequirements = aviary.getFoodRequirements();
      for (Map.Entry<BirdDiet, Integer> entry : aviaryFoodRequirements.entrySet()) {
        if (requirements.containsKey(entry.getKey())) {
          requirements.replace(
                  entry.getKey(), requirements.get(entry.getKey()) + entry.getValue());
        } else {
          requirements.put(entry.getKey(), entry.getValue());
        }
      }
    }

    return requirements;
  }

  /**
   * Describes the contents of the conservatory in human-readable form.
   *
   * @return String describing the conservatory
   */
  public String describe() {
    ArrayList<String> birdTypeStrList = new ArrayList<>();
    for (IAviary aviary : this.aviaries) {
      for (BirdType birdType : aviary.getBirdTypes()) {
        birdTypeStrList.add(birdType.label);
      }
    }



    String description = String.format(
            "This conservatory has %s aviaries located in sectors %s. These aviaries are home " +
            "to the following types of birds: %s. For more information please read the " +
            "conservatory directory or index.",
            this.aviaries.size(),
            this.aviaries.stream().map(aviary -> String.valueOf(aviary.getSector()))
                                  .collect(Collectors.joining(", ")),
            String.join(", ", birdTypeStrList));


    return description;
  }
}
