package bst;

/**
 * Implementation of a binary search tree. Is an ADT around Binary Search Tree nodes. Instantiated
 * with a leaf node at the root.
 *
 * @param <T> the type of data held by the binary search tree
 */
public class BSTImpl<T extends Comparable<? super T>> implements BST<T> {
  private BSTNode<T> root;

  /**
   * Generic constructor for the BST. Sets `root` to an empty leaf node.
   */
  public BSTImpl() {
    this.root = new BSTLeafNode<>();
  }

  @Override
  public void add(T obj) {
    this.root = this.root.add(obj);
  }

  @Override
  public int size() {
    return this.root.size();
  }

  @Override
  public boolean present(T obj) {
    return this.root.present(obj);
  }

  @Override
  public T minimum() {
    return this.root.minimum();
  }

  @Override
  public T maximum() {
    return this.root.maximum();
  }

  @Override
  public int height() {
    return this.root.height();
  }

  @Override
  public String preOrder() {
    return "[" + this.root.preOrder() + "]";
  }

  @Override
  public String inOrder() {
    return "[" + this.root.inOrder() + "]";
  }

  @Override
  public String postOrder() {
    return "[" + this.root.postOrder() + "]";
  }

  @Override
  public String toString() {
    return "[" + this.root.toString() + "]";
  }
}
