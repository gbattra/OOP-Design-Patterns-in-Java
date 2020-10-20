package listadt;

import java.util.Objects;

/**
 * Static utility class providing methods to manipulate and search ListADT objects.
 */
public final class ListADTUtilities {
  /**
   * Takes an array of type T and converts it to a list object.
   *
   * @param elements the array to convert
   * @param <T> the type of the array elements
   * @return the new list of the array elements
   * @throws IllegalArgumentException if any element is null
   */
  public static <T> ListADT<T> toList(T[] elements) throws IllegalArgumentException {
    ListADT<T> list = new ListADTImpl<>();
    for (T e : elements) {
      if (e == null) {
        throw new IllegalArgumentException("Provided list contains null values.");
      }
      list.addBack(e);
    }
    return list;
  }

  /**
   * Adds elements to the end of the provided list.
   *
   * @param list the list to add the elements to
   * @param elements the elements to add
   * @param <T> the type of the elements
   * @throws IllegalArgumentException if any element is null
   */
  public static <T> void addAll(ListADT<T> list, T... elements) throws IllegalArgumentException {
    for (T e : elements) {
      if (e == null) {
        throw new IllegalArgumentException("Element cannot be null.");
      }
      list.addBack(e);
    }
  }

  /**
   * Determines the frequency of one element in a given list.
   *
   * @param list the list to search
   * @param element the element to search for
   * @param <T> the type of elements in the list
   * @return the total count of occurrences in the list
   */
  public static <T> int frequency(ListADT<T> list, T element) {
    return list.filter(s -> Objects.equals(s, element)).getSize();
  }

  /**
   * Returns true if both lists contain no common elements. False otherwise.
   *
   * @param one first list to compare
   * @param two second list to compare
   * @return true / false are the lists disjointed?
   * @throws IllegalArgumentException when either list is null or contains null elements
   */
  public static boolean disjoint(ListADT<?> one, ListADT<?> two) throws IllegalArgumentException {
    if (one == null || two == null) {
      throw new IllegalArgumentException("One or both provided lists are null.");
    }

    boolean disjointed = true;
    for (var i = 0; i < one.getSize(); i++) {
      if (one.get(i) == null) {
        throw new IllegalArgumentException("First list contains a null element.");
      }

      for (var t = 0; t < two.getSize(); t++) {
        if (two.get(t) == null) {
          throw new IllegalArgumentException("Second list contains a null element.");
        }

        disjointed = !one.get(i).equals(two.get(t)) && disjointed;
      }
    }
    return disjointed;
  }

  /**
   * Determines if two lists are equal.
   *
   * @param one the first list to compare
   * @param two the second list to compare
   * @return true if equal / false otherwise
   * @throws IllegalArgumentException if either list is null or contains null elements
   */
  public static boolean equals(ListADT<?> one, ListADT<?> two) throws IllegalArgumentException {
    if (one == null || two == null) {
      throw new IllegalArgumentException("One or both lists provided are null.");
    }

    if (one.getSize() != two.getSize()) {
      return false;
    }

    for (var i = 0; i < one.getSize(); i++) {
      if (one.get(i) == null || two.get(i) == null) {
        throw new IllegalArgumentException("Lists cannot contain null elements.");
      }

      if (!one.get(i).equals(two.get(i))) {
        return false;
      }
    }

    return true;
  }

  /**
   * Reverses the provided list.
   *
   * @param list the list to reverse
   */
  public static void reverse(ListADT<?> list) {
    reverseHelper(list);
  }

  /**
   * Helper method to reverse a list which uses generic type T instead of wildcard ?.
   *
   * @param list the list to reverse
   * @param <T> the type of the list
   */
  private static <T> void reverseHelper(ListADT<T> list) {
    for (int i = 0; i < list.getSize(); i++) {
      list.add(list.getSize() - i, list.get(0));
      list.remove(list.get(0));
    }
  }

  /**
   * Swaps the elements at provided indices.
   *
   * @param list the list to swap
   * @param i index for first element to swap
   * @param j index for second element to swap
   */
  public static void swap(ListADT<?> list, int i, int j) {
    swapHelper(list, i, j);
  }

  /**
   * Helper method for swap() which uses generic type T instead of wildcard type ?.
   *
   * @param list the list to swap
   * @param i index for first element to swap
   * @param j index for second element to swap
   * @param <T> the type of elements in the list
   */
  private static <T> void swapHelper(ListADT<T> list, int i, int j) {
    if (i < 0 || j < 0 || i > list.getSize() || j > list.getSize()) {
      throw new IndexOutOfBoundsException("Provided indices are out of bounds.");
    }

    T iElement = list.get(i);
    T jElement = list.get(j);

    list.remove(iElement);
    list.remove(jElement);

    list.add(i, jElement);
    list.add(j, iElement);
  }
}
