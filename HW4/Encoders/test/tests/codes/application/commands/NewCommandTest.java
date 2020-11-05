package tests.codes.application.commands;

import org.junit.Test;

import codes.application.Controller;
import codes.application.commands.Command;
import codes.application.commands.NewCommand;
import mocks.DummyController;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Tests for the NewCommand.
 */
public class NewCommandTest {
  @Test
  public void testCommand() {
    try {
      StringBuffer out = new StringBuffer();
      StringBuilder log = new StringBuilder();
      Controller<String, String> controller = new DummyController(log);
      Command<Controller<String, String>> newCmd = new NewCommand("codes", "symbols", out);
      newCmd.execute(controller);
      assertEquals("new-codes-symbols", log.toString());
    } catch (Exception e) {
      fail("Valid command execution should not have failed.");
    }
  }
}
