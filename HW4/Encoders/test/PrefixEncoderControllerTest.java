import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import codes.application.EncoderController;
import codes.application.PrefixEncoderController;
import codes.encoders.Encoder;
import codes.encoders.PrefixEncoder;
import codes.trees.CodeNode;
import codes.trees.CodeTree;
import codes.trees.PrefixCodeGroup;
import codes.trees.PrefixCodeLeaf;
import codes.trees.PrefixCodeTree;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class PrefixEncoderControllerTest {
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
      String filename = "test.txt";
      this.encoder.save(filename);

      EncoderController<String, String> controller = new PrefixEncoderController();
      boolean success = controller.loadEncoder(filename);
      assertTrue(success);

      File file = new File(filename);
      success = file.delete();
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
    boolean success = controller.newEncoder(this.hexCodes, this.symbolSequence);
    fail("Invalid newEncoder() should have failed.");
  }
}
