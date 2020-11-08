package assignments;

import java.util.List;

/**
 * Interface for a scheduling strategy implementation.
 */
public interface SchedulingStrategy {
  /**
   * Schedules the list of assignments using a specific strategy
   * (i.e. alphabetical, by date, by difficulty).
   *
   * @param assignments the assignments to schedule
   * @return a string indicating the strategy used
   */
  String schedule(List<Assignment> assignments);
}
