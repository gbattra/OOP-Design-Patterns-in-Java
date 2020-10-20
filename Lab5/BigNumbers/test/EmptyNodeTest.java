import org.junit.Test;

import bignumber.ElementNode;
import bignumber.EmptyNode;
import bignumber.ListOfDigits;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

/**
 * Test for the EmptyNode in a ListOfDigits.
 */
public class EmptyNodeTest {
  @Test
  public void testLength() {
    ListOfDigits empty = new EmptyNode();
    assertEquals(0, empty.length());
  }

  @Test
  public void testCopy() {
    ListOfDigits digit = new EmptyNode();
    ListOfDigits copy = digit.copy();
    assertEquals(digit, copy);
  }

  @Test
  public void testReverse() {
    ListOfDigits digit = new EmptyNode();
    ListOfDigits reversed = digit.reverse();
    assertEquals(new EmptyNode(), reversed);
  }

  @Test
  public void testToString() {
    ListOfDigits empty = new EmptyNode();
    assertEquals("", empty.toString());
  }

  @Test
  public void testEquals() {
    ListOfDigits digit = new EmptyNode();
    ListOfDigits identical = new EmptyNode();
    assertEquals(digit, identical);
  }

  @Test
  public void testNotEquals() {
    ListOfDigits digit = new EmptyNode();
    ListOfDigits identical = new ElementNode("4321");
    assertNotEquals(digit, identical);
  }

  @Test
  public void testLeftShift() {
    ListOfDigits digit = new EmptyNode();
    ListOfDigits shifted = digit.leftShift(4);
    assertEquals("0000", shifted.toString());
  }

  @Test
  public void testLeftShiftNegative() {
    ListOfDigits digit = new EmptyNode();
    ListOfDigits shifted = digit.leftShift(-4);
    assertEquals("0", shifted.toString());
  }

  @Test
  public void testShiftRight() {
    ListOfDigits digits = new EmptyNode();
    ListOfDigits shifted = digits.rightShift(10);
    assertEquals(new ElementNode(0, new EmptyNode()), shifted);
  }

  @Test
  public void testShiftRightNegative() {
    ListOfDigits digit = new EmptyNode();
    ListOfDigits shifted = digit.rightShift(-3);
    assertEquals("000", shifted.toString());
  }

  @Test
  public void testGetDigitAt() {
    ListOfDigits digit = new EmptyNode();
    try {
      digit.getDigitAt(0);
      fail("Invalid call to getDigitAt() should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testAddDigit() {
    ListOfDigits empty = new EmptyNode();
    ListOfDigits added = empty.addDigit(7);
    assertEquals("7", added.toString());
  }

  @Test
  public void testAddBigNumber() {
    ListOfDigits empty = new EmptyNode();
    ListOfDigits other = new ElementNode("1234");
    ListOfDigits added = empty.add(other);
    assertEquals("1234", added.toString());
  }

  @Test
  public void testAddBigNumberSwitched() {
    ListOfDigits empty = new EmptyNode();
    ListOfDigits other = new ElementNode("1234");
    ListOfDigits added = other.add(empty);
    assertEquals("1234", added.toString());
  }

  @Test
  public void testCompareToTwoEmpty() {
    ListOfDigits empty = new EmptyNode();
    ListOfDigits other = new EmptyNode();
    assertEquals(0, empty.compareTo(other));
  }

  @Test
  public void testCompareTwoOneElement() {
    ListOfDigits empty = new EmptyNode();
    ListOfDigits other = new ElementNode(0, new EmptyNode());
    assertEquals(-1, empty.compareTo(other));
    assertEquals(1, other.compareTo(empty));
  }
}
