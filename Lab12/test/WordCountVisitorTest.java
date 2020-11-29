import org.junit.Test;

import document.Document;
import document.WordCountVisitor;
import document.element.BasicText;
import document.element.Heading;
import document.element.HyperText;
import document.element.ItalicText;
import document.element.Paragraph;
import document.element.TextElement;

import static org.junit.Assert.assertEquals;

public class WordCountVisitorTest {
  private Document document = new Document();
  private final String fourWordText = "This has four words.";

  @Test
  public void testBasicText() {
    TextElement basic = new BasicText(fourWordText);
    document.add(basic);
    assertEquals(4, document.countWords(new WordCountVisitor()));
  }

  @Test
  public void testBoldText() {
    TextElement bold = new BasicText(fourWordText);
    document.add(bold);
    assertEquals(4, document.countWords(new WordCountVisitor()));
  }

  @Test
  public void testHeading() {
    TextElement heading = new Heading(fourWordText, 1);
    document.add(heading);
    assertEquals(4, document.countWords(new WordCountVisitor()));
  }

  @Test
  public void testHyperText() {
    TextElement hyper = new HyperText(fourWordText, "www.website.com");
    document.add(hyper);
    assertEquals(4, document.countWords(new WordCountVisitor()));
  }

  @Test
  public void testItalicText() {
    TextElement italic = new ItalicText(fourWordText);
    document.add(italic);
    assertEquals(4, document.countWords(new WordCountVisitor()));
  }

  @Test
  public void testParagraph() {
    Paragraph paragraph = new Paragraph();
    paragraph.add(new BasicText(fourWordText));
    document.add(paragraph);
    assertEquals(4, document.countWords(new WordCountVisitor()));
  }

  @Test
  public void testAll() {

  }
}
