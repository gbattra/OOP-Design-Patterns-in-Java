package bst;

import java.util.Arrays;

/**
 * Implementation of binary search tree group node. Has a data attribute,
 * a left child and a right child.
 *
 * @param <T> the type of data held by the node
 */
public class BSTGroupNode<T extends Comparable<? super T>> implements BSTNode<T> {
  protected final T data;

  private BSTNode<T> left;
  private BSTNode<T> right;

  /**
   * Constructor for node. All BSTNode's are instantiated with leaf nodes for left and right.
   *
   * @param data the data held by this node
   * @throws IllegalArgumentException if initialized with empty data
   */
  public BSTGroupNode(T data) throws IllegalArgumentException {
    if (data == null) {
      throw new IllegalArgumentException("Cannot instantiate node with null data.");
    }

    this.data = data;
    this.left = new BSTLeafNode<>();
    this.right = new BSTLeafNode<>();
  }

  @Override
  public BSTNode<T> add(T obj) {
    if (obj.equals(this.data)) {
      return this;
    }

    if (this.data.compareTo(obj) < 0) {
      this.right = this.right.add(obj);
    } else {
      this.left = this.left.add(obj);
    }

    return this;
  }

  @Override
  public int size() {
    return 1 + this.left.size() + this.right.size();
  }

  @Override
  public boolean present(T obj) {
    if (this.data.equals(obj)) {
      return true;
    }

    if (this.data.compareTo(obj) > 0) {
      return this.left.present(obj);
    } else {
      return this.right.present(obj);
    }
  }

  @Override
  public T minimum() {
    return this.minimumHelper(this.data);
  }

  @Override
  public T minimumHelper(T other) {
    return this.left.minimumHelper(this.data);
  }

  @Override
  public T maximum() {
    return this.maximumHelper(this.data);
  }

  @Override
  public T maximumHelper(T other) {
    return this.right.maximumHelper(this.data);
  }

  @Override
  public String toString() {
    return String.format(
              "%s %s %s",
              this.left.toString(),
              this.data,
              this.right.toString())
            .trim();
  }

  @Override
  public int height() {
    return this.heightHelper(0);
  }

  @Override
  public int heightHelper(int height) {
    int leftHeight = this.left.heightHelper(height + 1);
    int rightHeight = this.right.heightHelper(height + 1);

    return Math.max(leftHeight, rightHeight);
  }

  @Override
  public String preOrder() {
    return String.join(
            " ",
            Arrays.asList(
                    this.data.toString().trim(),
                    this.left.preOrder(),
                    this.right.preOrder()))
            .trim();
  }

  @Override
  public String inOrder() {
    return String.join(
            " ",
            Arrays.asList(
                    this.left.inOrder(),
                    this.data.toString().trim(),
                    this.right.inOrder()))
            .trim();
  }

  @Override
  public String postOrder() {
    return String.join(
              " ",
              Arrays.asList(
                      this.left.postOrder(),
                      this.right.postOrder(),
                      this.data.toString().trim()))
            .trim();
  }
}
