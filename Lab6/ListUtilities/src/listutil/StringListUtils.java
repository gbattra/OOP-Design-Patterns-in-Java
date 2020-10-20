package listutil;

import java.util.List;

/**
 * A class that contains several useful operations on lists of strings.
 */
public class StringListUtils {

  /**
   * Swap two elements in a list.
   * 
   * @param <T>    the type of elements in the list
   * @param list   the list
   * @param index1 the index of the first element
   * @param index2 the index of the second element
   */
  public static <T> void swap(List<T> list, int index1, int index2) {
    T obj = list.get(index1);
    list.set(index1, list.get(index2));
    list.set(index2, obj);
  }

  /**
   * Convert a list to a string using a loops.
   * 
   * @param list the list to convert
   * @return the string representation of the list
   */
  public static String toStringLoop(List<String> list) {
    String result = "";
    for (String obj : list) {
      result = result + " (" + obj + ")";
    }
    return result.substring(1);
  }

  /**
   * Convert a list to a string using their indices.
   * 
   * @param list the list to convert
   * @return the string representation of the list
   */
  public static String toStringPos(List<String> list) {
    String result = "";
    for (int index = 0; index < list.size(); index = index + 1) {
      result = result + " (" + index + ":" + list.get(index).toString() + ")";
    }
    return result.substring(1);
  }

  /**
   * Converts a list to a string.
   * 
   * @param list the list to covert
   * @return the string representation of the list
   */
  public static String toString(List<String> list) {
    return toStringRec(list, 0);
  }

  /**
   * Helper for the recursive toString method.
   * @param list the list to convert
   * @param index the current index
   * @return the string representation of the list so far
   */
  private static String toStringRec(List<String> list, int index) {

    if (index >= list.size()) {
      return "";
    }
    String rest = toStringRec(list, index + 1);
    if (rest.length() > 0) {
      return "(" + list.get(index).toString() + ")" + " " + rest;
    } else {
      return "(" + list.get(index).toString() + ")";
    }

  }
}
