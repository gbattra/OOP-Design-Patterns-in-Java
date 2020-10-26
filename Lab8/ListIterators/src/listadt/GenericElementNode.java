package listadt;

import java.util.function.Function;

/**
 * This is a non-empty node in a generic list. It contains the object data and
 * the rest of the list.
 * 
 * @param <T> the type of element in the node
 */
public class GenericElementNode<T> implements GenericListADTNode<T> {
  protected T object;
  protected GenericListADTNode<T> rest;

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
  public String toString() {
    String objstring = this.object.toString();
    String rest = this.rest.toString();
    if (rest.length() > 0) {
      return objstring + " " + rest;
    } else {
      return objstring;
    }
  }

  @Override
  public GenericListADTNode<T> advance() {
    return this.rest;
  }

  @Override
  public boolean canAdvance() {
    return true;
  }
}
