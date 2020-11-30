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
    menu.batFrequency.setValue(1.5);
    menu.pitFrequency.setValue(1.5);
    assertEquals(25, Integer.parseInt(menu.rowCount.getText()));
    assertEquals(25, Integer.parseInt(menu.columnCount.getText()));
    assertEquals(1.0, Double.parseDouble(menu.batFrequency.getText()), 0.0001);
    assertEquals(1.0, Double.parseDouble(menu.pitFrequency.getText()), 0.0001);
  }
}
