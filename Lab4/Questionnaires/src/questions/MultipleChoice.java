package questions;

/**
 * Implementation of a Multiple Choice question. User makes a single choice from the list of options
 * and there is a correct answer.
 */
public class MultipleChoice extends AbstractQuestion {
  private static final QuestionType TYPE = QuestionType.MULTIPLE_CHOICE;

  /**
   * Constructor for multiple choice questions.
   *
   * @param args text, answer and options Strings
   * @throws IllegalArgumentException when text is empty, or options list is too small/big
   */
  public MultipleChoice(String... args) {
    super(args);
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

      return intChoice == Integer.parseInt(this.answer)
              ? AnswerType.CORRECT.label : AnswerType.INCORRECT.label;
    } catch (Exception e) {
      return AnswerType.INCORRECT.label;
    }
  }

}
