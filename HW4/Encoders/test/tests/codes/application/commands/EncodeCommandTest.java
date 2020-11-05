package tests.codes.application.commands;

import org.junit.Test;

import codes.application.Controller;
import codes.application.commands.Command;
import codes.application.commands.EncodeCommand;
import mocks.DummyController;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Tests for the EncodeCommand.
 */
public class EncodeCommandTest {
  @Test
  public void testCommand() {
    try {
      String sequence = "sequence";
      StringBuffer out = new StringBuffer();
      StringBuilder log = new StringBuilder();
      Controller<String, String> controller = new DummyController(log);
      Command<Controller<String, String>> encodeCmd = new EncodeCommand(sequence, out);
      encodeCmd.execute(controller);
      assertEquals("encode-sequence", log.toString());
    } catch (Exception e) {
      fail("Valid command execution should not have failed.");
    }
  }
}
