package birds.interfaces;

import java.util.Hashtable;

public interface IConservatoryIndex {
  /**
   * Accessor for the hashtable mapping the birds to their sector.
   *
   * @return Hashtable of birds and their respective sector
   */
  Hashtable<IBird, Integer> getIndex();

  /**
   * Describes the contents of the index in human-readable form.
   *
   * @return String describing the index
   */
  String describe();
}
