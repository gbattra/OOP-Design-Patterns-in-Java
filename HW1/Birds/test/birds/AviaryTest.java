package birds;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

import birds.enums.BirdClassification;
import birds.enums.BirdDiet;
import birds.enums.BirdType;
import birds.interfaces.IAviary;
import birds.interfaces.IBird;
import birds.models.Aviary;
import birds.models.Bird;
import birds.models.StandardBird;
import birds.models.TalkingBird;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class AviaryTest {
  @Test
  public void testValidConstructor() {
    try {
      List<IBird> birds  = new ArrayList<>(
              Arrays.asList(
                      new StandardBird(
                              "Rex",
                              BirdType.EAGLE,
                              new ArrayList<>(Arrays.asList(
                                      BirdDiet.SMALL_MAMMALS,
                                      BirdDiet.FISH,
                                      BirdDiet.OTHER_BIRDS)),
                              2),
                      new StandardBird(
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
                      new StandardBird(
                              "Rex",
                              BirdType.EAGLE,
                              new ArrayList<>(Arrays.asList(
                                      BirdDiet.SMALL_MAMMALS,
                                      BirdDiet.FISH,
                                      BirdDiet.OTHER_BIRDS)),
                              2),
                      new StandardBird(
                              "Axel",
                              BirdType.PASSENGER_PIGEON,  // can't mix these bird types
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
                      new StandardBird(
                              "Rex",
                              BirdType.EAGLE,
                              new ArrayList<>(Arrays.asList(
                                      BirdDiet.SMALL_MAMMALS,
                                      BirdDiet.FISH,
                                      BirdDiet.OTHER_BIRDS)),
                              2)));
      IAviary aviary = new Aviary(
              birds,
              0);  // invalid sector id
      fail("Instantiation of aviary should have thrown exception.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidConstructorExtinctBird() {
    try {
      List<IBird> birds  = new ArrayList<>(
              Arrays.asList(
                      new StandardBird(
                              "Sleepy",
                              BirdType.MOA,  // cannot add extinct bird
                              new ArrayList<>(Arrays.asList(
                                      BirdDiet.SMALL_MAMMALS,
                                      BirdDiet.FISH,
                                      BirdDiet.OTHER_BIRDS)),
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
  public void testInvalidConstructorBirdNames() {
    try {
      List<IBird> birds  = new ArrayList<>(
              Arrays.asList(
                      new StandardBird(
                              "Rex",
                              BirdType.EAGLE,
                              new ArrayList<>(Arrays.asList(
                                      BirdDiet.SMALL_MAMMALS,
                                      BirdDiet.FISH,
                                      BirdDiet.OTHER_BIRDS)),
                              2),
                      new StandardBird(
                              "Rex",  // names must be unique
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
      List<IBird> birds  = new ArrayList<>(  // too many birds (6) for aviary
              Arrays.asList(
                      new StandardBird(
                              "Rex",
                              BirdType.EAGLE,
                              new ArrayList<>(Arrays.asList(
                                      BirdDiet.SMALL_MAMMALS,
                                      BirdDiet.FISH,
                                      BirdDiet.OTHER_BIRDS)),
                              2),
                      new StandardBird(
                              "Axel",
                              BirdType.EAGLE,
                              new ArrayList<>(Arrays.asList(
                                      BirdDiet.BERRIES,
                                      BirdDiet.FRUIT)),
                              2),
                      new StandardBird(
                              "Baron",
                              BirdType.EAGLE,
                              new ArrayList<>(Arrays.asList(
                                      BirdDiet.BERRIES,
                                      BirdDiet.FRUIT)),
                              2),
                      new StandardBird(
                              "Alyx",
                              BirdType.EAGLE,
                              new ArrayList<>(Arrays.asList(
                                      BirdDiet.BERRIES,
                                      BirdDiet.FRUIT)),
                              2),
                      new StandardBird(
                              "Red",
                              BirdType.EAGLE,
                              new ArrayList<>(Arrays.asList(
                                      BirdDiet.BERRIES,
                                      BirdDiet.FRUIT)),
                              2),
                      new StandardBird(
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

  @Test
  public void testAccessors() {
    List<IBird> birds = new ArrayList<>(
            Arrays.asList(
                    new StandardBird(
                            "Rex",
                            BirdType.EAGLE,
                            new ArrayList<>(Arrays.asList(
                                    BirdDiet.SMALL_MAMMALS,
                                    BirdDiet.FISH,
                                    BirdDiet.OTHER_BIRDS)),
                            2),
                    new StandardBird(
                            "Axel",
                            BirdType.EAGLE,
                            new ArrayList<>(Arrays.asList(
                                    BirdDiet.BERRIES,
                                    BirdDiet.FRUIT)),
                            2)));
    IAviary aviary = new Aviary(
            birds,
            1);
    assertEquals(2, aviary.getBirds().size());
    assertEquals(new ArrayList<>(Arrays.asList(BirdType.EAGLE)), aviary.getBirdTypes());
    assertEquals(1, aviary.getSector());
  }

  @Test
  public void testGetFoodRequirements() {
    List<IBird> birds = new ArrayList<>(
            Arrays.asList(
                    new StandardBird(
                            "Rex",
                            BirdType.EAGLE,
                            new ArrayList<>(Arrays.asList(
                                    BirdDiet.SMALL_MAMMALS,
                                    BirdDiet.FISH,
                                    BirdDiet.OTHER_BIRDS)),
                            2),
                    new StandardBird(
                            "Axel",
                            BirdType.HAWK,
                            new ArrayList<>(Arrays.asList(
                                    BirdDiet.FISH,
                                    BirdDiet.EGGS)),
                            2)));
    IAviary aviary = new Aviary(
            birds,
            1);
    try {
      Hashtable<BirdDiet, Integer> actualFoodRequirements = aviary.getFoodRequirements();
      assertEquals(1, (int) actualFoodRequirements.get(BirdDiet.SMALL_MAMMALS));
      assertEquals(2, (int) actualFoodRequirements.get(BirdDiet.FISH));
      assertEquals(1, (int) actualFoodRequirements.get(BirdDiet.OTHER_BIRDS));
      assertEquals(1, (int) actualFoodRequirements.get(BirdDiet.EGGS));
    } catch (Exception e) {
      fail("Computed food requirements are missing a BirdDiet key.");
    }
  }

  @Test
  public void testDescribe() {
    IBird rex = new StandardBird(
            "Rex",
            BirdType.EAGLE,
            new ArrayList<>(Arrays.asList(
                    BirdDiet.SMALL_MAMMALS,
                    BirdDiet.FISH,
                    BirdDiet.OTHER_BIRDS)),
            2);
    IBird axel = new StandardBird(
            "Axel",
            BirdType.HAWK,
            new ArrayList<>(Arrays.asList(
                    BirdDiet.FISH,
                    BirdDiet.EGGS)),
            2);
    List<IBird> birds = new ArrayList<>(Arrays.asList(rex, axel));
    IAviary aviary = new Aviary(
            birds,
            1);
    String expectedDescription =
            String.format(
                    "The aviary in sector 1 houses 2 birds of types %s. " +
                    "Below are descriptions of each bird living in this aviary:\n",
                    String.join(", ",
                            BirdType.EAGLE.label,
                            BirdType.HAWK.label));
    expectedDescription += "- " + rex.describe() + "\n";
    expectedDescription += "- " + axel.describe() + "\n";
    assertEquals(expectedDescription, aviary.describe());
  }

  @Test
  public void testValidAddBird() {
    IBird rex = new StandardBird(
            "Rex",
            BirdType.EAGLE,
            new ArrayList<>(Arrays.asList(
                    BirdDiet.SMALL_MAMMALS,
                    BirdDiet.FISH,
                    BirdDiet.OTHER_BIRDS)),
            2);
    IBird axel = new StandardBird(
            "Axel",
            BirdType.HAWK,
            new ArrayList<>(Arrays.asList(
                    BirdDiet.FISH,
                    BirdDiet.EGGS)),
            2);
    IBird luke = new StandardBird(
            "Luke",
            BirdType.HAWK,
            new ArrayList<>(Arrays.asList(
                    BirdDiet.FISH,
                    BirdDiet.EGGS)),
            2);
    IAviary aviary = new Aviary(
            new ArrayList<>(Arrays.asList(rex, axel)),
            1);
    try {
      IAviary updatedAviary = aviary.addBird(luke);
      assertEquals(2, aviary.getBirds().size());
      assertEquals(3, updatedAviary.getBirds().size());
      // do nothing, test passes
    } catch (IllegalArgumentException e) {
      fail("Valid addBird call should not have thrown an exception.");
    }
  }

  @Test
  public void testInvalidAddBird() {
    IBird rex = new StandardBird(
            "Rex",
            BirdType.EAGLE,
            new ArrayList<>(Arrays.asList(
                    BirdDiet.SMALL_MAMMALS,
                    BirdDiet.FISH,
                    BirdDiet.OTHER_BIRDS)),
            2);
    IBird axel = new StandardBird(
            "Axel",
            BirdType.HAWK,
            new ArrayList<>(Arrays.asList(
                    BirdDiet.FISH,
                    BirdDiet.EGGS)),
            2);
    IBird luke = new TalkingBird(
            "Luke",
            BirdType.GRAY_PARROT,
            new ArrayList<>(Arrays.asList(
                    BirdDiet.FISH,
                    BirdDiet.EGGS)),
            2,
            "Flamingo",
            new ArrayList<>(Arrays.asList("Hello", "Goodbye", "Okay", "Love", "Sorry")));
    IAviary aviary = new Aviary(
            new ArrayList<>(Arrays.asList(rex, axel)),
            1);
    try {
      aviary.addBird(luke);
      fail("Invalid addBird call should have thrown an exception.");
    } catch (IllegalStateException e) {
      // do nothing, test passes
    }
  }
}
