package document;

import document.element.BasicText;
import document.element.BoldText;
import document.element.Heading;
import document.element.HyperText;
import document.element.ItalicText;
import document.element.Paragraph;
import document.element.TextElementVisitor;

/**
 * Concrete implementation of a HtmlStringVisitor.
 */
public class HtmlStringVisitor implements TextElementVisitor<Void> {
  private final StringBuilder stringBuilder = new StringBuilder();

  @Override
  public Void visitBasicText(BasicText current) {
    stringBuilder.append(current.getText()).append("\n");
    return null;
  }

  @Override
  public Void visitBoldText(BoldText current) {
    stringBuilder.append("<b>").append(current.getText()).append("</b>").append("\n");
    return null;
  }

  @Override
  public Void visitHeading(Heading current) {
    String startTag = String.format("<h%s>", current.getLevel());
    String endTag = String.format("</h%s>", current.getLevel());
    stringBuilder.append(startTag).append(current.getText()).append(endTag).append("\n");
    return null;
  }

  @Override
  public Void visitHyperText(HyperText current) {
    String tag = String.format("<a href=\"%s\">", current.getUrl());
    stringBuilder.append(tag).append(current.getText()).append("</a>").append("\n");
    return null;
  }

  @Override
  public Void visitItalicText(ItalicText current) {
    stringBuilder.append("<i>").append(current.getText()).append("</i>").append("\n");
    return null;
  }

  @Override
  public Void visitParagraph(Paragraph current) {
    stringBuilder.append("<p>").append(current.getText()).append("</p>").append("\n");
    return null;
  }

  @Override
  public String toString() {
    return this.stringBuilder.toString();
  }
}
