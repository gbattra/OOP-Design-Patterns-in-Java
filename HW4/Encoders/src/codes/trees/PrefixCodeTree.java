package codes.trees;

import java.util.Map;

/**
 * A code tree implementation. Uses recursive operations to decode or encode strings.
 */
public class PrefixCodeTree implements CodeTree<String, String> {
  private final CodeNode<String, String> root;

  /**
   * Standard constructor for a prefix code tree. Takes the root of the tree as input.
   *
   * @param root the root of the code tree
   */
  public PrefixCodeTree(CodeNode<String, String> root) {
    this.root = root;
  }

  /**
   * Constructor for a PrefixCodeTree which builds itself from a map.
   *
   * @param map the map to build from
   * @throws IllegalArgumentException if map entries are invalid
   * @throws IllegalStateException if map has redundant values
   */
  public PrefixCodeTree(Map<String, String> map)
          throws IllegalStateException, IllegalArgumentException {
    CodeNode<String, String> root = new PrefixCodeGroup();
    for (Map.Entry<String, String> entry : map.entrySet()) {
      root = root.add(entry.getValue(), entry.getKey());
    }

    this.root = root;
  }

  @Override
  public String encode(String sequence) throws IllegalArgumentException {
    StringBuilder encoding = new StringBuilder();
    while (!sequence.isEmpty()) {
      encoding.append(this.root.encode(sequence.substring(0, 1)));
      sequence = sequence.substring(1);
    }

    return encoding.toString();
  }

  @Override
  public String decode(String sequence) throws IllegalArgumentException {
    StringBuilder decoding = new StringBuilder();
    while (!sequence.isEmpty()) {
      String next = this.root.next(sequence);
      decoding.append(next);

      String encoding = this.encode(next);
      sequence = sequence.substring(encoding.length());
    }

    return decoding.toString();
  }

  @Override
  public Map<String, String> toMap() {
    return this.root.toMap();
  }
}
