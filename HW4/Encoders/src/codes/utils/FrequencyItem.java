package codes.utils;

/**
 * Temporary object to link a node to its symbol's frequency count.
 *
 * @param <T> the type of the node tracked by the item
 */
public interface FrequencyItem<T>
        extends Comparable<FrequencyItem<T>> {
  /**
   * Getter for the frequency.
   *
   * @return the frequency
   */
  Integer getFrequency();

  /**
   * Getter for the node.
   *
   * @return the node
   */
  T getNode();
}
