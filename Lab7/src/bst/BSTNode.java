package bst;

import java.util.Comparator;

public interface BSTNode<T extends Comparable<? super T>> {
  BSTNode<T> add(T obj, Comparator<T> comparator);
}
