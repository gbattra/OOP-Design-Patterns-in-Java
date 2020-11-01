package codes.encoders;

import codes.utils.Savable;

/**
 * An encoder encodes symbols of type S into encodings of type K.
 *
 * @param <K> the type of the codes
 * @param <S> the type of the symbols
 */
public interface Encoder<K, S> extends Savable {
  /**
   * Takes a sequence of symbols and encodes it.
   *
   * @param sequence the sequence to encode
   * @return the encoded sequence
   * @throws IllegalArgumentException if sequence contains symbols not known to the encoder
   */
  K encode(S sequence) throws IllegalArgumentException;

  /**
   * Takes a sequence of encodings and decodes it into the corresponding symbols.
   *
   * @param sequence the sequence of encodings to decode
   * @return the decoded sequence
   * @throws IllegalArgumentException if sequence contains invalid encodings
   */
  S decode(K sequence) throws IllegalArgumentException;
}
