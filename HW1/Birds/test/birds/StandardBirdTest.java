package birds;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import birds.enums.BirdDiet;
import birds.enums.BirdType;
import birds.models.StandardBird;

import static org.junit.Assert.fail;

public class StandardBirdTest {
  @Test
  public void testValidConstructorBirdTypes() {
    ArrayList<BirdType> birdTypes = new ArrayList<>(Arrays.asList(
            BirdType.EAGLE,
            BirdType.HAWK,
            BirdType.OSPREY,
            BirdType.OWL,
            BirdType.HOMING_PIGEON,
            BirdType.PASSENGER_PIGEON,
            BirdType.KIWIS,
            BirdType.MOA,
            BirdType.EMU));

    birdTypes.forEach(birdType -> {
      try {
        StandardBird bird = new StandardBird(
                "Rex",
                birdType,
                new ArrayList<>(Arrays.asList(
                        BirdDiet.SMALL_MAMMALS,
                        BirdDiet.FISH,
                        BirdDiet.OTHER_BIRDS)),
                2);
        // do nothing, test passes
      } catch (IllegalArgumentException e) {
        fail("Valid constructor should not have thrown exception.");
      }
    });
  }

  @Test
  public void testInvalidConstructorBirdType() {
    ArrayList<BirdType> invalidBirdTypes = new ArrayList<>(Arrays.asList(
            BirdType.GRAY_PARROT,
            BirdType.ROSE_RING_PARAKEET,
            BirdType.SULFUR_CRESTED_COCKATOO,
            BirdType.GREAT_AUK,
            BirdType.HORNED_PUFFIN,
            BirdType.GOOSE,
            BirdType.DUCK,
            BirdType.SWAN));

    invalidBirdTypes.forEach(invalidBirdType -> {
      try {
        StandardBird bird = new StandardBird(
                "Rex",
                invalidBirdType,
                new ArrayList<>(Arrays.asList(
                        BirdDiet.SMALL_MAMMALS,
                        BirdDiet.FISH,
                        BirdDiet.OTHER_BIRDS)),
                2);
        fail("Invalid constructor should have failed. Invalid BirdType for Bird class.");
      } catch (IllegalArgumentException e) {
        // do nothing, test passes
      }
    });
  }
}
