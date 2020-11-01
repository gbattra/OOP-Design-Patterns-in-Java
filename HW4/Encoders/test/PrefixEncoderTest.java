import org.junit.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

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

  @Test
  public void testConstructorFromMap() {
    try {
      Map<String, String> map = new HashMap<>();
      map.put("00", "A");
      map.put("01", "B");
      map.put("101", "C");
      Encoder<String, String> encoder = new PrefixEncoder(map);
      assertEquals("00", encoder.encode("A"));
      assertEquals("01", encoder.encode("B"));
      assertEquals("101", encoder.encode("C"));
    } catch (Exception e) {
      fail("Valid constructor with map should not have failed.");
    }
  }

  @Test
  public void testSave() {
    Map<String, String> map = new HashMap<>();
    map.put("00", "A");
    map.put("01", "B");
    map.put("101", "C");
    Encoder<String, String> encoder = new PrefixEncoder(map);
    try {
      String filename = "test.txt";
      encoder.save(filename);
      File file = new File(filename);
      boolean success = file.delete();
      assertTrue(success);
    } catch (Exception e) {
      fail("Valid save() should not have failed.");
    }
  }

  @Test
  public void testToString() {
    Map<String, String> map = new HashMap<>();
    map.put("00", "A");
    map.put("01", "B");
    map.put("101", "C");
    Encoder<String, String> encoder = new PrefixEncoder(map);
    String str = "00,A\n01,B\n101,C\n";
    assertEquals(str, encoder.toString());
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
