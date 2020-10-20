package questions;

/**
 * Implementation of a True or False question. A yes/no question that has no options and takes
 * has a correct answer.
 */
public class TrueFalse extends AbstractQuestion {
  private static final QuestionType TYPE = QuestionType.TRUE_FALSE;

  /**
   * Constructor for True/False question.
   *
   * @param args text, answer and options Strings
   * @throws IllegalArgumentException when the text description is empty
   */
  public TrueFalse(String... args) {
    super(args);
  }

  @Override
  public QuestionType getType() {
    return TYPE;
  }

  @Override
  public String answer(String choice) throws IllegalArgumentException {
    if (!choice.toLowerCase().equals("true")
        && !choice.toLowerCase().equals("false")) {
      return AnswerType.INCORRECT.label;
    }

    return choice.equalsIgnoreCase(this.answer)
            ? AnswerType.CORRECT.label : AnswerType.INCORRECT.label;
  }
}
