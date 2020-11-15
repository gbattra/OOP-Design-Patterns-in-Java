package tests.htw;

import org.junit.Test;

import java.io.IOException;
import java.util.Scanner;

import htw.game.IHtwGame;
import htw.game.commands.ICommand;
import htw.game.commands.MoveCommand;
import htw.game.commands.strategies.MoveByDirStrategy;
import htw.game.commands.strategies.MoveByIdStrategy;
import tests.htw.mocks.MockGame;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class MoveCommandTest {
  @Test
  public void testMoveById() {
    StringBuilder log = new StringBuilder();
    IHtwGame game = new MockGame(log);
    ICommand<IHtwGame> moveCmd = new MoveCommand(
            new Scanner("1"), System.out, new MoveByIdStrategy());
    try {
      moveCmd.execute(game);
      assertEquals("move - 1", log.toString());
    } catch (IOException e) {
      fail("Valid move() should not have failed.");
    }
  }

  @Test
  public void testMoveByDir() {
    StringBuilder log = new StringBuilder();
    IHtwGame game = new MockGame(log);
    ICommand<IHtwGame> moveCmd = new MoveCommand(
            new Scanner("e"), System.out, new MoveByDirStrategy());
    try {
      moveCmd.execute(game);
      assertEquals("move - EAST", log.toString());
    } catch (IOException e) {
      fail("Valid move() should not have failed.");
    }
  }
}
