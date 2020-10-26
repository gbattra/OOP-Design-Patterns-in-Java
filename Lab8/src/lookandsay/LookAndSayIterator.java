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
    this.current = this.forward(c);
    return c;
  }

  @Override
  public BigInteger prev() {
    return this.reverse(this.current);
  }

  @Override
  public boolean hasPrevious() {
    return this.current.toString().toCharArray().length > 1;
  }

  private BigInteger forward(BigInteger seq) {
    char[] cArray = seq.toString().toCharArray();
    StringBuilder number = new StringBuilder();
    char s = '0';
    int sCount = 0;
    for (char c : cArray) {
      if (c == s) {
        sCount++;
      } else {
        number.append(this.forward(s, sCount));
        s = c;
        sCount = 1;
      }
    }
    number.append(this.forward(s, sCount));

    return new BigInteger(number.toString());
  }

  private String forward(char s, int sCount) {
    if (s == '0' || sCount < 1) {
      return "";
    }

    int sInt = Integer.parseInt(String.valueOf(s));
    return String.format("%s%s", sCount, sInt);
  }

  private BigInteger reverse(BigInteger seq) {
    char[] cArray = seq.toString().toCharArray();
    StringBuilder number = new StringBuilder();
    for (int i = 0; i < cArray.length; i+=2) {
      char j = cArray[i];
      char k = cArray[i+1];
      number.append(this.reverse(j, k));
    }

    return new BigInteger(number.toString());
  }

  private String reverse(char j, char k) {
    int count = Integer.parseInt(String.valueOf(j));
    StringBuilder str = new StringBuilder();
    for (int i = 0; i < count; i++) {
      str.append(k);
    }

    return str.toString();
  }
}
