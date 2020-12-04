package tests.gui;

import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;

import gui.Container;
import gui.GuiController;
import gui.GuiView;
import gui.IGuiController;
import gui.IView;
import gui.MazeView;
import gui.NodeView;
import htw.level.Cave;
import htw.level.IHtwMaze;
import htw.level.IHtwNode;
import htw.level.StandardStrategy;
import htw.tools.HtwConfigurationBuilder;
import htw.tools.HtwMazeBuilder;
import maze.components.Coordinates;
import tests.htw.mocks.MockMaze;

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
