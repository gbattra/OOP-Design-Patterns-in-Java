import org.junit.Test;

import questions.AnswerType;
import questions.Question;
import questions.TrueFalse;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Tests functionality for TrueFalse questions.
 */
public class TrueFalseTest {
  @Test
  public void testValidConstructorTrue() {
    try {
      Question question = new TrueFalse(
              "Is 2 + 2 equal to 4?",
              "True");
      // do nothing, test passes
    } catch (Exception e) {
      fail("Valid constructor should not have failed.");
    }
  }

  @Test
  public void testValidConstructorFalse() {
    try {
      Question question = new TrueFalse(
              "Is 2 + 2 equal to 4?",
              "True");
      // do nothing, test passes
    } catch (Exception e) {
      fail("Valid constructor should not have failed.");
    }
  }

  @Test
  public void testInvalidConstructorEmptyText() {
    try {
      Question question = new TrueFalse(
              "",
              "True");
      fail("Invalid constructor should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidConstructorEmptyAnswer() {
    try {
      Question question = new TrueFalse(
              "Is 2 + 2 equal to 4?",
              "");
      fail("Invalid constructor should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testValidAnswer() {
    try {
      Question question = new TrueFalse(
              "Is 2 + 2 equal to 4?",
              "True");
      String answer = question.answer("True");
      assertEquals(AnswerType.CORRECT.label, answer);
    } catch (Exception e) {
      fail("Valid answer should not have failed.");
    }
  }

  @Test
  public void testIncorrectAnswer() {
    try {
      Question question = new TrueFalse(
              "Is 2 + 2 equal to 4?",
              "True");
      String answer = question.answer("False");
      assertEquals(AnswerType.INCORRECT.label, answer);
    } catch (Exception e) {
      fail("Valid answer should not have failed.");
    }
  }
}
