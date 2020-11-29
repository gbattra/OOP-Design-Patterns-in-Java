package document;

import document.element.BasicText;
import document.element.BoldText;
import document.element.Heading;
import document.element.HyperText;
import document.element.ItalicText;
import document.element.Paragraph;
import document.element.TextElementVisitor;

public class BasicStringVisitor implements TextElementVisitor<Void> {
  private StringBuilder stringBuilder = new StringBuilder();

  @Override
  public Void visitBasicText(BasicText current) {
    stringBuilder.append(current.getText()).append(" ");
    return null;
  }

  @Override
  public Void visitBoldText(BoldText current) {
    stringBuilder.append(current.getText()).append(" ");
    return null;
  }

  @Override
  public Void visitHeading(Heading current) {
    stringBuilder.append(current.getText()).append(" ");
    return null;
  }

  @Override
  public Void visitHyperText(HyperText current) {
    stringBuilder.append(current.getText()).append(" ");
    return null;
  }

  @Override
  public Void visitItalicText(ItalicText current) {
    stringBuilder.append(current.getText()).append(" ");
    return null;
  }

  @Override
  public Void visitParagraph(Paragraph current) {
    stringBuilder.append(current.getText()).append(" ");
    return null;
  }

  @Override
  public String toString() {
    return this.stringBuilder.toString().trim();
  }
}
