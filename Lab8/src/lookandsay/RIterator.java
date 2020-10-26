package lookandsay;

import java.util.Iterator;

/**
 * Interface for an iterator object which can go in reverse.
 *
 * @param <T> the type of object at each iteration
 */
public interface RIterator<T> extends Iterator<T> {
  /**
   * The previous value in the iteration.
   *
   * @return the previous value T
   */
  T prev();

  /**
   * Determines if there is a previous value from the current value in the iteration.
   *
   * @return true if previous value exists
   */
  boolean hasPrevious();
}
