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

  /**
   * Sets the 'code' on the called instance node. Factory method which returns new node instance.
   *
   * @param code the code of the node
   * @return a new node instance with the provided code and string
   */
  CodeNode<K, S> setCode(K code);

  /**
   * Adds a node with specified symbol to the code tree. Uses the provided encoding as an 'address'
   * for the location of the new node.
   *
   * @param symbol the symbol to add
   * @param encoding the encoding address for the symbol
   * @return a new tree with the added symbol
   * @throws IllegalArgumentException if encoding or symbol is empty
   * @throws IllegalStateException if a node already exists at the encoding address
   */
  CodeNode<K, S> add(S symbol, K encoding) throws IllegalStateException, IllegalArgumentException;
}
