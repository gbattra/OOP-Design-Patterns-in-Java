package gui;

import java.awt.*;

import javax.swing.*;
import javax.swing.text.NumberFormatter;

public class StartMenu extends JPanel {
  private static final int ROW_COUNT_LIMIT = 25;
  private static final int COL_COUNT_LIMIT = 25;

  public final JCheckBox useSameMaze;
  public final JCheckBox isMultiplayer;
  public final JCheckBox isRoomMaze;
  public final JFormattedTextField rowCount;
  public final JFormattedTextField columnCount;
  public final JFormattedTextField finalRoomCount;
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
    this.rowCount.setValue(10);
    this.rowCount.addPropertyChangeListener(l -> this.handleRowCountChange());

    JLabel columnCountLabel = new JLabel("Column count: ");
    this.columnCount = new JFormattedTextField(new NumberFormatter());
    this.columnCount.setColumns(10);
    this.columnCount.setValue(10);
    this.columnCount.addPropertyChangeListener(l -> this.handleColumnCountChange());

    JLabel batFrequencyLabel = new JLabel("Bat frequency (0.0 - 1.0): ");
    this.batFrequency = new JFormattedTextField(new NumberFormatter());
    this.batFrequency.setColumns(10);
    this.batFrequency.setValue(0.3);
    this.batFrequency.addPropertyChangeListener(l -> this.handleBatFrequencyChange());

    JLabel pitFrequencyLabel = new JLabel("Pit frequency (0.0 - 1.0): ");
    this.pitFrequency = new JFormattedTextField(new NumberFormatter());
    this.pitFrequency.setColumns(10);
    this.pitFrequency.setValue(0.2);
    this.batFrequency.addPropertyChangeListener(l -> this.handlePitFrequencyChange());


    JLabel isRoomMazeLabel = new JLabel("Is room maze? ");
    this.isRoomMaze = new JCheckBox();
    this.isRoomMaze.addItemListener(l -> this.handleIsRoomMazeChange());

    JLabel finalRoomCountLabel = new JLabel("Final Room Count: ");
    this.finalRoomCount = new JFormattedTextField(new NumberFormatter());
    this.finalRoomCount.setColumns(10);
    this.finalRoomCount.setValue(10);
    this.finalRoomCount.setEnabled(false);

    this.add(useSameMazeLabel);
    this.add(this.useSameMaze);

    this.add(isMultiplayerLabel);
    this.add(this.isMultiplayer);

    this.add(rowCountLabel);
    this.add(this.rowCount);

    this.add(columnCountLabel);
    this.add(this.columnCount);

    this.add(isRoomMazeLabel);
    this.add(this.isRoomMaze);

    this.add(finalRoomCountLabel);
    this.add(this.finalRoomCount);

    this.add(batFrequencyLabel);
    this.add(this.batFrequency);

    this.add(pitFrequencyLabel);
    this.add(this.pitFrequency);
  }

  private void handleIsRoomMazeChange() {
    this.finalRoomCount.setEnabled(this.isRoomMaze.isSelected());
  }

  private void handleRowCountChange() {
    if (this.rowCount.getValue() == null) {
      return;
    }
    int value = ((Number) this.rowCount.getValue()).intValue();
    if (value > ROW_COUNT_LIMIT) {
      this.rowCount.setValue(ROW_COUNT_LIMIT);
    }
  }

  private void handleColumnCountChange() {
    if (this.columnCount.getValue() == null) {
      return;
    }
    int value = ((Number) this.columnCount.getValue()).intValue();
    if (value > COL_COUNT_LIMIT) {
      this.columnCount.setValue(ROW_COUNT_LIMIT);
    }
  }

  private void handlePitFrequencyChange() {
    if (this.pitFrequency.getValue() == null) {
      return;
    }
    double value = ((Number) this.pitFrequency.getValue()).doubleValue();
    if (value > 1.0) {
      this.pitFrequency.setValue(1.0);
    }
  }

  private void handleBatFrequencyChange() {
    if (this.batFrequency.getValue() == null) {
      return;
    }
    double value = ((Number) this.batFrequency.getValue()).doubleValue();
    if (value > 1.0) {
      this.batFrequency.setValue(1.0);
    }
  }
}
