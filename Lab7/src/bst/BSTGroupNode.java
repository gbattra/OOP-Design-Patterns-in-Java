package bst;

import java.util.Comparator;

public class BSTGroupNode <T extends Comparable<? super T>> implements BSTNode<T> {
  protected final T data;

  private BSTNode<T> left;
  private BSTNode<T> right;

  public BSTGroupNode(T data) {
    this.data = data;
  }

  public BSTNode<T> add(T obj, Comparator<T> comparator) {
    if (obj.equals(this.data)) {
      return this;
    }

    if (comparator.compare(this.data, obj) > 0) {
      return this.right.add(obj, comparator);
    } else {
      return this.left.add(obj, comparator);
    }
  }
}
