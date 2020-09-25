package birds.interfaces;

import java.util.Hashtable;

/**
 * Interface for a conservatory index. Useful for tracking the location
 * of birds within the conservatory.
 */
public interface IConservatoryIndex extends IDescribable {
  /**
   * Accessor for the hashtable mapping the birds to their sector.
   *
   * @return Hashtable of birds and their respective sector
   */
  Hashtable<IBird, Integer> getIndex();
}
