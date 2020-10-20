package bignumber;

/**
 * EmptyNode object for a linked list of digits. Has no constructor and only servers to make
 * recursive operations smoother.
 */
public class EmptyNode implements ListOfDigits {
  /**
   * Returns the number of elements in the linked digits list.
   *
   * @return the count of elements in the linked list
   */
  public int length() {
    return 0;
  }

  /**
   * Helper method for length which uses an accumulator.
   *
   * @param acc the accumulator to be incremented
   * @return the length of the list
   */
  public int lengthHelp(int acc) {
    return acc;
  }

  /**
   * Copies the linked list into a new instance.
   *
   * @return the new linked list instance
   */
  public ListOfDigits copy() {
    return new EmptyNode();
  }

  /**
   * Creates a new instances of the number, but reversed.
   *
   * @return the reversed instance
   */
  public ListOfDigits reverse() {
    return new EmptyNode();
  }

  /**
   * Inserts the node at the end of the list.
   *
   * @param node the node to insert
   * @return the updated list
   */
  public ListOfDigits insert(ListOfDigits node) {
    return new ElementNode(node.getValue(), new EmptyNode());
  }

  /**
   * Removes the right-most digit from the linked list N number of times.
   *
   * @param shifts the number of times to right shift
   */
  public ListOfDigits leftShift(int shifts) {
    if (shifts == 0) {
      return new EmptyNode();
    }

    if (shifts < 0) {
      return this.rightShift(shifts * -1);
    }

    return new ElementNode(0, new EmptyNode().leftShift(shifts - 1));
  }

  /**
   * Adds a zero to the end of the linked list N number of times.
   *
   * @param shifts the number of times to left shift
   */
  public ListOfDigits rightShift(int shifts) {
    if (shifts < 0) {
      return this.leftShift(shifts * -1);
    }

    if (shifts == 0) {
      return new ElementNode(0, new EmptyNode());
    }

    return this.rightShift(shifts - 1);
  }

  /**
   * Adds a single digit to the value of the number. I.e. 144 + 5 = 159 || 9 + 2 = 11.
   *
   * @param digit the digit to add
   * @throws IllegalArgumentException if digit is > 10 or < 0
   */
  public ListOfDigits addDigit(int digit) {
    if (digit > 9 || digit < 0) {
      throw new IllegalArgumentException("Digit must be between 0 and 9.");
    }

    return this.addDigitHelp(digit);
  }

  public ListOfDigits addDigitHelp(int digit) {
    return new ElementNode(digit, new EmptyNode());
  }

  /**
   * Adds two ListOfDigits together and returns a new ListOfDigits instance of their sum.
   *
   * @param digits the ListOfDigits to add
   * @return a new ListOfDigits instance of the sum
   */
  public ListOfDigits add(ListOfDigits digits) {
    return this.addHelp(digits);
  }

  /**
   * Helper for adding list of digits. Assumes list is reversed by `add()`.
   *
   * @param digits the digits to add
   * @return the updated list of digits
   */
  public ListOfDigits addHelp(ListOfDigits digits) {
    return digits;
  }

  /**
   * Gets the digit at the provided position. Throws if provided position is out of range.
   *
   * @param position the position to query
   * @return the value of the digit at that position
   * @throws IllegalArgumentException if position is < 0 or out of range
   */
  public int getDigitAt(int position) {
    if (position < 0) {
      throw new IllegalArgumentException("Invalid position. Must be non-negative.");
    }
    throw new IllegalArgumentException("Position out of range.");
  }

  /**
   * Get the value of the node.
   *
   * @return the int value for the node in the list
   */
  public int getValue() {
    return 0;
  }

  /**
   * Get the next node in the linked list.
   *
   * @return this node's next node
   */
  public ListOfDigits getNext() {
    return new EmptyNode();
  }

  /**
   * Helper for the toString method to avoid timeouts/overflows.
   *
   * @param stringBuilder the string to build
   * @return the string representation of the list
   */
  public String toStringHelp(StringBuilder stringBuilder) {
    return stringBuilder.reverse().toString();
  }

  public int compareTo(ListOfDigits other) {
    return this.equals(other) ? 0 : -1;
  }

  @Override
  public String toString() {
    return "";
  }

  @Override
  public int hashCode() {
    return this.toString().hashCode();
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }

    if (other instanceof ListOfDigits) {
      return this.hashCode() == other.hashCode();
    }

    return false;
  }
}
