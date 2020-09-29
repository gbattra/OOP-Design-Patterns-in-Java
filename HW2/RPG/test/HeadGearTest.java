import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import rpg.enums.GearType;
import rpg.interfaces.IHeadGear;
import rpg.models.HeadGear;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Tests for the HeadGear class.
 */
public class HeadGearTest {
  private IHeadGear headGear1;
  private IHeadGear headGear2;
  private IHeadGear combinedHeadGear;

  @Before
  public void setup() {
    this.headGear1 = new HeadGear(GearType.HAT, 10, "strong", "hat");
    this.headGear2 = new HeadGear(GearType.HAT, 1, "weak", "hat");
    this.combinedHeadGear = new HeadGear(
            GearType.HAT,
            this.headGear1.getDefense() + this.headGear2.getDefense(),
            this.headGear2.getAdjective() + ", " + this.headGear1.getAdjective(),
            this.headGear1.getNoun(),
            new ArrayList<>(Arrays.asList(this.headGear1, this.headGear2)));
  }

  @Test
  public void testValidConstructorNotCombined() {
    try {
      IHeadGear gear = new HeadGear(GearType.HAT, 10, "strong", "hat");
      // do nothing, test passes
    } catch (Exception e) {
      fail("Valid constructor should not have failed.");
    }
  }

  @Test
  public void testInvalidConstructorWrongType() {
    try {
      IHeadGear gear = new HeadGear(GearType.GLOVE, 10, "strong", "hat");
      fail("Invalid constructor should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidConstructorNotCombinedNegDefense() {
    try {
      IHeadGear gear = new HeadGear(GearType.HAT, -10, "strong", "hat");
      fail("Invalid constructor should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidConstructorNotCombinedEmptyAdj() {
    try {
      IHeadGear gear = new HeadGear(GearType.HAT, 10, "", "hat");
      fail("Invalid constructor should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidConstructorNotCombinedEmptyNoun() {
    try {
      IHeadGear gear = new HeadGear(GearType.HAT, 10, "strong", "");
      fail("Invalid constructor should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testValidConstructorCombined() {
    try {
      IHeadGear gear = new HeadGear(
              GearType.HAT,
              10,
              "strong",
              "hat",
              new ArrayList<>(Arrays.asList(this.headGear1, this.headGear2)));
    } catch (Exception e) {
      fail("Valid constructor should not have failed.");
    }
  }

  @Test
  public void testInvalidConstructorCombinedWrongType() {
    try {
      IHeadGear gear = new HeadGear(
              GearType.HAT,
              10,
              "strong",
              "hat",
              new ArrayList<>(Arrays.asList(this.headGear2)));
      fail("Invalid constructor should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidConstructorCombinedWrongCount() {
    try {
      IHeadGear gear = new HeadGear(
              GearType.HAT,
              10,
              "strong",
              "hat",
              new ArrayList<>(Arrays.asList(this.headGear2)));
      fail("Invalid constructor should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidConstructorCombinedNegDefense() {
    try {
      IHeadGear gear = new HeadGear(
              GearType.HAT,
              -10,
              "strong",
              "hat",
              new ArrayList<>(Arrays.asList(this.headGear1, this.headGear2)));
      fail("Invalid constructor should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidConstructorCombinedEmptyAdj() {
    try {
      IHeadGear gear = new HeadGear(
              GearType.HAT,
              10,
              "",
              "hat",
              new ArrayList<>(Arrays.asList(this.headGear1, this.headGear2)));
      fail("Invalid constructor should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidConstructorCombinedEmptyNoun() {
    try {
      IHeadGear gear = new HeadGear(
              GearType.HAT,
              10,
              "strong",
              "",
              new ArrayList<>(Arrays.asList(this.headGear1, this.headGear2)));
      fail("Invalid constructor should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testToStringNotCombined() {
    String expected = String.format(
            "Gear - Type: %s, Name: %s, Attack: %s, Defense: %s.",
            GearType.HAT, "strong hat", 0, 10);
    assertEquals(expected, this.headGear1.toString());
  }

  @Test
  public void testToStringCombined() {
    String expected = String.format(
            "Gear - Type: %s, Name: %s, Attack: %s, Defense: %s.",
            GearType.HAT, "weak, strong hat", 0, 11);
    expected += String.format(
            " Combined with: Gear - Type: %s, Name: %s, Attack: %s, Defense: %s.",
            GearType.HAT, "strong hat", 0, 10);
    expected += String.format(
            "Gear - Type: %s, Name: %s, Attack: %s, Defense: %s.",
            GearType.HAT, "weak hat", 0, 1);
    assertEquals(expected, this.combinedHeadGear.toString());
  }

  @Test
  public void testEqualsTrue() {
    IHeadGear copyGear1 = new HeadGear(
            this.headGear1.getType(),
            this.headGear1.getDefense(),
            this.headGear1.getAdjective(),
            this.headGear1.getNoun());
    assertEquals(copyGear1, this.headGear1);
  }

  @Test
  public void testGetName() {
    assertEquals("strong hat", this.headGear1.getName());
  }

  @Test
  public void testGetNoun() {
    assertEquals("hat", this.headGear1.getNoun());
  }

  @Test
  public void testGetAdjective() {
    assertEquals("strong", this.headGear1.getAdjective());
  }

  @Test
  public void testGetAttack() {
    assertEquals(0, this.headGear1.getAttack());
  }

  @Test
  public void testGetDefense() {
    assertEquals(10, this.headGear1.getDefense());
  }

  @Test
  public void testGetAttackCombined() {
    assertEquals(0, this.combinedHeadGear.getAttack());
  }

  @Test
  public void testGetDefenseCombined() {
    assertEquals(11, this.combinedHeadGear.getDefense());
  }

  @Test
  public void testGetAdjectiveCombined() {
    assertEquals("weak, strong", this.combinedHeadGear.getAdjective());
  }

  @Test
  public void testGetNameCombined() {
    assertEquals("weak, strong hat", this.combinedHeadGear.getName());
  }

  @Test
  public void testIsCombinedTrue() {
    assertTrue(this.combinedHeadGear.isCombined());
  }

  @Test
  public void testIsCombinedFalse() {
    assertFalse(this.headGear1.isCombined());
  }

  @Test
  public void testCombinedWithCombined() {
    Optional<List<IHeadGear>> combined = this.combinedHeadGear.combinedWith();
    assertTrue(combined.isPresent());
    assertEquals(this.headGear1, combined.get().get(0));
    assertEquals(this.headGear2, combined.get().get(1));
  }

  @Test
  public void testCombinedWithNotCombined() {
    Optional<List<IHeadGear>> combined = this.headGear1.combinedWith();
    assertFalse(combined.isPresent());
  }

  @Test
  public void testValidCombine() {
    try {
      IHeadGear combined = this.headGear1.combine(this.headGear2);
      // do nothing, test passes
    } catch (Exception e) {
      fail("Valid combine() should not have failed.");
    }
  }

  @Test
  public void testInvalidCombineCallerAlreadyCombined() {
    try {
      IHeadGear combined = this.combinedHeadGear.combine(this.headGear2);
      fail("Invalid combine() should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidCombineProvidedAlreadyCombined() {
    try {
      IHeadGear combined = this.headGear1.combine(this.combinedHeadGear);
      fail("Invalid combine() should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidCombineWrongType() {
    try {
      IHeadGear gear = new HeadGear(GearType.VISOR, 10, "nice", "visor");
      IHeadGear combined = this.headGear1.combine(gear);
      fail("Invalid combine() should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }
}
