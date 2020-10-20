import org.junit.Test;

import bignumber.ElementNode;
import bignumber.ListOfDigits;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

/**
 * Test for the ElementNode in a ListOfDigits.
 */
public class ElementNodeTest {
  @Test
  public void testValidConstructor() {
    try {
      new ElementNode("1234");
      new ElementNode("12034");
      new ElementNode("120340");
    } catch (Exception e) {
      fail("Valid constructor should not have failed.");
    }
  }

  @Test
  public void testValidConstructorZero() {
    try {
      new ElementNode("0");
    } catch (Exception e) {
      fail("Valid constructor should not have failed.");
    }
  }

  @Test
  public void testInvalidConstructor() {
    try {
      new ElementNode("%");
      new ElementNode("-6");
      fail("Invalid constructor should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testLength() {
    ListOfDigits digit = new ElementNode("1234");
    assertEquals(4, digit.length());
  }

  @Test
  public void testCopy() {
    ListOfDigits digit = new ElementNode("1234");
    ListOfDigits copy = digit.copy();
    assertEquals(digit, copy);
  }

  @Test
  public void testToString() {
    ListOfDigits digits = new ElementNode("1234");
    assertEquals("1234", digits.toString());
  }

  @Test
  public void testEquals() {
    ListOfDigits digit = new ElementNode("1234");
    ListOfDigits identical = new ElementNode("1234");
    assertEquals(digit, identical);
  }

  @Test
  public void testNotEquals() {
    ListOfDigits digit = new ElementNode("1234");
    ListOfDigits identical = new ElementNode("4321");
    assertNotEquals(digit, identical);
  }

  @Test
  public void testAddDigit() {
    ListOfDigits digit = new ElementNode("1234");
    ListOfDigits added = digit.addDigit(8);
    assertEquals("1242", added.toString());
  }

  @Test
  public void testAddDigitNewLength() {
    ListOfDigits digit = new ElementNode("999");
    ListOfDigits added = digit.addDigit(1);
    assertEquals("1000", added.toString());
  }

  @Test
  public void testAddBigNumber() {
    ListOfDigits one = new ElementNode("100");
    ListOfDigits two = new ElementNode("10");
    ListOfDigits added = one.add(two);
    assertEquals("110", added.toString());
  }

  @Test
  public void testLeftShift() {
    ListOfDigits digit = new ElementNode("1234");
    ListOfDigits shifted = digit.leftShift(3);
    assertEquals("1234000", shifted.toString());
  }

  @Test
  public void testLeftShiftNegative() {
    ListOfDigits digit = new ElementNode("1234");
    ListOfDigits shifted = digit.leftShift(-3);
    assertEquals("1", shifted.toString());
  }

  @Test
  public void testRightShift() {
    ListOfDigits digit = new ElementNode("1234");
    ListOfDigits shifted = digit.rightShift(3);
    assertEquals("1", shifted.toString());
  }

  @Test
  public void testRightShiftNegative() {
    ListOfDigits digit = new ElementNode("1234");
    ListOfDigits shifted = digit.rightShift(-3);
    assertEquals("1234000", shifted.toString());
  }

  @Test
  public void testGetDigitAt() {
    ListOfDigits digit = new ElementNode("1234");
    int digitLast = digit.getDigitAt(0);
    int digitFirst = digit.getDigitAt(3);
    assertEquals(4, digitLast);
    assertEquals(1, digitFirst);
  }

  @Test
  public void testInvalidGetDigitAt() {
    ListOfDigits digit = new ElementNode("1234");
    try {
      int d = digit.getDigitAt(4);
      fail("Invalid getDigitAt() should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testReverse() {
    ListOfDigits element = new ElementNode("1234");
    ListOfDigits reversed = element.reverse();
    assertEquals("4321", reversed.toString());
  }

  @Test
  public void testCompareTo() {
    ListOfDigits greater = new ElementNode("1234");
    ListOfDigits lesser = new ElementNode("234");
    assertEquals(1, greater.compareTo(lesser));
    assertEquals(-1, lesser.compareTo(greater));

    ListOfDigits same = new ElementNode("1234");
    ListOfDigits equal = new ElementNode("1234");
    assertEquals(0, same.compareTo(equal));
    assertEquals(0, equal.compareTo(same));

    ListOfDigits smaller = new ElementNode("1134");
    ListOfDigits bigger = new ElementNode("1234");
    assertEquals(1, bigger.compareTo(smaller));
    assertEquals(-1, smaller.compareTo(bigger));
  }
}
