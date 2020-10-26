package lookandsay;

import java.math.BigInteger;

public class LookAndSayIterator implements RIterator<BigInteger> {
  private final BigInteger startSeed;
  private final BigInteger endValue;

  private BigInteger current;

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
  }

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
  }

  public LookAndSayIterator() {
    this.startSeed = new BigInteger("1");
    this.endValue = new BigInteger("9".repeat(100));
    this.current = this.startSeed;
  }

  @Override
  public boolean hasNext() {
    return this.current.compareTo(this.endValue) <= 0;
  }

  @Override
  public BigInteger next() {
    BigInteger c = this.current;
    this.current = this.lookAndSay();
    return c;
  }

  @Override
  public BigInteger prev() {
    return null;
  }

  @Override
  public boolean hasPrevious() {
    return false;
  }

  private BigInteger lookAndSay() {
    char[] cArray = this.current.toString().toCharArray();
    StringBuilder number = new StringBuilder();
    char s = '0';
    int sCount = 0;
    for (char c : cArray) {
      if (c == s) {
        sCount++;
      } else {
        number.append(this.lookAndSay(s, sCount));
        s = c;
        sCount = 1;
      }
    }
    number.append(this.lookAndSay(s, sCount));

    return new BigInteger(number.toString());
  }

  private String lookAndSay(char s, int sCount) {
    if (s == '0' || sCount < 1) {
      return "";
    }

    int sInt = Integer.parseInt(String.valueOf(s));
    return String.format("%s%s", sCount, sInt);
  }
}
