package codes.encoders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import codes.trees.CodeNode;
import codes.trees.CodeTree;
import codes.trees.PrefixCodeGroup;
import codes.trees.PrefixCodeLeaf;
import codes.trees.PrefixCodeTree;
import codes.utils.Frequency;
import codes.utils.FrequencyEntry;
import codes.utils.FrequencyHelper;
import codes.utils.StringHelper;

public abstract class AbstractPrefixEncoder {
  protected final CodeTree<String, String> symbolsToCodeTree(
          String sequence,
          String codes) throws IllegalArgumentException {
    if (sequence == null || sequence.isEmpty()) {
      throw new IllegalArgumentException("Cannot generate code tree. Sequence empty.");
    }
    if (codes == null || codes.isEmpty()) {
      throw new IllegalArgumentException("Cannot generate code tree. Empty code set.");
    }

    codes = StringHelper.distinctCharacters(codes);
    Stack<Frequency<CodeNode<String, String>>> nodes =
            FrequencyHelper.toStack(
                    sequence.split(""), (c) -> new PrefixCodeLeaf(c));
    nodes.sort(this::compareFrequencyEntries);

    while (nodes.size() > 1) {
      int frequency = 0;
      List<CodeNode<String, String>> children = new ArrayList<>();
      for (int i = 0; i < codes.length(); i++) {
        if (nodes.empty()) {
          break;
        }

        Frequency<CodeNode<String, String>> item = nodes.pop();
        frequency += item.getFrequency();
        children.add(item.getValue().setCode(String.valueOf(codes.charAt(i))));
      }
      nodes.add(new FrequencyEntry<>(frequency, new PrefixCodeGroup(children)));
      nodes.sort(this::compareFrequencyEntries);
    }

    return new PrefixCodeTree(nodes.get(0).getValue());
  }

  private int compareFrequencyEntries(
          Frequency<CodeNode<String, String>> n1,
          Frequency<CodeNode<String, String>> n2) {
    if (n1.getFrequency() > n2.getFrequency()) {
      return -1;
    }
    if (n1.getFrequency() < n2.getFrequency()) {
      return 1;
    }
    return n1.getValue().getSymbol().compareTo(n2.getValue().getSymbol());
  }
}
