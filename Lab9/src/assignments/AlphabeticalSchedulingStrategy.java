package assignments;

import java.util.List;

public class AlphabeticalSchedulingStrategy implements SchedulingStrategy {
  @Override
  public String schedule(List<Assignment> assignments) {
    assignments.sort(this::compareAssignments);
    return "alphabetical";
  }

  private int compareAssignments(Assignment one, Assignment two) {
    return one.getDescription().compareTo(two.getDescription());
  }
}
