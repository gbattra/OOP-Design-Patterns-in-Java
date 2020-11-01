import org.junit.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.fail;

public class PrefixEncoderControllerTest {
  @Test
  public void testConstructor() {
    String directory = System.getProperty("user.dir");
    String filepath = directory + File.separator + "test.txt";
    try {
      Map<String, String> map = new HashMap<>();
      String contents = Files.readString(Paths.get(filepath));
      String[] entries = contents.split("\n");
      for (String entry : entries) {
        String[] elements = entry.split(",");
        map.put(elements[0], elements[1]);
      }

    } catch (Exception e) {
      fail("Valid file read should not have failed");
    }
  }
}
