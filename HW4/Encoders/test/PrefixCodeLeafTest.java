import org.junit.Test;

import codes.trees.CodeNode;
import codes.trees.PrefixCodeLeaf;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Test class for the Prefix Code Leaf object.
 */
public class PrefixCodeLeafTest {
  @Test
  public void testValidConstructor() {
    try {
      CodeNode<String, String> leaf = new PrefixCodeLeaf("A");
    } catch (Exception e) {
      fail("Valid constructor should not have failed.");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructor() {
    CodeNode<String, String> leaf = new PrefixCodeLeaf("");
    fail("Invalid constructor should have failed.");
  }

  @Test
  public void testGetters() {
    CodeNode<String, String> leaf = new PrefixCodeLeaf("A");
    assertEquals("A", leaf.getSymbol());
    assertEquals("", leaf.getCode());

    leaf = leaf.setCode("0");
    assertEquals("0", leaf.getCode());
    assertEquals("A", leaf.getSymbol());
  }

  @Test(expected = IllegalStateException.class)
  public void testAdd() {
    CodeNode<String, String> leaf = new PrefixCodeLeaf("A");
    leaf.add("B", "1");
    fail("Invalid add() should have failed.");
  }

  @Test
  public void testDecode() {
    try {
      CodeNode<String, String> leaf = new PrefixCodeLeaf("A");
      assertEquals("A", leaf.decode(""));
    } catch (Exception e) {
      fail("Valid decode() should not have failed.");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidDecode() {
    CodeNode<String, String> leaf = new PrefixCodeLeaf("A");
    leaf.decode("0");
    fail("Invalid decode() should have failed.");
  }

  @Test
  public void testValidEncode() {
    try {
      CodeNode<String, String> leaf = new PrefixCodeLeaf("A").setCode("1");
      assertEquals("1", leaf.encode("A"));
    } catch (Exception e) {
      fail("Valid encode() should not have failed.");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidEncode() {
    CodeNode<String, String> leaf = new PrefixCodeLeaf("A").setCode("1");
    leaf.encode("B");
    fail("Invalid encode should have failed.");
  }

  @Test
  public void testValidNext() {
    try {
      CodeNode<String, String> leaf = new PrefixCodeLeaf("A").setCode("1");
      assertEquals("A", leaf.next("1"));
    } catch (Exception e) {
      fail("Valid encode() should not have failed.");
    }
  }
}
