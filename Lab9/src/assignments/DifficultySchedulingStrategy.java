package assignments;

import java.util.List;

public class DifficultySchedulingStrategy implements SchedulingStrategy {
  @Override
  public String schedule(List<Assignment> assignments) throws IllegalArgumentException {
    if (assignments == null) {
      throw new IllegalArgumentException("Assignments list is null.");
    }
    assignments.sort(this::compareAssignments);
    return "difficulty";
  }

  private int compareAssignments(Assignment one, Assignment two) {
    if (one.getDifficulty() > two.getDifficulty()) {
      return 1;
    }
    if (one.getDifficulty() < two.getDifficulty()) {
      return -1;
    }
    return one.getDescription().compareTo(two.getDescription());
  }
}
