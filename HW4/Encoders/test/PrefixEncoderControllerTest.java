import org.junit.Before;
import org.junit.Test;

import codes.application.EncoderController;
import codes.application.PrefixEncoderController;
import factories.DummyEncoderFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class PrefixEncoderControllerTest {
  private final String filename = "encoder.txt";
  private final String hexCodes = "0123456789ABCDEF";
  private final String symbolSequence =
          "this is a symbol sequence with many chars and thus is a suitable sample.";

  private DummyEncoderFactory factory;
  private StringBuilder log;

  @Before
  public void setup() {
    this.log = new StringBuilder();
    this.factory = new DummyEncoderFactory(this.log);
  }

  @Test
  public void testValidLoadEncoder() {
    try {
      EncoderController<String, String> controller = new PrefixEncoderController(this.factory);
      boolean success = controller.loadEncoder(this.filename);
      assertTrue(success);
      assertEquals(this.filename, this.log.toString());
    } catch (Exception e) {
      fail("Valid load should not have failed.");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidLoadEncoderEmptyPath() {
    EncoderController<String, String> controller = new PrefixEncoderController(this.factory);
    boolean success = controller.loadEncoder("");
    fail("Invalid loadEncoder() should have failed.");
  }

  @Test
  public void testNewEncoder() {
    try {
      EncoderController<String, String> controller = new PrefixEncoderController(this.factory);
      boolean success = controller.newEncoder(this.hexCodes, this.symbolSequence);
      assertTrue(success);
      assertEquals(this.hexCodes + this.symbolSequence, this.log.toString());
    } catch (Exception e) {
      fail("Valid newEncoder() should not have failed.");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidNewEncoder() {
    EncoderController<String, String> controller = new PrefixEncoderController(this.factory);
    boolean success = controller.newEncoder("", "");
    fail("Invalid newEncoder() should have failed.");
  }

  @Test
  public void testValidSaveEncoder() {
    try {
      EncoderController<String, String> controller = new PrefixEncoderController(this.factory);
      boolean success = controller.newEncoder(this.hexCodes, this.symbolSequence);
      assertTrue(success);
      assertEquals(this.hexCodes + this.symbolSequence, this.log.toString());

      success = controller.saveEncoder(this.filename);
      assertTrue(success);
      assertEquals(this.filename, this.log.toString());
    } catch (Exception e) {
      fail("Valid saveEncoder() should not have failed.");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSaveEncoderEmptyFilename() {
    EncoderController<String, String> controller = new PrefixEncoderController(this.factory);
    boolean success = controller.newEncoder(this.hexCodes, this.symbolSequence);
    assertTrue(success);
    assertEquals(this.hexCodes + this.symbolSequence, this.log.toString());

    success = controller.saveEncoder("");
    fail("Invalid saveEncoder() should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSaveEncoderNoEncoderLoaded() {
    EncoderController<String, String> controller = new PrefixEncoderController(this.factory);
    boolean success = controller.saveEncoder("");
    fail("Invalid saveEncoder() should have failed.");
  }

  @Test
  public void testValidEncode() {
    try {
      EncoderController<String, String> controller = new PrefixEncoderController(this.factory);
      boolean success = controller.newEncoder(this.hexCodes, this.symbolSequence);
      assertTrue(success);
      String encoding = controller.encode(this.symbolSequence);
      assertEquals(this.symbolSequence, encoding);
    } catch (Exception e) {
      fail("Valid encode() should not have failed.");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidEncodeEmptySeq() {
    EncoderController<String, String> controller = new PrefixEncoderController(this.factory);
    boolean success = controller.newEncoder(this.hexCodes, this.symbolSequence);
    assertTrue(success);
    controller.encode("");
    fail("Invalid encode() should have failed.");
  }

  @Test(expected = IllegalStateException.class)
  public void testInvalidEncodeNoEncoder() {
    EncoderController<String, String> controller = new PrefixEncoderController(this.factory);
    controller.encode(this.symbolSequence);
    fail("Invalid encode() should have failed.");
  }

  @Test
  public void testValidDecode() {
    try {
      EncoderController<String, String> controller = new PrefixEncoderController(this.factory);
      boolean success = controller.newEncoder(this.hexCodes, this.symbolSequence);
      assertTrue(success);
      assertEquals(this.hexCodes + this.symbolSequence, this.log.toString());

      String decoding = controller.decode(this.hexCodes);
      assertEquals(this.hexCodes, decoding);
    } catch (Exception e) {
      fail("Valid encode() should not have failed.");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidDecodeEmptySeq() {
    EncoderController<String, String> controller = new PrefixEncoderController(this.factory);
    boolean success = controller.newEncoder(this.hexCodes, this.symbolSequence);
    assertTrue(success);
    controller.decode("");
    fail("Invalid encode() should have failed.");
  }

  @Test(expected = IllegalStateException.class)
  public void testInvalidDecodeNoEncoder() {
    EncoderController<String, String> controller = new PrefixEncoderController(this.factory);
    controller.decode(this.symbolSequence);
    fail("Invalid encode() should have failed.");
  }
}
