package tests.gui;

import org.junit.Test;

import gui.NodeView;
import htw.level.Cave;
import htw.level.StandardStrategy;
import maze.components.Coordinates;

public class NodeViewTest {
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetOccupied() {
    NodeView nodeView = new NodeView(
            new Cave(1, new Coordinates(0, 0), new StandardStrategy(), new StringBuffer()));
    nodeView.setOccupied(0);
  }
}
