package tests.codes.trees;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import codes.trees.CodeNode;
import codes.trees.CodeTree;
import codes.trees.PrefixCodeGroup;
import codes.trees.PrefixCodeLeaf;
import codes.trees.PrefixCodeTree;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Tests for the prefix code tree.
 */
public class PrefixCodeTreeTest {
  private CodeNode<String, String> root;

  @Before
  public void setup() {
    CodeNode<String, String> a = new PrefixCodeLeaf("A").setCode("0");
    CodeNode<String, String> b = new PrefixCodeLeaf("B").setCode("1");
    List<CodeNode<String, String>> children = new ArrayList<>(Arrays.asList(a, b));

    CodeNode<String, String> group = new PrefixCodeGroup(children).setCode("0");
    List<CodeNode<String, String>> grouping = new ArrayList<>(Collections.singletonList(group));

    this.root = new PrefixCodeGroup(grouping);
    this.root = root.add("C", "101");
  }

  @Test
  public void testConstructor() {
    try {
      CodeTree<String, String> tree = new PrefixCodeTree(this.root);
    } catch (Exception e) {
      fail("Valid constructor should not have failed.");
    }
  }

  @Test
  public void testConstructorFromMap() {
    try {
      Map<String, String> map = new HashMap<>();
      map.put("00", "A");
      map.put("01", "B");
      map.put("101", "C");
      CodeTree<String, String> tree = new PrefixCodeTree(map);
      assertEquals("00", tree.encode("A"));
      assertEquals("01", tree.encode("B"));
      assertEquals("101", tree.encode("C"));
    } catch (Exception e) {
      fail("Valid constructor with map should not have failed.");
    }
  }

  @Test
  public void testValidEncode() {
    try {
      CodeTree<String, String> tree = new PrefixCodeTree(this.root);
      String encoding = tree.encode("ABC");
      assertEquals("0001101", encoding);
    } catch (Exception e) {
      fail("Valid encode() should not have failed.");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidEncode() {
    CodeTree<String, String> tree = new PrefixCodeTree(this.root);
    tree.encode("ABCD");
    fail("Invalid encode() should have failed.");
  }

  @Test
  public void testValidDecode() {
    try {
      CodeTree<String, String> tree = new PrefixCodeTree(this.root);
      String decoding = tree.decode("0001101");
      assertEquals("ABC", decoding);
    } catch (Exception e) {
      fail("Valid decode() should not have failed.");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidDecode() {
    CodeTree<String, String> tree = new PrefixCodeTree(this.root);
    String decoding = tree.decode("01001101");
    fail("Invalid decode() should have failed.");
  }

  @Test
  public void testToMap() {
    CodeTree<String, String> tree = new PrefixCodeTree(this.root);
    Map<String, String> map = new HashMap<>();
    map.put("00", "A");
    map.put("01", "B");
    map.put("101", "C");
    assertEquals(map, tree.toMap());
  }
}
