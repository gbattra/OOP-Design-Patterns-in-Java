package bst;


/**
 * Implementation of binary search tree leaf node. Has no attributes and exists to allow
 * for easier recursion.
 *
 * @param <T> the type of data held by the node
 */
public class BSTLeafNode<T extends Comparable<? super T>> implements BSTNode<T> {
  @Override
  public BSTNode<T> add(T obj) {
    return new BSTGroupNode<>(obj);
  }

  @Override
  public int size() {
    return 0;
  }

  @Override
  public boolean present(T obj) {
    return false;
  }

  @Override
  public T minimum() {
    return this.minimumHelper(null);
  }

  @Override
  public T minimumHelper(T other) {
    return other;
  }

  @Override
  public T maximum() {
    return this.minimumHelper(null);
  }

  @Override
  public T maximumHelper(T other) {
    return other;
  }

  @Override
  public String toString() {
    return "";
  }

  @Override
  public int height() {
    return this.heightHelper(0);
  }

  @Override
  public int heightHelper(int height) {
    return height;
  }

  @Override
  public String preOrder() {
    return "";
  }

  @Override
  public String inOrder() {
    return "";
  }

  @Override
  public String postOrder() {
    return "";
  }
}
