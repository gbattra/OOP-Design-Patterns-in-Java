package tests.codes.application.commands;

import org.junit.Test;

import codes.application.EncoderController;
import codes.application.commands.Command;
import codes.application.commands.DecodeCommand;
import factories.DummyController;

import static org.junit.Assert.assertEquals;

public class DecodeCommandTest {
  @Test
  public void testCommand() {
    String sequence = "00101001";
    StringBuilder log = new StringBuilder();
    EncoderController<String, String> controller = new DummyController(log);
    Command<EncoderController<String, String>> decodeCmd = new DecodeCommand(sequence);
    decodeCmd.execute(controller);
    assertEquals("decode-00101001", log.toString());
  }
}
