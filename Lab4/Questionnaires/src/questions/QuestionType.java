package questions;

/**
 * Enum representing types of questions and their respective ranks.
 */
public enum QuestionType {
  TRUE_FALSE(1),
  MULTIPLE_CHOICE(2),
  MULTIPLE_SELECT(3),
  LIKERT(4);

  public final int rank;

  QuestionType(int rank) {
    this.rank = rank;
  }
}
