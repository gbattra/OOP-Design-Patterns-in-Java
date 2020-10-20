package listadt;

import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * This represents an empty node of the generic list implementation.
 * 
 * @param <T> the type of element in this node
 */
public class GenericEmptyNode<T> implements GenericListADTNode<T> {
  @Override
  public int count() {
    return 0;
  }

  @Override
  public GenericListADTNode<T> addFront(T object) {
    return new GenericElementNode<>(object, this);
  }

  @Override
  public GenericListADTNode<T> addBack(T object) {
    return addFront(object);
  }

  @Override
  public GenericListADTNode<T> add(int index, T object)
      throws IllegalArgumentException {
    if (index == 0) {
      return addFront(object);
    }
    throw new IllegalArgumentException("Invalid index to add an element");
  }

  @Override
  public GenericListADTNode<T> remove(T object) {
    return this; // cannot remove from nothing!
  }

  @Override
  public T get(int index) throws IllegalArgumentException {
    throw new IllegalArgumentException("Wrong index");
  }

  @Override
  public <R> GenericListADTNode<R> map(Function<T, R> converter) {
    return new GenericEmptyNode<>();
  }

  @Override
  public GenericListADTNode<T> filter(Predicate<T> predicate) {
    return new GenericEmptyNode<T>();
  }

  @Override
  public T fold(T identity, BinaryOperator<T> accumulator) {
    return identity;
  }

  @Override
  public String toString() {
    return "";
  }
}
