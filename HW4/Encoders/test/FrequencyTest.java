import org.junit.Test;

import codes.utils.Frequency;
import codes.utils.FrequencyEntry;

import static org.junit.Assert.assertEquals;

public class FrequencyTest {
  @Test
  public void testConstructor() {
    Frequency<String> freq = new FrequencyEntry<>(10, "T");
    assertEquals(10, freq.getFrequency(), 0.0001);
    assertEquals("T", freq.getValue());
  }
}
