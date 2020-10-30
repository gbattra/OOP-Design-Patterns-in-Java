import org.junit.Test;

import cryptography.trees.CodeNode;
import cryptography.trees.PrefixCodeLeaf;

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
}
