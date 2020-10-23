package bst;

public interface BSTNode<T extends Comparable<? super T>> {
  BSTNode<T> add(T obj);
  int size();
  boolean present(T obj);
  T minimum();
  T minimumHelper(T other);
  T maximum();
  T maximumHelper(T other);
  int height();
  int heightHelper(int height);
  String preOrder();
  String inOrder();
  String postOrder();
}
