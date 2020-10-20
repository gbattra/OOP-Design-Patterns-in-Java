package booklistadt;

import bookutil.Book;

/**
 * This interface represents all operations for a node in a list of books
 * implemented as an ADT. The advantage of the ADT design is that this interface
 * may now represent operations on a single node, instead of the IListOfBooks that represented
 * nodes but the operations were really on lists.
 */
public interface BookListADTNode {

  /**
   * Return the number of books in this list.
   * 
   * @return the size of this list
   */
  int count();

  /**
   * Add the given book to the front of this list and return this modified list.
   * 
   * @param b the book to be added
   * @return the head of the resulting list
   */
  BookListADTNode addFront(Book b);

  /**
   * Add the given book to the back of this list and return this modified list.
   * 
   * @param b the book to be added
   * @return the head of the resulting list
   */
  BookListADTNode addBack(Book b);

  /**
   * A method that adds the given book at the given index in this list . If
   * index is 0, it means this book should be added to the front of this list
   * 
   * @param index the position to be occupied by this book, starting at 0
   * @param b     the book to be added
   * @return the head of the resulting list
   * @throws IllegalArgumentException if an invalid index is passed
   */
  BookListADTNode add(int index, Book b) throws IllegalArgumentException;

  /**
   * Remove the first instance of this book from the list.
   * 
   * @param b the book to be removed
   * @return the head of the resulting list after the book has been removed
   */
  BookListADTNode remove(Book b);

  /**
   * Get the book at the specified index, with 0 meaning the first book in this
   * list.
   * 
   * @param index the specified index
   * @return the book at the specified index
   * @throws IllegalArgumentException if an invalid index is passed
   */
  Book get(int index) throws IllegalArgumentException;
}
