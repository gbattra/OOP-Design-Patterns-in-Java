package bst;

/**
 * Interface for a node in a binary search tree.
 *
 * @param <T> the type of data held by the node
 */
public interface BSTNode<T extends Comparable<? super T>> {
  /**
   * Adds an object to the tree.
   *
   * @param obj the object to add
   */
  BSTNode<T> add(T obj);

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
   * Helper function for finding the minimum object in the tree. Tracks previous node (`other`)
   * so that leaf nodes can simply return `other` if called from group node, or null if called
   * by itself.
   *
   * @param other the data of the node calling this method
   * @return the data of the minimum node (furthest left)
   */
  T minimumHelper(T other);

  /**
   * Finds the object furthest right in the binary tree.
   *
   * @return the largest object as determined by tree structure
   */
  T maximum();

  /**
   * Helper function for finding the maximum object in the tree. Tracks previous node (`other`)
   * so that leaf nodes can simply return `other` if called from group node, or null if called
   * by itself.
   *
   * @param other the data of the node calling this method
   * @return the data of the maximum node (furthest right)
   */
  T maximumHelper(T other);

  /**
   * Gets the max number of layers for a single path down the tree, not counting leaf nodes.
   *
   * @return the number layers traversed by the longest path down the tree
   */
  int height();

  /**
   * Helper function for finding the height of the tree. Returns the height value passed in,
   * which would be 0 if called from within the leaf node itself.
   *
   * @param height the height "so far"
   * @return the data of the maximum node (furthest right)
   */
  int heightHelper(int height);

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
