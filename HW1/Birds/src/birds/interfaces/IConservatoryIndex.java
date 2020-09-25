package birds.interfaces;

import java.util.Hashtable;

public interface IConservatoryIndex extends IDescribable {
  /**
   * Accessor for the hashtable mapping the birds to their sector.
   *
   * @return Hashtable of birds and their respective sector
   */
  Hashtable<IBird, Integer> getIndex();
}
