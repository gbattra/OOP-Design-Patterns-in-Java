import org.junit.Test;

import java.awt.Dimension;
import java.util.ArrayList;

import gui.Container;
import gui.GuiView;
import gui.IView;
import gui.MazeView;
import gui.NodeView;
import htw.Cave;
import htw.IHtwMaze;
import htw.IHtwNode;
import htw.StandardStrategy;
import htw.HtwConfigurationBuilder;
import htw.HtwMazeBuilder;
import maze.Coordinates;

/**
 * Tests for NodeView.
 */
public class NodeViewTest {
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetOccupied() {
    IHtwNode cave = new Cave(1, new Coordinates(0, 0), new StandardStrategy(), new StringBuffer());
    IView view = new GuiView();
    IHtwMaze maze = (IHtwMaze) new HtwMazeBuilder(new HtwConfigurationBuilder().build()).build();
    Container container =
            new Container(view, new ArrayList<>(), maze, 1);
    MazeView mazeView = new MazeView(cave, new Dimension(1, 1), container);
    NodeView nodeView = new NodeView(cave, mazeView);
    nodeView.setOccupied(0);
  }
}