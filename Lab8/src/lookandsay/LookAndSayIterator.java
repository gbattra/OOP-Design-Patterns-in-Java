package lookandsay;

import java.math.BigInteger;

public class LookAndSayIterator implements RIterator<BigInteger> {
  private final BigInteger startSeed;
  private final BigInteger endValue;

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
  }

  @Override
  public boolean hasNext() {
    return false;
  }

  @Override
  public BigInteger next() {
    return null;
  }

  @Override
  public BigInteger prev() {
    return null;
  }

  @Override
  public boolean hasPrevious() {
    return false;
  }
}
