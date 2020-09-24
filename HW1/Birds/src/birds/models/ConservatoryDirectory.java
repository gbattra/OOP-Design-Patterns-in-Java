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
   * Constructor for ConservatoryDirectory.
   *
   * @param directory the initial directory of sectors mapped to aviaries
   * @throws IllegalArgumentException if constructor constraints violated
   */
  public ConservatoryDirectory(
          Hashtable<Integer, IAviary> directory) throws IllegalArgumentException {
    this.directory = directory;

    List<Integer> distinctSectors = this.directory.values()
                                          .stream().map(IAviary::getSector)
                                          .distinct().collect(Collectors.toList());
    if (distinctSectors.size() != this.directory.size()) {
      throw new IllegalArgumentException(
              "Each aviary in provided directory must have unique sector id");
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
    String description = String.format(
            "There are %s aviaries in the conservatory:\n",
            this.directory.size());

    for (IAviary aviary : this.directory.values())
    {
      description += String.format(
              "- Sector %s has an aviary with the bird types: %s\n",
              aviary.getSector(),
              aviary.getBirdTypes()
                    .stream().map(birdType -> birdType.label)
                    .collect(Collectors.joining(", ")));
    }

    return description;
  }
}
