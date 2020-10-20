package bignumber;

/**
 * Interface for a linked list of digits. These objects manage numbers bigger than int. Can hold
 * arbitrarily large numbers and perform basic math operations on them.
 */
public interface ListOfDigits extends Comparable<ListOfDigits> {
  /**
   * Get the value of the node.
   *
   * @return the int value for the node in the list
   */
  int getValue();

  /**
   * Get the next node in the linked list.
   *
   * @return this node's next node
   */
  ListOfDigits getNext();

  /**
   * Returns the number of elements in the linked digits list.
   *
   * @return the count of elements in the linked list
   */
  int length();

  /**
   * Helper method for length which uses an accumulator.
   *
   * @param acc the accumulator to be incremented
   * @return the length of the list
   */
  int lengthHelp(int acc);

  /**
   * Copies the linked list into a new instance.
   *
   * @return the new linked list instance
   */
  ListOfDigits copy();

  /**
   * Creates a new instances of the number, but reversed.
   *
   * @return the reversed instance
   */
  ListOfDigits reverse();

  /**
   * Inserts the node at the end of the list.
   *
   * @param node the node to insert
   * @return the updated list
   */
  ListOfDigits insert(ListOfDigits node);

  /**
   * Adds a zero to the end of the linked list N number of times.
   *
   * @param shifts the number of times to left shift
   */
  ListOfDigits rightShift(int shifts);

  /**
   * Removes the right-most digit from the linked list N number of times.
   *
   * @param shifts the number of times to right shift
   */
  ListOfDigits leftShift(int shifts);

  /**
   * Adds a single digit to the value of the number. I.e. 144 + 5 = 159 || 9 + 2 = 11.
   *
   * @param digit the digit to add
   * @throws IllegalArgumentException if digit is > 10 or < 0
   */
  ListOfDigits addDigit(int digit) throws IllegalArgumentException;

  /**
   * Gets the digit at the provided position. Throws if provided position is out of range.
   *
   * @param position the position to query
   * @return the value of the digit at that position
   * @throws IllegalArgumentException if position is < 0 or out of range
   */
  int getDigitAt(int position) throws IllegalArgumentException;

  /**
   * Adds two ListOfDigits together and returns a new ListOfDigits instance of their sum.
   *
   * @param digits the ListOfDigits to add
   * @return a new ListOfDigits instance of the sum
   */
  ListOfDigits add(ListOfDigits digits);

  /**
   * Helper for adding list of digits. Assumes list is reversed by `add()`.
   *
   * @param digits the digits to add
   * @return the updated list of digits
   */
  ListOfDigits addHelp(ListOfDigits digits);

  /**
   * Helper for adding a digit to a node. Does not reverse list like `add()`.
   *
   * @param digit the digit to add
   * @return the updated list with added digit
   */
  ListOfDigits addDigitHelp(int digit);

  /**
   * Helper for the toString method to avoid timeouts/overflows.
   *
   * @param stringBuilder the string to build
   * @return the string representation of the list
   */
  String toStringHelp(StringBuilder stringBuilder);
}
