import org.junit.Before;
import org.junit.Test;

import java.util.List;

import assignments.AssignedSchedulingStrategy;
import assignments.Assignment;
import assignments.AssignmentList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AssignedSchedulingStrategyTest {
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
    this.assignmentList.scheduleAssignments(new AssignedSchedulingStrategy());
    assertTrue(this.assignmentList.toString().startsWith("Ordered by assigned"));
    assertEquals(this.assignmentList.get(0), this.three);
    assertEquals(this.assignmentList.get(1), this.two);
    assertEquals(this.assignmentList.get(2), this.one);
  }
}
