package document;

import document.element.TextElementVisitor;
import java.util.ArrayList;
import java.util.List;

import document.element.TextElement;

/**
 * Concrete implementation of a Document.
 */
public class Document {
  private List<TextElement> content;

  /**
   * Document constructor.
   */
  public Document() {
    content = new ArrayList<>();
  }

  /**
   * Adds an element.
   *
   * @param e the element to add
   */
  public void add(TextElement e) {
    content.add(e);
  }

  /**
   * Counts the words in the doc.
   *
   * @param visitor the visitor doing the counting
   * @return the number of words in the doc
   */
  public int countWords(TextElementVisitor<Integer> visitor) {
    int count = 0;
    for (TextElement element : this.content) {
      count += element.accept(visitor);
    }
    return count;
  }

  /**
   * Converts the doc to a formatted string.
   *
   * @param visitor the visitor doing the formatting
   * @return the formatted string
   */
  public String toText(TextElementVisitor<Void> visitor) {
    for (TextElement element : this.content) {
      element.accept(visitor);
    }
    return visitor.toString();
  }
}
