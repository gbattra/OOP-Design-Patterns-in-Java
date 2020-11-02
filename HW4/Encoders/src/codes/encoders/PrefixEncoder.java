package codes.encoders;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class PrefixEncoder extends AbstractPrefixEncoder implements Encoder<String, String> {
  public PrefixEncoder() {
    super();
  }

  public PrefixEncoder(String codes, String symbols) throws IllegalArgumentException {
    super(codes, symbols);
  }

  public PrefixEncoder(Map<String, String> map) {
    super(map);
  }

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
  public boolean save(String filename) throws IOException {
    FileWriter writer = new FileWriter(filename);
    writer.write(this.toString());
    writer.close();
    return true;
  }
}
