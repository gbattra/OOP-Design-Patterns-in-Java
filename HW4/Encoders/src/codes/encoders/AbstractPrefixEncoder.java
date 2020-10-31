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
import codes.utils.StringHelper;

public abstract class AbstractPrefixEncoder {
  protected final Map<String, Integer> symbolsToFrequencyTable(
          String symbols) throws IllegalArgumentException {
    if (symbols.isEmpty()) {
      throw new IllegalArgumentException("Cannot generate frequency table. Symbol string empty.");
    }

    Map<String, Integer> frequencyTable = new HashMap<>();
    for (char c : symbols.toCharArray()) {
      frequencyTable.merge(String.valueOf(c), 1, Integer::sum);
    }

    return frequencyTable;
  }

  private Stack<Frequency<CodeNode<String, String>>> frequencyTableToNodeStack(
          Map<String, Integer> freqTable) {
    Stack<Frequency<CodeNode<String, String>>> nodes = new Stack<>();
    for (Map.Entry<String, Integer> entry : freqTable.entrySet()) {
      Frequency<CodeNode<String, String>> item = new FrequencyEntry<>(
              entry.getValue(),
              new PrefixCodeLeaf(entry.getKey()));
      nodes.add(item);
    }
    nodes.sort(this::compareFrequencyEntries);

    return nodes;
  }

  protected final CodeTree<String, String> frequencyTableToCodeTree(
          Map<String, Integer> freqTable,
          String codes) throws IllegalArgumentException {
    if (freqTable.isEmpty()) {
      throw new IllegalArgumentException("Cannot generate code tree. Frequency table empty.");
    }
    if (codes == null || codes.isEmpty()) {
      throw new IllegalArgumentException("Cannot generate code tree. Empty code set.");
    }

    codes = StringHelper.distinctCharacters(codes);
    Stack<Frequency<CodeNode<String, String>>> nodes = this.frequencyTableToNodeStack(freqTable);

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
