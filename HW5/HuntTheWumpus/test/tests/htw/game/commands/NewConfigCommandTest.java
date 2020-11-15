package tests.htw.game.commands;

import org.junit.Test;

import java.util.Scanner;

import htw.game.commands.ICommand;
import htw.game.commands.NewConfigCommand;
import htw.tools.IHtwConfigurationBuilder;
import htw.tools.IHtwMazeBuilder;
import tests.htw.mocks.MockCustomConfigCommand;
import tests.htw.mocks.MockStandardConfigCommand;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class NewConfigCommandTest {
  @Test
  public void testCustom() {
    try {
      StringBuilder log = new StringBuilder();
      ICommand<IHtwConfigurationBuilder> customCmd = new MockCustomConfigCommand(log);
      ICommand<IHtwConfigurationBuilder> standardCmd = new MockStandardConfigCommand(log);
      ICommand<IHtwConfigurationBuilder> newConfigCommand = new NewConfigCommand(
              new Scanner("custom standard"), System.out, customCmd, standardCmd);
      newConfigCommand.execute(null);
      assertEquals("custom - execute", log.toString());
      log.setLength(0);
      newConfigCommand.execute(null);
      assertEquals("standard - execute", log.toString());
    } catch (Exception e) {
      fail("Valid execute() should not have failed.");
    }
  }
}
