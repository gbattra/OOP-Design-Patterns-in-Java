import org.junit.Test;

import codes.encoders.Encoder;
import codes.encoders.PrefixEncoder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class PrefixEncoderTest {
  private final String binaryCodes = "01";
  private final String hexCodes = "0123456789ABCDEF";
  private final String symbolSequence =
          "this is a symbol sequence with many chars and thus is a suitable sample.";

  @Test
  public void testValidConstructor() {
    try {
      Encoder<String, String> encoder = new PrefixEncoder(binaryCodes, this.symbolSequence);
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
    Encoder<String, String> encoder = new PrefixEncoder(binaryCodes, "");
  }

  @Test
  public void testEncodeDecodeBinary() {
    Encoder<String, String> encoder = new PrefixEncoder(binaryCodes, this.symbolSequence);
    String encoding = encoder.encode(symbolSequence);
    for (Character c : encoding.toCharArray()) {
      assertTrue(binaryCodes.contains(String.valueOf(c)));
    }
    assertEquals(symbolSequence, encoder.decode(encoding));
  }

  @Test
  public void testEncodeDecodeHex() {
    Encoder<String, String> encoder = new PrefixEncoder(hexCodes, this.symbolSequence);
    String encoding = encoder.encode(symbolSequence);
    for (Character c : encoding.toCharArray()) {
      assertTrue(hexCodes.contains(String.valueOf(c)));
    }
    assertEquals(symbolSequence, encoder.decode(encoding));
  }
}
