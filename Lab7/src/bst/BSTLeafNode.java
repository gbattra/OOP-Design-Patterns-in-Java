package bst;

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
}
