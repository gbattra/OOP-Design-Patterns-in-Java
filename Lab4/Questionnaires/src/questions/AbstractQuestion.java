package questions;

import java.util.Arrays;

/**
 * Abstract representation of a Question object.
 */
public abstract class AbstractQuestion implements Question {
  protected static final int OPTIONS_MIN = 3;
  protected static final int OPTIONS_MAX = 8;

  protected final String text;
  protected final String answer;
  protected final String[] options;

  /**
   * Constructor for questions with no "right" answer, such as Likert questions.
   *
   * @param args the text description of the question
   * @throws IllegalArgumentException if the text is empty
   */
  public AbstractQuestion(
          String... args) throws IllegalArgumentException {
    this.text = args[0];
    this.answer = args.length > 1 ? args[1] : "";
    this.options = args.length > 2 ? Arrays.copyOfRange(args, 2, args.length) : new String[]{};

    if (this.text.isEmpty()) {
      throw new IllegalArgumentException("Text cannot be empty.");
    }

    if (this.getType() != QuestionType.LIKERT && this.answer.isEmpty()) {
      throw new IllegalArgumentException("Answer must not be empty");
    }

    if (this.options.length > OPTIONS_MAX) {
      throw new IllegalArgumentException(
              String.format("Option list cannot exceed %s items.", OPTIONS_MAX));
    }

    if (this.options.length < OPTIONS_MIN && this.getType() != QuestionType.TRUE_FALSE) {
      throw new IllegalArgumentException(
              String.format("Option list must have at least %s items.", OPTIONS_MIN));
    }
  }

  public abstract QuestionType getType();

  public abstract String answer(String choice);

  public String[] getOptions() {
    return this.options;
  }

  public String getText() {
    return this.text;
  }

  public String getAnswer() {
    return this.answer;
  }

  /**
   * Compares one question to this. Uses rank to determine order, otherwise uses lexicographical
   * text comparison.
   *
   * @param other the other question to compare
   * @return the order for this object (-1/0/1)
   */
  @Override
  public int compareTo(Question other) {
    if (other.getType().rank < this.getType().rank) {
      return 1;
    }
    if (other.getType().rank > this.getType().rank) {
      return -1;
    }

    return this.text.compareTo(other.getText());
  }


  /**
   * Overrides the objects toString() method.
   *
   * @return the string representation of this object
   */
  @Override
  public String toString() {
    return String.format(
            "Question - %s - %s - %s - %s",
            this.getType(),
            this.getText(),
            this.getText(),
            this.getOptions().toString());
  }

  /**
   * Overrides the hashCode() method using toString().
   *
   * @return int the hash code
   */
  @Override
  public int hashCode() {
    return this.toString().hashCode();
  }

  /**
   * Overrides the equals() method using hashCode().
   *
   * @param other the object to compare
   * @return true/false are the objects equal
   */
  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }

    if (other instanceof Question) {
      Question question = (Question) other;
      return question.hashCode() == this.hashCode();
    }

    return false;
  }
}
