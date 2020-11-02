package tests.codes.application.commands;

import org.junit.Test;

import codes.application.EncoderController;
import codes.application.commands.Command;
import codes.application.commands.SaveCommand;
import mocks.DummyController;

import static org.junit.Assert.assertEquals;

public class SaveCommandTest {
  @Test
  public void testCommand() {
    String filepath = "filepath";
    StringBuilder log = new StringBuilder();
    EncoderController<String, String> controller = new DummyController(log);
    Command<EncoderController<String, String>> saveCmd = new SaveCommand(filepath);
    saveCmd.execute(controller);
    assertEquals("save-filepath", log.toString());
  }
}
