import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import listadt.ListADT;
import listadt.ListADTImpl;

/**
 * Tests the listadt.ListADT implementation using a list of strings.
 */
public class ListADTTest {

  private ListADT<String> stringList;

  @Before
  public void setup() {

    stringList = new ListADTImpl<String>();
  }

  @Test
  public void testStringList() {
    stringList.addFront("won");
    stringList.addFront("Patriots");
    stringList.addBack("Super");
    stringList.addBack("Bowl");
    stringList.add(2, "the");
    assertEquals("(Patriots won the Super Bowl)", stringList.toString());
    assertEquals(5, stringList.getSize());
    assertEquals("Super", stringList.get(3));

    stringList.remove("Patriots");
    stringList.addFront("Falcons");
    stringList.add(1, "did");
    stringList.add(2, "not");
    stringList.remove("won");
    stringList.add(3, "win");
    assertEquals("(Falcons did not win the Super Bowl)", stringList.toString());
    assertEquals(7, stringList.getSize());

  }

  @Test
  public void testMap() {
    // convert the list of strings above to a list that contains the length of
    // each word in the list
    String sentence = "The quick brown fox jumps over the lazy dog";
    String[] words = sentence.split("\\s+");
    for (String w : words) {
      stringList.addBack(w);
    }

    ListADT<Integer> wordLengths = stringList.map(s -> s.length());
    assertEquals("The mapped list's length does not match the original list!",
        stringList.getSize(), wordLengths.getSize());

    for (int i = 0; i < words.length; i++) {
      assertEquals(words[i].length(), (int) wordLengths.get(i));
    }
  }

  @Test
  public void testFilter() {
    stringList.add(0, "one");
    stringList.add(1, "two");
    stringList.add(2, "three");
    stringList.add(3, "four");
    stringList.add(4, "five");
    stringList.add(5, "six");

    ListADT<String> filtered = stringList.filter(s -> s.length() == 3);
    assertEquals(filtered.get(0), "one");
    assertEquals(filtered.get(1), "two");
    assertEquals(filtered.get(2), "six");
  }

  @Test
  public void testFold() {
    stringList.add(0, "one");
    stringList.add(1, "two");
    stringList.add(2, "three");
    stringList.add(3, "four");
    stringList.add(4, "five");
    stringList.add(5, "six");

    String description = stringList.fold("", (s1, s2) -> String.format("%s, %s", s1, s2));
    assertEquals(", one, two, three, four, five, six", description);
  }

}