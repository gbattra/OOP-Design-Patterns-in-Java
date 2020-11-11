package tests.maze.components;

import org.junit.Test;

import maze.components.ICoordinates;
import maze.components.nodes.Node;
import maze.components.IPath;
import maze.components.nodes.DeadEndNode;
import maze.components.nodes.GoldRoomNode;
import maze.components.Coordinates;
import maze.components.Path;
import maze.components.nodes.ThiefRoomNode;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Test for the MazePath.
 */
public class MazePathTest {
  @Test
  public void testValidConstructor() {
    try {
      ICoordinates target = new Coordinates(1, 1);
      IPath path = new Path(target);
    } catch (Exception e) {
      fail("Valid constructor should not have failed.");
    }
  }

  @Test
  public void testGetTarget() {
    ICoordinates target = new Coordinates(1, 1);
    IPath path = new Path(target);
    assertEquals(target, path.getTarget());
  }

  @Test
  public void testAddNodes() {
    ICoordinates target = new Coordinates(1, 1);
    Node node = new DeadEndNode();
    IPath path = new Path(target);
    assertEquals(0, path.getCoordinatesTraversed().size());
    path = path.addCoordinates(node.getCoordinates());
    assertEquals(1, path.getCoordinatesTraversed().size());
  }

  @Test
  public void testSetReachesTarget() {
    ICoordinates target = new Coordinates(1, 1);
    IPath path = new Path(target);
    assertFalse(path.reachesTarget());
    path = path.setReachesTarget(true);
    assertTrue(path.reachesTarget());
  }

  @Test
  public void testTotalGold() {
    ICoordinates target = new Coordinates(3, 3);
    IPath path = new Path(target);
    Node room1 = new GoldRoomNode(new Coordinates(0,0), 10);
    Node thief1 = new ThiefRoomNode(new Coordinates(1, 0), 0.1);
    Node room2 = new GoldRoomNode(new Coordinates(2, 0), 10);
    Node thief2 = new ThiefRoomNode(new Coordinates(2, 1), 0.1);
    path = path
            .enter(room1)
            .enter(thief1)
            .enter(room2)
            .enter(thief2);
    assertEquals(17, path.totalGold());
  }
}
