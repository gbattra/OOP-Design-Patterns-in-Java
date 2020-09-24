package birds;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

import birds.enums.BirdDiet;
import birds.enums.BirdType;
import birds.interfaces.IAviary;
import birds.interfaces.IBird;
import birds.interfaces.IConservatoryDirectory;
import birds.models.Aviary;
import birds.models.Bird;
import birds.models.ConservatoryDirectory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ConservatoryDirectoryTest {
  @Test
  public void testValidConstructorWithAviary() {
    try {
      Hashtable<Integer, IAviary> directory = new Hashtable<>();
      directory.put(1, new Aviary(1));
      IConservatoryDirectory conservatoryDirectory = new ConservatoryDirectory(directory);
      // do nothing, test passes
    } catch (IllegalArgumentException e) {
      fail("Instantiation of ConservatoryDirectory should not have failed.");
    }
  }

  @Test
  public void testInvalidConstructorDupelicateSector() {
    try {
      Hashtable<Integer, IAviary> directory = new Hashtable<>();
      directory.put(1, new Aviary(1));
      directory.put(2, new Aviary(1));  // directory cannot have aviaries with same sector
      IConservatoryDirectory conservatoryDirectory = new ConservatoryDirectory(directory);
      fail("Instantiation of ConservatoryDirectory should have failed.");
    } catch (IllegalArgumentException e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidConstructorTooManyAviaries() {
    try {
      Hashtable<Integer, IAviary> directory = new Hashtable<>();
      directory.put(1, new Aviary(1));
      directory.put(2, new Aviary(1));  // directory cannot have aviaries with same sector
      IConservatoryDirectory conservatoryDirectory = new ConservatoryDirectory(directory);
      fail("Instantiation of ConservatoryDirectory should have failed.");
    } catch (IllegalArgumentException e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testDescribe() {
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
    IAviary aviary = new Aviary(birds, 1);
    Hashtable<Integer, IAviary> directory = new Hashtable<>();
    directory.put(aviary.getSector(), aviary);
    IConservatoryDirectory conservatoryDirectory = new ConservatoryDirectory(directory);

    String expectedDescription =
            "There are 1 aviaries in the conservatory:\n" +
            "- Sector 1 has an aviary with the bird types: Eagle, Hawk\n";
    assertEquals(expectedDescription, conservatoryDirectory.describe());
  }
}
