package birds;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import birds.enums.BirdDiet;
import birds.enums.BirdType;
import birds.models.TalkingBird;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TalkingBirdTest {
  @Test
  public void testValidConstructorBirdTypes() {
    ArrayList<BirdType> birdTypes = new ArrayList<>(Arrays.asList(
            BirdType.ROSE_RING_PARAKEET,
            BirdType.GRAY_PARROT,
            BirdType.SULFUR_CRESTED_COCKATOO));

    birdTypes.forEach(birdType -> {
      try {
        TalkingBird bird = new TalkingBird(
                "Rex",
                birdType,
                new ArrayList<>(Arrays.asList(
                        BirdDiet.SMALL_MAMMALS,
                        BirdDiet.FISH,
                        BirdDiet.OTHER_BIRDS)),
                2,
                "Trampoline",
                new ArrayList<>(Arrays.asList(
                        "Hello",
                        "Okay",
                        "Maybe",
                        "Nope",
                        "Goodbye",
                        "Love")));
        // do nothing, test passes
      } catch (IllegalArgumentException e) {
        fail("Valid constructor should not have thrown exception.");
      }
    });
  }

  @Test
  public void testInvalidConstructorBirdType() {
    ArrayList<BirdType> invalidBirdTypes = new ArrayList<>(Arrays.asList(
            BirdType.GREAT_AUK,
            BirdType.HORNED_PUFFIN,
            BirdType.GOOSE,
            BirdType.DUCK,
            BirdType.SWAN,
            BirdType.EAGLE,
            BirdType.HAWK,
            BirdType.OSPREY,
            BirdType.OWL,
            BirdType.HOMING_PIGEON,
            BirdType.PASSENGER_PIGEON,
            BirdType.KIWIS,
            BirdType.MOA,
            BirdType.EMU));

    invalidBirdTypes.forEach(invalidBirdType -> {
      try {
        TalkingBird bird = new TalkingBird(
                "Rex",
                invalidBirdType,
                new ArrayList<>(Arrays.asList(
                        BirdDiet.BERRIES,
                        BirdDiet.BUDS,
                        BirdDiet.FRUIT)),
                2,
                "Trampoline",
                new ArrayList<>(Arrays.asList(
                        "Hello",
                        "Okay",
                        "Maybe",
                        "Nope",
                        "Goodbye",
                        "Love")));
        fail("Invalid constructor should have failed. Invalid BirdType for Bird class.");
      } catch (IllegalArgumentException e) {
        // do nothing, test passes
      }
    });
  }

  @Test
  public void testInvalidConstructorFavoriteWord() {
    try {
      TalkingBird bird = new TalkingBird(
              "Rex",
              BirdType.GRAY_PARROT,
              new ArrayList<>(Arrays.asList(
                      BirdDiet.BERRIES,
                      BirdDiet.BUDS,
                      BirdDiet.FRUIT)),
              2,
              "",
              new ArrayList<>(Arrays.asList(
                      "Hello",
                      "Okay",
                      "Maybe",
                      "Nope",
                      "Goodbye",
                      "Love")));
      fail("Invalid constructor should have failed. Invalid BirdType for Bird class.");
    } catch (IllegalArgumentException e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidConstructorVocabularyTooShort() {
    try {
      TalkingBird bird = new TalkingBird(
              "Rex",
              BirdType.GRAY_PARROT,
              new ArrayList<>(Arrays.asList(
                      BirdDiet.BERRIES,
                      BirdDiet.BUDS,
                      BirdDiet.FRUIT)),
              2,
              "Trampoline",
              new ArrayList<>());  // vocab must be greater than 0 words
      fail("Invalid constructor should have failed. Invalid BirdType for Bird class.");
    } catch (IllegalArgumentException e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidConstructorVocabularyTooLong() {
    try {
      TalkingBird bird = new TalkingBird(
              "Rex",
              BirdType.GRAY_PARROT,
              new ArrayList<>(Arrays.asList(
                      BirdDiet.BERRIES,
                      BirdDiet.BUDS,
                      BirdDiet.FRUIT)),
              2,
              "Trampoline",
              new ArrayList<>(101));  // vocab cannot be greater than 100 words
      fail("Invalid constructor should have failed. Invalid BirdType for Bird class.");
    } catch (IllegalArgumentException e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testAccessors() {
    TalkingBird bird = new TalkingBird(
            "Rex",
            BirdType.GRAY_PARROT,
            new ArrayList<>(Arrays.asList(
                    BirdDiet.BERRIES,
                    BirdDiet.BUDS,
                    BirdDiet.FRUIT)),
            2,
            "Trampoline",
            new ArrayList<>(Arrays.asList("One", "Two", "Three")));

    assertEquals("Trampoline", bird.getFavoriteWord());
    assertEquals(new ArrayList<>(Arrays.asList("One", "Two", "Three")), bird.getVocabulary());
  }
}
