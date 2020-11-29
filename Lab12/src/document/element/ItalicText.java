package document.element;

/**
 * Representation for italic text.
 */
public class ItalicText extends BasicText {
  
  public ItalicText(String text) {
    super(text);
  }

  @Override
  public <R> R accept(TextElementVisitor<R> visitor) {
    return visitor.visitItalicText(this);
  }
}
