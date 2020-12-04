package tests.gui;

import org.junit.Test;

import htw.HtwMultiPlayer;
import htw.game.IHtwPlayer;

import static org.junit.Assert.assertEquals;

public class HtwMultiPlayerTest {
  @Test
  public void testNumber() {
    IHtwPlayer player = new HtwMultiPlayer("Joe", 1, 10);
    assertEquals(1, player.number());
  }
}
