
import org.junit.Test;

import bignumber.BigNumber;
import bignumber.BigNumberImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

/**
 * Test for the BigNumberImpl class.
 */
public class BigNumberImplTest {
  @Test
  public void testValidConstructorEmpty() {
    try {
      BigNumber number = new BigNumberImpl();
    } catch (Exception e) {
      fail("Valid constructor should not have failed.");
    }
  }

  @Test
  public void testValidConstructorString() {
    try {
      BigNumber number = new BigNumberImpl("1234");
      number = new BigNumberImpl("100234");
      number = new BigNumberImpl("1203334");
    } catch (Exception e) {
      fail("Valid constructor should not have failed.");
    }
  }

  @Test
  public void testInvalidConstructorInvalidString() {
    try {
      BigNumber number = new BigNumberImpl("-1234");
      number = new BigNumberImpl("12-34");
      number = new BigNumberImpl("12u34");
      number = new BigNumberImpl("12&34");
      fail("Invalid constructor should have failed.");
    } catch (Exception e) {
      // test passes
    }
  }

  @Test
  public void testLength() {
    BigNumber number = new BigNumberImpl("1234");
    assertEquals(4, number.length());
  }

  @Test
  public void testCopy() {
    BigNumber number = new BigNumberImpl("1234");
    BigNumber copy = number.copy();
    assertEquals(number, copy);
  }

  @Test
  public void testToString() {
    BigNumber number = new BigNumberImpl("1234");
    assertEquals("1234", number.toString());
  }

  @Test
  public void testEquals() {
    BigNumber number = new BigNumberImpl("1234");
    BigNumber identical = new BigNumberImpl("1234");
    assertEquals(number, identical);
  }

  @Test
  public void testNotEquals() {
    BigNumber number = new BigNumberImpl("1234");
    BigNumber identical = new BigNumberImpl("4321");
    assertNotEquals(number, identical);
  }

  @Test
  public void testLeftShift() {
    BigNumber number = new BigNumberImpl("1234");
    number.shiftLeft(3);
    assertEquals("1234000", number.toString());
  }

  @Test
  public void testLeftShiftNegative() {
    BigNumber number = new BigNumberImpl("1234");
    number.shiftLeft(-3);
    assertEquals("1", number.toString());
  }

  @Test
  public void testLeftShiftZeroNumber() {
    BigNumber number = new BigNumberImpl("0");
    number.shiftLeft(4);
    assertEquals("0", number.toString());
  }

  @Test
  public void testRightShift() {
    BigNumber number = new BigNumberImpl("1234");
    number.shiftRight(3);
    assertEquals("1", number.toString());
  }

  @Test
  public void testAddDigit() {
    BigNumber number = new BigNumberImpl("1234");
    number.addDigit(9);
    assertEquals("1243", number.toString());

    BigNumber other = new BigNumberImpl("100");
    other.addDigit(9);
    assertEquals("109", other.toString());
  }

  @Test
  public void testAddBigNumber() {
    BigNumber one = new BigNumberImpl("100");
    BigNumber two = new BigNumberImpl("10");
    BigNumber added = one.add(two);
    assertEquals("110", added.toString());

    one = new BigNumberImpl("1234");
    two = new BigNumberImpl("4");
    added = one.add(two);
    assertEquals("1238", added.toString());

    one = new BigNumberImpl("1234");
    two = new BigNumberImpl("1234");
    added = one.add(two);
    assertEquals("2468", added.toString());
  }

  @Test
  public void testGetDigitAt() {
    BigNumber number = new BigNumberImpl("1234");
    int digitLast = number.getDigitAt(0);
    int digitThird = number.getDigitAt(1);
    int digitSecond = number.getDigitAt(2);
    int digitFirst = number.getDigitAt(3);
    assertEquals(4, digitLast);
    assertEquals(3, digitThird);
    assertEquals(2, digitSecond);
    assertEquals(1, digitFirst);
  }

  @Test
  public void testInvalidGetDigit() {
    BigNumber number = new BigNumberImpl("1234");
    try {
      int digit = number.getDigitAt(4);
      fail("Invalid getDigitAt() should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testCompareTo() {
    BigNumber greater = new BigNumberImpl("1234");
    BigNumber lesser = new BigNumberImpl("234");
    assertEquals(1, greater.compareTo(lesser));
    assertEquals(-1, lesser.compareTo(greater));

    BigNumber same = new BigNumberImpl("1234");
    BigNumber equal = new BigNumberImpl("1234");
    assertEquals(0, same.compareTo(equal));
    assertEquals(0, equal.compareTo(same));

    BigNumber smaller = new BigNumberImpl("1234");
    BigNumber bigger = new BigNumberImpl("5134");
    assertEquals(1, bigger.compareTo(smaller));
    assertEquals(-1, smaller.compareTo(bigger));
  }

  @Test
  public void testReverse() {
    BigNumber number = new BigNumberImpl("1234");
    assertEquals("4321", number.reverse().toString());
  }

  @Test
  public void testShiftAndAdd() {
    BigNumber number = new BigNumberImpl();
    number.shiftLeft(1);
    number.addDigit(9);
    number.shiftLeft(1);
    number.addDigit(8);
    number.shiftLeft(1);
    number.addDigit(6);
    assertEquals("986", number.toString());
  }
}
