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

    Map<String, Integer> freqTable = this.symbolsToFrequencyTable(symbols);
    this.tree = this.frequencyTableToCodeTree(freqTable, codes);
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
}
