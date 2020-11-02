package codes.encoders;

import java.io.IOException;
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

/**
 * Abstract encoder containing algorithm to build the encoding tree. This class implements the
 * Huffman encoding algorithm.
 */
public abstract class AbstractPrefixEncoder {
  protected final CodeTree<String, String> tree;

  public AbstractPrefixEncoder() {
    this.tree = new PrefixCodeTree(new PrefixCodeGroup());
  }

  protected AbstractPrefixEncoder(String codes, String symbols) {
    if (codes == null || codes.isEmpty() || symbols == null || symbols.isEmpty()) {
      throw new IllegalArgumentException("Codes and symbols cannot be empty.");
    }
    if (codes.length() < 2) {
      throw new IllegalArgumentException(
              "Insufficient number of codes provided. At least 2 codes required.");
    }

    this.tree = this.symbolsToCodeTree(symbols, codes);
  }

  protected AbstractPrefixEncoder(Map<String, String> map) {
    this.tree = new PrefixCodeTree(map);
  }

  protected AbstractPrefixEncoder(String contents) throws IOException {
    this.tree = this.codeTreeFromString(contents);
  }

  /**
   * Reads the contents into a map, which is used to instantiate a CodeTree.
   *
   * @param contents the path to the file
   * @return the code tree loaded from the file
   */
  protected final CodeTree<String, String> codeTreeFromString(String contents)
          throws IOException, IllegalArgumentException {
    String[] entries = contents.split("\n");
    Map<String, String> map = new HashMap<>();

    for (String entry : entries) {
      String[] elements = entry.split(",");
      if (elements.length != 2) {
        throw new IllegalArgumentException("Contents of file not formatted properly.");
      }
      map.put(elements[0], elements[1]);
    }

    return new PrefixCodeTree(map);
  }

  /**
   * Takes a String sequence of symbols and the code set to encode them with. Returns a code tree
   * of each symbol's encoding.
   *
   * @param sequence the sequence of symbols for which to build encodings.
   * @param codes the code set to use for encoding
   * @return the code tree of the symbols
   * @throws IllegalArgumentException if sequence or codes is empty.
   */
  protected final CodeTree<String, String> symbolsToCodeTree(
          String sequence,
          String codes) throws IllegalArgumentException {
    if (sequence == null || sequence.isEmpty() || codes == null || codes.isEmpty()) {
      throw new IllegalArgumentException("Cannot generate code tree. Empty sequence or code set.");
    }

    codes = StringHelper.distinctCharacters(codes);
    Stack<Frequency<CodeNode<String, String>>> nodes =
            FrequencyHelper.toStack(sequence.split(""), (c) -> new PrefixCodeLeaf(c));
    nodes.sort(this::compareFrequencies);

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
      nodes.sort(this::compareFrequencies);
    }

    return new PrefixCodeTree(nodes.get(0).getValue());
  }

  /**
   * Compare method for each frequency item. Must be one-off and not the frequency instance
   * compareTo() method as each frequency object is generic, so different comparing operations
   * would be needed depending on the type held by the frequency object.
   *
   * @param n1 the first node to compare
   * @param n2 the second node to compare
   * @return the ranking of the first node compared to the second node
   */
  private int compareFrequencies(
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

  @Override
  public String toString() {
    Map<String, String> map = this.tree.toMap();
    StringBuilder str = new StringBuilder();
    for (Map.Entry<String, String> entry : map.entrySet()) {
      str.append(String.format("%s,%s\n", entry.getKey(), entry.getValue()));
    }

    return str.toString();
  }

  @Override
  public int hashCode() {
    return this.toString().hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (obj instanceof PrefixEncoder) {
      PrefixEncoder other = (PrefixEncoder) obj;
      return other.toString().equals(this.toString());
    }

    return false;
  }
}
