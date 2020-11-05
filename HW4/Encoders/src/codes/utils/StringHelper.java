package codes.utils;

import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * A helper class for selecting the unique elements from a string.
 */
public class StringHelper {
  /**
   * Takes a string and returns another string consisting of the distinct characters in the
   * original.
   *
   * @param str the string to read
   * @return a string with only distinct characters from original
   */
  public static String distinctCharacters(String str) {
    Set<Character> chars = new TreeSet<>();
    for (char c : str.toCharArray()) {
      chars.add(c);
    }

    return chars.stream().map(String::valueOf).collect(Collectors.joining(""));
  }
}
