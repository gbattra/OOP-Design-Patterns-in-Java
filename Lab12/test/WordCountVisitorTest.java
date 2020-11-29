import org.junit.Test;

import document.Document;
import document.WordCountVisitor;
import document.element.BasicText;
import document.element.Heading;
import document.element.HyperText;
import document.element.ItalicText;
import document.element.Paragraph;
import document.element.TextElement;
import document.element.TextElementVisitor;

import static org.junit.Assert.assertEquals;

public class WordCountVisitorTest {
  private Document document = new Document();
  private final String fourWordText = "This has four words.";
  private final TextElementVisitor<Integer> sut = new WordCountVisitor();

  @Test
  public void testBasicText() {
    TextElement basic = new BasicText(fourWordText);
    document.add(basic);
    assertEquals(4, document.countWords(sut));
  }

  @Test
  public void testBoldText() {
    TextElement bold = new BasicText(fourWordText);
    document.add(bold);
    assertEquals(4, document.countWords(sut));
  }

  @Test
  public void testHeading() {
    TextElement heading = new Heading(fourWordText, 1);
    document.add(heading);
    assertEquals(4, document.countWords(sut));
  }

  @Test
  public void testHyperText() {
    TextElement hyper = new HyperText(fourWordText, "www.website.com");
    document.add(hyper);
    assertEquals(4, document.countWords(sut));
  }

  @Test
  public void testItalicText() {
    TextElement italic = new ItalicText(fourWordText);
    document.add(italic);
    assertEquals(4, document.countWords(sut));
  }

  @Test
  public void testParagraph() {
    Paragraph paragraph = new Paragraph();
    paragraph.add(new BasicText(fourWordText));
    document.add(paragraph);
    assertEquals(4, document.countWords(sut));
  }

  @Test
  public void testAll() {
    TextElement basic = new BasicText(fourWordText);
    TextElement bold = new BasicText(fourWordText);
    TextElement heading = new Heading(fourWordText, 1);
    TextElement hyper = new HyperText(fourWordText, "www.website.com");
    TextElement italic = new ItalicText(fourWordText);
    Paragraph paragraph = new Paragraph();
    paragraph.add(new BasicText(fourWordText));

    document.add(basic);
    document.add(bold);
    document.add(heading);
    document.add(hyper);
    document.add(italic);
    document.add(paragraph);

    assertEquals(4 * 6, document.countWords(sut));
  }
}
