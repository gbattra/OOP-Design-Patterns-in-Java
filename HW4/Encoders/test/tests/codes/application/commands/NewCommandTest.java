package tests.codes.application.commands;

import org.junit.Test;

import codes.application.EncoderController;
import codes.application.commands.Command;
import codes.application.commands.NewCommand;
import mocks.DummyController;

import static org.junit.Assert.assertEquals;

public class NewCommandTest {
  @Test
  public void testCommand() {
    StringBuilder log = new StringBuilder();
    EncoderController<String, String> controller = new DummyController(log);
    Command<EncoderController<String, String>> newCmd = new NewCommand("codes", "symbols");
    newCmd.execute(controller);
    assertEquals("new-codes-symbols", log.toString());
  }
}
