package cryptography.trees;

public class PrefixCodeLeaf implements CodeNode<String, String> {
  private final String code;
  private final String symbol;

  public PrefixCodeLeaf(String symbol) throws IllegalArgumentException {
    if (symbol == null || symbol.isEmpty()) {
      throw new IllegalArgumentException("Symbol cannot be empty.");
    }

    this.symbol = symbol;
    this.code = "";
  }

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
}
