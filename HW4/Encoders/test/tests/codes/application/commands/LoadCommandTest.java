package tests.codes.application.commands;

import org.junit.Test;

import codes.application.EncoderController;
import codes.application.commands.Command;
import codes.application.commands.LoadCommand;
import factories.DummyController;

import static org.junit.Assert.assertEquals;

public class LoadCommandTest {
  @Test
  public void testCommand() {
    String filepath = "filepath";
    StringBuilder log = new StringBuilder();
    EncoderController<String, String> controller = new DummyController(log);
    Command<EncoderController<String, String>> loadCmd = new LoadCommand(filepath);
    loadCmd.execute(controller);
    assertEquals("load-filepath", log.toString());
  }
}
