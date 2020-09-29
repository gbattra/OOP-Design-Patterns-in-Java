import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import rpg.interfaces.IFootGear;
import rpg.interfaces.IGear;
import rpg.models.FootGear;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class FootGearTest {
  private IFootGear footGear1;
  private IFootGear footGear2;

  @Before
  public void setup() {
    footGear1 = new FootGear(10, 10, "strong", "boot");
    footGear2 = new FootGear(1, 1, "weak", "slipper");
  }

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

  @Test
  public void testValidConstructorCombined() {
    try {
      IFootGear gear = new FootGear(
              10,
              10,
              "strong",
              "shoe",
              new ArrayList<>(Arrays.asList(this.footGear1, this.footGear2)));
    } catch (Exception e) {
      fail("Valid constructor should not have failed.");
    }
  }

  @Test
  public void testInvalidConstructorCombinedWrongCount() {
    try {
      IFootGear gear = new FootGear(
              10,
              10,
              "strong",
              "shoe",
              new ArrayList<>(Arrays.asList(this.footGear2)));
      fail("Invalid constructor should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidConstructorCombinedNegAttack() {
    try {
      IFootGear gear = new FootGear(
              -10,
              10,
              "strong",
              "shoe",
              new ArrayList<>(Arrays.asList(this.footGear1, this.footGear2)));
      fail("Invalid constructor should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidConstructorCombinedNegDefense() {
    try {
      IFootGear gear = new FootGear(
              10,
              -10,
              "strong",
              "shoe",
              new ArrayList<>(Arrays.asList(this.footGear1, this.footGear2)));
      fail("Invalid constructor should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidConstructorCombinedEmptyAdj() {
    try {
      IFootGear gear = new FootGear(
              10,
              10,
              "",
              "shoe",
              new ArrayList<>(Arrays.asList(this.footGear1, this.footGear2)));
      fail("Invalid constructor should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidConstructorCombinedEmptyNoun() {
    try {
      IFootGear gear = new FootGear(
              10,
              10,
              "strong",
              "",
              new ArrayList<>(Arrays.asList(this.footGear1, this.footGear2)));
      fail("Invalid constructor should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testToStringNotCombined() {
    String expected = String.format(
            "Gear - Adj: %s, Noun: %s, Attack: %s, Defense: %s.",
            "strong", "boot", 10, 10);
    assertEquals(expected, this.footGear1.toString());
  }

  @Test
  public void testToCombined() {
    String expected = String.format(
            "Gear - Adj: %s, Noun: %s, Attack: %s, Defense: %s.",
            "weak, strong", "boot", 11, 11);
    expected += String.format(
            " Combined with: Gear - Adj: %s, Noun: %s, Attack: %s, Defense: %s.",
            "strong", "boot", 10, 10);
    expected += String.format(
            "Gear - Adj: %s, Noun: %s, Attack: %s, Defense: %s.",
            "weak", "slipper", 1, 1);
    IFootGear combined = new FootGear(
            this.footGear1.getAttack() + this.footGear2.getAttack(),
            this.footGear1.getDefense() + this.footGear2.getDefense(),
            this.footGear2.getAdjective() + ", " + this.footGear1.getAdjective(),
            this.footGear1.getNoun(),
            new ArrayList<>(Arrays.asList(this.footGear1, this.footGear2)));
    assertEquals(expected, combined.toString());
  }

  @Test
  public void testEqualsTrue() {
    IFootGear copyGear1 = new FootGear(
            this.footGear1.getAttack(),
            this.footGear1.getDefense(),
            this.footGear1.getAdjective(),
            this.footGear1.getNoun());
    assertEquals(copyGear1, this.footGear1);
  }
}
