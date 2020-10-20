import org.junit.Test;

import rpg.enums.GearType;
import rpg.interfaces.IPlayer;
import rpg.interfaces.IPlayerBuilder;
import rpg.models.PlayerBuilder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class PlayerBuilderTest {
  @Test
  public void testValidConstructor() {
    try {
      IPlayerBuilder builder = new PlayerBuilder(1, 10, 10);
      // do nothing, test passes
    } catch (Exception e) {
      fail("Valid constructor should not have failed.");
    }
  }

  @Test
  public void testInvalidConstructorNegativeNumber() {
    try {
      IPlayerBuilder builder = new PlayerBuilder(-1, 10, 10);
      fail("Invalid constructor should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidConstructorNegativeAttack() {
    try {
      IPlayerBuilder builder = new PlayerBuilder(1, -10, 10);
      fail("Invalid constructor should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidConstructorNegativeDefense() {
    try {
      IPlayerBuilder builder = new PlayerBuilder(1, -10, 10);
      fail("Invalid constructor should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testValidPlayerBuild() {
    try {
      IPlayer player = new PlayerBuilder(1, 10, 10)
              .addGear(GearType.HAT, 0, 10, "small", "hat")
              .addGear(GearType.SNEAKER, 3, 5, "soft", "sneaker")
              .addGear(GearType.BOOT, 10, 15, "steel-toed", "boot")
              .addGear(GearType.SWORD, 20, 5, "long", "sword")
              .build();
      assertEquals(1, player.getNumber());
      assertEquals(43, player.getAttack());
      assertEquals(45, player.getDefense());
    } catch (Exception e) {
      fail("Valid player build() should not have failed.");
    }
  }

  @Test
  public void testValidAddGear() {
    try {
      IPlayerBuilder builder = new PlayerBuilder(1, 10, 10)
              .addGear(GearType.HAT, 0, 10, "small", "hat")
              .addGear(GearType.HAT, 0, 10, "small", "hat");
    } catch (Exception e) {
      fail("Valid player build() should not have failed.");
    }
  }

  @Test
  public void testValidAddGearAlreadyCombined() {
    try {
      IPlayerBuilder builder = new PlayerBuilder(1, 10, 10)
              .addGear(GearType.HAT, 0, 10, "small", "hat")
              .addGear(GearType.HAT, 0, 10, "small", "hat")
              .addGear(GearType.HAT, 0, 10, "small", "hat");
      fail("Invalid addHeadGear() should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidAddGearNegDefense() {
    try {
      IPlayerBuilder builder = new PlayerBuilder(1, 10, 10)
              .addGear(GearType.SWORD, 0, -10, "small", "hat");
      fail("Invalid addHeadGear() should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidAddGearNoAdj() {
    try {
      IPlayerBuilder builder = new PlayerBuilder(1, 10, 10)
              .addGear(GearType.SWORD, 0, 10, "", "hat");
      fail("Invalid addHeadGear() should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidAddGearNoNoun() {
    try {
      IPlayerBuilder builder = new PlayerBuilder(1, 10, 10)
              .addGear(GearType.SWORD, 0, 10, "small", "");
      fail("Invalid addHeadGear() should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }
}
