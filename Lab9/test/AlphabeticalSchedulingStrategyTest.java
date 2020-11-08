import org.junit.Before;
import org.junit.Test;

import assignments.AlphabeticalSchedulingStrategy;
import assignments.Assignment;
import assignments.AssignmentList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Tests for alphabetical scheduling strategy.
 */
public class AlphabeticalSchedulingStrategyTest {
  private Assignment one;
  private Assignment two;
  private Assignment three;
  private AssignmentList assignmentList;

  @Before
  public void setup() {
    this.one = new Assignment("one");
    this.two = new Assignment("two");
    this.three = new Assignment("three");
    this.assignmentList = new AssignmentList();
  }

  @Test
  public void testValidSchedule() {
    this.one.setStart(3, 3, 3);
    this.two.setStart(2, 2, 2);
    this.three.setStart(1, 1, 1);
    this.assignmentList.add(this.one);
    this.assignmentList.add(this.two);
    this.assignmentList.add(this.three);
    this.assignmentList.scheduleAssignments(new AlphabeticalSchedulingStrategy());
    assertEquals("alphabetical", this.assignmentList.getOrdering());
    assertEquals(this.assignmentList.get(0), this.one);
    assertEquals(this.assignmentList.get(1), this.three);
    assertEquals(this.assignmentList.get(2), this.two);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSchedule() {
    this.assignmentList.scheduleAssignments(null);
    fail("Invalid schedule() should have failed.");
  }
}
