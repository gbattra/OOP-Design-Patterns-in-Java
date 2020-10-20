package questions;

import java.util.Arrays;

/**
 * Implementation of a Multiple Select question. User can select multiple choices from the list of
 * options and there is a correct answer.
 */
public class MultipleSelect extends AbstractQuestion {
  private static final QuestionType TYPE = QuestionType.MULTIPLE_SELECT;

  /**
   * Constructor for multiple select type questions.
   *
   * @param args text, answer and options Strings
   * @throws IllegalArgumentException when text is empty, or options list is too small/big
   */
  public MultipleSelect(String... args) {
    super(args);
  }

  @Override
  public QuestionType getType() {
    return TYPE;
  }

  @Override
  public String answer(String choice) {
    String[] splitChoice = choice.split(" ");
    if (splitChoice.length > this.options.length) {
      return AnswerType.INCORRECT.label;
    }

    String[] answers = this.answer.split(" ");
    for (String c : splitChoice) {
      try {
        int intChoice = Integer.parseInt(c);
        if (intChoice > this.options.length) {
          return AnswerType.INCORRECT.label;
        }
        if (Arrays.stream(answers).noneMatch(a -> Integer.parseInt(a) == intChoice)) {
          return AnswerType.INCORRECT.label;
        }
      } catch (Exception e) {
        return AnswerType.INCORRECT.label;
      }
    }

    return AnswerType.CORRECT.label;
  }
}
