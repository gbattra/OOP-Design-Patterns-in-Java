package codes.encoders;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import codes.trees.CodeNode;
import codes.trees.CodeTree;
import codes.trees.PrefixCodeLeaf;
import codes.utils.FrequencyItem;
import codes.utils.PrefixCodeFrequencyItem;

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

  protected final CodeTree<String, String> frequencyTableToCodeTree(
          Map<String, Integer> freqTable,
          String codes) throws IllegalArgumentException {
    if (freqTable.isEmpty()) {
      throw new IllegalArgumentException("Cannot generate code tree. Frequency table empty.");
    }

    List<FrequencyItem<CodeNode<String, String>>> nodes = this.frequencyTableToNodeList(freqTable);

    return null;
  }

  private List<FrequencyItem<CodeNode<String, String>>> frequencyTableToNodeList(
          Map<String, Integer> freqTable) {
    List<FrequencyItem<CodeNode<String, String>>> nodes = new LinkedList<>();
    for (Map.Entry<String, Integer> entry : freqTable.entrySet()) {
      FrequencyItem<CodeNode<String, String>> item = new PrefixCodeFrequencyItem(
              entry.getValue(),
              new PrefixCodeLeaf(entry.getKey()));
      nodes.add(item);
    }
    nodes.sort(Comparable::compareTo);
    return nodes;
  }
}
