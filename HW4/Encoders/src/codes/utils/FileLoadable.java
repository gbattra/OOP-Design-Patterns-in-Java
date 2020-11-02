package codes.utils;

import java.io.IOException;

/**
 * Objects implementing this interface can be loadable given a filepath.
 *
 * @param <T>
 */
public interface FileLoadable<T> {
  T load(String filepath) throws IOException;
}
