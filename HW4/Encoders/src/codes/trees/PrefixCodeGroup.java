package codes.trees;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Concrete implementation of a group node in a code tree.
 */
public class PrefixCodeGroup
        extends AbstractCodeNode<String, String> implements CodeNode<String, String> {
  private final String code;
  private final List<CodeNode<String, String>> children;

  /**
   * Empty constructor for a group node. Typically used to start a new tree and serves as the root
   * of that new tree.
   */
  public PrefixCodeGroup() {
    this.code = "";
    this.children = new LinkedList<>();
  }

  /**
   * Constructor of a group node which takes the list of children of the node.
   *
   * @param children the children of the group node
   */
  public PrefixCodeGroup(List<CodeNode<String, String>> children) {
    this.code = "";
    this.children = children;
  }

  /**
   * Private constructor which sets the code and the children on the group node.
   *
   * @param code the code of the group node
   * @param children the group node children
   */
  private PrefixCodeGroup(
          String code,
          List<CodeNode<String, String>> children) {
    if (code == null || code.isEmpty()) {
      throw new IllegalArgumentException("Cannot instantiate group node: Code is empty.");
    }

    this.code = code;
    this.children = children;
  }

  @Override
  public String getCode() {
    return this.code;
  }

  @Override
  public String getSymbol() {
    return this.children.stream().map(CodeNode::getSymbol).collect(Collectors.joining(""));
  }

  @Override
  public CodeNode<String, String> setCode(String code) {
    return new PrefixCodeGroup(code, this.children);
  }

  @Override
  public CodeNode<String, String> add(String symbol, String encoding)
          throws IllegalStateException, IllegalArgumentException {
    if (symbol == null || symbol.isEmpty() || encoding == null || encoding.isEmpty()) {
      throw new IllegalArgumentException(
              "Call to add() invalid: Symbol and encoding must not be empty.");
    }
    if (this.getSymbol().equals(symbol)) {
      throw new IllegalStateException(String.format(
              "Call to add() invalid: A node with symbol %s already exists in the tree.", symbol));
    }

    char code = encoding.charAt(0);
    List<CodeNode<String, String>> children = new LinkedList<>(this.children);
    List<CodeNode<String, String>> filtered = children.stream()
            .filter(c -> c.getCode().equals(String.valueOf(code)))
            .collect(Collectors.toList());

    if (filtered.isEmpty()) {
      if (encoding.length() > 1) {
        CodeNode<String, String> group = new PrefixCodeGroup()
                .setCode(String.valueOf(code))
                .add(symbol, encoding.substring(1));
        children.add(group);
        return this.getCode().isEmpty()
                ? new PrefixCodeGroup(children) : new PrefixCodeGroup(this.code, children);
      }

      CodeNode<String, String> leaf = new PrefixCodeLeaf(symbol).setCode(String.valueOf(code));
      children.add(leaf);
      return this.getCode().isEmpty()
              ? new PrefixCodeGroup(children) : new PrefixCodeGroup(this.code, children);
    }

    CodeNode<String, String> child = filtered.get(0);
    int i = children.indexOf(child);
    children.set(i, child.add(symbol, encoding.substring(1)));
    return this.getCode().isEmpty()
            ? new PrefixCodeGroup(children) : new PrefixCodeGroup(this.code, children);
  }

  @Override
  public String decode(String encoding) throws IllegalArgumentException {
    if (encoding.isEmpty()) {
      return this.getSymbol();
    }

    char code = encoding.charAt(0);
    List<CodeNode<String, String>> filtered = this.children.stream()
            .filter(c -> c.getCode().equals(String.valueOf(code)))
            .collect(Collectors.toList());
    if (filtered.isEmpty()) {
      throw new IllegalArgumentException("No symbol found for provided encoding.");
    }

    return filtered.get(0).decode(encoding.substring(1));
  }

  @Override
  public String encode(String symbol) throws IllegalArgumentException {
    return this.encode(symbol, "");
  }

  @Override
  public String encode(String symbol, String encoding) throws IllegalArgumentException {
    for (CodeNode<String, String> child : this.children) {
      try {
        encoding = child.encode(symbol, encoding + this.getCode());
        return encoding;
      } catch (Exception ignored) {
        // do nothing
      }
    }

    throw new IllegalArgumentException("Provided symbol not found in code tree.");
  }

  @Override
  public String next(String sequence) throws IllegalArgumentException {
    if (sequence.isEmpty()) {
      throw new IllegalArgumentException("Sequence does not map to a symbol. Empty sequence.");
    }

    String code = sequence.substring(0, 1);
    List<CodeNode<String, String>> filtered = this.children.stream()
            .filter(c -> c.getCode().equals(code))
            .collect(Collectors.toList());
    if (filtered.isEmpty()) {
      throw new IllegalArgumentException("Sequence does not map to a symbol. No child found.");
    }

    CodeNode<String, String> child = filtered.get(0);
    return child.next(sequence.substring(1));
  }

  @Override
  public Map<String, String> toMap() {
    return this.toMap(new HashMap<>(), "");
  }

  @Override
  public Map<String, String> toMap(Map<String, String> map, String encoding) {
    for (CodeNode<String, String> child : this.children) {
      map.putAll(child.toMap(map, encoding + this.getCode()));
    }

    return map;
  }
}
