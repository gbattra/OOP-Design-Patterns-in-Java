package tests.htw;

import org.junit.Before;
import org.junit.Test;

import htw.game.IHtwPlayer;
import htw.game.HtwPlayer;
import htw.level.nodes.Cave;
import htw.level.nodes.IHtwNode;
import htw.level.strategies.PitStrategy;
import maze.components.Coordinates;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Tests for the pit cave
 */
public class PitCaveTest {
  private IHtwNode pit;

  @Before
  public void setup() {
    this.pit = new Cave(1, new Coordinates(0, 0), new PitStrategy(), System.out);
  }

  @Test
  public void testReceive() {
    try {
      IHtwPlayer player = new HtwPlayer("Joe", 10);
      this.pit.receive(player);
      assertFalse(player.isAlive());
    } catch (Exception e) {
      fail("Valid receive() should not have failed.");
    }
  }

  @Test
  public void testToString() {
    assertEquals("(0, 0) - Pit", this.pit.toString());
  }

  @Test
  public void testDrafty() {
    assertTrue(this.pit.drafty());
  }
}
