package codes.utils;

import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class StringHelper {
  public static String distinctCharacters(String str) {
    Set<Character> chars = new TreeSet<>();
    for (char c : str.toCharArray()) {
      chars.add(c);
    }

    return chars.stream().map(String::valueOf).collect(Collectors.joining(""));
  }
}
