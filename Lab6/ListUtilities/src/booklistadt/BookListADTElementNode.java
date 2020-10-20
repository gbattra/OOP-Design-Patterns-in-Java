package booklistadt;

import bookutil.Book;

/**
 * This class represents an element node in the list adt implementation.
 */
public class BookListADTElementNode implements BookListADTNode {
  private Book book;
  private BookListADTNode rest;

  /**
   * Constructor.
   * 
   * @param p    the book at this node
   * @param rest the rest of the list
   */
  public BookListADTElementNode(Book p, BookListADTNode rest) {
    this.book = p;
    this.rest = rest;
  }

  @Override
  public int count() {
    return 1 + this.rest.count();
  }

  @Override
  public BookListADTNode addFront(Book book) {
    return new BookListADTElementNode(book, this);
  }

  @Override
  public BookListADTNode addBack(Book book) {
    this.rest = this.rest.addBack(book);
    return this;
  }

  @Override
  public BookListADTNode add(int index, Book book) {
    if (index == 0) {
      return addFront(book);
    } else {
      this.rest = this.rest.add(index - 1, book);
      return this;
    }
  }

  @Override
  public BookListADTNode remove(Book book) {
    if (this.book.equals(book)) {
      return this.rest;
    } else {
      this.rest = this.rest.remove(book);
      return this;
    }
  }

  @Override
  public Book get(int index) throws IllegalArgumentException {
    if (index == 0) {
      return this.book;
    }
    return this.rest.get(index - 1);
  }

  @Override
  public String toString() {
    return "(" + this.book.toString() + ")" + this.rest.toString();
  }
}
