package tests.htw;

import org.junit.Test;

import htw.game.IPlayer;
import htw.game.Player;
import htw.nodes.Cave;
import htw.nodes.INode;
import htw.strategies.PitStrategy;
import maze.components.Coordinates;

import static org.junit.Assert.assertFalse;

/**
 * Tests for the pit cave
 */
public class PitCaveTest {
  @Test
  public void testReceive() {
    IPlayer player = new Player("Joe", 10);
    INode pit = new Cave(new Coordinates(0, 0), new PitStrategy());
    pit.receive(player);
    assertFalse(player.isAlive());
  }
}
