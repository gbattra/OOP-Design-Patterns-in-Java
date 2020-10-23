package bst;

import java.util.Comparator;

public class BSTImpl<T extends Comparable<? super T>> implements BST<T> {
  private BSTNode<T> root;

  public BSTImpl() {
    this.root = new BSTLeafNode<>();
  }

  public void add(T obj) {
    this.root = this.root.add(obj, Comparator.naturalOrder());
  }
}
