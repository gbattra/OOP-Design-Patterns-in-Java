import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import cryptography.trees.CodeNode;
import cryptography.trees.PrefixCodeGroup;
import cryptography.trees.PrefixCodeLeaf;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class PrefixCodeGroupTest {
  private List<CodeNode<String, String>> children;

  @Before
  public void setup() {
    CodeNode<String, String> a = new PrefixCodeLeaf("A").setCode("0");
    CodeNode<String, String> b = new PrefixCodeLeaf("B").setCode("1");
    this.children = new ArrayList<>(Arrays.asList(a, b));
  }

  @Test
  public void testValidConstructor() {
    try {
      CodeNode<String, String> node = new PrefixCodeGroup(this.children);
    } catch (Exception e) {
      fail("Valid constructor should not have failed.");
    }
  }

  @Test
  public void testSetCode() {
    try {
      CodeNode<String, String> node = new PrefixCodeGroup(this.children).setCode("0");
    } catch (Exception e) {
      fail("Valid setCode() should not have failed");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetCode() {
    CodeNode<String, String> node = new PrefixCodeGroup(this.children).setCode("");
    fail("Invalid setCode() should have failed.");
  }
}
