package booklistadt;

import bookutil.Book;

/**
 * This class represents an empty node in the list adt implementation.
 */
public class BookListADTEmptyNode implements BookListADTNode {
  @Override
  public int count() {
    return 0;
  }

  @Override
  public String toString() {
    return "";
  }

  @Override
  public BookListADTNode addFront(Book book) {
    return new BookListADTElementNode(book, this);
  }

  @Override
  public BookListADTNode addBack(Book book) {
    return addFront(book);
  }

  @Override
  public BookListADTNode add(int index, Book book)
      throws IllegalArgumentException {
    if (index == 0) {
      return addFront(book);
    }
    throw new IllegalArgumentException("Invalid index to add an element");
  }

  @Override
  public BookListADTNode remove(Book b) {
    return this; // cannot remove from nothing!
  }

  @Override
  public Book get(int index) throws IllegalArgumentException {
    throw new IllegalArgumentException("Wrong index");
  }
}
