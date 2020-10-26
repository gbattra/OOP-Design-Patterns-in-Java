package lookandsay;

import java.math.BigInteger;

/**
 * Concrete class for a look-and-say sequence generator.
 */
public class LookAndSayIterator implements RIterator<BigInteger> {
  private final BigInteger startSeed;
  private final BigInteger endValue;

  private BigInteger current;
  private BigInteger previous;

  /**
   * Constructor for LookAndStayIterator class.
   *
   * @param startSeed the starting point for the sequence moving forward
   * @param endValue the stopping point for the sequence
   * @throws IllegalArgumentException if startSeed is negative, greater than endValue or has zeros
   */
  public LookAndSayIterator(
          BigInteger startSeed,
          BigInteger endValue) throws IllegalArgumentException {
    if (startSeed.compareTo(endValue) >= 0) {
      throw new IllegalArgumentException("Start seed must be less than end value.");
    }
    if (startSeed.toString().contains("0")) {
      throw new IllegalArgumentException("Start seed cannot contain any zeros.");
    }
    if (startSeed.compareTo(new BigInteger("0")) <= 0) {
      throw new IllegalArgumentException("Start seed must be greater than zero.");
    }
    if (endValue.compareTo(new BigInteger("0")) <= 0) {
      throw new IllegalArgumentException("End value must be greater than zero.");
    }

    this.startSeed = startSeed;
    this.endValue = endValue;
    this.current = startSeed;
    this.previous = startSeed;
  }

  /**
   * Constructor for LookAndStayIterator class.
   *
   * @param startSeed the starting point for the sequence moving forward
   * @throws IllegalArgumentException if startSeed is negative, greater than endValue or has zeros
   */
  public LookAndSayIterator(BigInteger startSeed) {
    if (startSeed.toString().contains("0")) {
      throw new IllegalArgumentException("Start seed cannot contain any zeros.");
    }
    if (startSeed.compareTo(new BigInteger("0")) <= 0) {
      throw new IllegalArgumentException("Start seed must be greater than zero.");
    }

    BigInteger endValue = new BigInteger("9".repeat(100));
    if (startSeed.compareTo(endValue) >= 0) {
      throw new IllegalArgumentException("Start seed must be less than end value.");
    }

    this.startSeed = startSeed;
    this.endValue = endValue;
    this.current = startSeed;
    this.previous = startSeed;
  }

  /**
   * Constructor for LookAndStayIterator class.
   */
  public LookAndSayIterator() {
    this.startSeed = new BigInteger("1");
    this.endValue = new BigInteger("9".repeat(100));
    this.current = this.startSeed;
    this.previous = this.startSeed;
  }

  @Override
  public boolean hasNext() {
    return this.current.compareTo(this.endValue) <= 0;
  }

  @Override
  public BigInteger next() {
    BigInteger c = this.current;
    this.current = this.forward(c);
    this.previous = c;
    return c;
  }

  @Override
  public BigInteger prev() {
    return this.backward(this.previous);
  }

  @Override
  public boolean hasPrevious() {
    return this.current.toString().toCharArray().length % 2 == 0;
  }

  /**
   * Computes the next number in the sequence using the look-and-say algorithm.
   *
   * @param seq the starting sequence
   * @return the next number in the sequence
   */
  private BigInteger forward(BigInteger seq) {
    char[] charArray = seq.toString().toCharArray();
    StringBuilder number = new StringBuilder();
    char s = '0';
    int count = 0;
    for (char c : charArray) {
      if (c == s) {
        count++;
      } else {
        number.append(this.forward(s, count));
        s = c;
        count = 1;
      }
    }
    number.append(this.forward(s, count));

    return new BigInteger(number.toString());
  }

  /**
   * Helper for forward() which converts the count and number into a string.
   *
   * @param s the character number
   * @param count the count of that character
   * @return the string concatenation of s and count
   */
  private String forward(char s, int count) {
    if (s == '0' || count < 1) {
      return "";
    }

    return String.format("%s%s", count, Integer.parseInt(String.valueOf(s)));
  }

  /**
   * Computes the previous value in the sequence by reversing the look-and-say algorithm.
   *
   * @param seq the current number in the sequence
   * @return the previous number in the sequence
   */
  private BigInteger backward(BigInteger seq) {
    char[] charArray = seq.toString().toCharArray();

    StringBuilder number = new StringBuilder();
    for (int i = 0; i < charArray.length; i += 2) {
      char j = charArray[i];
      char k = charArray[i + 1];
      number.append(this.backward(j, k));
    }

    return new BigInteger(number.toString());
  }

  /**
   * Helper for backward() which returns a string containing k, j number of times.
   *
   * @param j the count for the loop
   * @param k the value to add to the string
   * @return a string containing k, j number of times
   */
  private String backward(char j, char k) {
    int count = Integer.parseInt(String.valueOf(j));
    StringBuilder str = new StringBuilder();
    for (int i = 0; i < count; i++) {
      str.append(k);
    }

    return str.toString();
  }
}
