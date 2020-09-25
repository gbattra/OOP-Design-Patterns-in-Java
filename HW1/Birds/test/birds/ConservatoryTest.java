package birds;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import birds.enums.BirdDiet;
import birds.enums.BirdType;
import birds.interfaces.IAviary;
import birds.interfaces.IBird;
import birds.interfaces.IConservatory;
import birds.models.Aviary;
import birds.models.Bird;
import birds.models.Conservatory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ConservatoryTest {
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
      IBird rex = new Bird(
              "Rex",
              BirdType.EAGLE,
              new ArrayList<>(Arrays.asList(
                      BirdDiet.SMALL_MAMMALS,
                      BirdDiet.FISH,
                      BirdDiet.OTHER_BIRDS)),
              2);
      IBird alex = new Bird(
              "Alex",
              BirdType.OSPREY,
              new ArrayList<>(Arrays.asList(
                      BirdDiet.SMALL_MAMMALS,
                      BirdDiet.EGGS,
                      BirdDiet.OTHER_BIRDS)),
              2);
      IBird suzy = new Bird(
              "Rex",
              BirdType.EAGLE,
              new ArrayList<>(Arrays.asList(
                      BirdDiet.SMALL_MAMMALS,
                      BirdDiet.FISH,
                      BirdDiet.OTHER_BIRDS)),
              2);
      IAviary aviary1 = new Aviary(1).addBird(rex).addBird(alex);
      IAviary aviary2 = new Aviary(2).addBird(suzy);
      IConservatory conservatory = new Conservatory().addAviary(aviary1).addAviary(aviary2);
      // do nothing, test passes
    } catch (Exception e) {
      fail("Instantiation should not have failed.");
    }
  }

  @Test
  public void testInvalidConstructorDupelicateSectors() {
    try {
      IBird rex = new Bird(
              "Rex",
              BirdType.EAGLE,
              new ArrayList<>(Arrays.asList(
                      BirdDiet.SMALL_MAMMALS,
                      BirdDiet.FISH,
                      BirdDiet.OTHER_BIRDS)),
              2);
      IBird alex = new Bird(
              "Alex",
              BirdType.OSPREY,
              new ArrayList<>(Arrays.asList(
                      BirdDiet.SMALL_MAMMALS,
                      BirdDiet.EGGS,
                      BirdDiet.OTHER_BIRDS)),
              2);
      IBird suzy = new Bird(
              "Rex",
              BirdType.EAGLE,
              new ArrayList<>(Arrays.asList(
                      BirdDiet.SMALL_MAMMALS,
                      BirdDiet.FISH,
                      BirdDiet.OTHER_BIRDS)),
              2);
      IAviary aviary1 = new Aviary(1).addBird(rex).addBird(alex);
      IAviary aviary2 = new Aviary(1).addBird(suzy);
      List<IAviary> aviaries = new ArrayList<>();
      aviaries.add(aviary1);
      aviaries.add(aviary2);
      IConservatory conservatory = new Conservatory(aviaries);
      fail("Instantiation should have failed. Sectors are not unique.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidConstructorSectorOutOfBounds() {
    try {
      IBird rex = new Bird(
              "Rex",
              BirdType.EAGLE,
              new ArrayList<>(Arrays.asList(
                      BirdDiet.SMALL_MAMMALS,
                      BirdDiet.FISH,
                      BirdDiet.OTHER_BIRDS)),
              2);
      IBird alex = new Bird(
              "Alex",
              BirdType.OSPREY,
              new ArrayList<>(Arrays.asList(
                      BirdDiet.SMALL_MAMMALS,
                      BirdDiet.EGGS,
                      BirdDiet.OTHER_BIRDS)),
              2);
      IAviary aviary = new Aviary(21).addBird(rex).addBird(alex);
      List<IAviary> aviaries = new ArrayList<>();
      aviaries.add(aviary);
      IConservatory conservatory = new Conservatory(aviaries);
      fail("Instantiation should have failed. Sector is out of bounds.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }
}
