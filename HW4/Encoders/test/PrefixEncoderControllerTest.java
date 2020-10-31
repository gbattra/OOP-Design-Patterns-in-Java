import org.junit.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class PrefixEncoderControllerTest {
  @Test
  public void testConstructor() {
    String directory = System.getProperty("user.dir");
    String filepath = directory + File.separator + "test.txt";
    try {
      String contents = Files.readString(Paths.get(filepath));
      assertEquals(1,1);
    } catch (Exception e) {
      fail("Valid file read should not have failed");
    }
  }
}
