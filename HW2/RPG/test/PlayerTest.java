import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import rpg.enums.GearType;
import rpg.interfaces.IFootGear;
import rpg.interfaces.IHandGear;
import rpg.interfaces.IHeadGear;
import rpg.interfaces.IPlayer;
import rpg.models.FootGear;
import rpg.models.HandGear;
import rpg.models.HeadGear;
import rpg.models.Player;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class PlayerTest {
  private IHeadGear headGear1;
  private IHeadGear headGear2;
  private IHeadGear combinedHeadGear;

  private IHandGear handGear1;
  private IHandGear handGear2;
  private IHandGear combinedHandGear;

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

    this.headGear1 = new HeadGear(GearType.HAT, 10, "strong", "hat");
    this.headGear2 = new HeadGear(GearType.HAT, 1, "weak", "hat");
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
      IPlayer player = new Player(
              1,
              10,
              10,
              new ArrayList<>(Arrays.asList(this.headGear1)),
              new ArrayList<>(Arrays.asList(this.handGear1, this.handGear2)),
              new ArrayList<>(Arrays.asList(this.footGear1, this.footGear2)));
      // do nothing, test passes
    } catch (Exception e) {
      fail("Valid constructor should not have failed.");
    }
  }

  @Test
  public void testInvalidConstructorWithGearsTooManyHeadGears() {
    try {
      IPlayer player = new Player(
              1,
              10,
              10,
              new ArrayList<>(Arrays.asList(this.headGear1, this.headGear2)),
              new ArrayList<>(Arrays.asList(this.handGear1, this.handGear2)),
              new ArrayList<>(Arrays.asList(this.footGear1, this.footGear2)));
      fail("Invalid constructor should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidConstructorWithGearsTooManyhandGears() {
    try {
      IPlayer player = new Player(
              1,
              10,
              10,
              new ArrayList<>(Arrays.asList(this.headGear1, this.headGear2)),
              new ArrayList<>(Arrays.asList(this.handGear1, this.handGear2, this.handGear2)),
              new ArrayList<>(Arrays.asList(this.footGear1, this.footGear2)));
      fail("Invalid constructor should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidConstructorWithGearsTooManyFootGears() {
    try {
      IPlayer player = new Player(
              1,
              10,
              10,
              new ArrayList<>(Arrays.asList(this.headGear1, this.headGear2)),
              new ArrayList<>(Arrays.asList(this.handGear1, this.handGear2)),
              new ArrayList<>(Arrays.asList(this.footGear1, this.footGear2, this.footGear2)));
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
    IPlayer player = new Player(
            1,
            10,
            10,
            new ArrayList<>(Arrays.asList(this.headGear1)),
            new ArrayList<>(Arrays.asList(this.handGear1, this.handGear2)),
            new ArrayList<>(Arrays.asList(this.footGear1, this.footGear2)));
    assertEquals(
            10
            + this.headGear1.getAttack()
            + this.handGear1.getAttack() + this.handGear2.getAttack()
            + this.footGear1.getAttack() + this.footGear2.getAttack(),
            player.getAttack());
  }

  @Test
  public void getDefenseWithGears() {
    IPlayer player = new Player(
            1,
            10,
            10,
            new ArrayList<>(Arrays.asList(this.headGear1)),
            new ArrayList<>(Arrays.asList(this.handGear1, this.handGear2)),
            new ArrayList<>(Arrays.asList(this.footGear1, this.footGear2)));
    assertEquals(
            10
                    + this.headGear1.getDefense()
                    + this.handGear1.getDefense() + this.handGear2.getDefense()
                    + this.footGear1.getDefense() + this.footGear2.getDefense(),
            player.getDefense());
  }
}
