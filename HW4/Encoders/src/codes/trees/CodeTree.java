package codes.trees;

/**
 * Interface for a code tree. Maps codes (of type K) to symbols (of type S).
 *
 * @param <K> the type of the codes
 * @param <S> the type of the symbols
 */
public interface CodeTree<K, S> {
  /**
   * Encodes a sequence of symbols into a sequence of encodings.
   *
   * @param sequence the sequence to encode
   * @return the encoded sequence
   * @throws IllegalArgumentException if sequence does not map to symbols
   */
  K encode(S sequence) throws IllegalArgumentException;

  /**
   * Decodes a sequence of encodings into their corresponding symbols.
   *
   * @param sequence the sequence to decode
   * @return the decoded sequence of symbols
   * @throws IllegalArgumentException if the provided sequence does not map to any symbols
   */
  S decode(K sequence) throws IllegalArgumentException;
}
