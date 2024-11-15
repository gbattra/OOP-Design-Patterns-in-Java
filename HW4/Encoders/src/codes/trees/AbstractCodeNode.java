package codes.trees;

/**
 * Abstract representation of a node in a code tree.
 *
 * @param <K> the type of the codes
 * @param <S> the type of the symbols
 */
public abstract class AbstractCodeNode<K, S> implements CodeNode<K, S> {
  @Override
  public int hashCode() {
    return this.toString().hashCode();
  }

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
