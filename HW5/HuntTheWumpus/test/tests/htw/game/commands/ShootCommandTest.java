package tests.htw.game.commands;

import org.junit.Test;

import java.io.IOException;
import java.util.Scanner;

import htw.game.IHtwGame;
import htw.game.commands.ICommand;
import htw.game.commands.ShootCommand;
import htw.game.commands.strategies.ActionByDirStrategy;
import htw.game.commands.strategies.ActionByIdStrategy;
import tests.htw.mocks.MockGame;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ShootCommandTest {
  @Test
  public void testMoveById() {
    StringBuffer log = new StringBuffer();
    IHtwGame game = new MockGame(log);
    ICommand<IHtwGame> shootCmd = new ShootCommand(
            new Scanner("1 1"), System.out, new ActionByIdStrategy());
    try {
      shootCmd.execute(game);
      assertEquals("shoot - 1 - 1", log.toString());
    } catch (IOException e) {
      fail("Valid move() should not have failed.");
    }
  }

  @Test
  public void testMoveByDir() {
    StringBuffer log = new StringBuffer();
    IHtwGame game = new MockGame(log);
    ICommand<IHtwGame> shootCmd = new ShootCommand(
            new Scanner("e 1"), System.out, new ActionByDirStrategy());
    try {
      shootCmd.execute(game);
      assertEquals("shoot - EAST - 1", log.toString());
    } catch (IOException e) {
      fail("Valid move() should not have failed.");
    }
  }
}
