import org.junit.Test;

import bst.BST;
import bst.BSTImpl;

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
    BST<Integer> bst = new BSTImpl<>();
    bst.add(10);
  }

  @Test
  public void testSize() {
    BST<Integer> bst = new BSTImpl<>();
    assertEquals(0, bst.size());
    bst.add(10);
    assertEquals(1, bst.size());
    bst.add(1);
    assertEquals(2, bst.size());
  }

  @Test
  public void testPresent() {
    BST<Integer> bst = new BSTImpl<>();
    assertFalse(bst.present(10));
    bst.add(10);
    assertTrue(bst.present(10));
  }
}
