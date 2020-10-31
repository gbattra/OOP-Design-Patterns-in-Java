import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import codes.trees.CodeNode;
import codes.trees.PrefixCodeGroup;
import codes.trees.PrefixCodeLeaf;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Tests for the prefix code group node.
 */
public class PrefixCodeGroupTest {
  private List<CodeNode<String, String>> leafChildren;
  private List<CodeNode<String, String>> groupChildren;

  @Before
  public void setup() {
    CodeNode<String, String> a = new PrefixCodeLeaf("A").setCode("0");
    CodeNode<String, String> b = new PrefixCodeLeaf("B").setCode("1");
    this.leafChildren = new ArrayList<>(Arrays.asList(a, b));

    CodeNode<String, String> group = new PrefixCodeGroup(this.leafChildren).setCode("0");
    this.groupChildren = new ArrayList<>(Collections.singletonList(group));
  }

  @Test
  public void testValidConstructor() {
    try {
      CodeNode<String, String> node = new PrefixCodeGroup(this.leafChildren);
    } catch (Exception e) {
      fail("Valid constructor should not have failed.");
    }
  }

  @Test
  public void testSetCode() {
    try {
      CodeNode<String, String> node = new PrefixCodeGroup(this.leafChildren).setCode("0");
    } catch (Exception e) {
      fail("Valid setCode() should not have failed");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetCode() {
    CodeNode<String, String> node = new PrefixCodeGroup(this.leafChildren).setCode("");
    fail("Invalid setCode() should have failed.");
  }

  @Test
  public void testGetters() {
    CodeNode<String, String> node = new PrefixCodeGroup(this.leafChildren).setCode("0");
    assertEquals("AB", node.getSymbol());
    assertEquals("0", node.getCode());
  }

  @Test
  public void testValidAdd() {
    try {
      CodeNode<String, String> node = new PrefixCodeGroup(this.leafChildren);
      node = node.add("C", "2");
      assertEquals("ABC", node.getSymbol());
    } catch (Exception e) {
      fail("Valid add() should not have failed.");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidAddEmptySymbol() {
    CodeNode<String, String> node = new PrefixCodeGroup(this.leafChildren);
    node.add("", "2");
    fail("Invalid add() should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidAddEmptyEncoding() {
    CodeNode<String, String> node = new PrefixCodeGroup(this.leafChildren);
    node.add("A", "");
    fail("Invalid add() should have failed.");
  }

  @Test(expected = IllegalStateException.class)
  public void testInvalidAddExistingNode() {
    CodeNode<String, String> node = new PrefixCodeGroup(this.leafChildren);
    node.add("A", "0");
    fail("Invalid add() should have failed.");
  }

  @Test
  public void testMultilayerAdd() {
    try {
      CodeNode<String, String> node = new PrefixCodeGroup(this.groupChildren);
      node = node.add("C", "101");
      assertEquals("ABC", node.getSymbol());
    } catch (Exception e) {
      fail("Valid add() should not have failed.");
    }
  }

  @Test
  public void testDecode() {
    CodeNode<String, String> node = new PrefixCodeGroup(this.groupChildren);
    node = node.add("C", "101");
    assertEquals("C", node.decode("101"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidDecode() {
    CodeNode<String, String> node = new PrefixCodeGroup(this.groupChildren);
    node.decode("101");
    fail("Invalid decode should have failed.");
  }

  @Test
  public void testValidEncode() {
    try {
      CodeNode<String, String> node = new PrefixCodeGroup(this.groupChildren);
      node = node.add("C", "101");
      assertEquals("101", node.encode("C"));
    } catch (Exception e) {
      fail("Valid encode() should not have failed.");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidEncode() {
    CodeNode<String, String> node = new PrefixCodeGroup(this.groupChildren);
    node.encode("C");
    fail("Invalid encode() should have failed.");
  }

  @Test
  public void testValidNext() {
    try {
      CodeNode<String, String> node = new PrefixCodeGroup(this.groupChildren);
      node = node.add("C", "101");
      assertEquals("C", node.next("101100"));
    } catch (Exception e) {
      fail("Valid encode() should not have failed.");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidNextNoMatch() {
    CodeNode<String, String> node = new PrefixCodeGroup(this.groupChildren);
    node = node.add("C", "101");
    node.next("");
    fail("Invalid next() should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidNextNoChild() {
    CodeNode<String, String> node = new PrefixCodeGroup(this.groupChildren);
    node = node.add("C", "101");
    node.next("10010");
    fail("Invalid next() should have failed.");
  }
}
