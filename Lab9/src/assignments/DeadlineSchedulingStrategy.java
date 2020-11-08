package assignments;

import java.util.List;

/**
 * Concrete implementation of scheduling strategy. Uses deadline ordering.
 */
public class DeadlineSchedulingStrategy implements SchedulingStrategy {
  @Override
  public String schedule(List<Assignment> assignments) throws IllegalArgumentException {
    if (assignments == null) {
      throw new IllegalArgumentException("Assignments list is null.");
    }
    assignments.sort(this::compareAssignments);
    return "deadline";
  }

  /**
   * Compares the two assignments using their deadlines.
   *
   * @param one first assignment to compare
   * @param two second assignment to compare
   * @return comparison int
   */
  private int compareAssignments(Assignment one, Assignment two) {
    if (one.getEndDate().compareTo(two.getEndDate()) > 0) {
      return 1;
    }
    if (one.getEndDate().compareTo(two.getEndDate()) < 0) {
      return -1;
    }
    return one.getDescription().compareTo(two.getDescription());
  }
}
