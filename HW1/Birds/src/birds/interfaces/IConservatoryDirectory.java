package birds.interfaces;

import java.util.Hashtable;

public interface IConservatoryDirectory {
  /**
   * Accessor to the Hashtable of sector, aviary mappings.
   *
   * @return the hashtable of the directory
   */
  Hashtable<Integer, IAviary> getDirectory();

  /**
   * Describes the contents of the directory in human-readable form.
   *
   * @return String describing the directory
   */
  String describe();
}
