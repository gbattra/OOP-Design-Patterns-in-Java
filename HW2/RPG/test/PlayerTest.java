import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import rpg.enums.GearType;
import rpg.interfaces.IGear;
import rpg.interfaces.IPlayer;
import rpg.models.FootGear;
import rpg.models.HandGear;
import rpg.models.HeadGear;
import rpg.models.Player;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

/**
 * Tests for the Player class.
 */
public class PlayerTest {
  private IGear headGear1;
  private IGear headGear2;
  private IGear combinedHeadGear;

  private IGear handGear1;
  private IGear handGear2;
  private IGear combinedHandGear;

  private IGear footGear1;
  private IGear footGear2;
  private IGear combinedFootGear;


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

    this.headGear1 = new HeadGear(GearType.HAT, 0, "strong", "hat");
    this.headGear2 = new HeadGear(GearType.HAT, 0, "weak", "hat");
    this.combinedHeadGear = new HeadGear(
            GearType.HAT,
            this.headGear1.getDefense() + this.headGear2.getDefense(),
            this.headGear2.getAdjective() + ", " + this.headGear1.getAdjective(),
            this.headGear1.getNoun(),
            new ArrayList<>(Arrays.asList(this.headGear1, this.headGear2)));

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
  public void testValidConstructor() {
    try {
      IPlayer player = new Player(1, 10, 10);
      // do nothing, test passes
    } catch (Exception e) {
      fail("Valid constructor should not have failed.");
    }
  }

  @Test
  public void testInvalidConstructorNegNumber() {
    try {
      IPlayer player = new Player(-1, 10, 10);
      fail("Invalid constructor should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidConstructorNegAttack() {
    try {
      IPlayer player = new Player(1, -10, 10);
      fail("Invalid constructor should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidConstructorNegDefense() {
    try {
      IPlayer player = new Player(1, 10, -10);
      fail("Invalid constructor should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testValidConstructorWithGears() {
    try {
      IPlayer player = new Player(1, 10, 10,
              new ArrayList<>(Arrays.asList(
                      this.headGear1,
                      this.handGear1, this.handGear2,
                      this.footGear1, this.footGear2)));
      // do nothing, test passes
    } catch (Exception e) {
      fail("Valid constructor should not have failed.");
    }
  }

  @Test
  public void testValidConstructorWithGearsNegNumber() {
    try {
      IPlayer player = new Player(-1, 10, 10,
              new ArrayList<>(Arrays.asList(
                      this.headGear1,
                      this.handGear1, this.handGear2,
                      this.footGear1, this.footGear2)));
      fail("Invalid constructor should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testValidConstructorWithGearsNegAttack() {
    try {
      IPlayer player = new Player(1, -10, 10,
              new ArrayList<>(Arrays.asList(
                      this.headGear1,
                      this.handGear1, this.handGear2,
                      this.footGear1, this.footGear2)));
      fail("Invalid constructor should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testValidConstructorWithGearsNegDefense() {
    try {
      IPlayer player = new Player(1, 10, -10,
              new ArrayList<>(Arrays.asList(
                      this.headGear1,
                      this.handGear1, this.handGear2,
                      this.footGear1, this.footGear2)));
      fail("Invalid constructor should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidConstructorWithGearsTooManyHeadGears() {
    try {
      IPlayer player = new Player(1, 10, 10,
              new ArrayList<>(Arrays.asList(
                      this.headGear1, this.headGear2,
                      this.handGear1, this.handGear2,
                      this.footGear1, this.footGear2)));
      fail("Invalid constructor should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidConstructorWithGearsTooManyhandGears() {
    try {
      IPlayer player = new Player(1, 10, 10,
              new ArrayList<>(Arrays.asList(
                      this.headGear1,
                      this.handGear1, this.handGear2, this.handGear1,
                      this.footGear1, this.footGear2)));
      fail("Invalid constructor should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidConstructorWithGearsTooManyFootGears() {
    try {
      IPlayer player = new Player(1, 10, 10,
              new ArrayList<>(Arrays.asList(
                      this.headGear1,
                      this.handGear1, this.handGear2,
                      this.footGear1, this.footGear2, this.footGear1)));
      fail("Invalid constructor should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testGetNumber() {
    IPlayer player = new Player(1, 10, 10);
    assertEquals(1, player.getNumber());
  }

  @Test
  public void getAttackNoGear() {
    IPlayer player = new Player(1, 10, 10);
    assertEquals(10, player.getAttack());
  }

  @Test
  public void getDefenseNoGear() {
    IPlayer player = new Player(1, 10, 10);
    assertEquals(10, player.getDefense());
  }

  @Test
  public void getAttackWithGears() {
    IPlayer player = new Player(1, 10, 10,
            new ArrayList<>(Arrays.asList(
                    this.headGear1,
                    this.handGear1, this.handGear2,
                    this.footGear1, this.footGear2)));
    assertEquals(
            10
                    + this.headGear1.getAttack()
                    + this.handGear1.getAttack() + this.handGear2.getAttack()
                    + this.footGear1.getAttack() + this.footGear2.getAttack(),
            player.getAttack());
  }

  @Test
  public void getDefenseWithGears() {
    IPlayer player = new Player(1, 10, 10,
            new ArrayList<>(Arrays.asList(
                    this.headGear1,
                    this.handGear1, this.handGear2,
                    this.footGear1, this.footGear2)));
    assertEquals(
            10
                    + this.headGear1.getDefense()
                    + this.handGear1.getDefense() + this.handGear2.getDefense()
                    + this.footGear1.getDefense() + this.footGear2.getDefense(),
            player.getDefense());
  }

  @Test
  public void testValidAddHeadGear() {
    try {
      IPlayer player = new Player(1, 10, 10).addGear(this.headGear1);
      // do nothing, test passes
    } catch (Exception e) {
      fail("Valid addHeadGear() should not have failed.");
    }
  }

  @Test
  public void testValidAddHeadGearCombine() {
    try {
      IPlayer player = new Player(1, 10, 10)
              .addGear(this.headGear1)
              .addGear(this.headGear2);
      // do nothing, test passes
    } catch (Exception e) {
      fail("Valid addHeadGear() should have not failed.");
    }
  }

  @Test
  public void testInvalidAddHeadGearCombined() {
    try {
      IPlayer player = new Player(1, 10, 10)
              .addGear(this.combinedHeadGear)
              .addGear(this.headGear2);
      fail("Invalid addHeadGear() should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testValidAddHandGear() {
    try {
      IPlayer player = new Player(1, 10, 10)
              .addGear(this.handGear1)
              .addGear(this.handGear2);
      // do nothing, test passes
    } catch (Exception e) {
      fail("Valid addHandGear() should not have failed.");
    }
  }

  @Test
  public void testValidAddHandGearCombine() {
    try {
      IPlayer player = new Player(1, 10, 10)
              .addGear(this.handGear1)
              .addGear(this.handGear2)
              .addGear(this.handGear1);
      // do nothing, test passes
    } catch (Exception e) {
      fail("Valid addHandGear() should not have failed.");
    }
  }

  @Test
  public void testValidAddHandGearOneAlreadyCombined() {
    try {
      IPlayer player = new Player(1, 10, 10)
              .addGear(this.combinedHandGear)
              .addGear(this.handGear2)
              .addGear(this.handGear1);
      // do nothing, test passes
    } catch (Exception e) {
      fail("Valid addHandGear() should not have failed.");
    }
  }

  @Test
  public void testInvalidAddHandGearBothAlreadyCombined() {
    try {
      IPlayer player = new Player(1, 10, 10)
              .addGear(this.combinedHandGear)
              .addGear(this.combinedHandGear)
              .addGear(this.handGear1);
      fail("Invalid addHandGear() should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testValidAddFootGear() {
    try {
      IPlayer player = new Player(1, 10, 10)
              .addGear(this.footGear1)
              .addGear(this.footGear2);
      // do nothing, test passes
    } catch (Exception e) {
      fail("Valid addFootGear() should not have failed.");
    }
  }

  @Test
  public void testValidAddFootGearCombine() {
    try {
      IPlayer player = new Player(1, 10, 10)
              .addGear(this.footGear1)
              .addGear(this.footGear2)
              .addGear(this.footGear1);
      // do nothing, test passes
    } catch (Exception e) {
      fail("Valid addFootGear() should not have failed.");
    }
  }

  @Test
  public void testValidAddFootGearOneAlreadyCombined() {
    try {
      IPlayer player = new Player(1, 10, 10)
              .addGear(this.combinedFootGear)
              .addGear(this.footGear2)
              .addGear(this.footGear1);
      // do nothing, test passes
    } catch (Exception e) {
      fail("Valid addFootGear() should not have failed.");
    }
  }

  @Test
  public void testInvalidAddFootGearBothAlreadyCombined() {
    try {
      IPlayer player = new Player(1, 10, 10)
              .addGear(this.combinedFootGear)
              .addGear(this.combinedFootGear)
              .addGear(this.footGear1);
      fail("Invalid addFootGear() should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testToString() {
    IPlayer player = new Player(1, 10, 10)
            .addGear(this.headGear1)
            .addGear(this.handGear1)
            .addGear(this.footGear1);
    String expect = String.format(
            "Player %s:\n"
                    + "- Total Attack: %s,\n- Total Defense: %s,\n- Base Attack: %s,\n- Base Defense: %s,"
                    + "\n- HeadGear: %s,\n- Handgear: %s,\n- Footgear: %s",
            1,
            10 + 10 + 10,
            10 + 10 + 10,
            10,
            10,
            this.headGear1.toString(),
            this.handGear1.toString(),
            this.footGear1.toString());

    assertEquals(expect, player.toString());
  }

  @Test
  public void testEqualsTrue() {
    IPlayer playerOne = new Player(1, 10, 10)
            .addGear(this.headGear1)
            .addGear(this.handGear1)
            .addGear(this.footGear1);
    IPlayer playerTwo = new Player(1, 10, 10)
            .addGear(this.headGear1)
            .addGear(this.handGear1)
            .addGear(this.footGear1);
    assertEquals(playerOne, playerTwo);
  }

  @Test
  public void testEqualsFalse() {
    IPlayer playerOne = new Player(1, 5, 5)
            .addGear(this.headGear1)
            .addGear(this.handGear1)
            .addGear(this.footGear1);
    IPlayer playerTwo = new Player(1, 10, 10)
            .addGear(this.headGear1)
            .addGear(this.handGear1);
    assertNotEquals(playerOne, playerTwo);
  }
}