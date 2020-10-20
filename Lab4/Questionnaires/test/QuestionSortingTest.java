import org.junit.Test;

import java.util.Arrays;

import questions.Question;
import questions.Likert;
import questions.MultipleChoice;
import questions.MultipleSelect;
import questions.TrueFalse;

import static org.junit.Assert.assertEquals;

/**
 * Tests the sorting mechanism of Question objects.
 */
public class QuestionSortingTest {
  @Test
  public void testSortingLexicographically() {
    Question trueFalseOne = new TrueFalse(
            "Dogs could be squirrels.",
            "False",
            "");
    Question trueFalseTwo = new TrueFalse(
            "A squirrel is not a dog.",
            "True");
    Question[] questionnaire = { trueFalseOne, trueFalseTwo };
    Arrays.sort(questionnaire);
    assertEquals(trueFalseTwo, questionnaire[0]);
  }

  @Test
  public void testSortingByType() {
    Question trueFalse = new TrueFalse(
            "Dogs could be squirrels.",
            "False",
            "");
    Question multipleChoice = new MultipleChoice(
            "What is the correct answer?",
            "C",
            "A","B","C","D","E");
    Question multipleSelect = new MultipleSelect(
            "What are the correct answers?",
            "A C",
            "A","B","C","D","E");
    Question likert = new Likert("Waffles are delicious.");
    Question[] questionnaire = { multipleSelect, likert, trueFalse, multipleChoice };
    Arrays.sort(questionnaire);
    assertEquals(trueFalse, questionnaire[0]);
    assertEquals(multipleChoice, questionnaire[1]);
    assertEquals(multipleSelect, questionnaire[2]);
    assertEquals(likert, questionnaire[3]);
  }
}
