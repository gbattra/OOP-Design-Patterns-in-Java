package codes.encoders;

import java.io.IOException;

import codes.encoders.Encoder;

/**
 * An encoder factory object. Used for dependency injection to allow for mocking classes in
 * unit tests.
 *
 * @param <K> the type of the codes
 * @param <S> the type of the symbols
 */
public interface EncoderFactory<K, S> {
  /**
   * Makes an encoder given a string of codes and a string of symbols.
   *
   * @param codes the codes for the encoder
   * @param symbols the symbols to encode
   * @return an encoder with type K codes and type S symbols
   * @throws IllegalArgumentException if args are empty
   */
  Encoder<K, S> make(K codes, S symbols) throws IllegalArgumentException;

  /**
   * Loads an encoder from a file at the specified filepath. Encoder extends FileLoadable, so
   * we know an Encoder object supports this functionality.
   *
   * @param filepath the location of the file
   * @return an encoder loaded from the data in the file
   * @throws IOException if an error occurs during the file read
   */
  Encoder<K, S> load(String filepath) throws IOException;
}
