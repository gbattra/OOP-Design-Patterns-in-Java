package birds;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import birds.enums.BirdDiet;
import birds.enums.BirdType;
import birds.interfaces.IBird;
import birds.models.Bird;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


public class BirdTest {
  @Test
  public void testValidConstructorBirdTypes() {
    try {
      IBird bird = new Bird(
              "Rex",
              BirdType.EAGLE,
              new ArrayList<>(Arrays.asList(
                              BirdDiet.SMALL_MAMMALS,
                              BirdDiet.FISH,
                              BirdDiet.OTHER_BIRDS)),
              2);
      // do nothing, test passes
    } catch (IllegalArgumentException e) {
      fail("Valid constructor should not have thrown exception.");
    }
  }

  @Test
  public void testInvalidConstructorName() {
    try {
      IBird bird = new Bird(
              "",
              BirdType.EAGLE,
              new ArrayList<>(Arrays.asList(
                              BirdDiet.SMALL_MAMMALS,
                              BirdDiet.FISH,
                              BirdDiet.OTHER_BIRDS)),
              2);
      fail("Invalid constructor should have failed. Name must not be empty.");
    } catch (IllegalArgumentException e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidConstructorDiet() {
    try {
      IBird bird = new Bird(
              "",
              BirdType.EAGLE,
              new ArrayList<>(Arrays.asList(
                              BirdDiet.SMALL_MAMMALS,
                              BirdDiet.FISH,
                              BirdDiet.OTHER_BIRDS,
                              BirdDiet.BUDS,
                              BirdDiet.FRUIT)),
              2);
      fail("Invalid constructor should have failed. Diet cannot exceed 4 items.");
    } catch (IllegalArgumentException e) {
      // do nothing, test passes
    }

    try {
      IBird bird = new Bird(
              "",
              BirdType.EAGLE,
              new ArrayList<>(Arrays.asList(
                              BirdDiet.SMALL_MAMMALS)),
              2);
      fail("Invalid constructor should have failed. Diet must contain at least 2 items.");
    } catch (IllegalArgumentException e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidConstructorWingCount() {
    try {
      IBird bird = new Bird(
              "",
              BirdType.EAGLE,
              new ArrayList<>(Arrays.asList(
                              BirdDiet.SMALL_MAMMALS,
                              BirdDiet.FISH,
                              BirdDiet.OTHER_BIRDS)),
              3);
      fail("Invalid constructor should have failed. Wing count cannot be greater than 2.");
    } catch (IllegalArgumentException e) {
      // do nothing, test passes
    }

    try {
      IBird bird = new Bird(
              "",
              BirdType.EAGLE,
              new ArrayList<>(Arrays.asList(
                              BirdDiet.SMALL_MAMMALS,
                              BirdDiet.FISH,
                              BirdDiet.OTHER_BIRDS)),
              -1);
      fail("Invalid constructor should have failed. Wing count cannot be negative.");
    } catch (IllegalArgumentException e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testAccessors() {
    ArrayList<BirdDiet> diet = new ArrayList<>(Arrays.asList(
            BirdDiet.SMALL_MAMMALS,
            BirdDiet.FISH,
            BirdDiet.OTHER_BIRDS));

    IBird bird = new Bird(
            "Rex",
            BirdType.EAGLE,
            diet,
            2);

    assertEquals("Rex", bird.getName());
    assertEquals(BirdType.EAGLE, bird.getType());
    assertEquals(BirdType.EAGLE.classification, bird.getClassification());
    assertEquals(diet, bird.getDiet());
    assertEquals(2, bird.getWingCount());
    assertEquals(BirdType.EAGLE.isExtinct, bird.isExtinct());
  }

  @Test
  public void testDescribe() {
    ArrayList<BirdDiet> diet = new ArrayList<>(Arrays.asList(
            BirdDiet.SMALL_MAMMALS,
            BirdDiet.FISH,
            BirdDiet.OTHER_BIRDS));

    IBird bird = new Bird(
            "Rex",
            BirdType.EAGLE,
            diet,
            2);

    String expectedDescription = String.format(
            "This bird's name is %s. %s is a %s, which belongs to the classification %s. %s " +
            "%s likes to eat %s.",
            "Rex",
            "Rex",
            BirdType.EAGLE.label,
            BirdType.EAGLE.classification.label,
            BirdType.EAGLE.classification.description,
            "Rex",
            "Small mammals, Fish, Other birds");

    assertEquals(expectedDescription, bird.describe());
  }
}
