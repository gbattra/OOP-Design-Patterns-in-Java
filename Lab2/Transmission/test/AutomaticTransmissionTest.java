import org.junit.Test;

import transmission.AutomaticTransmission;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


/**
 * Test class for the AutomaticTransmission object.
 */
public class AutomaticTransmissionTest {

  @Test
  public void testValidConstructor() {
    try {
      AutomaticTransmission transmission = new AutomaticTransmission(24, 10, 20, 40, 60, 80);
      // do nothing, test passes
    } catch (Exception e) {
      fail("Instantiation of AutomaticTransmission object should not have failed.");
    }
  }

  @Test
  public void testValidConstructorWithSpeed() {
    try {
      AutomaticTransmission transmission = new AutomaticTransmission(10, 20, 40, 60, 80);
      // do nothing, test passes
    } catch (Exception e) {
      fail("Instantiation of AutomaticTransmission object should not have failed.");
    }
  }

  @Test
  public void testInvalidConstructorThresholds() {
    try {
      AutomaticTransmission transmission = new AutomaticTransmission(10, 5, 40, 50, 70);
      fail("Instantiation of AutomaticTransmission object should have failed.");
    } catch (IllegalArgumentException e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidConstructorSpeed() {
    try {
      AutomaticTransmission transmission = new AutomaticTransmission(-5, 10, 20, 40, 50, 70);
      fail("Instantiation of AutomaticTransmission object should have failed.");
    } catch (IllegalArgumentException e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testToString() {
    String expectedString = "Transmission (speed = 15, gear = 2)";
    AutomaticTransmission transmission = new AutomaticTransmission(15, 10, 20, 40, 50, 70);
    assertEquals(expectedString, transmission.toString());
  }

  @Test
  public void testDecreaseSpeed() {
    int currentSpeed = 10;
    AutomaticTransmission transmission =
            new AutomaticTransmission(currentSpeed, 10, 20, 40, 50, 70);
    AutomaticTransmission newTransmission = transmission.decreaseSpeed();
    assertEquals(currentSpeed - 2, newTransmission.getSpeed());
  }

  @Test
  public void testIncreaseSpeed() {
    int currentSpeed = 10;
    AutomaticTransmission transmission =
            new AutomaticTransmission(currentSpeed, 10, 20, 40, 50, 70);
    AutomaticTransmission newTransmission = transmission.increaseSpeed();
    assertEquals(currentSpeed + 2, newTransmission.getSpeed());
  }

  @Test
  public void testValidDescreaseSpeed() {
    try {
      AutomaticTransmission transmission = new AutomaticTransmission(10, 10, 20, 40, 50, 70);
      transmission.decreaseSpeed();
      // do nothing, test passes
    } catch (IllegalStateException e) {
      fail("Decrease speed call should not have thrown an exception.");
    }
  }

  @Test
  public void testValidIncreaseSpeed() {
    try {
      AutomaticTransmission transmission = new AutomaticTransmission(10, 10, 20, 40, 50, 70);
      transmission.increaseSpeed();
      // do nothing, test passes
    } catch (IllegalStateException e) {
      fail("Increase speed call should not have thrown an exception.");
    }
  }

  @Test
  public void testInvalidDecreaseSpeed() {
    try {
      AutomaticTransmission transmission = new AutomaticTransmission(10, 20, 40, 50, 70);
      transmission.decreaseSpeed();
      fail("Decrease speed call should have thrown an exception.");
    } catch (IllegalStateException e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testGetCurrentGearZero() {
    AutomaticTransmission transmission = new AutomaticTransmission(10, 20, 40, 50, 70);
    assertEquals(0, transmission.getGear());
  }

  @Test
  public void testGetCurrentGearFirst() {
    AutomaticTransmission transmission = new AutomaticTransmission(1, 10, 20, 40, 50, 70);
    assertEquals(1, transmission.getGear());
  }

  @Test
  public void testGetCurrentGearSecond() {
    AutomaticTransmission transmission = new AutomaticTransmission(15, 10, 20, 40, 50, 70);
    assertEquals(2, transmission.getGear());
  }

  @Test
  public void testGetCurrentSpeed() {
    AutomaticTransmission transmission = new AutomaticTransmission(1, 10, 20, 40, 50, 70);
    assertEquals(1, transmission.getSpeed());
  }
}
