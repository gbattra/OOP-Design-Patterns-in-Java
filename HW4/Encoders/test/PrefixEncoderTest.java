import org.junit.Test;

import codes.encoders.Encoder;
import codes.encoders.PrefixEncoder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class PrefixEncoderTest {
  private final String symbolSequence =
          "this is a symbol sequence with many chars and thus is a suitable sample.";

  @Test
  public void testValidConstructor() {
    try {
      Encoder<String, String> encoder = new PrefixEncoder("01", this.symbolSequence);
    } catch (Exception e) {
      fail("Valid constructor should not have failed.");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructorEmptyCodes() {
    Encoder<String, String> encoder = new PrefixEncoder("", this.symbolSequence);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructorEmptySequence() {
    Encoder<String, String> encoder = new PrefixEncoder("01", "");
  }

  @Test
  public void testEncodeDecode() {
    Encoder<String, String> encoder = new PrefixEncoder("01", this.symbolSequence);
    String encoding = encoder.encode(symbolSequence);
    assertEquals(symbolSequence, encoder.decode(encoding));
  }
}
