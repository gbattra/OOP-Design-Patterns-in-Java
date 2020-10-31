import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import static org.junit.Assert.fail;

public class PrefixEncoderControllerTest {
  @Test
  public void testConstructor() {
    String directory = System.getProperty("user.dir");
    String filepath = directory + File.separator + "test.txt";
    try(FileReader reader = new FileReader(filepath)) {

    } catch (Exception e) {
      fail(e.getMessage());
    }
    fail();
  }
}
