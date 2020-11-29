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

  /**
   * Accepts a visitor object.
   *
   * @param visitor the visitor to accept
   * @param <R> the return type of the function
   * @return instance of type R
   */
  <R> R accept(TextElementVisitor<R> visitor);
}
