package tests.maze.builders;

import org.junit.Test;

import maze.config.IConfigurationBuilder;
import maze.config.MazeConfigurationBuilder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class MazeConfigurationBuilderTest {
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetColumnCountZero() {
    IConfigurationBuilder builder = new MazeConfigurationBuilder().setColumnCount(0);
    fail("Invalid setter should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetRowCountZero() {
    IConfigurationBuilder builder = new MazeConfigurationBuilder().setRowCount(0);
    fail("Invalid setter should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetColumnCountNeg() {
    IConfigurationBuilder builder = new MazeConfigurationBuilder().setColumnCount(-1);
    fail("Invalid setter should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetColumnCountOOB() {
    IConfigurationBuilder builder = new MazeConfigurationBuilder().setColumnCount(66);
    fail("Invalid setter should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetRowCountNeg() {
    IConfigurationBuilder builder = new MazeConfigurationBuilder().setRowCount(-1);
    fail("Invalid setter should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetRowCountOOB() {
    IConfigurationBuilder builder = new MazeConfigurationBuilder().setRowCount(66);
    fail("Invalid setter should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetStartNegCol() {
    IConfigurationBuilder builder = new MazeConfigurationBuilder().setStart(-1, 0);
    fail("Invalid setter should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetStartNegRow() {
    IConfigurationBuilder builder = new MazeConfigurationBuilder().setStart(0, -1);
    fail("Invalid setter should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetExitNegRow() {
    IConfigurationBuilder builder = new MazeConfigurationBuilder().setGoal(0, -1);
    fail("Invalid setter should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetExitNegCol() {
    IConfigurationBuilder builder = new MazeConfigurationBuilder().setGoal(-1, 0);
    fail("Invalid setter should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetExitOOBCol() {
    IConfigurationBuilder builder = new MazeConfigurationBuilder().setGoal(15, 0);
    fail("Invalid setter should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetExitOOBRow() {
    IConfigurationBuilder builder = new MazeConfigurationBuilder().setGoal(0, 15);
    fail("Invalid setter should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetStartOOBRow() {
    IConfigurationBuilder builder = new MazeConfigurationBuilder().setStart(0, 15);
    fail("Invalid setter should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetStartOOBCol() {
    IConfigurationBuilder builder = new MazeConfigurationBuilder().setStart(15, 0);
    fail("Invalid setter should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetThiefFrequencyNeg() {
    IConfigurationBuilder builder = new MazeConfigurationBuilder().setThiefFrequency(-0.3);
    fail("Invalid setter should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetThiefFrequencyAboveOne() {
    IConfigurationBuilder builder = new MazeConfigurationBuilder().setThiefFrequency(1.1);
    fail("Invalid setter should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetThiefPenaltyNeg() {
    IConfigurationBuilder builder = new MazeConfigurationBuilder().setThiefPenalty(-0.3);
    fail("Invalid setter should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetThiefPenaltyAboveOne() {
    IConfigurationBuilder builder = new MazeConfigurationBuilder().setThiefPenalty(1.1);
    fail("Invalid setter should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetGoldFrequencyNeg() {
    IConfigurationBuilder builder = new MazeConfigurationBuilder().setGoldFrequency(-0.3);
    fail("Invalid setter should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetGoldFrequencyAboveOne() {
    IConfigurationBuilder builder = new MazeConfigurationBuilder().setGoldFrequency(1.1);
    fail("Invalid setter should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetGoldAmountNeg() {
    IConfigurationBuilder builder = new MazeConfigurationBuilder().setGoldAmount(-1);
    fail("Invalid setter should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetTargetRoomCountNeg() {
    IConfigurationBuilder builder = new MazeConfigurationBuilder().setThiefFrequency(-1);
    fail("Invalid setter should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetTargetRoomCountOOB() {
    IConfigurationBuilder builder = new MazeConfigurationBuilder().setTargetEdgeCount(500);
    fail("Invalid setter should have failed.");
  }
}
