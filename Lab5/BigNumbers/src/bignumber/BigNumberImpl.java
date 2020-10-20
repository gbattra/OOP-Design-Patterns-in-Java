package bignumber;

/**
 * Implementation of the BigNumber interface. Takes either an empty constructor or a String
 * representation of the number, which it uses to build the linked digits list.
 */
public class BigNumberImpl implements BigNumber {
  private ListOfDigits digits;

  /**
   * Empty constructor which setups a zero number.
   */
  public BigNumberImpl() {
    digits = new ElementNode(0, new EmptyNode());
  }

  /**
   * Constructor which takes a String representing the full number and converts it to a linked
   * list.
   *
   * @param number the number to setup
   * @throws IllegalArgumentException if ElementNode constructor throws (when number is not valid)
   */
  public BigNumberImpl(String number)  throws IllegalArgumentException {
    this.digits = new EmptyNode();
    String[] numbers = number.split("");
    for (int i = 0; i < number.length(); i++) {
      this.shiftLeft(1);
      this.addDigit(Integer.parseInt(numbers[i]));
    }
  }

  private BigNumberImpl(ListOfDigits digits) {
    this.digits = digits;
  }

  /**
   * Returns the number of elements in the linked digits list.
   *
   * @return the count of elements in the linked list
   */
  public int length() {
    return digits.length();
  }

  /**
   * Copies the number into a new instance.
   *
   * @return the new BigNumber instance
   */
  public BigNumber copy() {
    return new BigNumberImpl(this.digits.copy());
  }

  /**
   * Creates a new instances of the number, but reversed.
   *
   * @return the reversed instance
   */
  public BigNumber reverse() {
    return new BigNumberImpl(this.digits.reverse());
  }

  /**
   * Removes the right-most digit from the linked list N number of times.
   *
   * @param shifts the number of times to right shift
   */
  public void shiftRight(int shifts) {
    if (shifts < 0) {
      this.shiftLeft(shifts * -1);
      return;
    }

    this.digits = this.digits.rightShift(shifts);
  }

  /**
   * Adds a zero to the end of the linked list N number of times.
   *
   * @param shifts the number of times to left shift
   */
  public void shiftLeft(int shifts) {
    if (shifts < 0) {
      this.shiftRight(shifts * -1);
      return;
    }

    if (shifts == 0) {
      return;
    }

    if (this.length() == 1 && this.digits.getDigitAt(0) == 0) {
      return;
    }

    this.digits = new ElementNode(0, this.digits);
    this.shiftLeft(shifts - 1);
  }

  /**
   * Adds a single digit to the value of the number. I.e. 144 + 5 = 159 || 9 + 2 = 11.
   *
   * @param digit the digit to add
   * @throws IllegalArgumentException if digit is > 10 or < 0
   */
  public void addDigit(int digit) throws IllegalArgumentException {
    if (digit > 9 || digit < 0) {
      throw new IllegalArgumentException("Digit must be between 0 and 9.");
    }

    this.digits = this.digits.addDigit(digit);
  }

  /**
   * Gets the digit at the provided position. Throws if provided position is out of range.
   *
   * @param position the position to query
   * @return the value of the digit at that position
   * @throws IllegalArgumentException if position is < 0 or out of range
   */
  public int getDigitAt(int position) throws IllegalArgumentException {
    return this.digits.getDigitAt(position);
  }

  /**
   * Gets the digits for this BigNumber instance.
   *
   * @return the first element in the linked list of digits
   */
  public ListOfDigits getDigits() {
    return this.digits;
  }

  /**
   * Adds two big numbers together and returns a new BigNumber instance of their sum.
   *
   * @param number the number to add
   * @return a new BigNumber instance of the sum
   */
  public BigNumber add(BigNumber number) {
    ListOfDigits added = this.digits.add(number.getDigits());
    return new BigNumberImpl(added);
  }

  @Override
  public int compareTo(BigNumber other) {
    return this.reverse().getDigits().compareTo(other.reverse().getDigits());
  }

  @Override
  public String toString() {
    return this.digits.toString();
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

    if (other instanceof BigNumber) {
      return this.hashCode() == other.hashCode();
    }

    return false;
  }
}
