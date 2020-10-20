package booklistadt;

import bookutil.Book;

/**
 * This interface represents the List of books ADT.
 */
public interface BookListADT {
  /**
   * Add a book to the front of this list.
   * 
   * @param b the book to be added to the front of this list
   */
  void addFront(Book b);

  /**
   * Add a book to the back of this list (so it is the last book in the list.
   * 
   * @param b the book to be added to teh back of this list
   */
  void addBack(Book b);

  /**
   * Add a book to this list so that it occupies the provided index. Index
   * begins with 0
   * 
   * @param index the index to be occupied by this book, beginning at 0
   * @param b     the book to be added to the list
   */
  void add(int index, Book b);

  /**
   * Return the number of books currently in this list.
   * 
   * @return the size of the list
   */
  int getSize();

  /**
   * Remove the first instance of this book from this list.
   * 
   * @param b the book to be removed
   */
  void remove(Book b);

  /**
   * Get the (index)th book in this list.
   * 
   * @param index the index of the book to be returned
   * @return the book at the given index
   * @throws IllegalArgumentException if an invalid index is passed
   */
  Book get(int index) throws IllegalArgumentException;
}
