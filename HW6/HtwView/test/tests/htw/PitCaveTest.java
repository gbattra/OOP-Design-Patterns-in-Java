package tests.htw;

import org.junit.Before;
import org.junit.Test;

import htw.game.HtwPlayer;
import htw.game.IHtwPlayer;
import htw.level.Cave;
import htw.level.IHtwNode;
import htw.level.PitStrategy;
import maze.components.Coordinates;
import maze.Direction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Tests for the pit cave.
 */
public class PitCaveTest {
  private IHtwNode pit;
  private Appendable out;

  @Before
  public void setup() {
    this.out = new StringBuilder();
    this.pit = new Cave(1, new Coordinates(0, 0), new PitStrategy(), this.out);
  }

  @Test
  public void testReceive() {
    try {
      IHtwPlayer player = new HtwPlayer("Joe", 10);
      this.pit.receive(player);
      assertFalse(player.isAlive());
      assertEquals(
              "'Ahhhhhhhh', you shout as you fall into a bottomless pit!\n", this.out.toString());
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
    assertTrue(this.pit.drafty(Direction.SOUTH));
  }
}
