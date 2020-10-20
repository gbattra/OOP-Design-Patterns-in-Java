package bignumber;

/**
 * Constructor for an Element of a ListOfDigits linked list. Number can be >= 0 and can
 * have Element or Empty nodes as `next`.
 */
public class ElementNode implements ListOfDigits {
  private int value;
  private ListOfDigits next;

  /**
   * String constructor for the node which takes a string representation for the number
   * to build and setups the corresponding linked list.
   *
   * @param number the number to represent
   * @throws IllegalArgumentException if the number String is not a valid int
   */
  public ElementNode(String number) throws IllegalArgumentException {
    try {
      ListOfDigits digits = new EmptyNode();
      String[] numbers = number.split("");
      for (int i = 0; i < number.length(); i++) {
        digits = digits.leftShift(1);
        digits = digits.addDigit(Integer.parseInt(numbers[i]));
      }
      this.value = digits.getValue();
      this.next = digits.getNext();
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException(String.format(
              "Value: %s is an invalid number.",
              number.substring(0, 1)));
    }
  }

  /**
   * Alternative constructor which takes this node's value and the next item in the list.
   *
   * @param value this node's value
   * @param next the node's next item
   * @throws IllegalArgumentException if value < 0
   */
  public ElementNode(
          int value,
          ListOfDigits next) throws IllegalArgumentException {
    if (value < 0) {
      throw new IllegalArgumentException("Value must be non-negative.");
    }

    this.value = value;
    this.next = next;
  }

  /**
   * Returns the number of elements in the linked digits list.
   *
   * @return the count of elements in the linked list
   */
  public int length() {
    return this.lengthHelp(0);
  }

  /**
   * Helper method for length which uses an accumulator.
   *
   * @param acc the accumulator to be incremented
   * @return the length of the list
   */
  public int lengthHelp(int acc) {
    return this.next.lengthHelp(acc + 1);
  }

  /**
   * Copies the linked list into a new instance.
   *
   * @return the new linked list instance
   */
  public ListOfDigits copy() {
    return new ElementNode(
            this.value,
            this.next.copy());
  }

  /**
   * Creates a new instances of the number, but reversed.
   *
   * @return the reversed instance
   */
  public ListOfDigits reverse() {
    return this.next.reverse().insert(this);
  }

  /**
   * Inserts the node at the end of the list.
   *
   * @param node the node to insert
   * @return the updated list
   */
  public ListOfDigits insert(ListOfDigits node) {
    this.next = this.next.insert(node);
    return this;
  }

  /**
   * Removes the right-most digit from the linked list N number of times.
   *
   * @param shifts the number of times to right shift
   */
  public ListOfDigits leftShift(int shifts) {
    if (shifts < 0) {
      return this.rightShift(shifts * -1);
    }

    return new ElementNode(0, this).leftShift(shifts - 1);
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
      return new ElementNode(this.value, this.next);
    }

    return this.next.rightShift(shifts - 1);
  }

  /**
   * Adds a single digit to the value of the number. I.e. 144 + 5 = 159 || 9 + 2 = 11.
   *
   * @param digit the digit to add
   * @throws IllegalArgumentException if digit is > 10 or < 0
   */
  public ListOfDigits addDigit(int digit) {
    int a = this.value + digit;
    if (a >= 10) {
      return new ElementNode(
              a - 10,
              this.next.addDigit(1));
    } else {
      return new ElementNode(a, this.next);
    }
  }

  /**
   * Helper for adding a digit to a node. Does not reverse list like `add()`.
   *
   * @param digit the digit to add
   * @return the updated list with added digit
   */
  public ListOfDigits addDigitHelp(int digit) {
    int a = this.value + digit;
    if (a >= 10) {
      return new ElementNode(
              a - 10,
              this.next.addDigitHelp(1));
    } else {
      return new ElementNode(a, this.next);
    }
  }

  /**
   * Gets the digit at the provided position. Throws if provided position is out of range.
   *
   * @param position the position to query
   * @return the value of the digit at that position
   * @throws IllegalArgumentException if position is < 0 or out of range
   */
  public int getDigitAt(int position) throws IllegalArgumentException {
    if (position < 0) {
      throw new IllegalArgumentException("Invalid position. Must be non-negative.");
    }
    if (position == 0) {
      return this.value;
    }

    return this.next.getDigitAt(position - 1);
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
    ListOfDigits added = this.addDigitHelp(digits.getValue());
    return new ElementNode(
            added.getValue(),
            added.getNext().addHelp(digits.getNext()));
  }

  /**
   * Get the value of the node.
   *
   * @return the int value for the node in the list
   */
  public int getValue() {
    return this.value;
  }

  /**
   * Get the next node in the linked list.
   *
   * @return this node's next node
   */
  public ListOfDigits getNext() {
    return this.next;
  }

  /**
   * Determines whether this list represents a number bigger, smaller or equal to the one provided.
   *
   * @param other the other list to compare
   * @return int ranking 1 = greater, -1 = less, 0 = even
   */
  public int compareTo(ListOfDigits other) {
    if (this.length() > other.length()) {
      return 1;
    }
    if (this.length() < other.length()) {
      return -1;
    }
    if (this.value > other.getValue()) {
      return 1;
    }
    if (this.value < other.getValue()) {
      return -1;
    }

    return this.next.compareTo(other.getNext());
  }

  /**
   * Helper for the toString method to avoid timeouts/overflows.
   *
   * @param stringBuilder the string to build
   * @return the string representation of the list
   */
  public String toStringHelp(StringBuilder stringBuilder) {
    return this.next.toStringHelp(stringBuilder.append(this.value));
  }

  @Override
  public String toString() {
    return this.toStringHelp(new StringBuilder());
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
