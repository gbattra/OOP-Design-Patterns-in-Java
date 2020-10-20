package rpg.interfaces;

import java.util.List;
import java.util.Optional;

/**
 * Interface for an object of type T that can be combined with another object of type T.
 *
 * @param <T> the type that can be combined.
 */
public interface ICombinable<T> {
  /**
   * Combines called instance with provided instance and returns new combined instance.
   *
   * @param other the object to combine to the one being called
   * @return an updated T instance with `other` combined
   * @throws IllegalArgumentException when provided `other` is invalid for combining
   * @throws IllegalStateException when the called object state is invalid for combining
   */
  T combine(T other) throws IllegalArgumentException, IllegalStateException;

  /**
   * Returns the objects of type T which were combined to create the instance being called.
   *
   * @return the list of objects used to create the called instance
   */
  Optional<List<T>> combinedWith();

  /**
   * True if instance is the result of combining other instances of type T.
   *
   * @return true/false is this a "composite" of other type T objects
   */
  boolean isCombined();
}
