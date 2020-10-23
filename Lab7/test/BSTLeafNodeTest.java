import org.junit.Test;

import bst.BSTLeafNode;
import bst.BSTNode;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

public class BSTLeafNodeTest {
  @Test
  public void testAdd() {
    BSTNode<Integer> node = new BSTLeafNode<>();
    assertEquals(0, node.size());
    node = node.add(10);
    assertEquals(1, node.size());
  }

  @Test
  public void testSize() {
    BSTNode<Integer> node = new BSTLeafNode<>();
    assertEquals(0, node.size());
  }

  @Test
  public void testPresent() {
    BSTNode<Integer> node = new BSTLeafNode<>();
    assertFalse(node.present(10));
  }

  @Test
  public void testMinimum() {
    BSTNode<Integer> node = new BSTLeafNode<>();
    assertNull(node.minimum());
  }

  @Test
  public void testMaximum() {
    BSTNode<Integer> node = new BSTLeafNode<>();
    assertNull(node.maximum());
  }

  @Test
  public void testToString() {
    BSTNode<Integer> node = new BSTLeafNode<>();
    assertEquals("", node.toString());
  }

  @Test
  public void testHeight() {
    BSTNode<Integer> node = new BSTLeafNode<>();
    assertEquals(0, node.height());
  }

  @Test
  public void testPreOrder() {
    BSTNode<Integer> node = new BSTLeafNode<>();
    assertEquals("", node.preOrder());
  }
}
