import org.junit.Test;

import java.util.Arrays;

import questions.AnswerType;
import questions.Question;
import questions.MultipleChoice;
import questions.QuestionType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Tests functionality for MultipleChoice questions.
 */
public class MultipleChoiceTest {
  @Test
  public void testValidConstructor() {
    try {
      Question question = new MultipleChoice(
              "What is the correct answer?",
              "3",
              "A","B","C","D","E");
    } catch (Exception e) {
      fail("Valid constructor should not have failed.");
    }
  }

  @Test
  public void testInvalidConstructorEmptyText() {
    try {
      Question question = new MultipleChoice(
              "",
              "3",
              "A","B","C","D","E");
      fail("Invalid constructor should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidConstructorTooFewOptions() {
    try {
      Question question = new MultipleChoice(
              "What is the correct answer?",
              "3",
              "");
      fail("Invalid constructor should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidConstructorTooManyOptions() {
    try {
      Question question = new MultipleChoice(
              "What is the correct answer?",
              "3",
              "A","B","C","D","E","F","G","H","I");
      fail("Invalid constructor should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidConstructorEmptyAnswer() {
    try {
      Question question = new MultipleChoice(
              "What is the correct answer?",
              "",
              "A","B","C","D","E");
      fail("Invalid constructor should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testGetType() {
    Question question = new MultipleChoice(
            "What is the correct answer?",
            "3",
            "A","B","C","D","E");
    assertEquals(QuestionType.MULTIPLE_CHOICE, question.getType());
  }

  @Test
  public void testGetOptions() {
    String[] expected = { "A","B","C","D","E" };
    Question question = new MultipleChoice(
            "What is the correct answer?",
            "3",
            "A","B","C","D","E");
    assertEquals(Arrays.asList(expected), Arrays.asList(question.getOptions()));
  }

  @Test
  public void testGetText() {
    Question question = new MultipleChoice(
            "What is the correct answer?",
            "3",
            "A","B","C","D","E");
    assertEquals(
            "What is the correct answer?",
            question.getText());
  }

  @Test
  public void testCorrectAnswer() {
    Question question = new MultipleChoice(
            "What is the correct answer?",
            "3",
            "A","B","C","D","E");
    String answer = question.answer("3");
    assertEquals(AnswerType.CORRECT.label, answer);
  }

  @Test
  public void testIncorrectAnswer() {
    Question question = new MultipleChoice(
            "What is the correct answer?",
            "3",
            "A","B","C","D","E");
    String answer = question.answer("2");
    assertEquals(AnswerType.INCORRECT.label, answer);
  }

  @Test
  public void testInvalidAnswerOutOfRange() {
    Question question = new MultipleChoice(
            "What is the correct answer?",
            "3",
            "A","B","C","D","E");
    String answer = question.answer("6");
    assertEquals(AnswerType.INCORRECT.label, answer);
  }

  @Test
  public void testInvalidAnswerInvalidText() {
    Question question = new MultipleChoice(
            "What is the correct answer?",
            "3",
            "A","B","C","D","E");
    String answer = question.answer("C");
    assertEquals(AnswerType.INCORRECT.label, answer);
  }

  @Test
  public void testInvalidAnswerMultipleAnswers() {
    Question question = new MultipleChoice(
            "What is the correct answer?",
            "3",
            "A","B","C","D","E");
    String answer = question.answer("1 4");
    assertEquals(AnswerType.INCORRECT.label, answer);
  }
}
