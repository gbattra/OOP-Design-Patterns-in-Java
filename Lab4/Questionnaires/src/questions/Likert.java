package questions;

/**
 * Implementation of a Likert question. Options are fixed and there is no correct answer.
 */
public class Likert extends AbstractQuestion {
  private static final QuestionType TYPE = QuestionType.LIKERT;
  private static final String[] OPTIONS = {
      "Strongly Agree",
      "Agree",
      "Neither Agree Nor Disagree",
      "Disagree",
      "Strongly Disagree" };

  /**
   * Constructor for Likert question.
   *
   * @param args text, answer and options Strings
   * @throws IllegalArgumentException if the text is empty
   */
  public Likert(String... args) {
    super(args[0], "", OPTIONS[0], OPTIONS[1], OPTIONS[2], OPTIONS[3], OPTIONS[4]);
  }

  @Override
  public QuestionType getType() {
    return TYPE;
  }

  @Override
  public String answer(String choice) {
    String[] splitChoice = choice.split(" ");
    if (splitChoice.length > 1) {
      return AnswerType.INCORRECT.label;
    }

    try {
      int intChoice = Integer.parseInt(splitChoice[0]);
      if (intChoice > this.options.length) {
        return AnswerType.INCORRECT.label;
      }

      return AnswerType.CORRECT.label;
    } catch (Exception e) {
      return AnswerType.INCORRECT.label;
    }
  }
}
