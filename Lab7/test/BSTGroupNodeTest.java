import org.junit.Test;

import bst.BSTGroupNode;
import bst.BSTNode;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class BSTGroupNodeTest {
  @Test
  public void testValidConstructor() {
    try {
      BSTNode<Integer> node = new BSTGroupNode<>(10);
    } catch (Exception e) {
      fail("Valid constructor should not have failed.");
    }
  }

  @Test
  public void testSize() {
    BSTNode<Integer> node = new BSTGroupNode<>(10);
    assertEquals(1, node.size());
  }

  @Test
  public void testAdd() {
    BSTNode<Integer> node = new BSTGroupNode<>(10);
    assertEquals(1, node.size());
    node = node.add(14);
    assertEquals(2, node.size());
  }

  @Test
  public void testPresent() {
    BSTNode<Integer> node = new BSTGroupNode<>(10);
    assertTrue(node.present(10));
    assertFalse(node.present(6));
  }

  @Test
  public void testMinimum() {
    BSTNode<Integer> node = new BSTGroupNode<>(10);
    node = node.add(3).add(5).add(15).add(1);
    assertEquals(1, node.minimum(), 0.0001);
  }

  @Test
  public void testMaximum() {
    BSTNode<Integer> node = new BSTGroupNode<>(10);
    node = node.add(3).add(5).add(15).add(1);
    assertEquals(15, node.maximum(), 0.0001);
  }

  @Test
  public void testToString() {
    BSTNode<Integer> node = new BSTGroupNode<>(10);
    node = node.add(3).add(5).add(15).add(1);
    assertEquals("1 3 5 10 15", node.toString());
  }
}
