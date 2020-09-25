package birds.interfaces;

import java.util.Hashtable;

/**
 * Interface for a conservatory directory object. Useful for tracking aviaries and thier
 * sector locations.
 */
public interface IConservatoryDirectory extends IDescribable {
  /**
   * Accessor to the Hashtable of sector, aviary mappings.
   *
   * @return the hashtable of the directory
   */
  Hashtable<Integer, IAviary> getDirectory();
}
