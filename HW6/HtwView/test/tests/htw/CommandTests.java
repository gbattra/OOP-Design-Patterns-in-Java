package tests.htw;

import org.junit.Test;

import java.io.IOException;
import java.util.Scanner;

import htw.game.IHtwGame;
import htw.game.commands.CustomConfigCommand;
import htw.game.commands.ICommand;
import htw.game.commands.MoveCommand;
import htw.game.commands.NewConfigCommand;
import htw.game.commands.ShootCommand;
import htw.game.commands.StandardConfigCommand;
import htw.game.commands.StartGameCommand;
import htw.game.DirActionStrategy;
import htw.game.commands.IdActionStrategy;
import htw.tools.HtwConfigurationBuilder;
import htw.tools.IHtwConfiguration;
import htw.tools.IHtwConfigurationBuilder;
import tests.htw.mocks.MockCustomConfigCommand;
import tests.htw.mocks.MockGame;
import tests.htw.mocks.MockNewConfigCommand;
import tests.htw.mocks.MockStandardConfigCommand;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

/**
 * Tests for all the commands. In one file due to Handins file limit.
 */
public class CommandTests {
  @Test
  public void testCustomConfig() {
    try {
      StringBuilder log = new StringBuilder();
      ICommand<IHtwConfigurationBuilder> cmd = new CustomConfigCommand(
              new Scanner("5 5 0.2 0.3 "), log);
      IHtwConfiguration config = cmd.execute(new HtwConfigurationBuilder()).build();
      assertEquals(5, config.rowCount());
      assertEquals(5, config.columnCount());
      assertEquals(0.2, config.pitFrequency(), 0.001);
      assertEquals(0.3, config.batFrequency(), 0.001);
      assertFalse(config.isRoomMaze());
      assertFalse(config.isWrappingMaze());
      assertEquals(
              "Row count: "
                      + "Column count: "
                      + "Pit frequency (double): "
                      + "Bat frequency (double): ",
              log.toString());
    } catch (Exception e) {
      fail("Valid execute should not have failed.");
    }
  }

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

  @Test
  public void testExecute() {
    try {
      ICommand<IHtwConfigurationBuilder> standard = new StandardConfigCommand();
      IHtwConfigurationBuilder builder = new HtwConfigurationBuilder();
      assertEquals(builder, standard.execute(builder));
    } catch (Exception e) {
      fail("Valid execute should not have failed.");
    }
  }

  @Test
  public void testShootById() {
    StringBuffer log = new StringBuffer();
    IHtwGame game = new MockGame(log);
    ICommand<IHtwGame> shootCmd = new ShootCommand(
            new Scanner("1 1"), System.out, new IdActionStrategy());
    try {
      shootCmd.execute(game);
      assertEquals("shoot - 1 - 1", log.toString());
    } catch (IOException e) {
      fail("Valid move() should not have failed.");
    }
  }

  @Test
  public void testShootByDir() {
    StringBuffer log = new StringBuffer();
    IHtwGame game = new MockGame(log);
    ICommand<IHtwGame> shootCmd = new ShootCommand(
            new Scanner("e 1"), System.out, new DirActionStrategy());
    try {
      shootCmd.execute(game);
      assertEquals("shoot - EAST - 1", log.toString());
    } catch (IOException e) {
      fail("Valid move() should not have failed.");
    }
  }

  @Test
  public void testNewConfig() {
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

  @Test
  public void testMoveById() {
    StringBuffer log = new StringBuffer();
    IHtwGame game = new MockGame(log);
    ICommand<IHtwGame> moveCmd = new MoveCommand(
            new Scanner("1"), System.out, new IdActionStrategy());
    try {
      moveCmd.execute(game);
      assertEquals("move - 1", log.toString());
    } catch (IOException e) {
      fail("Valid move() should not have failed.");
    }
  }

  @Test
  public void testMoveByDir() {
    StringBuffer log = new StringBuffer();
    IHtwGame game = new MockGame(log);
    ICommand<IHtwGame> moveCmd = new MoveCommand(
            new Scanner("e"), System.out, new DirActionStrategy());
    try {
      moveCmd.execute(game);
      assertEquals("move - EAST", log.toString());
    } catch (IOException e) {
      fail("Valid move() should not have failed.");
    }
  }
}
