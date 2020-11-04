package tests.codes.encoders;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import codes.encoders.Encoder;
import codes.encoders.PrefixEncoder;
import codes.encoders.EncoderFactory;
import codes.encoders.PrefixEncoderFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Tests for the PrefixEncoderFactory.
 */
public class PrefixEncoderFactoryTest {
  private final String filename = "encoder.txt";
  private final String hexCodes = "0123456789ABCDEF";
  private final String symbolSequence =
          "this is a symbol sequence with many chars and thus is a suitable sample.";

  private EncoderFactory<String, String> factory;

  @Before
  public void setup() {
    this.factory = new PrefixEncoderFactory();
  }

  @Test
  public void testMake() {
    Encoder<String, String> expected = new PrefixEncoder(this.hexCodes, this.symbolSequence);
    Encoder<String, String> encoder = this.factory.make(this.hexCodes, this.symbolSequence);
    assertEquals(expected.encode(this.symbolSequence), encoder.encode(this.symbolSequence));
  }

  @Test
  public void testLoad() {
    Encoder<String, String> expected = new PrefixEncoder(this.hexCodes, this.symbolSequence);
    try {
      expected.save(this.filename);
      Encoder<String, String> encoder = this.factory.load(this.filename);
      assertEquals(expected.encode(this.symbolSequence), encoder.encode(this.symbolSequence));
    } catch (Exception ignored) {
      fail("Valid load() should not have failed.");
    }
  }

  @After
  public void teardown() {
    try {
      File file = new File(this.filename);
      boolean success = file.delete();
    } catch (Exception ignored) {
    }
  }
}
