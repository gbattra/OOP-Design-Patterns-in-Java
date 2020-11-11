package tests.mazes.models;

import org.junit.Test;

import maze.interfaces.ConfigurationBuilder;
import maze.interfaces.MazeBuilder;
import maze.models.Maze2dBuilder;
import maze.models.MazeCoordinates;
import maze.models.MazeConfigurationBuilder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class MazeConfigurationBuilderTest {
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetColumnCountZero() {
    ConfigurationBuilder builder = new MazeConfigurationBuilder().setColumnCount(0);
    fail("Invalid setter should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetRowCountZero() {
    ConfigurationBuilder builder = new MazeConfigurationBuilder().setRowCount(0);
    fail("Invalid setter should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetColumnCountNeg() {
    ConfigurationBuilder builder = new MazeConfigurationBuilder().setColumnCount(-1);
    fail("Invalid setter should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetColumnCountOOB() {
    ConfigurationBuilder builder = new MazeConfigurationBuilder().setColumnCount(66);
    fail("Invalid setter should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetRowCountNeg() {
    ConfigurationBuilder builder = new MazeConfigurationBuilder().setRowCount(-1);
    fail("Invalid setter should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetRowCountOOB() {
    ConfigurationBuilder builder = new MazeConfigurationBuilder().setRowCount(66);
    fail("Invalid setter should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetStartNegCol() {
    ConfigurationBuilder builder = new MazeConfigurationBuilder().setStart(-1, 0);
    fail("Invalid setter should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetStartNegRow() {
    ConfigurationBuilder builder = new MazeConfigurationBuilder().setStart(0, -1);
    fail("Invalid setter should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetExitNegRow() {
    ConfigurationBuilder builder = new MazeConfigurationBuilder().setGoal(0, -1);
    fail("Invalid setter should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetExitNegCol() {
    ConfigurationBuilder builder = new MazeConfigurationBuilder().setGoal(-1, 0);
    fail("Invalid setter should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetExitOOBCol() {
    ConfigurationBuilder builder = new MazeConfigurationBuilder().setGoal(15, 0);
    fail("Invalid setter should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetExitOOBRow() {
    ConfigurationBuilder builder = new MazeConfigurationBuilder().setGoal(0, 15);
    fail("Invalid setter should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetStartOOBRow() {
    ConfigurationBuilder builder = new MazeConfigurationBuilder().setStart(0, 15);
    fail("Invalid setter should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetStartOOBCol() {
    ConfigurationBuilder builder = new MazeConfigurationBuilder().setStart(15, 0);
    fail("Invalid setter should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetThiefFrequencyNeg() {
    ConfigurationBuilder builder = new MazeConfigurationBuilder().setThiefFrequency(-0.3);
    fail("Invalid setter should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetThiefFrequencyAboveOne() {
    ConfigurationBuilder builder = new MazeConfigurationBuilder().setThiefFrequency(1.1);
    fail("Invalid setter should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetThiefPenaltyNeg() {
    ConfigurationBuilder builder = new MazeConfigurationBuilder().setThiefPenalty(-0.3);
    fail("Invalid setter should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetThiefPenaltyAboveOne() {
    ConfigurationBuilder builder = new MazeConfigurationBuilder().setThiefPenalty(1.1);
    fail("Invalid setter should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetGoldFrequencyNeg() {
    ConfigurationBuilder builder = new MazeConfigurationBuilder().setGoldFrequency(-0.3);
    fail("Invalid setter should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetGoldFrequencyAboveOne() {
    ConfigurationBuilder builder = new MazeConfigurationBuilder().setGoldFrequency(1.1);
    fail("Invalid setter should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetGoldAmountNeg() {
    ConfigurationBuilder builder = new MazeConfigurationBuilder().setGoldAmount(-1);
    fail("Invalid setter should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetTargetRoomCountNeg() {
    ConfigurationBuilder builder = new MazeConfigurationBuilder().setThiefFrequency(-1);
    fail("Invalid setter should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetTargetRoomCountOOB() {
    ConfigurationBuilder builder = new MazeConfigurationBuilder().setTargetEdgeCount(500);
    fail("Invalid setter should have failed.");
  }
}
