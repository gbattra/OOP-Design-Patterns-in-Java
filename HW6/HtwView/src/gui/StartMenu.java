package gui;

import java.awt.*;

import javax.swing.*;
import javax.swing.text.NumberFormatter;

public class StartMenu extends JPanel {
  private static final int ROW_COUNT_LIMIT = 25;
  private static final int COL_COUNT_LIMIT = 25;

  public final JCheckBox useSameMaze;
  public final JCheckBox isMultiplayer;
  public final JFormattedTextField rowCount;
  public final JFormattedTextField columnCount;
  public final JFormattedTextField batFrequency;
  public final JFormattedTextField pitFrequency;

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
    this.rowCount.addPropertyChangeListener(l -> this.handleRowCountChange());

    JLabel columnCountLabel = new JLabel("Column count: ");
    this.columnCount = new JFormattedTextField(new NumberFormatter());
    this.columnCount.setColumns(10);
    this.columnCount.addPropertyChangeListener(l -> this.handleColumnCountChange());

    JLabel batFrequencyLabel = new JLabel("Bat frequency (0.0 - 1.0): ");
    this.batFrequency = new JFormattedTextField(new NumberFormatter());
    this.batFrequency.setColumns(10);
    this.batFrequency.addPropertyChangeListener(l -> this.handleBatFrequencyChange());

    JLabel pitFrequencyLabel = new JLabel("Pit frequency (0.0 - 1.0): ");
    this.pitFrequency = new JFormattedTextField(new NumberFormatter());
    this.pitFrequency.setColumns(10);
    this.batFrequency.addPropertyChangeListener(l -> this.handlePitFrequencyChange());

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

  private void handleRowCountChange() {
    if (this.rowCount.getValue() == null) {
      return;
    }
    int value = Integer.parseInt(this.rowCount.getText());
    if (value > ROW_COUNT_LIMIT) {
      this.rowCount.setValue(ROW_COUNT_LIMIT);
    }
  }

  private void handleColumnCountChange() {
    if (this.columnCount.getValue() == null) {
      return;
    }
    int value = Integer.parseInt(this.columnCount.getText());
    if (value > COL_COUNT_LIMIT) {
      this.columnCount.setValue(ROW_COUNT_LIMIT);
    }
  }

  private void handlePitFrequencyChange() {
    if (this.pitFrequency.getValue() == null) {
      return;
    }
    double value = Double.parseDouble(this.pitFrequency.getText());
    if (value > 1.0) {
      this.pitFrequency.setValue(1.0);
    }
  }

  private void handleBatFrequencyChange() {
    if (this.batFrequency.getValue() == null) {
      return;
    }
    double value = Double.parseDouble(this.batFrequency.getText());
    if (value > 1.0) {
      this.batFrequency.setValue(1.0);
    }
  }
}
