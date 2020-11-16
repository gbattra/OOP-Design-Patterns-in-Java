package tests.htw.game.commands;

import org.junit.Test;

import java.io.IOException;
import java.util.Scanner;

import htw.game.IHtwGame;
import htw.game.commands.ICommand;
import htw.game.commands.MoveCommand;
import htw.game.commands.strategies.ActionByDirStrategy;
import htw.game.commands.strategies.ActionByIdStrategy;
import tests.htw.mocks.MockGame;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class MoveCommandTest {
  @Test
  public void testMoveById() {
    StringBuffer log = new StringBuffer();
    IHtwGame game = new MockGame(log);
    ICommand<IHtwGame> moveCmd = new MoveCommand(
            new Scanner("1"), System.out, new ActionByIdStrategy());
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
            new Scanner("e"), System.out, new ActionByDirStrategy());
    try {
      moveCmd.execute(game);
      assertEquals("move - EAST", log.toString());
    } catch (IOException e) {
      fail("Valid move() should not have failed.");
    }
  }
}
