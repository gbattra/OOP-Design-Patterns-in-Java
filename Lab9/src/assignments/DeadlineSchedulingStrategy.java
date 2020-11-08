package assignments;

import java.util.List;

public class DeadlineSchedulingStrategy implements SchedulingStrategy {
  @Override
  public String schedule(List<Assignment> assignments) throws IllegalArgumentException {
    if (assignments == null) {
      throw new IllegalArgumentException("Assignments list is null.");
    }
    assignments.sort(this::compareAssignments);
    return "deadline";
  }

  private int compareAssignments(Assignment one, Assignment two) {
    if (one.getEndDate().compareTo(two.getEndDate()) > 0) {
      return 1;
    }
    if (one.getEndDate().compareTo(two.getEndDate()) < 0) {
      return -1;
    }
    return one.toString().compareTo(two.toString());
  }
}
