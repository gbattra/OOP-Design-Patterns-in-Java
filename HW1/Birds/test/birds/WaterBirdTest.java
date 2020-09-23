package birds;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import birds.enums.BirdDiet;
import birds.enums.BirdType;
import birds.models.Bird;
import birds.models.WaterBird;

import static org.junit.Assert.fail;

public class WaterBirdTest {
  @Test
  public void testValidConstructorBirdTypes() {
    ArrayList<BirdType> birdTypes = new ArrayList<>(Arrays.asList(
            BirdType.GREAT_AUK,
            BirdType.HORNED_PUFFIN,
            BirdType.AFRICAN_JACANA,
            BirdType.GOOSE,
            BirdType.DUCK,
            BirdType.SWAN));

    birdTypes.forEach(birdType -> {
      try {
        WaterBird bird = new WaterBird(
                "Rex",
                birdType,
                new ArrayList<>(Arrays.asList(
                        BirdDiet.FISH,
                        BirdDiet.INSECTS,
                        BirdDiet.LARVAE)),
                2,
                "Moosehead Lake");
        // do nothing, test passes
      } catch (IllegalArgumentException e) {
        fail("Valid constructor should not have thrown exception.");
      }
    });
  }

  @Test
  public void testInvalidConstructorBirdType() {
    ArrayList<BirdType> invalidBirdTypes = new ArrayList<>(Arrays.asList(
            BirdType.EAGLE,
            BirdType.HAWK,
            BirdType.OSPREY,
            BirdType.OWL,
            BirdType.HOMING_PIGEON,
            BirdType.PASSENGER_PIGEON,
            BirdType.KIWIS,
            BirdType.MOA,
            BirdType.EMU,
            BirdType.ROSE_RING_PARAKEET,
            BirdType.GRAY_PARROT,
            BirdType.SULFUR_CRESTED_COCKATOO));

    invalidBirdTypes.forEach(invalidBirdType -> {
      try {
        WaterBird bird = new WaterBird(
                "Rex",
                invalidBirdType,
                new ArrayList<>(Arrays.asList(
                        BirdDiet.FISH,
                        BirdDiet.INSECTS,
                        BirdDiet.LARVAE)),
                2,
                "Moosehead Lake");
        fail("Invalid constructor should have failed. Invalid BirdType for Bird class.");
      } catch (IllegalArgumentException e) {
        // do nothing, test passes
      }
    });
  }
}
