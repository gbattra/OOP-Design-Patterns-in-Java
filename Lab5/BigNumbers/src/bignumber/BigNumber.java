package bignumber;

/**
 * Interface for a BigNumber class. Can hold arbitrarily long number via a linked list.
 */
public interface BigNumber extends Comparable<BigNumber> {
  /**
   * Gets the digits for this BigNumber instance.
   *
   * @return the first element in the linked list of digits
   */
  ListOfDigits getDigits();

  /**
   * Returns the number of elements in the linked digits list.
   *
   * @return the count of elements in the linked list
   */
  int length();

  /**
   * Copies the number into a new instance.
   *
   * @return the new BigNumber instance
   */
  BigNumber copy();

  /**
   * Creates a new instances of the number, but reversed.
   *
   * @return the reversed instance
   */
  BigNumber reverse();

  /**
   * Adds a zero to the end of the linked list N number of times.
   *
   * @param shifts the number of times to left shift
   */
  void shiftLeft(int shifts);

  /**
   * Removes the right-most digit from the linked list N number of times.
   *
   * @param shifts the number of times to right shift
   */
  void shiftRight(int shifts);

  /**
   * Adds a single digit to the value of the number. I.e. 144 + 5 = 159 || 9 + 2 = 11.
   *
   * @param digit the digit to add
   * @throws IllegalArgumentException if digit is > 10 or < 0
   */
  void addDigit(int digit) throws IllegalArgumentException;

  /**
   * Gets the digit at the provided position. Throws if provided position is out of range.
   *
   * @param position the position to query
   * @return the value of the digit at that position
   * @throws IllegalArgumentException if position is < 0 or out of range
   */
  int getDigitAt(int position) throws IllegalArgumentException;

  /**
   * Adds two big numbers together and returns a new BigNumber instance of their sum.
   *
   * @param number the number to add
   * @return a new BigNumber instance of the sum
   */
  BigNumber add(BigNumber number);
}
