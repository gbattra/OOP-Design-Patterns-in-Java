import org.junit.Test;

import rpg.interfaces.IFootGear;
import rpg.models.FootGear;

import static org.junit.Assert.fail;

public class FootGearTest {
  @Test
  public void testValidConstructorNotCombined() {
    try {
      IFootGear gear = new FootGear(10, 10, "strong", "shoe");
      // do nothing, test passes
    } catch (Exception e) {
      fail("Valid constructor should not have failed.");
    }
  }

  @Test
  public void testInvalidConstructorNotCombinedNegAttack() {
    try {
      IFootGear gear = new FootGear(-10, 10, "strong", "shoe");
      fail("Invalid constructor should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidConstructorNotCombinedNegDefense() {
    try {
      IFootGear gear = new FootGear(10, -10, "strong", "shoe");
      fail("Invalid constructor should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidConstructorNotCombinedEmptyAdj() {
    try {
      IFootGear gear = new FootGear(10, 10, "", "shoe");
      fail("Invalid constructor should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidConstructorNotCombinedEmptyNoun() {
    try {
      IFootGear gear = new FootGear(10, 10, "strong", "");
      fail("Invalid constructor should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }
}
