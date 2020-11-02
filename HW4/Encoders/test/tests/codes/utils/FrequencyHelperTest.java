package tests.codes.utils;

import org.junit.Test;

import java.util.Stack;

import codes.trees.CodeNode;
import codes.trees.PrefixCodeLeaf;
import codes.utils.Frequency;
import codes.utils.FrequencyHelper;

import static org.junit.Assert.assertEquals;

public class FrequencyHelperTest {
  @Test
  public void testToStack() {
    String sequence = "abcddeefg";
    String codes = "01";
    Stack<Frequency<CodeNode<String, String>>> stack =
            FrequencyHelper.toStack(sequence.split(""), c -> new PrefixCodeLeaf(c));
    assertEquals(7, stack.size());
    for (Frequency<CodeNode<String, String>> entity : stack) {
      if (entity.getValue().getSymbol().equals("d")
          || entity.getValue().getSymbol().equals("e")) {
        assertEquals(2, entity.getFrequency(), 0.0001);
      } else {
        assertEquals(1, entity.getFrequency(), 0.0001);
      }
    }
  }
}
