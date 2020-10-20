package questions;

/**
 * Enum representing possible answer responses.
 */
public enum AnswerType {
  CORRECT("Correct"),
  INCORRECT("Incorrect");

  public final String label;

  AnswerType(String label) {
    this.label = label;
  }
}
