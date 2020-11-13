package tests.htw;

import org.junit.Before;
import org.junit.Test;

import htw.game.IPlayer;
import htw.game.Player;
import htw.nodes.Cave;
import htw.nodes.INode;
import htw.strategies.PitStrategy;
import maze.components.Coordinates;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests for the pit cave
 */
public class PitCaveTest {
  private INode pit;

  @Before
  public void setup() {
    this.pit = new Cave(new Coordinates(0, 0), new PitStrategy());
  }

  @Test
  public void testReceive() {
    IPlayer player = new Player("Joe", 10);
    this.pit.receive(player);
    assertFalse(player.isAlive());
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
