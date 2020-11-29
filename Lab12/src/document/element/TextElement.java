package document.element;

/**
 * Interface that represents an element in our document.
 */
public interface TextElement {

  /**
   * Returns the text contained within the element.
   * 
   * @return the text
   */
  String getText();

  // accept method goes here...=
  <R> R accept(TextElementVisitor<R> visitor);
}
