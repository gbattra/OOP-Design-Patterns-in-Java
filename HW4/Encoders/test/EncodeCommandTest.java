import org.junit.Test;

import codes.application.EncoderController;
import codes.application.commands.Command;
import codes.application.commands.EncodeCommand;
import factories.DummyController;

import static org.junit.Assert.assertEquals;

public class EncodeCommandTest {
  @Test
  public void testCommand() {
    String sequence = "sequence";
    StringBuilder log = new StringBuilder();
    EncoderController<String, String> controller = new DummyController(log);
    Command<EncoderController<String, String>> encodeCmd = new EncodeCommand(sequence);
    encodeCmd.execute(controller);
    assertEquals("encode-sequence", log.toString());
  }
}
