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
import birds.interfaces.IConservatory;
import birds.interfaces.IConservatoryDirectory;
import birds.interfaces.IConservatoryIndex;
import birds.models.Aviary;
import birds.models.Bird;
import birds.models.Conservatory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ConservatoryTest {
  private final IConservatory genericConservatory;
  private final IBird rex;
  private final IBird alex;

  public ConservatoryTest() {
    this.alex = new Bird(
            "Alex",
            BirdType.OSPREY,
            new ArrayList<>(Arrays.asList(
                    BirdDiet.SMALL_MAMMALS,
                    BirdDiet.EGGS,
                    BirdDiet.OTHER_BIRDS)),
            2);
    this.rex = new Bird(
            "Rex",
            BirdType.EAGLE,
            new ArrayList<>(Arrays.asList(
                    BirdDiet.SMALL_MAMMALS,
                    BirdDiet.FISH,
                    BirdDiet.OTHER_BIRDS)),
            2);
    IAviary aviary1 = new Aviary(1).addBird(rex);
    IAviary aviary2 = new Aviary(2).addBird(alex);
    this.genericConservatory = new Conservatory().addAviary(aviary1).addAviary(aviary2);
  }

  @Test
  public void testValidConstructorEmpty() {
    try {
      IConservatory conservatory = new Conservatory();
      // do nothing, test passes
    } catch (Exception e) {
      fail("Instantiation should not have failed.");
    }
  }

  @Test
  public void testValidConstructorFull() {
    try {
      IConservatory conservatory = new Conservatory(this.genericConservatory.getAviaries());
      // do nothing, test passes
    } catch (Exception e) {
      fail("Instantiation should not have failed.");
    }
  }

  @Test
  public void testInvalidConstructorDupelicateSectors() {
    try {
      List<IAviary> aviaries = new ArrayList<>();
      aviaries.add(this.genericConservatory.getAviaries().get(0));
      aviaries.add(this.genericConservatory.getAviaries().get(0));
      IConservatory conservatory = new Conservatory(aviaries);
      fail("Instantiation should have failed. Sectors are not unique.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidConstructorSectorOutOfBounds() {
    try {
      IAviary aviary = new Aviary(21).addBird(rex).addBird(alex);
      List<IAviary> aviaries = new ArrayList<>();
      aviaries.add(aviary);
      IConservatory conservatory = new Conservatory(aviaries);
      fail("Instantiation should have failed. Sector is out of bounds.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidConstructorTooManyAviaries() {
    try {
      List<IAviary> aviaries = new ArrayList<>();
      for (int i = 0; i < 21; i++) {
        IAviary aviary = new Aviary(i + 1);
        aviaries.add(aviary);
      }

      IConservatory conservatory = new Conservatory(aviaries);
      fail("Instantiation should have failed. Sector is out of bounds.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testValidAddAviary() {
    IAviary aviary = new Aviary(1).addBird(this.rex).addBird(this.alex);
    IConservatory conservatory = new Conservatory();
    try {
      conservatory = conservatory.addAviary(aviary);
      // do nothing, test passes
    } catch (Exception e) {
      fail("Valid addAviary() call should not have thrown exception.");
    }
  }

  @Test
  public void testInvalidAddAviary() {
    IAviary aviary1 = new Aviary(1).addBird(this.rex);
    IAviary aviary2 = new Aviary(1).addBird(this.alex);
    IConservatory conservatory = new Conservatory();
    try {
      conservatory.addAviary(aviary1).addAviary(aviary2);
      fail("Valid addAviary() call should not have thrown exception.");
    } catch (Exception e) {
      // invalid addAviary() call should have failed
    }
  }

  @Test
  public void testAccessors() {
    IAviary aviary1 = new Aviary(1).addBird(this.rex);
    IAviary aviary2 = new Aviary(2).addBird(this.alex);
    assertEquals(aviary1, this.genericConservatory.getAviaries().get(0));
    assertEquals(aviary2, this.genericConservatory.getAviaries().get(1));
  }

  @Test
  public void testGetDirectory() {
    IConservatoryDirectory directory = this.genericConservatory.getDirectory();
    IAviary aviary1 = new Aviary(1).addBird(this.rex);
    IAviary aviary2 = new Aviary(2).addBird(this.alex);

    assertEquals(aviary1, directory.getDirectory().get(aviary1.getSector()));
    assertEquals(aviary2, directory.getDirectory().get(aviary2.getSector()));
  }

  @Test
  public void testGetIndex() {
    IConservatoryIndex index = this.genericConservatory.getIndex();
    IBird bird1 = new Bird(
            this.rex.getName(),
            this.rex.getType(),
            this.rex.getDiet(),
            this.rex.getWingCount());
    IBird bird2 = new Bird(
            this.alex.getName(),
            this.alex.getType(),
            this.alex.getDiet(),
            this.alex.getWingCount());
    assertEquals(1, (int) index.getIndex().get(bird1));
    assertEquals(2, (int) index.getIndex().get(bird2));
  }

  @Test
  public void testGetFoodRequirements() {
    Hashtable<BirdDiet, Integer> foodRequirements = this.genericConservatory.getFoodRequirements();
    assertEquals(2, (int) foodRequirements.get(BirdDiet.SMALL_MAMMALS));
    assertEquals(2, (int) foodRequirements.get(BirdDiet.OTHER_BIRDS));
    assertEquals(1, (int) foodRequirements.get(BirdDiet.EGGS));
    assertEquals(1, (int) foodRequirements.get(BirdDiet.FISH));
  }

  @Test
  public void testDescribe() {
    String expectedDescription = String.format(
            "This conservatory has %s aviaries located in sectors %s. These aviaries are home " +
            "to the following types of birds: %s. For more information please read the " +
            "conservatory directory or index.",
            2,
            "1, 2",
            String.format("%s, %s", this.rex.getType().label, this.alex.getType().label));
    assertEquals(expectedDescription, this.genericConservatory.describe());
  }
}
