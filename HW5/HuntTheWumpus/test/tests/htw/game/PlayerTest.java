package tests.htw.game;

import org.junit.Test;

import htw.game.HtwPlayer;
import htw.game.IHtwPlayer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class PlayerTest {
  @Test
  public void testConstructor() {
    try {
      IHtwPlayer player = new HtwPlayer("Joe", 10);
    } catch (Exception e) {
      fail();
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructorEmptyName() {
    IHtwPlayer player = new HtwPlayer("", 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructorNulls() {
    IHtwPlayer player = new HtwPlayer(null, 0);
  }

  @Test
  public void testDecrement() {
    IHtwPlayer player = new HtwPlayer("Joe", 2);
    player.decrementArrowCount(1);
    assertEquals(1, player.arrowCount());
  }
}
