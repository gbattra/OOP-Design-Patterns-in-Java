package tests.htw;

import org.junit.Test;

import java.io.IOException;
import java.util.Scanner;

import htw.game.IHtwGame;
import htw.game.commands.ICommand;
import htw.game.commands.MoveCommand;
import htw.game.commands.ShootCommand;
import htw.game.commands.strategies.MoveByDirStrategy;
import htw.game.commands.strategies.MoveByIdStrategy;
import htw.game.commands.strategies.ShootByDirStrategy;
import htw.game.commands.strategies.ShootByIdStrategy;
import tests.htw.mocks.MockGame;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ShootCommandTest {
  @Test
  public void testMoveById() {
    StringBuilder log = new StringBuilder();
    IHtwGame game = new MockGame(log);
    ICommand<IHtwGame> shootCmd = new ShootCommand(
            new Scanner("1 1"), System.out, new ShootByIdStrategy());
    try {
      shootCmd.execute(game);
      assertEquals("shoot - 1 - 1", log.toString());
    } catch (IOException e) {
      fail("Valid move() should not have failed.");
    }
  }

  @Test
  public void testMoveByDir() {
    StringBuilder log = new StringBuilder();
    IHtwGame game = new MockGame(log);
    ICommand<IHtwGame> shootCmd = new ShootCommand(
            new Scanner("e 1"), System.out, new ShootByDirStrategy());
    try {
      shootCmd.execute(game);
      assertEquals("shoot - EAST - 1", log.toString());
    } catch (IOException e) {
      fail("Valid move() should not have failed.");
    }
  }
}
