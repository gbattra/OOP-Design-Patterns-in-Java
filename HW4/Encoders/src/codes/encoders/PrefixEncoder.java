package codes.encoders;

import java.util.Map;

import codes.trees.CodeTree;

public class PrefixEncoder extends AbstractPrefixEncoder implements Encoder<String, String> {
  private final CodeTree<String, String> tree;

  public PrefixEncoder(String codes, String symbols) throws IllegalArgumentException {
    if (codes == null || codes.isEmpty() || symbols == null || symbols.isEmpty()) {
      throw new IllegalArgumentException("Codes and symbols cannot be empty.");
    }

    Map<String, Integer> freqTable = this.symbolsToFrequencyTable(symbols);
    this.tree = this.frequencyTableToCodeTree(freqTable, codes);
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
