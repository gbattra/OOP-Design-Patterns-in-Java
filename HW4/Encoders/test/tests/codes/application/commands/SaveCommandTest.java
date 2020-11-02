package tests.codes.application.commands;

import org.junit.Test;

import codes.application.EncoderController;
import codes.application.commands.Command;
import codes.application.commands.SaveCommand;
import mocks.DummyController;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class SaveCommandTest {
  @Test
  public void testCommand() {
    try {
      StringBuffer out = new StringBuffer();
      String filepath = "filepath";
      StringBuilder log = new StringBuilder();
      EncoderController<String, String> controller = new DummyController(log);
      Command<EncoderController<String, String>> saveCmd = new SaveCommand(filepath, out);
      saveCmd.execute(controller);
      assertEquals("save-filepath", log.toString());
    } catch (Exception e) {
      fail("Valid command execution should not have failed.");
    }
  }
}
