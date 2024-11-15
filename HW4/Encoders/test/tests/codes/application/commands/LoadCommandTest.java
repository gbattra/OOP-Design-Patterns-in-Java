package tests.codes.application.commands;

import org.junit.Test;

import codes.application.Controller;
import codes.application.commands.Command;
import codes.application.commands.LoadCommand;
import mocks.DummyController;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Tests for the LoadCommand.
 */
public class LoadCommandTest {
  @Test
  public void testCommand() {
    try {
      String filepath = "filepath";
      StringBuffer out = new StringBuffer();
      StringBuilder log = new StringBuilder();
      Controller<String, String> controller = new DummyController(log);
      Command<Controller<String, String>> loadCmd = new LoadCommand(filepath, out);
      loadCmd.execute(controller);
      assertEquals("load-filepath", log.toString());
    } catch (Exception e) {
      fail("Valid command execution should not have failed.");
    }
  }
}
