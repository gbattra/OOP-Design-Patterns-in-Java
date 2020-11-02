package tests.codes.application.commands;

import org.junit.Test;

import codes.application.EncoderController;
import codes.application.commands.Command;
import codes.application.commands.DecodeCommand;
import mocks.DummyController;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class DecodeCommandTest {
  @Test
  public void testCommand() {
    try {
      String sequence = "00101001";
      StringBuffer out = new StringBuffer();
      StringBuilder log = new StringBuilder();
      EncoderController<String, String> controller = new DummyController(log);
      Command<EncoderController<String, String>> decodeCmd = new DecodeCommand(sequence, out);
      decodeCmd.execute(controller);
      assertEquals("decode-00101001", log.toString());
    } catch (Exception e) {
      fail("Valid command execution should not have failed.");
    }
  }
}
