import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import codes.application.EncoderController;
import codes.application.PrefixEncoderController;
import codes.encoders.Encoder;
import codes.encoders.PrefixEncoder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class PrefixEncoderControllerTest {
  private final String filename = "encoder";
  private final String hexCodes = "0123456789ABCDEF";
  private final String symbolSequence =
          "this is a symbol sequence with many chars and thus is a suitable sample.";

  private Encoder<String, String> encoder;

  @Before
  public void setup() {
    this.encoder = new PrefixEncoder(hexCodes, symbolSequence);
  }

  @Test
  public void testValidLoadEncoder() {
    try {
      this.encoder.save(this.filename);

      EncoderController<String, String> controller = new PrefixEncoderController();
      boolean success = controller.loadEncoder(this.filename);
      assertTrue(success);
    } catch (Exception e) {
      fail("Valid load should not have failed.");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidLoadEncoderEmptyPath() {
    EncoderController<String, String> controller = new PrefixEncoderController();
    boolean success = controller.loadEncoder("");
    fail("Invalid loadEncoder() should have failed.");
  }

  @Test
  public void testInvalidLoadEncoderNoFile() {
    try {
      EncoderController<String, String> controller = new PrefixEncoderController();
      boolean success = controller.loadEncoder("file-that-does-not-exist.txt");
      assertFalse(success);
    } catch (Exception e) {
      fail(
              "Although the file doesn't exist, this should be handled internally" +
              "and a 'false' boolean should still be returned.");
    }
  }

  @Test
  public void testNewEncoder() {
    try {
      EncoderController<String, String> controller = new PrefixEncoderController();
      boolean success = controller.newEncoder(this.hexCodes, this.symbolSequence);
      assertTrue(success);
    } catch (Exception e) {
      fail("Valid newEncoder() should not have failed.");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidNewEncoder() {
    EncoderController<String, String> controller = new PrefixEncoderController();
    boolean success = controller.newEncoder("", "");
    fail("Invalid newEncoder() should have failed.");
  }

  @Test
  public void testValidSaveEncoder() {
    try {
      EncoderController<String, String> controller = new PrefixEncoderController();
      boolean success = controller.newEncoder(this.hexCodes, this.symbolSequence);
      assertTrue(success);

      success = controller.saveEncoder(this.filename);
      assertTrue(success);
    } catch (Exception e) {
      fail("Valid saveEncoder() should not have failed.");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSaveEncoderEmptyFilename() {
    EncoderController<String, String> controller = new PrefixEncoderController();
    boolean success = controller.newEncoder(this.hexCodes, this.symbolSequence);
    assertTrue(success);

    success = controller.saveEncoder("");
    fail("Invalid saveEncoder() should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSaveEncoderNoEncoderLoaded() {
    EncoderController<String, String> controller = new PrefixEncoderController();
    boolean success = controller.saveEncoder("");
    fail("Invalid saveEncoder() should have failed.");
  }

  @Test
  public void testValidEncode() {
    try {
      EncoderController<String, String> controller = new PrefixEncoderController();
      boolean success = controller.newEncoder(this.hexCodes, this.symbolSequence);
      assertTrue(success);
      controller.encode(this.symbolSequence);
    } catch (Exception e) {
      fail("Valid encode() should not have failed.");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidEncodeEmptySeq() {
    EncoderController<String, String> controller = new PrefixEncoderController();
    boolean success = controller.newEncoder(this.hexCodes, this.symbolSequence);
    assertTrue(success);
    controller.encode("");
    fail("Invalid encode() should have failed.");
  }

  @Test(expected = IllegalStateException.class)
  public void testInvalidEncodeNoEncoder() {
    EncoderController<String, String> controller = new PrefixEncoderController();
    controller.encode(this.symbolSequence);
    fail("Invalid encode() should have failed.");
  }

  @Test
  public void testValidDecode() {
    try {
      EncoderController<String, String> controller = new PrefixEncoderController();
      boolean success = controller.newEncoder(this.hexCodes, this.symbolSequence);
      assertTrue(success);

      String encoding = controller.encode(this.symbolSequence);
      String decoding = controller.decode(encoding);
      assertEquals(this.symbolSequence, decoding);
    } catch (Exception e) {
      fail("Valid encode() should not have failed.");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidDecodeEmptySeq() {
    EncoderController<String, String> controller = new PrefixEncoderController();
    boolean success = controller.newEncoder(this.hexCodes, this.symbolSequence);
    assertTrue(success);
    controller.decode("");
    fail("Invalid encode() should have failed.");
  }

  @Test(expected = IllegalStateException.class)
  public void testInvalidDecodeNoEncoder() {
    EncoderController<String, String> controller = new PrefixEncoderController();
    controller.decode(this.symbolSequence);
    fail("Invalid encode() should have failed.");
  }

  @After
  public void tearDown() {
    try {
      File file = new File(this.filename + ".txt");
      boolean success = file.delete();
    } catch (Exception ignored) {
    }
  }
}
