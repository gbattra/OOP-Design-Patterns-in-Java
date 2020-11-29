import org.junit.Test;

import document.BasicStringVisitor;
import document.Document;
import document.element.BasicText;
import document.element.BoldText;
import document.element.Heading;
import document.element.HyperText;
import document.element.ItalicText;
import document.element.Paragraph;
import document.element.TextElement;

import static org.junit.Assert.assertEquals;

/**
 * Tests for a BasicStringVisitor.
 */
public class BasicStringVisitorTest {
  private Document document = new Document();
  private final String fourWordText = "This has four words.";
  private final BasicStringVisitor sut = new BasicStringVisitor();

  @Test
  public void testBasicText() {
    TextElement element = new BasicText(fourWordText);
    document.add(element);
    assertEquals(fourWordText, document.toText(sut));
  }

  @Test
  public void testBoldText() {
    TextElement element = new BoldText(fourWordText);
    document.add(element);
    assertEquals(fourWordText, document.toText(sut));
  }

  @Test
  public void testHeading() {
    TextElement element = new Heading(fourWordText, 1);
    document.add(element);
    assertEquals(fourWordText, document.toText(sut));
  }

  @Test
  public void testHyperText() {
    TextElement element = new HyperText(fourWordText, "www.website.com");
    document.add(element);
    assertEquals(fourWordText, document.toText(sut));
  }

  @Test
  public void testItalicText() {
    TextElement element = new ItalicText(fourWordText);
    document.add(element);
    assertEquals(fourWordText, document.toText(sut));
  }

  @Test
  public void testParagraph() {
    Paragraph paragraph = new Paragraph();
    paragraph.add(new BasicText(fourWordText));
    document.add(paragraph);
    assertEquals(fourWordText, document.toText(sut));
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

    assertEquals((fourWordText + " ").repeat(6).trim(), document.toText(sut));
  }
}
