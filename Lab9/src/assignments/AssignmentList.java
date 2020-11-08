package assignments;

import java.util.ArrayList;
import java.util.List;

/**
 * A list of task that need to be completed.
 */
public class AssignmentList {

  private List<Assignment> tasks;
  private String ordering;

  /** Default constructor. */
  public AssignmentList() {
    tasks = new ArrayList<>();
    ordering = "assigned";
  }

  /**
   * Add a task to the task list.
   * 
   * @param t the task
   */
  public void add(Assignment t) {
    tasks.add(t);
  }

  /**
   * Get an assignment at an index.
   *
   * @param index the index to query by
   * @return the assignment at that index
   */
  public Assignment get(int index) {
    return this.tasks.get(index);
  }

  public void scheduleAssignments(SchedulingStrategy strategy) throws IllegalArgumentException {
    if (strategy == null) {
      throw new IllegalArgumentException("Provided strategy is null.");
    }
    this.ordering = strategy.schedule(this.tasks);
  }

  @Override
  public String toString() {
    StringBuffer sb = new StringBuffer("Ordered by ");
    sb.append(ordering);
    sb.append("\n");
    for (int i = 0 ; i < tasks.size() ; i++) {
      sb.append(i + 1);
      sb.append(" -- ");
      sb.append(tasks.get(i));
      sb.append("\n");
    }
    return sb.toString();
  }
}
