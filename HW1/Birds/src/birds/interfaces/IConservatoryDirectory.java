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
   * Adds an aviary to the directory and returns updated instance.
   *
   * @param aviary the aviary to add
   * @return a new directory instance with updated aviaries
   */
  IConservatoryDirectory addAviary(IAviary aviary) throws IllegalStateException ;

  /**
   * Describes the contents of the directory in human-readable form.
   *
   * @return String describing the directory
   */
  String describe();
}
