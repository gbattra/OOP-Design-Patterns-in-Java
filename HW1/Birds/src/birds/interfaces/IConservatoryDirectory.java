package birds.interfaces;

import java.util.Hashtable;

public interface IConservatoryDirectory extends IDescribable {
  /**
   * Accessor to the Hashtable of sector, aviary mappings.
   *
   * @return the hashtable of the directory
   */
  Hashtable<Integer, IAviary> getDirectory();
}
