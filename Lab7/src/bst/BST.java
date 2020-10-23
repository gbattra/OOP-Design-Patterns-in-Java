package bst;

public interface BST<T> {
  void add(T obj);
  int size();
  boolean present(T obj);
  T minimum();
  T maximum();
  int height();
  String preOrder();
  String inOrder();
  String postOrder();
}
