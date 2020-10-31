package codes.utils;

import codes.trees.CodeNode;

public class PrefixCodeFrequencyItem implements FrequencyItem<CodeNode<String, String>> {
  private final Integer frequency;
  private final CodeNode<String, String> node;

  public PrefixCodeFrequencyItem(Integer frequency, CodeNode<String, String> node) {
    this.frequency = frequency;
    this.node = node;
  }

  @Override
  public Integer getFrequency() {
    return this.frequency;
  }

  @Override
  public CodeNode<String, String> getNode() {
    return this.node;
  }

  @Override
  public int compareTo(FrequencyItem<CodeNode<String, String>> o) {
    if (o.getFrequency() > this.getFrequency()) {
      return 1;
    }
    if (o.getFrequency() < this.getFrequency()) {
      return -1;
    }
    return o.getNode().getSymbol().compareTo(this.getNode().getSymbol());
  }
}
