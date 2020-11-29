package document;

import document.element.BasicText;
import document.element.BoldText;
import document.element.Heading;
import document.element.HyperText;
import document.element.ItalicText;
import document.element.Paragraph;
import document.element.TextElementVisitor;

public class WordCountVisitor implements TextElementVisitor<Integer> {
  @Override
  public Integer visitBasicText(BasicText current) {
    return current.getText().split(" ").length;
  }

  @Override
  public Integer visitBoldText(BoldText current) {
    return current.getText().split(" ").length;
  }

  @Override
  public Integer visitHeading(Heading current) {
    return current.getText().split(" ").length;
  }

  @Override
  public Integer visitHyperText(HyperText current) {
    return current.getText().split(" ").length;
  }

  @Override
  public Integer visitItalicText(ItalicText current) {
    return current.getText().split(" ").length;
  }

  @Override
  public Integer visitParagraph(Paragraph current) {
    return current.getText().split(" ").length;
  }
}
