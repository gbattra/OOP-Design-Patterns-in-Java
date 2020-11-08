import org.junit.Before;
import org.junit.Test;

import assignments.Assignment;
import assignments.AssignmentList;
import assignments.DeadlineSchedulingStrategy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Tests for deadline scheduling strategy.
 */
public class DeadlineSchedulingStrategyTest {
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
    this.one.setStart(1, 1, 1);
    this.two.setStart(1, 1, 1);
    this.three.setStart(1, 1, 1);

    this.one.setDeadline(3, 3, 3);
    this.two.setDeadline(2, 2, 2);
    this.three.setDeadline(1, 1, 1);

    this.assignmentList.add(this.one);
    this.assignmentList.add(this.two);
    this.assignmentList.add(this.three);

    this.assignmentList.scheduleAssignments(new DeadlineSchedulingStrategy());
    assertEquals("deadline", this.assignmentList.getOrdering());
    assertEquals(this.assignmentList.get(0), this.three);
    assertEquals(this.assignmentList.get(1), this.two);
    assertEquals(this.assignmentList.get(2), this.one);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSchedule() {
    this.assignmentList.scheduleAssignments(null);
    fail("Invalid schedule() should have failed.");
  }
}
