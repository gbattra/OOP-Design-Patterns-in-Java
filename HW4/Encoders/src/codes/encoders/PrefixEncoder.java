package codes.encoders;

import java.util.Map;

import codes.trees.CodeTree;
import codes.trees.PrefixCodeTree;

public class PrefixEncoder extends AbstractPrefixEncoder implements Encoder<String, String> {
  private final CodeTree<String, String> tree;

  public PrefixEncoder(String codes, String symbols) throws IllegalArgumentException {
    if (codes == null || codes.isEmpty() || symbols == null || symbols.isEmpty()) {
      throw new IllegalArgumentException("Codes and symbols cannot be empty.");
    }
    if (codes.length() < 2) {
      throw new IllegalArgumentException(
              "Insufficient number of codes provided. At least 2 codes required.");
    }

    this.tree = this.symbolsToCodeTree(symbols, codes);
  }

  public PrefixEncoder(Map<String, String> map) {
    this.tree = new PrefixCodeTree(map);
  }

  @Override
  public String encode(String sequence) throws IllegalArgumentException {
    return this.tree.encode(sequence);
  }

  @Override
  public String decode(String sequence) throws IllegalArgumentException {
    return this.tree.decode(sequence);
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
}
