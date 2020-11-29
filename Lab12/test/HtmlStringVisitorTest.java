import org.junit.Test;

import document.Document;
import document.HtmlStringVisitor;
import document.element.BasicText;
import document.element.BoldText;
import document.element.Heading;
import document.element.HyperText;
import document.element.ItalicText;
import document.element.Paragraph;
import document.element.TextElement;
import document.element.TextElementVisitor;

import static org.junit.Assert.assertEquals;

/**
 * Tests for a HtmlStringVisitor.
 */
public class HtmlStringVisitorTest {
  private Document document = new Document();
  private final String fourWordText = "This has four words.";
  private final TextElementVisitor<Void> sut = new HtmlStringVisitor();

  @Test
  public void testBasicText() {
    TextElement element = new BasicText(fourWordText);
    document.add(element);
    document.toText(sut);
    assertEquals(fourWordText, sut.toString());
  }

  @Test
  public void testBoldText() {
    TextElement element = new BoldText(fourWordText);
    document.add(element);
    document.toText(sut);
    assertEquals("<b>" + fourWordText + "</b>", sut.toString());
  }

  @Test
  public void testHeading() {
    TextElement element = new Heading(fourWordText, 1);
    document.add(element);
    document.toText(sut);
    assertEquals("<h1>" + fourWordText + "</h1>", sut.toString());
  }

  @Test
  public void testHyperText() {
    TextElement element = new HyperText(fourWordText, "www.website.com");
    document.add(element);
    document.toText(sut);
    assertEquals("<a href=\"www.website.com\">" + fourWordText + "</a>", sut.toString());
  }

  @Test
  public void testItalicText() {
    TextElement element = new ItalicText(fourWordText);
    document.add(element);
    document.toText(sut);
    assertEquals("<i>" + fourWordText + "</i>", sut.toString());
  }

  @Test
  public void testParagraph() {
    Paragraph paragraph = new Paragraph();
    paragraph.add(new BasicText(fourWordText));
    document.add(paragraph);
    document.toText(sut);
    assertEquals("<p>" + fourWordText + "\n</p>", sut.toString());
  }

  @Test
  public void testAll() {
    TextElement basic = new BasicText(fourWordText);
    TextElement bold = new BoldText(fourWordText);
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

    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(fourWordText + "\n");
    stringBuilder.append("<b>" + fourWordText + "</b>\n");
    stringBuilder.append("<h1>" + fourWordText + "</h1>\n");
    stringBuilder.append("<a href=\"www.website.com\">" + fourWordText + "</a>\n");
    stringBuilder.append("<i>" + fourWordText + "</i>\n");
    stringBuilder.append("<p>" + fourWordText + "\n</p>");

    document.toText(sut);
    assertEquals(stringBuilder.toString(), sut.toString());
  }
}
