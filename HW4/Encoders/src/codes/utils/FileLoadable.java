package codes.utils;

import java.io.IOException;

/**
 * Objects implementing this interface can be loadable given a filepath.
 *
 * @param <T>
 */
public interface FileLoadable<T> {
  /**
   * Loads the object from the data in the file specified.
   *
   * @param filepath the path to the file
   * @return a loaded T instance
   * @throws IOException if file read fails
   */
  T load(String filepath) throws IOException;
}
