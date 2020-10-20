package booklistadt;

import bookutil.Book;

/**
 * This class implements the List of Books ADT.
 */
public class BookListADTImpl implements BookListADT {
  private BookListADTNode head;

  /**
   * Constructor for an empty list.
   */
  public BookListADTImpl() {
    head = new BookListADTEmptyNode();
  }

  @Override
  public void addFront(Book b) {
    head = head.addFront(b);
  }

  @Override
  public void addBack(Book b) {
    head = head.addBack(b);
  }

  @Override
  public void add(int index, Book b) {
    head = head.add(index, b);
  }

  @Override
  public int getSize() {
    return head.count();
  }

  @Override
  public void remove(Book b) {
    head = head.remove(b);
  }

  @Override
  public Book get(int index) throws IllegalArgumentException {
    if ((index >= 0) && (index < getSize())) {
      return head.get(index);
    } else {
      throw new IllegalArgumentException("Invalid index");
    }

  }

  @Override
  public String toString() {
    return head.toString();
  }
}
