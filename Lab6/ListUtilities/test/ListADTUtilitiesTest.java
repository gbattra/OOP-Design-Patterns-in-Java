import org.junit.Test;

import listadt.ListADT;
import listadt.ListADTUtilities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

/**
 * Tests for the static list utilitiy class.
 */
public class ListADTUtilitiesTest {
  @Test
  public void testValidToList() {
    String[] arr = {"one", "two", "three"};
    ListADT<String> list = ListADTUtilities.toList(arr);
    assertEquals(arr.length, list.getSize());
    assertEquals("one", list.get(0));
    assertEquals("two", list.get(1));
    assertEquals("three", list.get(2));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidToList() {
    String[] arr = {"one", null, "three"};
    ListADT<String> list = ListADTUtilities.toList(arr);
    fail("Invalid toList() should have failed.");
  }

  @Test
  public void testValidAddAll() {
    String[] arr = {"one", "two", "three"};
    ListADT<String> list = ListADTUtilities.toList(arr);
    ListADTUtilities.addAll(list, "four", "five", "six");
    assertEquals("four", list.get(3));
    assertEquals("five", list.get(4));
    assertEquals("six", list.get(5));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidAddAll() {
    String[] arr = {"one", "two", "three"};
    ListADT<String> list = ListADTUtilities.toList(arr);
    ListADTUtilities.addAll(list, "four", null, "six");
    fail("Invalid addAll() should have failed.");
  }

  @Test
  public void testFrequency() {
    String[] arr = {"one", "one", "three"};
    ListADT<String> list = ListADTUtilities.toList(arr);
    list.addBack(null);
    assertEquals(2, ListADTUtilities.frequency(list, "one"));
    assertEquals(1, ListADTUtilities.frequency(list, null));
  }

  @Test
  public void testValidDisjoint() {
    String[] arr1 = {"one", "one", "three"};
    ListADT<String> list1 = ListADTUtilities.toList(arr1);

    String[] arr2 = {"three", "four", "five"};
    ListADT<String> list2 = ListADTUtilities.toList(arr2);

    String[] arr3 = {"six", "seven", "eight"};
    ListADT<String> list3 = ListADTUtilities.toList(arr3);

    assertTrue(ListADTUtilities.disjoint(list1, list3));
    assertTrue(ListADTUtilities.disjoint(list2, list3));
    assertFalse(ListADTUtilities.disjoint(list1, list2));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidDisjointNullElement() {
    String[] arr1 = {"one", null, "three"};
    ListADT<String> list1 = ListADTUtilities.toList(arr1);

    String[] arr2 = {"three", "four", "five"};
    ListADT<String> list2 = ListADTUtilities.toList(arr2);

    ListADTUtilities.disjoint(list1, list2);
    fail("Invalid disjoint() should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidDisjointNullList() {
    ListADT<String> list1 = null;

    String[] arr2 = {"three", "four", "five"};
    ListADT<String> list2 = ListADTUtilities.toList(arr2);

    ListADTUtilities.disjoint(list1, list2);
    fail("Invalid disjoint() should have failed.");
  }

  @Test
  public void testValidEquals() {
    String[] arr1 = {"one", "two", "three"};
    ListADT<String> list1 = ListADTUtilities.toList(arr1);
    ListADT<String> list2 = ListADTUtilities.toList(arr1);

    String[] arr2 = {"three", "four", "five"};
    ListADT<String> list3 = ListADTUtilities.toList(arr2);

    assertTrue(ListADTUtilities.equals(list1, list2));
    assertFalse(ListADTUtilities.equals(list1, list3));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidEqualsNullElement() {
    String[] arr1 = {"one", null, "three"};
    ListADT<String> list1 = ListADTUtilities.toList(arr1);
    ListADT<String> list2 = ListADTUtilities.toList(arr1);
    assertTrue(ListADTUtilities.equals(list1, list2));
    fail("Invalid equals() should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidEqualsNullList() {
    String[] arr1 = {"one", "two", "three"};
    ListADT<String> list1 = ListADTUtilities.toList(arr1);
    ListADT<String> list2 = null;
    assertTrue(ListADTUtilities.equals(list1, list2));
    fail("Invalid equals() should have failed.");
  }

  @Test
  public void testReverse() {
    String[] arr = {"one", "two", "three"};
    ListADT<String> list = ListADTUtilities.toList(arr);
    ListADTUtilities.reverse(list);
    assertEquals("three", list.get(0));
    assertEquals("two", list.get(1));
    assertEquals("one", list.get(2));
  }

  @Test
  public void testSwap() {
    String[] arr = {"one", "two", "three", "four", "five"};
    ListADT<String> list = ListADTUtilities.toList(arr);
    ListADTUtilities.swap(list, 1, 3);
    assertEquals("four", list.get(1));
    assertEquals("two", list.get(3));
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void testInvalidSwapNeg() {
    String[] arr = {"one", "two", "three", "four", "five"};
    ListADT<String> list = ListADTUtilities.toList(arr);
    ListADTUtilities.swap(list, -1, 3);
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void testInvalidSwapOOB() {
    String[] arr = {"one", "two", "three", "four", "five"};
    ListADT<String> list = ListADTUtilities.toList(arr);
    ListADTUtilities.swap(list, 1, 13);
  }
}
