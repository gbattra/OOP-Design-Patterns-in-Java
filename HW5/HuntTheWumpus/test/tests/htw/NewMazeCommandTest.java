package tests.htw;

import org.junit.Test;

import java.util.Scanner;

import htw.game.IHtwGame;
import htw.game.commands.ICommand;
import htw.game.commands.NewMazeCommand;
import htw.level.HtwMaze;
import htw.level.IHtwMaze;
import htw.tools.HtwConfigurationBuilder;
import htw.tools.IHtwConfigurationBuilder;
import htw.tools.IHtwMazeBuilder;
import tests.htw.mocks.MockCustomMazeCommand;
import tests.htw.mocks.MockStandardMazeCommand;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class NewMazeCommandTest {
  @Test
  public void testCustom() {
    try {
      StringBuilder log = new StringBuilder();
      ICommand<IHtwConfigurationBuilder> customCmd = new MockCustomMazeCommand(log);
      ICommand<IHtwConfigurationBuilder> standardCmd = new MockStandardMazeCommand(log);
      ICommand<IHtwMazeBuilder> newMazeCmd = new NewMazeCommand(
              new Scanner("custom standard"), System.out, customCmd, standardCmd);
      newMazeCmd.execute(null);
      assertEquals("custom - execute", log.toString());
      log.setLength(0);
      newMazeCmd.execute(null);
      assertEquals("standard - execute", log.toString());
    } catch (Exception e) {
      fail("Valid execute() should not have failed.");
    }
  }
}
