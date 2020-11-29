package document.element;

/**
 * Interface for a text element visitor.
 *
 * @param <R> the return type of the visitor
 */
public interface TextElementVisitor<R> {
  /**
   * Visitor for a BasicText element.
   *
   * @param current the text element
   * @return R type
   */
  R visitBasicText(BasicText current);

  /**
   * Visitor for a BoldText element.
   *
   * @param current the text element
   * @return R type
   */
  R visitBoldText(BoldText current);

  /**
   * Visitor for a Heading element.
   *
   * @param current the text element
   * @return R type
   */
  R visitHeading(Heading current);

  /**
   * Visitor for a HyperText element.
   *
   * @param current the text element
   * @return R type
   */
  R visitHyperText(HyperText current);

  /**
   * Visitor for a ItalicText element.
   *
   * @param current the text element
   * @return R type
   */
  R visitItalicText(ItalicText current);

  /**
   * Visitor for a Paragraph element.
   *
   * @param current the text element
   * @return R type
   */
  R visitParagraph(Paragraph current);
}
