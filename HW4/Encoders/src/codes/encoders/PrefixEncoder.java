package codes.encoders;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

/**
 * Concrete implementation of a PrefixEncoder.
 */
public class PrefixEncoder extends AbstractPrefixEncoder implements Encoder<String, String> {
  /**
   * Empty constructor for the encoder. Sets tree to empty tree.
   */
  public PrefixEncoder() {
    super();
  }

  /**
   * Standard constructor for a PrefixEncoder. Takes the codes to use and the symbols to encode.
   *
   * @param codes the codes to use
   * @param symbols the symbols to encode
   */
  public PrefixEncoder(String codes, String symbols) throws IllegalArgumentException {
    super(codes, symbols);
  }

  /**
   * Constructor which takes a map of the code tree and builds the tree from that map.
   *
   * @param map the map of the code tree.
   * @throws IllegalArgumentException if map contains invalid entries
   * @throws IllegalStateException if map contains duplicate entries
   */
  public PrefixEncoder(Map<String, String> map) {
    super(map);
  }

  /**
   * Constructs the encoder from a string representation of the code tree.
   *
   * @param contents the string representation of the code tree
   * @throws IllegalArgumentException if an invalid string is provided
   */
  private PrefixEncoder(String contents) throws IOException {
    super(contents);
  }

  @Override
  public String encode(String sequence) throws IllegalArgumentException {
    return this.tree.encode(sequence);
  }

  @Override
  public String decode(String sequence) throws IllegalArgumentException {
    return this.tree.decode(sequence);
  }

  @Override
  public Encoder<String, String> load(String filepath) throws IOException {
    String contents = Files.readString(Paths.get(filepath));
    return new PrefixEncoder(contents);
  }

  @Override
  public boolean save(String filename) throws IllegalArgumentException, IOException {
    if (filename == null || filename.isEmpty()) {
      throw new IllegalArgumentException("Filename cannot be empty.");
    }
    FileWriter writer = new FileWriter(filename);
    writer.write(this.toString());
    writer.close();
    return true;
  }
}
