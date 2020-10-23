package bst;

/**
 * Interface for a Binary Search Tree ADT wrapper.
 *
 * @param <T> type of object stored in the binary search tree
 */
public interface BST<T> {
  /**
   * Adds an object to the tree.
   *
   * @param obj the object to add
   */
  void add(T obj);

  /**
   * Counts the number of (non-leaf) nodes in the tree.
   *
   * @return the number of nodes in the tree
   */
  int size();

  /**
   * Searches for node containing matching object. Returns true if found, false otherwise.
   *
   * @param obj the object to search for
   * @return true if found, false otherwise
   */
  boolean present(T obj);

  /**
   * Finds the object furthest left in the binary tree.
   *
   * @return the smallest object as determined by tree structure
   */
  T minimum();

  /**
   * Finds the object furthest right in the binary tree.
   *
   * @return the largest object as determined by tree structure
   */
  T maximum();

  /**
   * Gets the max number of layers for a single path down the tree, not counting leaf nodes.
   *
   * @return the number layers traversed by the longest path down the tree
   */
  int height();

  /**
   * Returns a string in pre-order arrangement: node + left pre-order + right pre-order.
   *
   * @return the pre-ordered string representation
   */
  String preOrder();

  /**
   * Returns a string in in-order arrangement: left in-order + node + right in-order.
   *
   * @return the in-order string representation
   */
  String inOrder();

  /**
   * Returns a string in post-order arrangement: left post-order + right post-order + node.
   *
   * @return the post-order string representation
   */
  String postOrder();
}
