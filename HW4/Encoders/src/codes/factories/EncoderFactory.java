package codes.factories;

import java.io.IOException;

import codes.encoders.Encoder;

public interface EncoderFactory<K, S> {
  Encoder<K, S> make(K codes, S symbols) throws IllegalArgumentException;
  Encoder<K, S> load(String filepath) throws IOException;
}
