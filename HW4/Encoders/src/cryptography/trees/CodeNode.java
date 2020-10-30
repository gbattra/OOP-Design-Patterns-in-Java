package cryptography.trees;

/**
 * Interface for a node in a code tree. Has a code of type K and a symbol of type S.
 *
 * @param <K> the type of the code
 * @param <S> the type of the symbol
 */
public interface CodeNode<K, S> {
  /**
   * Getter for the node code.
   *
   * @return the code
   */
  K getCode();

  /**
   * Getter for the node symbol.
   *
   * @return the symbol
   */
  S getSymbol();

  CodeNode<K, S> set(String code, String symbol);
}
