package codes.utils;

import java.util.Map;

/**
 * Generic interface for object that can be converted to map.
 *
 * @param <K> the key type
 * @param <V> the value type
 */
public interface Mappable<K, V> {
  /**
   * Converts the object to a map.
   *
   * @return the map of the object
   */
  Map<K, V> toMap();
}
