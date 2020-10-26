import org.junit.Test;

import java.math.BigInteger;

import lookandsay.LookAndSayIterator;
import lookandsay.RIterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

  @Test
  public void testNext() {
    BigInteger start = new BigInteger("1");
    BigInteger end = new BigInteger("999999");
    RIterator<BigInteger> lookAndSay = new LookAndSayIterator(start, end);

    String[] expected = { "1", "11", "21", "1211", "111221", "312211" };
    int counter = 0;
    for (RIterator<BigInteger> it = lookAndSay; it.hasNext();) {
      BigInteger number = it.next();
      assertTrue(number.compareTo(end) <= 0);
      assertEquals(expected[counter], number.toString());
      counter++;
    }
  }

  @Test
  public void testPrevious() {
    BigInteger start = new BigInteger("11");
    BigInteger end = new BigInteger("999999");
    RIterator<BigInteger> lookAndSay = new LookAndSayIterator(start, end);

    BigInteger prev = new BigInteger("1");
    for (RIterator<BigInteger> it = lookAndSay; it.hasNext();) {
      assertEquals(prev.toString(), lookAndSay.prev().toString());
      prev = it.next();
    }
  }
}
