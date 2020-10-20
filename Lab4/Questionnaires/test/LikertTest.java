import org.junit.Test;

import java.util.Arrays;

import questions.AnswerType;
import questions.Question;
import questions.Likert;
import questions.QuestionType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Tests functionality for Likert questions.
 */
public class LikertTest {
  @Test
  public void testValidConstructor() {
    try {
      Question question = new Likert("Waffles are delicious.");
    } catch (Exception e) {
      fail("Valid constructor should not have failed.");
    }
  }

  @Test
  public void testInvalidConstructorEmptyText() {
    try {
      Question question = new Likert("");
      fail("Invalid constructor should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testGetType() {
    Question question = new Likert("Waffles are delicious.");
    assertEquals(QuestionType.LIKERT, question.getType());
  }

  @Test
  public void testGetOptions() {
    String[] expected = {
        "Strongly Agree",
        "Agree",
        "Neither Agree Nor Disagree",
        "Disagree",
        "Strongly Disagree" };
    Question question = new Likert("Waffles are delicious.");
    assertEquals(Arrays.asList(expected), Arrays.asList(question.getOptions()));
  }

  @Test
  public void testGetText() {
    Question question = new Likert("Waffles are delicious.");
    assertEquals(
            "Waffles are delicious.",
            question.getText());
  }

  @Test
  public void testValidAnswer() {
    Question question = new Likert("Waffles are delicious.");
    try {
      String answer = question.answer("1");
      assertEquals(AnswerType.CORRECT.label, answer);
    } catch (Exception e) {
      fail("Valid answer should not have failed.");
    }
  }

  @Test
  public void testInvalidAnswerOutOfRange() {
    Question question = new Likert("Waffles are delicious.");
    String answer = question.answer("6");
    assertEquals(AnswerType.INCORRECT.label, answer);
  }

  @Test
  public void testInvalidAnswerInvalidText() {
    Question question = new Likert("Waffles are delicious.");
    try {
      String answer = question.answer("Strongly agree.");
      assertEquals(AnswerType.INCORRECT.label, answer);
    } catch (Exception e) {
      fail("Valid answer should not have failed.");
    }
  }
}
