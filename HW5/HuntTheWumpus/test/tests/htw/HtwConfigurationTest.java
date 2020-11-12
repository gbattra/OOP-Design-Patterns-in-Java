package tests.htw;

import org.junit.Test;

import htw.tools.HtwConfiguration;
import htw.tools.IHtwConfiguration;
import maze.components.Coordinates;

import static org.junit.Assert.fail;

public class HtwConfigurationTest {
  @Test
  public void testValidConstructor() {
    try {
      IHtwConfiguration config = new HtwConfiguration(
              5,5, new Coordinates(0, 0), 0.1, 0.2, true, false, 3, 1);
    } catch (Exception e) {
      fail("Valid constructor should not have failed.");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructor() {
    IHtwConfiguration config = new HtwConfiguration(
            5,5, new Coordinates(0, 0), -0.1, -0.2, true, false, 3, 1);
  }
}
