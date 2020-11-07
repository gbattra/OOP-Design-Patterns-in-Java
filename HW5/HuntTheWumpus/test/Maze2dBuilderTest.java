import org.junit.Test;

import maze.interfaces.Builder;
import maze.models.Maze2dBuilder;
import maze.models.MazeCoordinates;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Test for the Maze2dBuilder.
 */
public class Maze2dBuilderTest {
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetColumnCountZero() {
    Builder builder = new Maze2dBuilder().setColumnCount(0);
    fail("Invalid setter should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetRowCountZero() {
    Builder builder = new Maze2dBuilder().setRowCount(0);
    fail("Invalid setter should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetColumnCountNeg() {
    Builder builder = new Maze2dBuilder().setColumnCount(-1);
    fail("Invalid setter should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetColumnCountOOB() {
    Builder builder = new Maze2dBuilder().setColumnCount(66);
    fail("Invalid setter should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetRowCountNeg() {
    Builder builder = new Maze2dBuilder().setRowCount(-1);
    fail("Invalid setter should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetRowCountOOB() {
    Builder builder = new Maze2dBuilder().setRowCount(66);
    fail("Invalid setter should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetStartNegCol() {
    Builder builder = new Maze2dBuilder().setStart(-1, 0);
    fail("Invalid setter should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetStartNegRow() {
    Builder builder = new Maze2dBuilder().setStart(0, -1);
    fail("Invalid setter should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetExitNegRow() {
    Builder builder = new Maze2dBuilder().setGoal(0, -1);
    fail("Invalid setter should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetExitNegCol() {
    Builder builder = new Maze2dBuilder().setGoal(-1, 0);
    fail("Invalid setter should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetExitOOBCol() {
    Builder builder = new Maze2dBuilder().setGoal(15, 0);
    fail("Invalid setter should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetExitOOBRow() {
    Builder builder = new Maze2dBuilder().setGoal(0, 15);
    fail("Invalid setter should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetStartOOBRow() {
    Builder builder = new Maze2dBuilder().setStart(0, 15);
    fail("Invalid setter should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetStartOOBCol() {
    Builder builder = new Maze2dBuilder().setStart(15, 0);
    fail("Invalid setter should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetThiefFrequencyNeg() {
    Builder builder = new Maze2dBuilder().setThiefFrequency(-0.3);
    fail("Invalid setter should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetThiefFrequencyAboveOne() {
    Builder builder = new Maze2dBuilder().setThiefFrequency(1.1);
    fail("Invalid setter should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetThiefPenaltyNeg() {
    Builder builder = new Maze2dBuilder().setThiefPenalty(-0.3);
    fail("Invalid setter should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetThiefPenaltyAboveOne() {
    Builder builder = new Maze2dBuilder().setThiefPenalty(1.1);
    fail("Invalid setter should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetGoldFrequencyNeg() {
    Builder builder = new Maze2dBuilder().setGoldFrequency(-0.3);
    fail("Invalid setter should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetGoldFrequencyAboveOne() {
    Builder builder = new Maze2dBuilder().setGoldFrequency(1.1);
    fail("Invalid setter should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetGoldAmountNeg() {
    Builder builder = new Maze2dBuilder().setGoldAmount(-1);
    fail("Invalid setter should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetTargetRoomCountNeg() {
    Builder builder = new Maze2dBuilder().setThiefFrequency(-1);
    fail("Invalid setter should have failed.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetTargetRoomCountOOB() {
    Builder builder = new Maze2dBuilder().setTargetEdgeCount(500);
    fail("Invalid setter should have failed.");
  }

  @Test
  public void testValidSettersGetters() {
    try {
      Builder builder = new Maze2dBuilder()
              .setColumnCount(5)
              .setRowCount(5)
              .setStart(0, 0)
              .setGoal(4, 4)
              .setThiefPenalty(0.3)
              .setThiefFrequency(0.4)
              .setGoldFrequency(0.5)
              .setGoldAmount(15)
              .setTargetEdgeCount(3)
              .setRandomSeed(1);
      assertEquals(5, builder.getColumnCount());
      assertEquals(5, builder.getRowCount());
      assertEquals(new MazeCoordinates(0,0), builder.getStart());
      assertEquals(new MazeCoordinates(4,4), builder.getGoal());
      assertEquals(0.3, builder.getThiefPenalty(), .0001);
      assertEquals(0.4, builder.getThiefFrequency(), .0001);
      assertEquals(0.5, builder.getGoldFrequency(), .0001);
      assertEquals(15, builder.getGoldAmount());
      assertEquals(3, builder.getTargetEdgeCount());
      assertEquals(1, builder.getRandomSeed());
    } catch (Exception e) {
      fail("Valid setters should not have failed.");
    }
  }
}
