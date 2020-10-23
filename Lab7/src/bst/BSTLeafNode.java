package bst;

import java.util.Comparator;

public class BSTLeafNode<T extends Comparable<? super T>> implements BSTNode<T> {
  public BSTNode<T> add(T obj, Comparator<T> comparator) {
    return new BSTGroupNode<>(obj);
  }
}
