package assignments;

import java.util.List;

/**
 * Concrete implementation of scheduling strategy. Uses assigned ordering.
 */
public class AssignedSchedulingStrategy implements SchedulingStrategy {
  @Override
  public String schedule(List<Assignment> assignments) throws IllegalArgumentException {
    if (assignments == null) {
      throw new IllegalArgumentException("Assignments list is null.");
    }
    assignments.sort(this::compareAssignments);
    return "assigned";
  }

  /**
   * Compares the two assignments using their start date.
   *
   * @param one first assignment to compare
   * @param two second assignment to compare
   * @return comparison int
   */
  private int compareAssignments(Assignment one, Assignment two) {
    if (one.getStartDate().compareTo(two.getStartDate()) > 0) {
      return 1;
    }
    if (one.getStartDate().compareTo(two.getStartDate()) < 0) {
      return -1;
    }
    return one.getDescription().compareTo(two.getDescription());
  }
}
