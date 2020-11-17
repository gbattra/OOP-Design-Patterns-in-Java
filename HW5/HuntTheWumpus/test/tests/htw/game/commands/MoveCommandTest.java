package tests.htw.game.commands;

import org.junit.Test;

import java.io.IOException;
import java.util.Scanner;

import htw.game.IHtwGame;
import htw.game.commands.ICommand;
import htw.game.commands.MoveCommand;
import htw.game.commands.strategies.DirActionStrategy;
import htw.game.commands.strategies.IdActionStrategy;
import tests.htw.mocks.MockGame;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Tests for MoveCommandTest.
 */
public class MoveCommandTest {
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
