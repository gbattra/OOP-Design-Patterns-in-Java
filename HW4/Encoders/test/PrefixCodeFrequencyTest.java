import org.junit.Before;
import org.junit.Test;

import codes.trees.CodeNode;
import codes.trees.PrefixCodeLeaf;
import codes.utils.CodeFrequency;
import codes.utils.PrefixCodeFrequency;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class PrefixCodeFrequencyTest {
  private CodeNode<String, String> nodeOne;
  private CodeNode<String, String> nodeTwo;

  @Before
  public void setup() {
    this.nodeOne = new PrefixCodeLeaf("A").setCode("1");
    this.nodeTwo = new PrefixCodeLeaf("B").setCode("0");
  }

  @Test
  public void testConstructor() {
    try {
      CodeFrequency<CodeNode<String, String>> freq = new PrefixCodeFrequency(10, this.nodeOne);
    } catch (Exception e) {
      fail("Valid constructor should not have failed.");
    }
  }

  @Test
  public void testGetters() {
    CodeFrequency<CodeNode<String, String>> freq = new PrefixCodeFrequency(10, this.nodeOne);
    assertEquals(10, freq.getFrequency(), 0.0001);
    assertEquals(this.nodeOne, freq.getNode());
  }

  @Test
  public void testCompareTo() {
    CodeFrequency<CodeNode<String, String>> one = new PrefixCodeFrequency(10, this.nodeOne);
    CodeFrequency<CodeNode<String, String>> two = new PrefixCodeFrequency(5, this.nodeTwo);
    CodeFrequency<CodeNode<String, String>> three = new PrefixCodeFrequency(5, this.nodeOne);
    CodeFrequency<CodeNode<String, String>> four = new PrefixCodeFrequency(5, this.nodeOne);
    assertEquals(1, one.compareTo(two));
    assertEquals(-1, two.compareTo(one));

    assertEquals(-1, two.compareTo(three));
    assertEquals(1, three.compareTo(two));

    assertEquals(0, three.compareTo(four));
  }
}
