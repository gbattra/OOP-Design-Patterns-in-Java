package birds;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import birds.enums.BirdDiet;
import birds.enums.BirdType;
import birds.interfaces.IAviary;
import birds.interfaces.IBird;
import birds.models.Aviary;
import birds.models.Bird;

import static org.junit.Assert.fail;

public class AviaryTest {
  @Test
  public void testValidConstructor() {
    try {
      List<IBird> birds  = new ArrayList<>(
              Arrays.asList(
                      new Bird(
                              "Rex",
                              BirdType.EAGLE,
                              new ArrayList<>(Arrays.asList(
                                      BirdDiet.SMALL_MAMMALS,
                                      BirdDiet.FISH,
                                      BirdDiet.OTHER_BIRDS)),
                              2),
                      new Bird(
                              "Axel",
                              BirdType.HAWK,
                              new ArrayList<>(Arrays.asList(
                                      BirdDiet.SMALL_MAMMALS,
                                      BirdDiet.FISH,
                                      BirdDiet.OTHER_BIRDS)),
                              2)));
      IAviary aviary = new Aviary(
              birds,
              1);
      // do nothing, test passes
    } catch (Exception e) {
      fail("Instantiation of aviary should not have thrown exception.");
    }
  }

  @Test
  public void testInvalidConstructorBirdTypes() {
    try {
      List<IBird> birds  = new ArrayList<>(
              Arrays.asList(
                      new Bird(
                              "Rex",
                              BirdType.EAGLE,
                              new ArrayList<>(Arrays.asList(
                                      BirdDiet.SMALL_MAMMALS,
                                      BirdDiet.FISH,
                                      BirdDiet.OTHER_BIRDS)),
                              2),
                      new Bird(
                              "Axel",
                              BirdType.PASSENGER_PIGEON,
                              new ArrayList<>(Arrays.asList(
                                      BirdDiet.BERRIES,
                                      BirdDiet.FRUIT)),
                              2)));
      IAviary aviary = new Aviary(
              birds,
              1);
      fail("Instantiation of aviary should have thrown exception.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidConstructorSector() {
    try {
      List<IBird> birds  = new ArrayList<>(
              Arrays.asList(
                      new Bird(
                              "Rex",
                              BirdType.EAGLE,
                              new ArrayList<>(Arrays.asList(
                                      BirdDiet.SMALL_MAMMALS,
                                      BirdDiet.FISH,
                                      BirdDiet.OTHER_BIRDS)),
                              2)));
      IAviary aviary = new Aviary(
              birds,
              0);
      fail("Instantiation of aviary should have thrown exception.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidConstructorBirdNames() {
    try {
      List<IBird> birds  = new ArrayList<>(
              Arrays.asList(
                      new Bird(
                              "Rex",
                              BirdType.EAGLE,
                              new ArrayList<>(Arrays.asList(
                                      BirdDiet.SMALL_MAMMALS,
                                      BirdDiet.FISH,
                                      BirdDiet.OTHER_BIRDS)),
                              2),
                      new Bird(
                              "Rex",
                              BirdType.EAGLE,
                              new ArrayList<>(Arrays.asList(
                                      BirdDiet.BERRIES,
                                      BirdDiet.FRUIT)),
                              2)));
      IAviary aviary = new Aviary(
              birds,
              1);
      fail("Instantiation of aviary should have thrown exception.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidConstructorBirdCount() {
    try {
      List<IBird> birds  = new ArrayList<>(
              Arrays.asList(
                      new Bird(
                              "Rex",
                              BirdType.EAGLE,
                              new ArrayList<>(Arrays.asList(
                                      BirdDiet.SMALL_MAMMALS,
                                      BirdDiet.FISH,
                                      BirdDiet.OTHER_BIRDS)),
                              2),
                      new Bird(
                              "Axel",
                              BirdType.EAGLE,
                              new ArrayList<>(Arrays.asList(
                                      BirdDiet.BERRIES,
                                      BirdDiet.FRUIT)),
                              2),
                      new Bird(
                              "Baron",
                              BirdType.EAGLE,
                              new ArrayList<>(Arrays.asList(
                                      BirdDiet.BERRIES,
                                      BirdDiet.FRUIT)),
                              2),
                      new Bird(
                              "Alyx",
                              BirdType.EAGLE,
                              new ArrayList<>(Arrays.asList(
                                      BirdDiet.BERRIES,
                                      BirdDiet.FRUIT)),
                              2),
                      new Bird(
                              "Red",
                              BirdType.EAGLE,
                              new ArrayList<>(Arrays.asList(
                                      BirdDiet.BERRIES,
                                      BirdDiet.FRUIT)),
                              2),
                      new Bird(
                              "Suzie",
                              BirdType.EAGLE,
                              new ArrayList<>(Arrays.asList(
                                      BirdDiet.BERRIES,
                                      BirdDiet.FRUIT)),
                              2)));
      IAviary aviary = new Aviary(
              birds,
              1);
      fail("Instantiation of aviary should have thrown exception.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }
}
