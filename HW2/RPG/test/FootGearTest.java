import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import rpg.enums.GearType;
import rpg.interfaces.IFootGear;
import rpg.models.FootGear;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Tests for the FootGear class.
 */
public class FootGearTest {
  private IFootGear footGear1;
  private IFootGear footGear2;
  private IFootGear combinedFootGear;

  @Before
  public void setup() {
    this.footGear1 = new FootGear(GearType.BOOT, 10, 10, "strong", "boot");
    this.footGear2 = new FootGear(GearType.BOOT, 1, 1, "weak", "boot");
    this.combinedFootGear = new FootGear(
            GearType.BOOT,
            this.footGear1.getAttack() + this.footGear2.getAttack(),
            this.footGear1.getDefense() + this.footGear2.getDefense(),
            this.footGear2.getAdjective() + ", " + this.footGear1.getAdjective(),
            this.footGear1.getNoun(),
            new ArrayList<>(Arrays.asList(this.footGear1, this.footGear2)));
  }

  @Test
  public void testValidConstructorNotCombined() {
    try {
      IFootGear gear = new FootGear(GearType.BOOT, 10, 10, "strong", "shoe");
      // do nothing, test passes
    } catch (Exception e) {
      fail("Valid constructor should not have failed.");
    }
  }

  @Test
  public void testInvalidConstructorNotCombinedWrongType() {
    try {
      IFootGear gear = new FootGear(GearType.HAT, 10, 10, "strong", "shoe");
      fail("Invalid constructor should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidConstructorNotCombinedNegAttack() {
    try {
      IFootGear gear = new FootGear(GearType.BOOT, -10, 10, "strong", "shoe");
      fail("Invalid constructor should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidConstructorNotCombinedNegDefense() {
    try {
      IFootGear gear = new FootGear(GearType.BOOT, 10, -10, "strong", "shoe");
      fail("Invalid constructor should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidConstructorNotCombinedEmptyAdj() {
    try {
      IFootGear gear = new FootGear(GearType.BOOT, 10, 10, "", "shoe");
      fail("Invalid constructor should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidConstructorNotCombinedEmptyNoun() {
    try {
      IFootGear gear = new FootGear(GearType.BOOT, 10, 10, "strong", "");
      fail("Invalid constructor should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testValidConstructorCombined() {
    try {
      IFootGear gear = new FootGear(
              GearType.BOOT,
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
  public void testInvalidConstructorCombinedWrongType() {
    try {
      IFootGear gear = new FootGear(
              GearType.HAT,
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
  public void testInvalidConstructorCombinedWrongCount() {
    try {
      IFootGear gear = new FootGear(
              GearType.BOOT,
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
              GearType.BOOT,
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
              GearType.BOOT,
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
              GearType.BOOT,
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
              GearType.BOOT,
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
            "Gear - Type: %s, Adj: %s, Noun: %s, Attack: %s, Defense: %s.",
            GearType.BOOT.toString(), "strong", "boot", 10, 10);
    assertEquals(expected, this.footGear1.toString());
  }

  @Test
  public void testToStringCombined() {
    String expected = String.format(
            "Gear - Type: %s, Adj: %s, Noun: %s, Attack: %s, Defense: %s.",
            GearType.BOOT.toString(), "weak, strong", "boot", 11, 11);
    expected += String.format(
            " Combined with: Gear - Type: %s, Adj: %s, Noun: %s, Attack: %s, Defense: %s.",
            GearType.BOOT.toString(),  "strong", "boot", 10, 10);
    expected += String.format(
            "Gear - Type: %s, Adj: %s, Noun: %s, Attack: %s, Defense: %s.",
            GearType.BOOT.toString(),  "weak", "boot", 1, 1);
    assertEquals(expected, this.combinedFootGear.toString());
  }

  @Test
  public void testEqualsTrue() {
    IFootGear copyGear1 = new FootGear(
            this.footGear1.getType(),
            this.footGear1.getAttack(),
            this.footGear1.getDefense(),
            this.footGear1.getAdjective(),
            this.footGear1.getNoun());
    assertEquals(copyGear1, this.footGear1);
  }

  @Test
  public void testGetName() {
    assertEquals("strong boot", this.footGear1.getName());
  }

  @Test
  public void testGetNoun() {
    assertEquals("boot", this.footGear1.getNoun());
  }

  @Test
  public void testGetAdjective() {
    assertEquals("strong", this.footGear1.getAdjective());
  }

  @Test
  public void testGetAttack() {
    assertEquals(10, this.footGear1.getAttack());
  }

  @Test
  public void testGetDefense() {
    assertEquals(10, this.footGear1.getDefense());
  }

  @Test
  public void testGetAttackCombined() {
    assertEquals(11, this.combinedFootGear.getAttack());
  }

  @Test
  public void testGetDefenseCombined() {
    assertEquals(11, this.combinedFootGear.getDefense());
  }

  @Test
  public void testGetAdjectiveCombined() {
    assertEquals("weak, strong", this.combinedFootGear.getAdjective());
  }

  @Test
  public void testGetNameCombined() {
    assertEquals("weak, strong boot", this.combinedFootGear.getName());
  }

  @Test
  public void testIsCombinedTrue() {
    assertTrue(this.combinedFootGear.isCombined());
  }

  @Test
  public void testIsCombinedFalse() {
    assertFalse(this.footGear1.isCombined());
  }

  @Test
  public void testCombinedWithCombined() {
    Optional<List<IFootGear>> combined = this.combinedFootGear.combinedWith();
    assertTrue(combined.isPresent());
    assertEquals(this.footGear1, combined.get().get(0));
    assertEquals(this.footGear2, combined.get().get(1));
  }

  @Test
  public void testCombinedWithNotCombined() {
    Optional<List<IFootGear>> combined = this.footGear1.combinedWith();
    assertFalse(combined.isPresent());
  }

  @Test
  public void testValidCombine() {
    try {
      IFootGear combined = this.footGear1.combine(this.footGear2);
      // do nothing, test passes
    } catch (Exception e) {
      fail("Valid combine() should not have failed.");
    }
  }

  @Test
  public void testInvalidCombineCallerAlreadyCombined() {
    try {
      IFootGear combined = this.combinedFootGear.combine(this.footGear2);
      fail("Invalid combine() should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidCombineProvidedAlreadyCombined() {
    try {
      IFootGear combined = this.footGear1.combine(this.combinedFootGear);
      fail("Invalid combine() should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidCombineWrongType() {
    try {
      IFootGear gear = new FootGear(
              GearType.SNEAKER,
              10, 10, "nice", "sneaker");
      IFootGear combined = this.footGear1.combine(gear);
      fail("Invalid combine() should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }
}
