package birds;

import org.junit.Test;

import java.util.Hashtable;

import birds.interfaces.IAviary;
import birds.interfaces.IConservatoryDirectory;
import birds.models.Aviary;
import birds.models.ConservatoryDirectory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ConservatoryDirectoryTest {
  @Test
  public void testValidConstructorEmpty() {
    try {
      IConservatoryDirectory conservatoryDirectory = new ConservatoryDirectory(20);
      // do nothing, test passes
    } catch (IllegalArgumentException e) {
      fail("Instantiation of ConservatoryDirectory should not have failed.");
    }
  }

  @Test
  public void testValidConstructorWithAviary() {
    try {
      Hashtable<Integer, IAviary> directory = new Hashtable<>();
      directory.put(1, new Aviary(1));
      IConservatoryDirectory conservatoryDirectory = new ConservatoryDirectory(
              20,
              directory);
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
      IConservatoryDirectory conservatoryDirectory = new ConservatoryDirectory(
              20,
              directory);
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
      IConservatoryDirectory conservatoryDirectory = new ConservatoryDirectory(
              1,
              directory);
      fail("Instantiation of ConservatoryDirectory should have failed.");
    } catch (IllegalArgumentException e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testValidAddAviary() {
    try {
      Hashtable<Integer, IAviary> directory = new Hashtable<>();
      directory.put(1, new Aviary(1));
      IConservatoryDirectory conservatoryDirectory = new ConservatoryDirectory(
              10,
              directory);
      IConservatoryDirectory updatedConservatoryDirectory =
              conservatoryDirectory.addAviary(new Aviary(2));
      assertEquals(1, conservatoryDirectory.getDirectory().size());
      assertEquals(2, updatedConservatoryDirectory.getDirectory().size());
      // do nothing, test should not have failed
    } catch (IllegalArgumentException e) {
      fail("Adding aviary to directory should not have failed.");
    }
  }

  @Test
  public void testInvalidAddAviary() {
    try {
      Hashtable<Integer, IAviary> directory = new Hashtable<>();
      directory.put(1, new Aviary(1));
      IConservatoryDirectory conservatoryDirectory = new ConservatoryDirectory(
              1,
              directory);
      IConservatoryDirectory updatedConservatoryDirectory =
              conservatoryDirectory.addAviary(new Aviary(1));
      fail("Adding aviary to directory should have failed.");
    } catch (IllegalStateException e) {
      // do nothing, test passes
    }
  }
}
