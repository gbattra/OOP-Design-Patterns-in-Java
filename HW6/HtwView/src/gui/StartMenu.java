package gui;

import java.awt.*;
import java.text.NumberFormat;

import javax.swing.*;
import javax.swing.text.NumberFormatter;

public class StartMenu extends JPanel {
  private final JCheckBox useSameMaze;
  private final JCheckBox isMultiplayer;
  private final JFormattedTextField rowCount;
  private final JFormattedTextField columnCount;
  private final JFormattedTextField batFrequency;
  private final JFormattedTextField pitFrequency;

  public StartMenu(String caption) {
    super();

    this.setLayout(new GridLayout(0, 1));

    JLabel useSameMazeLabel = new JLabel("Use same maze? ");
    this.useSameMaze = new JCheckBox();

    JLabel isMultiplayerLabel = new JLabel("Multiplayer mode? ");
    this.isMultiplayer = new JCheckBox();

    JLabel rowCountLabel = new JLabel("Row Count: ");
    this.rowCount = new JFormattedTextField(new NumberFormatter());
    this.rowCount.setColumns(10);

    JLabel columnCountLabel = new JLabel("Column count: ");
    this.columnCount = new JFormattedTextField(new NumberFormatter());
    this.columnCount.setColumns(10);

    JLabel batFrequencyLabel = new JLabel("Bat frequency (0.0 - 1.0): ");
    this.batFrequency = new JFormattedTextField(new NumberFormatter());
    this.batFrequency.setColumns(10);

    JLabel pitFrequencyLabel = new JLabel("Pit frequency (0.0 - 1.0): ");
    this.pitFrequency = new JFormattedTextField(new NumberFormatter());
    this.pitFrequency.setColumns(10);

    this.add(useSameMazeLabel);
    this.add(this.useSameMaze);

    this.add(isMultiplayerLabel);
    this.add(this.isMultiplayer);

    this.add(rowCountLabel);
    this.add(this.rowCount);

    this.add(columnCountLabel);
    this.add(this.columnCount);

    this.add(batFrequencyLabel);
    this.add(this.batFrequency);

    this.add(pitFrequencyLabel);
    this.add(this.pitFrequency);
  }


}
