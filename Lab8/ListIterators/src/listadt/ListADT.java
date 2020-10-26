package listadt;

import java.util.function.Function;

/**
 * This interface represents a generic list.
 *
 * We represent the type of data that this will work with a generic parameter T.
 * This is a placeholder for the actual data type.
 *
 * This interface is also iterable.
 * 
 * @param <T> the type of element in the list
 */
public interface ListADT<T> extends Iterable<T> {
  /**
   * Add an object to the front of this list.
   * 
   * @param b the object to be added to the front of this list
   */
  void addFront(T b);

  /**
   * Add an object to the back of this list (so it is the last object in the
   * list.
   * 
   * @param b the object to be added to teh back of this list
   */
  void addBack(T b);

  /**
   * Add an object to this list so that it occupies the provided index. Index
   * begins with 0
   * 
   * @param index the index to be occupied by this object, beginning at 0
   * @param b     the object to be added to the list
   */
  void add(int index, T b);

  /**
   * Return the number of objects currently in this list.
   * 
   * @return the size of the list
   */
  int getSize();

  /**
   * Remove the first instance of this object from this list.
   * 
   * @param b the object to be removed
   */
  void remove(T b);

  /**
   * Get the (index)th object in this list.
   * 
   * @param index the index of the object to be returned
   * @return the object at the given index
   * @throws IllegalArgumentException if an invalid index is passed
   */
  T get(int index) throws IllegalArgumentException;

  /**
   * A general purpose map higher order function on this list, that returns the
   * corresponding list of type R.
   * 
   * @param converter the function that converts T into R
   * @param <R>       the type of data in the resulting list
   * @return the resulting list that is identical in structure to this list, but
   *         has data of type R
   */
  <R> ListADT<R> map(Function<T, R> converter);
}
