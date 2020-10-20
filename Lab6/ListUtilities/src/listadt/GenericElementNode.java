package listadt;

import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * This is a non-empty node in a generic list. It contains the object data and
 * the rest of the list.
 * 
 * @param <T> the type of the element in the list
 */
public class GenericElementNode<T> implements GenericListADTNode<T> {
  private T object;
  private GenericListADTNode<T> rest;

  /**
   * Constructor.
   * 
   * @param p    the element at this node.
   * @param rest the rest of the list
   */
  public GenericElementNode(T p, GenericListADTNode<T> rest) {
    this.object = p;
    this.rest = rest;
  }

  @Override
  public int count() {
    return 1 + this.rest.count();
  }

  @Override
  public GenericListADTNode<T> addFront(T object) {
    return new GenericElementNode<>(object, this);
  }

  @Override
  public GenericListADTNode<T> addBack(T object) {
    this.rest = this.rest.addBack(object);
    return this;
  }

  @Override
  public GenericListADTNode<T> add(int index, T object) {
    if (index == 0) {
      return addFront(object);
    } else {
      this.rest = this.rest.add(index - 1, object);
      return this;
    }
  }

  @Override
  public GenericListADTNode<T> remove(T object) {
    if (this.object.equals(object)) {
      return this.rest;
    } else {
      this.rest = this.rest.remove(object);
      return this;
    }
  }

  @Override
  public T get(int index) throws IllegalArgumentException {
    if (index == 0) {
      return this.object;
    }
    return this.rest.get(index - 1);
  }

  @Override
  public <R> GenericListADTNode<R> map(Function<T, R> converter) {
    /*
     * Starting from this list of T, the resulting list of type R is an element
     * that contains this data converted to T, followed by the rest of the
     * converted list
     */
    return new GenericElementNode<>(converter.apply(this.object),
        this.rest.map(converter));
  }

  @Override
  public GenericListADTNode<T> filter(Predicate<T> predicate) {
    if (predicate.test(this.object)) {
      return new GenericElementNode<T>(this.object, this.rest.filter(predicate));
    }

    return this.rest.filter(predicate);
  }

  @Override
  public T fold(T identity, BinaryOperator<T> accumulator) {
    T aggregate = accumulator.apply(identity, this.object);
    return this.rest.fold(aggregate, accumulator);
  }

  @Override
  public String toString() {
    String objstring = this.object.toString();
    String rest = this.rest.toString();
    if (rest.length() > 0) {
      return objstring + " " + rest;
    } else {
      return objstring;
    }
  }
}
