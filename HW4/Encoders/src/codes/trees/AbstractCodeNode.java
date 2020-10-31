package codes.trees;

public abstract class AbstractCodeNode<K, S> implements CodeNode<K, S> {
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj instanceof AbstractCodeNode) {
      try {
        AbstractCodeNode<K, S> node = (AbstractCodeNode<K, S>) obj;
        return node.getCode().equals(this.getCode()) && node.getSymbol().equals(this.getSymbol());
      } catch (Exception e) {
        return false;
      }
    }

    return false;
  }
}
