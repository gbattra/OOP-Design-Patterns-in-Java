package tests.htw;

import org.junit.Test;

import java.util.Scanner;

import htw.game.IHtwGame;
import htw.game.commands.ICommand;
import htw.game.commands.StartGameCommand;
import htw.tools.IHtwConfigurationBuilder;
import tests.htw.mocks.MockNewConfigCommand;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class NewGameCommandTest {
  @Test
  public void testNewGameCommand() {
    try {
      StringBuilder log = new StringBuilder();
      ICommand<IHtwConfigurationBuilder> configCmd = new MockNewConfigCommand(log);
      ICommand<IHtwGame> gameCommand = new StartGameCommand(
              new Scanner("Greg 10"), System.out, configCmd);
      gameCommand.execute(null);
      assertEquals("config - execute", log.toString());
    } catch (Exception e) {
      fail("Valid execute() should not have failed.");
    }
  }
}
