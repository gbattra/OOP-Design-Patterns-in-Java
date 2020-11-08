package assignments;

import java.util.List;

/**
 * Concrete implementation of scheduling strategy. Uses alphabetical ordering.
 */
public class AlphabeticalSchedulingStrategy implements SchedulingStrategy {
  @Override
  public String schedule(List<Assignment> assignments) {
    assignments.sort(this::compareAssignments);
    return "alphabetical";
  }

  /**
   * Compares the two assignments using their desc.
   *
   * @param one first assignment to compare
   * @param two second assignment to compare
   * @return comparison int
   */
  private int compareAssignments(Assignment one, Assignment two) {
    return one.getDescription().compareTo(two.getDescription());
  }
}
