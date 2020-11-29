package document;

import document.element.BasicText;
import document.element.BoldText;
import document.element.Heading;
import document.element.HyperText;
import document.element.ItalicText;
import document.element.Paragraph;
import document.element.TextElementVisitor;

/**
 * Concrete implementation of a MarkdownStringVisitor.
 */
public class MarkdownStringVisitor implements TextElementVisitor<Void> {
  private final StringBuilder stringBuilder = new StringBuilder();

  @Override
  public Void visitBasicText(BasicText current) {
    stringBuilder.append(current.getText()).append("\n");
    return null;
  }

  @Override
  public Void visitBoldText(BoldText current) {
    stringBuilder.append("**").append(current.getText()).append("**").append("\n");
    return null;
  }

  @Override
  public Void visitHeading(Heading current) {
    stringBuilder.append("#".repeat(current.getLevel())).append(" ")
                                                        .append(current.getText()).append("\n");
    return null;
  }

  @Override
  public Void visitHyperText(HyperText current) {
    String title = "[" + current.getText() + "]";
    String url = "(" + current.getUrl() + ")";
    stringBuilder.append(title).append(url).append("\n");
    return null;
  }

  @Override
  public Void visitItalicText(ItalicText current) {
    stringBuilder.append("*").append(current.getText()).append("*").append("\n");
    return null;
  }

  @Override
  public Void visitParagraph(Paragraph current) {
    stringBuilder.append(current.getText()).append("\n");
    return null;
  }

  @Override
  public String toString() {
    return stringBuilder.toString();
  }
}
