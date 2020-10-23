import org.junit.Test;

import bst.BST;
import bst.BSTGroupNode;
import bst.BSTImpl;
import bst.BSTNode;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class BSTImplTest {
  @Test
  public void testConstructor() {
    try {
      BST<Integer> bst = new BSTImpl<>();
    } catch (Exception e) {
      fail("Valid constructor should not have failed.");
    }
  }

  @Test
  public void testAdd() {
    BST<Integer> tree = new BSTImpl<>();
    tree.add(10);
    assertEquals("[10]", tree.toString());
  }

  @Test
  public void testSize() {
    BST<Integer> tree = new BSTImpl<>();
    assertEquals(0, tree.size());
    tree.add(10);
    assertEquals(1, tree.size());
    tree.add(1);
    assertEquals(2, tree.size());
  }

  @Test
  public void testPresent() {
    BST<Integer> tree = new BSTImpl<>();
    assertFalse(tree.present(10));
    tree.add(10);
    assertTrue(tree.present(10));
  }

  @Test
  public void testMinimum() {
    BST<Integer> tree = new BSTImpl<>();
    tree.add(10);
    tree.add(3);
    tree.add(5);
    tree.add(15);
    tree.add(1);
    assertEquals(1, tree.minimum(), 0.0001);
  }

  @Test
  public void testMaximum() {
    BST<Integer> tree = new BSTImpl<>();
    tree.add(10);
    tree.add(3);
    tree.add(5);
    tree.add(15);
    tree.add(1);
    assertEquals(15, tree.maximum(), 0.0001);
  }

  @Test
  public void testToString() {
    BST<Integer> tree = new BSTImpl<>();
    tree.add(10);
    tree.add(3);
    tree.add(5);
    tree.add(15);
    tree.add(1);
    assertEquals("[1 3 5 10 15]", tree.toString());
  }

  @Test
  public void testHeight() {
    BST<Integer> tree = new BSTImpl<>();
    tree.add(10);
    tree.add(3);
    tree.add(5);
    tree.add(15);
    tree.add(1);
    assertEquals(3, tree.height());
  }

  @Test
  public void testPreOrder() {
    BST<Integer> tree = new BSTImpl<>();
    tree.add(10);
    tree.add(3);
    tree.add(5);
    tree.add(15);
    tree.add(1);
    assertEquals("10 3 1 5 15", tree.preOrder());
  }
}
