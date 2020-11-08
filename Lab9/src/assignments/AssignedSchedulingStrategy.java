package assignments;

import java.util.List;

public class AssignedSchedulingStrategy implements SchedulingStrategy {
  @Override
  public String schedule(List<Assignment> assignments) throws IllegalArgumentException {
    if (assignments == null) {
      throw new IllegalArgumentException("Assignments list is null.");
    }
    assignments.sort(this::compareAssignments);
    return "assigned";
  }

  private int compareAssignments(Assignment one, Assignment two) {
    if (one.getStartDate().compareTo(two.getStartDate()) > 0) {
      return 1;
    }
    if (one.getStartDate().compareTo(two.getStartDate()) < 0) {
      return -1;
    }
    return one.toString().compareTo(two.toString());
  }
}
