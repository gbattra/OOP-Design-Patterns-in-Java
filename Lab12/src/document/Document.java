package document;

import document.element.TextElementVisitor;
import java.util.ArrayList;
import java.util.List;

import document.element.TextElement;

public class Document {
  
  private List<TextElement> content;
  
  public Document() {
    content = new ArrayList<>();
  }

  public void add(TextElement e) {
    content.add(e);
  }

  public int countWords(TextElementVisitor<Integer> visitor) {
    int count = 0;
    for (TextElement element : this.content) {
      count += element.accept(visitor);
    }
    return count;
  }

  public String toText(TextElementVisitor<Void> visitor) {
    for (TextElement element : this.content) {
      element.accept(visitor);
    }
    return visitor.toString();
  }
}
