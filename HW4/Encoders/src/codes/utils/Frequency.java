package codes.utils;

/**
 * Links an object of type T to an int indicating the frequency of that object in some sequence.
 *
 * @param <T> the type of the object
 */
public interface Frequency<T> {
  /**
   * Getter for the frequency.
   *
   * @return the frequency
   */
  Integer getFrequency();

  /**
   * Getter for the value.
   *
   * @return the value
   */
  T getValue();
}
