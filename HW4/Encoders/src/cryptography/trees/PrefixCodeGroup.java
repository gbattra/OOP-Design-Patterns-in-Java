package cryptography.trees;

import java.util.List;
import java.util.stream.Collectors;

import cryptography.trees.CodeNode;

public class PrefixCodeGroup implements CodeNode<String, String> {
  private final String code;

  private List<CodeNode<String, String>> children;

  public PrefixCodeGroup(List<CodeNode<String, String>> children)
          throws IllegalArgumentException {
    this.code = "";
    this.children = children;
  }

  private PrefixCodeGroup(
          String code,
          List<CodeNode<String, String>> children) {
    if (code == null || code.isEmpty()) {
      throw new IllegalArgumentException("Code cannot be empty.");
    }

    this.code = code;
    this.children = children;
  }

  @Override
  public String getCode() {
    return this.code;
  }

  @Override
  public String getSymbol() {
    return this.children.stream().map(CodeNode::getSymbol).collect(Collectors.joining(""));
  }

  @Override
  public CodeNode<String, String> setCode(String code) {
    return new PrefixCodeGroup(code, this.children);
  }
}
