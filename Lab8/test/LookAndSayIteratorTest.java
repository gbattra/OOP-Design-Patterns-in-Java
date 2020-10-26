import org.junit.Test;

import java.math.BigInteger;

import lookandsay.LookAndSayIterator;
import lookandsay.RIterator;

public class LookAndSayIteratorTest {
  @Test
  public void testValidConstructor() {
    RIterator<BigInteger> iterator = new LookAndSayIterator(
            new BigInteger("1"),
            new BigInteger("10"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructorNegSeed() {
    RIterator<BigInteger> iterator = new LookAndSayIterator(
            new BigInteger("-1"),
            new BigInteger("10"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructorNegEnd() {
    RIterator<BigInteger> iterator = new LookAndSayIterator(
            new BigInteger("11"),
            new BigInteger("-1"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructorSeedGreaterThanEnd() {
    RIterator<BigInteger> iterator = new LookAndSayIterator(
            new BigInteger("11"),
            new BigInteger("1"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructorSeedHasZeros() {
    RIterator<BigInteger> iterator = new LookAndSayIterator(
            new BigInteger("10"),
            new BigInteger("100"));
  }

  @Test
  public void testValidSecondConstructor() {
    RIterator<BigInteger> iterator = new LookAndSayIterator(new BigInteger("1"));

  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSecondConstructorNegSeed() {
    RIterator<BigInteger> iterator = new LookAndSayIterator(new BigInteger("-1"));

  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSecondConstructorSeedEqualToEnd() {
    RIterator<BigInteger> iterator = new LookAndSayIterator(new BigInteger("9".repeat(100)));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSecondConstructorSeedHasZeros() {
    RIterator<BigInteger> iterator = new LookAndSayIterator(new BigInteger("10"));
  }
}
