package gui;

import java.awt.GridLayout;

import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
  private static final double DEF_PIT_FREQ = 0.4;

  public final JCheckBox useSameMaze;
  public final JCheckBox isMultiplayer;
  public final JCheckBox isRoomMaze;
  public final JFormattedTextField arrowCount;
  public final JFormattedTextField rowCount;
  public final JFormattedTextField columnCount;
  public final JFormattedTextField finalEdgeCount;
  public final JFormattedTextField batFrequency;
  public final JFormattedTextField pitFrequency;

  /**
   * Constructor for the start menu.
   */
  public StartMenu() {
    super();

    this.setLayout(new GridLayout(0, 1));

    JLabel useSameMazeLabel = new JLabel("Use same maze? ");
    this.add(useSameMazeLabel);
    this.useSameMaze = new JCheckBox();
    this.add(this.useSameMaze);

    JLabel isMultiplayerLabel = new JLabel("Multiplayer mode? ");
    this.add(isMultiplayerLabel);
    this.isMultiplayer = new JCheckBox();
    this.isMultiplayer.setSelected(true);
    this.add(this.isMultiplayer);

    JLabel arrowCountLabel = new JLabel("Arrow count: ");
    this.add(arrowCountLabel);
    this.arrowCount = new JFormattedTextField(new NumberFormatter());
    this.arrowCount.setColumns(10);
    this.arrowCount.setValue(10);
    this.add(this.arrowCount);

    JLabel rowCountLabel = new JLabel(
            String.format("Row Count (max %s): ", ROW_COUNT_LIMIT));
    this.add(rowCountLabel);
    this.rowCount = new JFormattedTextField(new NumberFormatter());
    this.rowCount.setColumns(10);
    this.rowCount.setValue(DEF_ROW_COUNT);
    this.rowCount.addPropertyChangeListener(l -> this.handleRowCountChange());
    this.add(this.rowCount);

    JLabel columnCountLabel = new JLabel(
            String.format("Column count (max %s): ", COL_COUNT_LIMIT));
    this.add(columnCountLabel);
    this.columnCount = new JFormattedTextField(new NumberFormatter());
    this.columnCount.setColumns(10);
    this.columnCount.setValue(DEF_COL_COUNT);
    this.columnCount.addPropertyChangeListener(l -> this.handleColumnCountChange());
    this.add(this.columnCount);

    JLabel batFrequencyLabel = new JLabel("Bat frequency (0.0 - 1.0): ");
    this.add(batFrequencyLabel);
    this.batFrequency = new JFormattedTextField(new NumberFormatter());
    this.batFrequency.setColumns(10);
    this.batFrequency.setValue(DEF_BAT_FREQ);
    this.batFrequency.addPropertyChangeListener(l -> this.handleBatFrequencyChange());
    this.add(this.batFrequency);

    JLabel pitFrequencyLabel = new JLabel("Pit frequency (0.0 - 1.0): ");
    this.add(pitFrequencyLabel);
    this.pitFrequency = new JFormattedTextField(new NumberFormatter());
    this.pitFrequency.setColumns(10);
    this.pitFrequency.setValue(DEF_PIT_FREQ);
    this.batFrequency.addPropertyChangeListener(l -> this.handlePitFrequencyChange());
    this.add(this.pitFrequency);

    JLabel isRoomMazeLabel = new JLabel("Is room maze? ");
    this.add(isRoomMazeLabel);
    this.isRoomMaze = new JCheckBox();
    this.isRoomMaze.addItemListener(l -> this.handleIsRoomMazeChange());
    this.add(this.isRoomMaze);

    JLabel finalEdgeCountLabel = new JLabel("Final Edge Count: ");
    this.add(finalEdgeCountLabel);
    this.finalEdgeCount = new JFormattedTextField(new NumberFormatter());
    this.finalEdgeCount.setColumns(10);
    this.finalEdgeCount.setValue((DEF_ROW_COUNT - 1) * (DEF_COL_COUNT - 1));
    this.finalEdgeCount.setEnabled(false);
    this.add(this.finalEdgeCount);
  }

  /**
   * Modifies form when room maze is selected/deselected.
   */
  private void handleIsRoomMazeChange() {
    this.finalEdgeCount.setEnabled(this.isRoomMaze.isSelected());
  }

  /**
   * Runs validation on the row count value and corrects if user input is invalid.
   */
  private void handleRowCountChange() {
    if (this.rowCount.getValue() == null) {
      return;
    }
    int value = ((Number) this.rowCount.getValue()).intValue();
    if (value > ROW_COUNT_LIMIT) {
      this.rowCount.setValue(ROW_COUNT_LIMIT);
    }
  }

  /**
   * Runs validation on the col count value and corrects if user input is invalid.
   */
  private void handleColumnCountChange() {
    if (this.columnCount.getValue() == null) {
      return;
    }
    int value = ((Number) this.columnCount.getValue()).intValue();
    if (value > COL_COUNT_LIMIT) {
      this.columnCount.setValue(ROW_COUNT_LIMIT);
    }
  }

  /**
   * Runs validation on the pit freq value and corrects if user input is invalid.
   */
  private void handlePitFrequencyChange() {
    if (this.pitFrequency.getValue() == null) {
      return;
    }
    double value = ((Number) this.pitFrequency.getValue()).doubleValue();
    if (value > 1.0) {
      this.pitFrequency.setValue(1.0);
    }
  }

  /**
   * Runs validation on the bat freq value and corrects if user input is invalid.
   */
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
