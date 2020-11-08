package assignments;

import java.time.LocalDate;
import java.time.Period;

/**
 * A class that represents an assignment for class. Assignments are compared by
 * their descriptions.
 */
public class Assignment implements Comparable<Assignment> {

  private static int totalCount;
  
  private final int number;
  private final String description;
  private LocalDate start;
  private LocalDate end;

  static {
    totalCount = 0;
  }
  
  /**
   * Constructor for a task that is due now.
   *
   * @param description the description
   */
  public Assignment(String description) {
    this.description = description;
    this.start = LocalDate.now();
    this.end = LocalDate.now();
    this.number = Assignment.totalCount++;
  }

  public static int getTotalCount() {
    return totalCount;
  }

  public String getDescription() {
    return description;
  }

  public LocalDate getStartDate() {
    return start;
  }

  public LocalDate getEndDate() {
    return end;
  }

  public int getNumber() {
    return number;
  }

  /**
   * Sets the start date.
   *
   * @param month the month
   * @param day   the day
   * @param year  the year
   */
  public void setStart(int month, int day, int year) {
    LocalDate start = LocalDate.of(year, month, day);
    if (start.compareTo(end) > 0) {
      throw new IllegalArgumentException("Start date is after the deadline.");
    }
    this.start = start;
  }
  
  /**
   * Sets the due date.
   *
   * @param month the month
   * @param day   the day
   * @param year  the year
   */
  public void setDeadline(int month, int day, int year) {
    LocalDate end = LocalDate.of(year, month, day);
    if (start.compareTo(end) > 0) {
      throw new IllegalArgumentException("Deadline is before assignment start date.");
    }
    this.end = end;
  }

  public int getDifficulty() {
    return Period.between(start, end).getDays();
  }

  @Override
  public int compareTo(Assignment o) {
    return description.compareTo(o.description);
  }

  @Override
  public String toString() {
    return String.format("%s, starting %s, ending %s", this.description, this.getStartDate(),
        this.getEndDate());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Assignment)) {
      return false;
    }
    Assignment other = (Assignment) o;
    return this.description.equals(other.description) && this.start.equals(other.start)
        && this.end.equals(other.end);
  }

  @Override
  public int hashCode() {
    final int prime = 17;
    int hash = 1;
    hash = prime * hash + this.description.hashCode();
    hash = prime * hash + this.start.hashCode();
    hash = prime * hash + this.end.hashCode();
    return hash;
  }
}
