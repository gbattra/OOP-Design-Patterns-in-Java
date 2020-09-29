import org.junit.Test;

import rpg.enums.GearType;
import rpg.interfaces.IPlayer;
import rpg.interfaces.IPlayerBuilder;
import rpg.models.PlayerBuilder;

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
              .addHeadGear(GearType.HAT, 10, "small", "hat")
              .addFootGear(GearType.SNEAKER, 3, 5, "soft", "sneaker")
              .addFootGear(GearType.BOOT, 10, 15, "steel-toed", "boot")
              .addHandGear(GearType.SWORD, 20, 5, "long", "sword")
              .build();
      // do nothing, test passes
    } catch (Exception e) {
      fail("Valid player build() should not have failed.");
    }
  }

  @Test
  public void testInvalidPlayerBuildWrongType() {
    try {
      IPlayer player = new PlayerBuilder(1, 10, 10)
              .addHeadGear(GearType.SWORD, 10, "small", "hat")  // wrong type
              .addFootGear(GearType.SNEAKER, 3, 5, "soft", "sneaker")
              .addFootGear(GearType.BOOT, 10, 15, "steel-toed", "boot")
              .addHandGear(GearType.SWORD, 20, 5, "long", "sword")
              .build();
      fail("Invalid player build() should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidPlayerBuildNegAttack() {
    try {
      IPlayer player = new PlayerBuilder(1, 10, 10)
              .addHeadGear(GearType.SWORD, 10, "small", "hat")  // wrong type
              .addFootGear(GearType.SNEAKER, -3, 5, "soft", "sneaker")
              .addFootGear(GearType.BOOT, 10, 15, "steel-toed", "boot")
              .addHandGear(GearType.SWORD, 20, 5, "long", "sword")
              .build();
      fail("Invalid player build() should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidPlayerBuildTooMuchGear() {
    try {
      IPlayer player = new PlayerBuilder(1, 10, 10)
              .addHeadGear(GearType.SWORD, 10, "small", "hat")  // wrong type
              .addFootGear(GearType.SNEAKER, 3, 5, "soft", "sneaker")
              .addFootGear(GearType.SNEAKER, 5, 7, "hard", "sneaker")
              .addFootGear(GearType.BOOT, 10, 15, "steel-toed", "boot")
              .addFootGear(GearType.BOOT, 5, 10, "soft", "boot")
              .addHandGear(GearType.SWORD, 20, 5, "long", "sword")
              .addHandGear(GearType.SWORD, 10, 10, "steel", "sword")
              .addHandGear(GearType.SHIELD, 4, 15, "soft", "shield")
              .addHandGear(GearType.SHIELD, 10, 10, "steel", "shield")
              .addHandGear(GearType.SHIELD, 10, 10, "steel", "shield")
              .build();
      fail("Invalid player build() should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }
}
