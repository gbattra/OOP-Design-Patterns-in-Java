package bst;

import java.util.Comparator;

public class BSTGroupNode <T extends Comparable<? super T>> implements BSTNode<T> {
  protected final T data;

  private BSTNode<T> left;
  private BSTNode<T> right;

  public BSTGroupNode(T data) {
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
}
