package gui;

import java.awt.*;

import javax.swing.*;
import javax.swing.text.NumberFormatter;

/**
 * JPanel object holding the form for configuring a new game. I chose to use `public` access
 * modifiers here as the properties are immutable through the use of `final`, and little would be
 * gained from providing getters.
 */
public class StartMenu extends JPanel {
  private static final int ROW_COUNT_LIMIT = 25;
  private static final int COL_COUNT_LIMIT = 25;
  private static final int DEF_ROW_COUNT = 10;
  private static final int DEF_COL_COUNT = 10;
  private static final double DEF_BAT_FREQ = 0.3;
  private static final double DEF_PIT_FREQ = 0.2;

  public final JCheckBox useSameMaze;
  public final JCheckBox isMultiplayer;
  public final JCheckBox isRoomMaze;
  public final JFormattedTextField arrowCount;
  public final JFormattedTextField rowCount;
  public final JFormattedTextField columnCount;
  public final JFormattedTextField finalEdgeCount;
  public final JFormattedTextField batFrequency;
  public final JFormattedTextField pitFrequency;

  public StartMenu() {
    super();

    this.setLayout(new GridLayout(0, 1));

    JLabel useSameMazeLabel = new JLabel("Use same maze? ");
    this.useSameMaze = new JCheckBox();

    JLabel isMultiplayerLabel = new JLabel("Multiplayer mode? ");
    this.isMultiplayer = new JCheckBox();
    this.isMultiplayer.setSelected(true);

    JLabel arrowCountLabel = new JLabel("Arrow count: ");
    this.arrowCount = new JFormattedTextField(new NumberFormatter());
    this.arrowCount.setColumns(10);
    this.arrowCount.setValue(10);

    JLabel rowCountLabel = new JLabel(
            String.format("Row Count (max %s): ", ROW_COUNT_LIMIT));
    this.rowCount = new JFormattedTextField(new NumberFormatter());
    this.rowCount.setColumns(10);
    this.rowCount.setValue(DEF_ROW_COUNT);
    this.rowCount.addPropertyChangeListener(l -> this.handleRowCountChange());

    JLabel columnCountLabel = new JLabel(
            String.format("Column count (max %s): ", COL_COUNT_LIMIT));
    this.columnCount = new JFormattedTextField(new NumberFormatter());
    this.columnCount.setColumns(10);
    this.columnCount.setValue(DEF_COL_COUNT);
    this.columnCount.addPropertyChangeListener(l -> this.handleColumnCountChange());

    JLabel batFrequencyLabel = new JLabel("Bat frequency (0.0 - 1.0): ");
    this.batFrequency = new JFormattedTextField(new NumberFormatter());
    this.batFrequency.setColumns(10);
    this.batFrequency.setValue(DEF_BAT_FREQ);
    this.batFrequency.addPropertyChangeListener(l -> this.handleBatFrequencyChange());

    JLabel pitFrequencyLabel = new JLabel("Pit frequency (0.0 - 1.0): ");
    this.pitFrequency = new JFormattedTextField(new NumberFormatter());
    this.pitFrequency.setColumns(10);
    this.pitFrequency.setValue(DEF_PIT_FREQ);
    this.batFrequency.addPropertyChangeListener(l -> this.handlePitFrequencyChange());

    JLabel isRoomMazeLabel = new JLabel("Is room maze? ");
    this.isRoomMaze = new JCheckBox();
    this.isRoomMaze.addItemListener(l -> this.handleIsRoomMazeChange());

    JLabel finalEdgeCountLabel = new JLabel("Final Edge Count: ");
    this.finalEdgeCount = new JFormattedTextField(new NumberFormatter());
    this.finalEdgeCount.setColumns(10);
    this.finalEdgeCount.setValue((DEF_ROW_COUNT - 1) * (DEF_COL_COUNT - 1));
    this.finalEdgeCount.setEnabled(false);

    this.add(useSameMazeLabel);
    this.add(this.useSameMaze);

    this.add(isMultiplayerLabel);
    this.add(this.isMultiplayer);

    this.add(arrowCountLabel);
    this.add(this.arrowCount);

    this.add(rowCountLabel);
    this.add(this.rowCount);

    this.add(columnCountLabel);
    this.add(this.columnCount);

    this.add(isRoomMazeLabel);
    this.add(this.isRoomMaze);

    this.add(finalEdgeCountLabel);
    this.add(this.finalEdgeCount);

    this.add(batFrequencyLabel);
    this.add(this.batFrequency);

    this.add(pitFrequencyLabel);
    this.add(this.pitFrequency);
  }

  private void handleIsRoomMazeChange() {
    this.finalEdgeCount.setEnabled(this.isRoomMaze.isSelected());
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
