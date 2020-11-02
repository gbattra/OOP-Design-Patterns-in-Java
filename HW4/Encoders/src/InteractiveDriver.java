import java.io.InputStreamReader;

import codes.application.EncoderClient;
import codes.application.EncoderController;
import codes.application.PrefixEncoderController;
import codes.factories.EncoderFactory;
import codes.factories.PrefixEncoderFactory;

/**
 * Driver class which runs an encoder client. Takes user inputs and runs commands via an
 * encoder controller.
 */
public class InteractiveDriver {
  /**
   * The entry point to the driver. Sets up a client and controller, then runs the client.
   *
   * @param args optional args for the program (unused)
   */
  public static void main(String[] args) {
    EncoderFactory<String, String> factory = new PrefixEncoderFactory();
    EncoderController<String, String> controller = new PrefixEncoderController(factory);
    Readable reader = new InputStreamReader(System.in);
    EncoderClient client = new EncoderClient(controller, reader, System.out);
    client.run();
  }
}
