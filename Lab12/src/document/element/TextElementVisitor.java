package document.element;

public interface TextElementVisitor<R> {
  
  R visitBasicText(BasicText current);
  
  R visitBoldText(BoldText current);

  R visitHeading(Heading current);

  R visitHyperText(HyperText current);

  R visitItalicText(ItalicText current);

  R visitParagraph(Paragraph current);
}
