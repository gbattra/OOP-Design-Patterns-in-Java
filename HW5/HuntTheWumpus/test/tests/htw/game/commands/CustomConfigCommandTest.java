package tests.htw.game.commands;

import org.junit.Test;

import java.util.Scanner;

import htw.game.commands.CustomConfigCommand;
import htw.game.commands.ICommand;
import htw.tools.HtwConfigurationBuilder;
import htw.tools.IHtwConfiguration;
import htw.tools.IHtwConfigurationBuilder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Tests for CustomConfigCommand.
 */
public class CustomConfigCommandTest {
  @Test
  public void testExecute() {
    try {
      StringBuilder log = new StringBuilder();
      ICommand<IHtwConfigurationBuilder> cmd = new CustomConfigCommand(
              new Scanner("5 5 false false 0.2 0.3 "), log);
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
}
