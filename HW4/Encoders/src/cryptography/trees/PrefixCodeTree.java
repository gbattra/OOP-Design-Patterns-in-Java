package cryptography.trees;

public class PrefixCodeTree implements CodeTree<String, String> {
  private final CodeNode<String, String> root;

  public PrefixCodeTree(CodeNode<String, String> root) {
    this.root = root;
  }

  @Override
  public String encode(String sequence) throws IllegalArgumentException {
    StringBuilder encoding = new StringBuilder();
    while (!sequence.isEmpty()) {
      encoding.append(this.root.encode(sequence.substring(0, 1)));
      sequence = sequence.substring(1);
    }

    return encoding.toString();
  }
}
