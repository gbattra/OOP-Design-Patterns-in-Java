package questions;

/**
 * Interface for a question object. Each question has a type, text, options, and answers.
 */
public interface Question extends Comparable<Question> {
  /**
   * Accessor for the question type.
   *
   * @return the type of question
   */
  QuestionType getType();

  /**
   * Accessor for the question description.
   *
   * @return the text description of the question
   */
  String getText();

  /**
   * Accessor for the question's options.
   *
   * @return the list of options
   */
  String[] getOptions();

  /**
   * Accessor for the question's answer.
   *
   * @return the answer to the question
   */
  String getAnswer();

  /**
   * Method to "answer" a question. Compares input to the question answer and returns a String
   * indicating whether the answer was correct or incorrect.
   *
   * @param choice String the choice made
   * @return String indicating whether the choice was correct
   */
  String answer(String choice);
}
