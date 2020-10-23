package bst;

public class BSTImpl<T extends Comparable<? super T>> implements BST<T> {
  private BSTNode<T> root;

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
    return this.root.preOrder();
  }

  @Override
  public String toString() {
    return "[" + this.root.toString() + "]";
  }
}
