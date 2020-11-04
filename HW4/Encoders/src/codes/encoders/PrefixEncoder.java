package codes.encoders;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import codes.trees.CodeNode;
import codes.trees.CodeTree;
import codes.trees.PrefixCodeGroup;
import codes.trees.PrefixCodeLeaf;
import codes.trees.PrefixCodeTree;
import codes.utils.Frequency;
import codes.utils.FrequencyEntry;
import codes.utils.FrequencyHelper;
import codes.utils.StringHelper;

/**
 * Concrete implementation of a PrefixEncoder.
 */
public class PrefixEncoder implements Encoder<String, String> {
  protected final CodeTree<String, String> tree;
  /**
   * Empty constructor for the encoder. Sets tree to empty tree.
   */
  public PrefixEncoder() {
    this.tree = new PrefixCodeTree(new PrefixCodeGroup());
  }

  /**
   * Standard constructor for a PrefixEncoder. Takes the codes to use and the symbols to encode.
   *
   * @param codes the codes to use
   * @param symbols the symbols to encode
   */
  public PrefixEncoder(String codes, String symbols) throws IllegalArgumentException {
    if (codes == null || codes.isEmpty() || symbols == null || symbols.isEmpty()) {
      throw new IllegalArgumentException("Codes and symbols cannot be empty.");
    }
    if (codes.length() < 2) {
      throw new IllegalArgumentException(
              "Insufficient number of codes provided. At least 2 codes required.");
    }

    this.tree = this.symbolsToCodeTree(symbols, codes);
  }

  /**
   * Constructor which takes a map of the code tree and builds the tree from that map.
   *
   * @param map the map of the code tree.
   * @throws IllegalArgumentException if map contains invalid entries
   * @throws IllegalStateException if map contains duplicate entries
   */
  public PrefixEncoder(Map<String, String> map)
          throws IllegalArgumentException, IllegalStateException {
    this.tree = new PrefixCodeTree(map);
  }

  /**
   * Constructs the encoder from a string representation of the code tree.
   *
   * @param contents the string representation of the code tree
   * @throws IllegalArgumentException if an invalid string is provided
   */
  private PrefixEncoder(String contents) throws IllegalArgumentException {
    this.tree = this.stringToCodeTree(contents);
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

  @Override
  public String toString() {
    Map<String, String> map = this.tree.toMap();
    StringBuilder str = new StringBuilder();
    for (Map.Entry<String, String> entry : map.entrySet()) {
      str.append(String.format("%s,%s\n", entry.getKey(), entry.getValue()));
    }

    return str.toString();
  }

  @Override
  public int hashCode() {
    return this.toString().hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (obj instanceof PrefixEncoder) {
      PrefixEncoder other = (PrefixEncoder) obj;
      return other.toString().equals(this.toString());
    }

    return false;
  }

  /**
   * Reads the contents into a map, which is used to instantiate a CodeTree.
   *
   * @param contents the path to the file
   * @return the code tree loaded from the file
   */
  private CodeTree<String, String> stringToCodeTree(String contents)
          throws IllegalArgumentException {
    String[] entries = contents.split("\n");
    Map<String, String> map = new HashMap<>();

    for (String entry : entries) {
      String[] elements = entry.split(",");
      if (elements.length != 2) {
        throw new IllegalArgumentException("Contents of file not formatted properly.");
      }
      map.put(elements[0], elements[1]);
    }

    return new PrefixCodeTree(map);
  }

  /**
   * Takes a String sequence of symbols and the code set to encode them with. Returns a code tree
   * of each symbol's encoding.
   *
   * @param symbols the sequence of symbols for which to build encodings.
   * @param codes the code set to use for encoding
   * @return the code tree of the symbols
   * @throws IllegalArgumentException if sequence or codes is empty.
   */
  private CodeTree<String, String> symbolsToCodeTree(
          String symbols,
          String codes) throws IllegalArgumentException {
    if (symbols == null || symbols.isEmpty() || codes == null || codes.isEmpty()) {
      throw new IllegalArgumentException("Cannot generate code tree. Empty sequence or code set.");
    }

    codes = StringHelper.distinctCharacters(codes);
    Stack<Frequency<CodeNode<String, String>>> nodes =
            FrequencyHelper.toStack(symbols.split(""), (c) -> new PrefixCodeLeaf(c));
    nodes.sort(this::compareFrequencies);

    while (nodes.size() > 1) {
      int frequency = 0;
      List<CodeNode<String, String>> children = new ArrayList<>();
      for (int i = 0; i < codes.length(); i++) {
        if (nodes.empty()) {
          break;
        }

        Frequency<CodeNode<String, String>> item = nodes.pop();
        frequency += item.getFrequency();
        children.add(item.getValue().setCode(String.valueOf(codes.charAt(i))));
      }

      nodes.add(new FrequencyEntry<>(frequency, new PrefixCodeGroup(children)));
      nodes.sort(this::compareFrequencies);
    }

    return new PrefixCodeTree(nodes.get(0).getValue());
  }

  /**
   * Compare method for each frequency item. Must be one-off and not the frequency instance
   * compareTo() method as each frequency object is generic, so different comparing operations
   * would be needed depending on the type held by the frequency object.
   *
   * @param n1 the first node to compare
   * @param n2 the second node to compare
   * @return the ranking of the first node compared to the second node
   */
  private int compareFrequencies(
          Frequency<CodeNode<String, String>> n1,
          Frequency<CodeNode<String, String>> n2) {
    if (n1.getFrequency() > n2.getFrequency()) {
      return -1;
    }
    if (n1.getFrequency() < n2.getFrequency()) {
      return 1;
    }
    return n1.getValue().getSymbol().compareTo(n2.getValue().getSymbol());
  }
}
