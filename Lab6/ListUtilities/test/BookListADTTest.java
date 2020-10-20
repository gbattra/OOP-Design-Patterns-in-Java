import org.junit.Test;

import booklistadt.BookListADT;
import booklistadt.BookListADTImpl;
import bookutil.Book;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the list as an ADT.
 */
public class BookListADTTest {

  @Test
  public void testListADT() {
    BookListADT list = new BookListADTImpl();
    assertEquals("", list.toString());

    // add one at beginning
    list.addFront(new Book("HP 2", "J.K. Rowling", 1998, 10.99f));
    list.addFront(new Book("HP 1", "J.K. Rowling", 1997, 19.99f));

    assertEquals(
        "(Title: HP 1 Author: J.K. Rowling Year: 1997 Price: 19.99)"
            + "(Title: HP 2 Author: J.K. Rowling Year: 1998 Price: 10.99)",
        list.toString());

    // add to the end
    list.addBack(new Book("HP 3", "J.K. Rowling", 1999, 12.99f));
    list.addBack(new Book("HP 4", "J.K. Rowling", 2001, 15.99f));

    assertEquals(
        "(Title: HP 1 Author: J.K. Rowling Year: 1997 Price: 19.99)"
            + "(Title: HP 2 Author: J.K. Rowling Year: 1998 Price: 10.99)"
            + "(Title: HP 3 Author: J.K. Rowling Year: 1999 Price: 12.99)"
            + "(Title: HP 4 Author: J.K. Rowling Year: 2001 Price: 15.99)",
        list.toString());

    assertEquals("HP 1", list.get(0).getTitle());
    assertEquals("HP 2", list.get(1).getTitle());
    assertEquals("HP 3", list.get(2).getTitle());
    assertEquals("HP 4", list.get(3).getTitle());

    list.remove(new Book("HP 3", "J.K. Rowling", 1999, 12.99f));
    assertEquals(
        "(Title: HP 1 Author: J.K. Rowling Year: 1997 Price: 19.99)"
            + "(Title: HP 2 Author: J.K. Rowling Year: 1998 Price: 10.99)"
            + "(Title: HP 4 Author: J.K. Rowling Year: 2001 Price: 15.99)",
        list.toString());
  }
}