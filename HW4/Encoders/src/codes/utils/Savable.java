package codes.utils;

import java.io.IOException;

/**
 * Interface for an object which can save itself to a file.
 */
public interface Savable {
  /**
   * Saves the instance to a file, presumably in such a way that a new instance could load itself
   * from the contents of the file.
   *
   * @param filepath the path of the file to write to
   * @return was the save successful
   */
  boolean save(String filepath) throws IOException;
}
