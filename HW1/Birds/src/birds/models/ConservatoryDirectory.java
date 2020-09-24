package birds.models;

import java.util.Hashtable;
import java.util.List;
import java.util.stream.Collectors;

import birds.interfaces.IAviary;
import birds.interfaces.IConservatoryDirectory;

/**
 * Class for a conservatory directory. Holds a hashtable tracking the sectors where each aviary
 * is located.
 */
public class ConservatoryDirectory implements IConservatoryDirectory  {
  /**
   * The hashtable directory mapping sectors to aviaries.
   */
  private final Hashtable<Integer, IAviary> directory;

  /**
   * The limit of aviaries the directory can hold.
   */
  private final int limit;

  /**
   * Constructor for ConservatoryDirectory.
   *
   * @param limit the max number of aviaries the directory can hold
   * @throws IllegalArgumentException if limit <= 0
   */
  public ConservatoryDirectory(int limit) throws IllegalArgumentException {
    this.limit = limit;
    this.directory = new Hashtable<>();

    if (this.limit <= 0) {
      throw new IllegalArgumentException("Limit must be greater than zero.");
    }
  }

  /**
   * Constructor for ConservatoryDirectory.
   *
   * @param limit the max number of aviaries the directory can hold
   * @param directory the initial directory of sectors mapped to aviaries
   * @throws IllegalArgumentException if constructor constraints violated
   */
  public ConservatoryDirectory(
          int limit,
          Hashtable<Integer, IAviary> directory) throws IllegalArgumentException {
    this.limit = limit;
    this.directory = directory;

    if (this.limit <= 0) {
      throw new IllegalArgumentException("Limit must be greater than zero.");
    }

    if (this.directory.size() > this.limit) {
      throw new IllegalArgumentException(
              String.format("Directory size cannot exceed directory limit of %s", this.limit));
    }

    List<Integer> distinctSectors = this.directory.values()
                                          .stream().map(IAviary::getSector)
                                          .distinct().collect(Collectors.toList());
    if (distinctSectors.size() != this.directory.size()) {
      throw new IllegalArgumentException(
              "Each aviary in provided directory must have unique sector id");
    }
  }

  /**
   * Adds an aviary to the directory and returns updated instance.
   *
   * @param aviary the aviary to add
   * @return a new directory instance with updated aviaries
   */
  public IConservatoryDirectory addAviary(IAviary aviary) throws IllegalStateException {
    try {
      Hashtable<Integer, IAviary> newDirectory = new Hashtable<>(this.directory);
      newDirectory.put(aviary.getSector(), aviary);
      return new ConservatoryDirectory(
              this.limit,
              newDirectory);
    } catch (IllegalArgumentException e) {
      throw new IllegalStateException(e.getMessage());
    }
  }

  /**
   * Accessor to the Hashtable of sector, aviary mappings.
   *
   * @return the hashtable of the directory
   */
  public Hashtable<Integer, IAviary> getDirectory() {
    return this.directory;
  }

  /**
   * Describes the contents of the directory in human-readable form.
   *
   * @return String describing the directory
   */
  public String describe() {
    return "";
  }
}
