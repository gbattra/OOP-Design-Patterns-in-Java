package tests.maze.components.nodes;

import org.junit.Test;

import maze.utils.Direction;
import maze.components.nodes.Node;
import maze.components.nodes.DeadEndNode;
import maze.components.Coordinates;
import maze.components.Path;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

/**
 * Test for the DeadEndNode.
 */
public class DeadEndNodeTest {
  @Test
  public void testConstructor() {
    try {
      Node node = new DeadEndNode();
    } catch (Exception e) {
      fail("Valid constructor should not have failed.");
    }
  }

  @Test
  public void testGetters() {
    Node node = new DeadEndNode();
    assertEquals(0, node.getGoldCount());
    assertEquals(0, node.getThiefPenalty(), 0.00001);
    assertEquals(new Coordinates(0, 0), node.getCoordinates());
    assertEquals(new DeadEndNode(), node.getNode(Direction.EAST));
    assertFalse(node.canReach(new Coordinates(0, 0)));
    assertEquals(
            new Path(new Coordinates(0, 0)),
            node.wealthiestPathTo(new Coordinates(0, 0)));
    assertEquals(
            new Path(new Coordinates(0, 0)),
            node.exploreTo(new Coordinates(0, 0)));
    assertEquals("Dead End Node", node.toString());
    assertEquals(node.get(new Coordinates(0, 0)), new DeadEndNode());
  }

  @Test
  public void testLoot() {
    Node node = new DeadEndNode();
    assertEquals(10, node.loot(10));
  }
}
