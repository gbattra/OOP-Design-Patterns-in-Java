import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import rpg.enums.GearType;
import rpg.interfaces.IHandGear;
import rpg.models.HandGear;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Tests for the HandGear class.
 */
public class HandGearTest {
  private IHandGear handGear1;
  private IHandGear handGear2;
  private IHandGear combinedHandGear;

  @Before
  public void setup() {
    this.handGear1 = new HandGear(GearType.GLOVE, 10, 10, "strong", "glove");
    this.handGear2 = new HandGear(GearType.GLOVE, 1, 1, "weak", "glove");
    this.combinedHandGear = new HandGear(
            GearType.GLOVE,
            this.handGear1.getAttack() + this.handGear2.getAttack(),
            this.handGear1.getDefense() + this.handGear2.getDefense(),
            this.handGear2.getAdjective() + ", " + this.handGear1.getAdjective(),
            this.handGear1.getNoun(),
            new ArrayList<>(Arrays.asList(this.handGear1, this.handGear2)));
  }

  @Test
  public void testValidConstructorNotCombined() {
    try {
      IHandGear gear = new HandGear(GearType.GLOVE, 10, 10, "strong", "glove");
      // do nothing, test passes
    } catch (Exception e) {
      fail("Valid constructor should not have failed.");
    }
  }

  @Test
  public void testInvalidConstructorWrongType() {
    try {
      IHandGear gear = new HandGear(GearType.HAT, 10, 10, "strong", "glove");
      fail("Invalid constructor should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidConstructorNotCombinedNegAttack() {
    try {
      IHandGear gear = new HandGear(GearType.GLOVE, -10, 10, "strong", "glove");
      fail("Invalid constructor should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidConstructorNotCombinedNegDefense() {
    try {
      IHandGear gear = new HandGear(GearType.GLOVE, 10, -10, "strong", "glove");
      fail("Invalid constructor should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidConstructorNotCombinedEmptyAdj() {
    try {
      IHandGear gear = new HandGear(GearType.GLOVE, 10, 10, "", "glove");
      fail("Invalid constructor should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidConstructorNotCombinedEmptyNoun() {
    try {
      IHandGear gear = new HandGear(GearType.GLOVE, 10, 10, "strong", "");
      fail("Invalid constructor should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testValidConstructorCombined() {
    try {
      IHandGear gear = new HandGear(
              GearType.GLOVE,
              10,
              10,
              "strong",
              "glove",
              new ArrayList<>(Arrays.asList(this.handGear1, this.handGear2)));
    } catch (Exception e) {
      fail("Valid constructor should not have failed.");
    }
  }

  @Test
  public void testInvalidConstructorCombinedWrongType() {
    try {
      IHandGear gear = new HandGear(
              GearType.HAT,
              10,
              10,
              "strong",
              "glove",
              new ArrayList<>(Arrays.asList(this.handGear2)));
      fail("Invalid constructor should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidConstructorCombinedWrongCount() {
    try {
      IHandGear gear = new HandGear(
              GearType.GLOVE,
              10,
              10,
              "strong",
              "glove",
              new ArrayList<>(Arrays.asList(this.handGear2)));
      fail("Invalid constructor should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidConstructorCombinedNegAttack() {
    try {
      IHandGear gear = new HandGear(
              GearType.GLOVE,
              -10,
              10,
              "strong",
              "glove",
              new ArrayList<>(Arrays.asList(this.handGear1, this.handGear2)));
      fail("Invalid constructor should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidConstructorCombinedNegDefense() {
    try {
      IHandGear gear = new HandGear(
              GearType.GLOVE,
              10,
              -10,
              "strong",
              "glove",
              new ArrayList<>(Arrays.asList(this.handGear1, this.handGear2)));
      fail("Invalid constructor should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidConstructorCombinedEmptyAdj() {
    try {
      IHandGear gear = new HandGear(
              GearType.GLOVE,
              10,
              10,
              "",
              "glove",
              new ArrayList<>(Arrays.asList(this.handGear1, this.handGear2)));
      fail("Invalid constructor should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidConstructorCombinedEmptyNoun() {
    try {
      IHandGear gear = new HandGear(
              GearType.GLOVE,
              10,
              10,
              "strong",
              "",
              new ArrayList<>(Arrays.asList(this.handGear1, this.handGear2)));
      fail("Invalid constructor should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testToStringNotCombined() {
    String expected = String.format(
            "Gear - Type: %s, Adj: %s, Noun: %s, Attack: %s, Defense: %s.",
            GearType.GLOVE, "strong", "glove", 10, 10);
    assertEquals(expected, this.handGear1.toString());
  }

  @Test
  public void testToStringCombined() {
    String expected = String.format(
            "Gear - Type: %s, Adj: %s, Noun: %s, Attack: %s, Defense: %s.",
            GearType.GLOVE, "weak, strong", "glove", 11, 11);
    expected += String.format(
            " Combined with: Gear - Type: %s, Adj: %s, Noun: %s, Attack: %s, Defense: %s.",
            GearType.GLOVE, "strong", "glove", 10, 10);
    expected += String.format(
            "Gear - Type: %s, Adj: %s, Noun: %s, Attack: %s, Defense: %s.",
            GearType.GLOVE, "weak", "glove", 1, 1);
    assertEquals(expected, this.combinedHandGear.toString());
  }

  @Test
  public void testEqualsTrue() {
    IHandGear copyGear1 = new HandGear(
            this.handGear1.getType(),
            this.handGear1.getAttack(),
            this.handGear1.getDefense(),
            this.handGear1.getAdjective(),
            this.handGear1.getNoun());
    assertEquals(copyGear1, this.handGear1);
  }

  @Test
  public void testGetName() {
    assertEquals("strong glove", this.handGear1.getName());
  }

  @Test
  public void testGetNoun() {
    assertEquals("glove", this.handGear1.getNoun());
  }

  @Test
  public void testGetAdjective() {
    assertEquals("strong", this.handGear1.getAdjective());
  }

  @Test
  public void testGetAttack() {
    assertEquals(10, this.handGear1.getAttack());
  }

  @Test
  public void testGetDefense() {
    assertEquals(10, this.handGear1.getDefense());
  }

  @Test
  public void testGetAttackCombined() {
    assertEquals(11, this.combinedHandGear.getAttack());
  }

  @Test
  public void testGetDefenseCombined() {
    assertEquals(11, this.combinedHandGear.getDefense());
  }

  @Test
  public void testGetAdjectiveCombined() {
    assertEquals("weak, strong", this.combinedHandGear.getAdjective());
  }

  @Test
  public void testGetNameCombined() {
    assertEquals("weak, strong glove", this.combinedHandGear.getName());
  }

  @Test
  public void testIsCombinedTrue() {
    assertTrue(this.combinedHandGear.isCombined());
  }

  @Test
  public void testIsCombinedFalse() {
    assertFalse(this.handGear1.isCombined());
  }

  @Test
  public void testCombinedWithCombined() {
    Optional<List<IHandGear>> combined = this.combinedHandGear.combinedWith();
    assertTrue(combined.isPresent());
    assertEquals(this.handGear1, combined.get().get(0));
    assertEquals(this.handGear2, combined.get().get(1));
  }

  @Test
  public void testCombinedWithNotCombined() {
    Optional<List<IHandGear>> combined = this.handGear1.combinedWith();
    assertFalse(combined.isPresent());
  }

  @Test
  public void testValidCombine() {
    try {
      IHandGear combined = this.handGear1.combine(this.handGear2);
      // do nothing, test passes
    } catch (Exception e) {
      fail("Valid combine() should not have failed.");
    }
  }

  @Test
  public void testInvalidCombineCallerAlreadyCombined() {
    try {
      IHandGear combined = this.combinedHandGear.combine(this.handGear2);
      fail("Invalid combine() should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidCombineProvidedAlreadyCombined() {
    try {
      IHandGear combined = this.handGear1.combine(this.combinedHandGear);
      fail("Invalid combine() should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidCombineWrongType() {
    try {
      IHandGear gear = new HandGear(
              GearType.SHIELD,
              10, 10, "nice", "sneaker");
      IHandGear combined = this.handGear1.combine(gear);
      fail("Invalid combine() should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }
}
