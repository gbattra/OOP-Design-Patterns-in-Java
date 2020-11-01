package codes.application;

/**
 * Interface for an encoder controller. Can load or save an encoder, encode or decode a sequence.
 *
 * @param <K> the type of the codes
 * @param <S> the type of the symbols
 */
public interface EncoderController<K, S> {
  /**
   * Loads an encoder from the specified file.
   *
   * @param filepath the path to the file of the encoder
   * @return was the load successful
   */
  boolean loadEncoder(String filepath) throws IllegalArgumentException;

  /**
   * Creates a new encoder instance for the controller to work with.
   *
   * @param codes the codes to use for encoding
   * @param symbols the symbol set to encode
   * @return was the operation successful
   */
  boolean newEncoder(K codes, S symbols) throws IllegalArgumentException;

  /**
   * Saves the encoder in use to the specified filepath.
   *
   * @param filename the filepath to save the encoder.
   * @return was the save successful
   */
  boolean saveEncoder(String filename) throws IllegalArgumentException, IllegalStateException;

  /**
   * Encodes the provided sequence using the loaded encoder.
   *
   * @param sequence the sequence to encode
   * @return the encoding of the sequence
   */
  K encode(S sequence) throws IllegalArgumentException, IllegalStateException;

  /**
   * Decodes the provided encoding into the corresponding symbol sequence.
   *
   * @param sequence the encoding to decode
   * @return the decoded sequence
   */
  S decode(K sequence) throws IllegalArgumentException, IllegalStateException;
}
