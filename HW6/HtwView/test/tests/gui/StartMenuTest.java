package tests.gui;

import org.junit.Test;

import gui.StartMenu;

import static org.junit.Assert.assertEquals;

public class StartMenuTest {
  @Test
  public void testValidConstructor() {
    StartMenu menu = new StartMenu("StartMenu");
  }

  @Test
  public void testValueConstraints() {
    StartMenu menu = new StartMenu("StartMenu");
    menu.rowCount.setValue(26);
    menu.columnCount.setValue(26);
    assertEquals(25, ((Number) menu.rowCount.getValue()).intValue());
    assertEquals(25, ((Number) menu.columnCount.getValue()).intValue());
  }
}
