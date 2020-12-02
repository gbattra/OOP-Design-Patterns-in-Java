package tests.gui;

import org.junit.Test;

import gui.NodeView;

public class NodeViewTest {
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetOccupied() {
    NodeView nodeView = new NodeView();
    nodeView.setOccupied(0);
  }
}
