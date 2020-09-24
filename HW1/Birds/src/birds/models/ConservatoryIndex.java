package birds.models;

import java.util.Collections;
import java.util.Hashtable;

import birds.interfaces.IBird;
import birds.interfaces.IConservatoryIndex;

/**
 * Class for a conservator index. Keeps track of birds and the sector in which they live.
 */
public class ConservatoryIndex implements IConservatoryIndex {
  /**
   * A hashtable tracking each bird and the sector where it lives.
   */
  private final Hashtable<IBird, Integer> index;

  /**
   * Constructor for ConservatoryIndex.
   *
   * @param index a hashtable tracking birds and their sectors
   */
  public ConservatoryIndex(
          Hashtable<IBird, Integer> index) {
    this.index = index;
  }

  /**
   * Accessor for the hashtable mapping the birds to their sector.
   *
   * @return Hashtable of birds and their respective sector
   */
  public Hashtable<IBird, Integer> getIndex() {
    return this.index;
  }

  /**
   * Describes the contents of the index in human-readable form.
   *
   * @return String describing the index
   */
  public String describe() {
    String description = "The following birds are housed in this conservatory:\n";
    for (IBird bird : Collections.list(this.index.keys())) {
      description += String.format(
              "- %s the %s lives in the aviary at sector %s\n",
              bird.getName(),
              bird.getType().label,
              this.index.get(bird));
    }

    return description;
  }
}
