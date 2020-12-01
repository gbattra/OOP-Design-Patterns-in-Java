package tests.gui;

import org.junit.Test;

import gui.StartMenu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class StartMenuTest {
  @Test
  public void testValidConstructor() {
    StartMenu menu = new StartMenu();
  }

  @Test
  public void testValueConstraints() {
    StartMenu menu = new StartMenu();
    menu.rowCount.setValue(26);
    menu.columnCount.setValue(26);
    assertFalse(menu.isRoomMaze.isSelected());
    assertFalse(menu.finalEdgeCount.isEnabled());
    menu.isRoomMaze.setSelected(true);
    assertTrue(menu.finalEdgeCount.isEnabled());
    assertEquals(25, ((Number) menu.rowCount.getValue()).intValue());
    assertEquals(25, ((Number) menu.columnCount.getValue()).intValue());
  }
}
