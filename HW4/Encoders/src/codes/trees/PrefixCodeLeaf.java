package codes.trees;

import java.util.HashMap;
import java.util.Map;

/**
 * Concrete implementation of a leaf node in a prefix code tree.
 */
public class PrefixCodeLeaf
        extends AbstractCodeNode<String, String> implements CodeNode<String, String> {
  private final String code;
  private final String symbol;

  /**
   * Constructor for the leaf node of a code tree.
   *
   * @param symbol the symbol held by this node.
   * @throws IllegalArgumentException if symbol is empty
   */
  public PrefixCodeLeaf(String symbol) throws IllegalArgumentException {
    if (symbol == null || symbol.isEmpty()) {
      throw new IllegalArgumentException("Symbol cannot be empty.");
    }

    this.symbol = symbol;
    this.code = "";
  }

  /**
   * Private constructor which takes the code of the node and the symbol associated with
   * this node.
   *
   * @param code the code of the node
   * @param symbol the symbol for this node
   */
  private PrefixCodeLeaf(String code, String symbol) {
    if (symbol == null || symbol.isEmpty()) {
      throw new IllegalArgumentException("Symbol cannot be empty.");
    }
    if (code == null || code.isEmpty()) {
      throw new IllegalArgumentException("Code cannot be empty.");
    }

    this.code = code;
    this.symbol = symbol;
  }

  @Override
  public String getCode() {
    return this.code;
  }

  @Override
  public String getSymbol() {
    return this.symbol;
  }

  @Override
  public CodeNode<String, String> setCode(String code) {
    return new PrefixCodeLeaf(code, this.symbol);
  }

  @Override
  public CodeNode<String, String> add(String symbol, String encoding)
          throws IllegalStateException, IllegalArgumentException {
    throw new IllegalStateException("Invalid call to add(): A symbol already exists at encoding.");
  }

  @Override
  public String decode(String encoding) throws IllegalArgumentException {
    if (!encoding.isEmpty()) {
      throw new IllegalArgumentException("No symbol found for provided encoding.");
    }

    return this.getSymbol();
  }

  @Override
  public String encode(String symbol) throws IllegalArgumentException {
    return this.encode(symbol, "");
  }

  @Override
  public String encode(String symbol, String encoding) throws IllegalArgumentException {
    if (!this.getSymbol().equals(symbol)) {
      throw new IllegalArgumentException("Provided symbol not present in code tree.");
    }

    return encoding + this.getCode();
  }

  @Override
  public String next(String sequence) throws IllegalArgumentException {
    return this.getSymbol();
  }

  @Override
  public Map<String, String> toMap() {
    return this.toMap(new HashMap<>(), "");
  }

  @Override
  public Map<String, String> toMap(Map<String, String> map, String encoding) {
    map.put(encoding + this.code, this.symbol);
    return map;
  }
}
