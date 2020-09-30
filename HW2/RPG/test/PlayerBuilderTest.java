import org.junit.Test;

import rpg.enums.GearType;
import rpg.interfaces.IPlayer;
import rpg.interfaces.IPlayerBuilder;
import rpg.models.HeadGear;
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
              .addHeadGear(GearType.HAT, 10, "small", "hat")
              .addFootGear(GearType.SNEAKER, 3, 5, "soft", "sneaker")
              .addFootGear(GearType.BOOT, 10, 15, "steel-toed", "boot")
              .addHandGear(GearType.SWORD, 20, 5, "long", "sword")
              .build();
      assertEquals(1, player.getNumber());
      assertEquals(43, player.getAttack());
      assertEquals(45, player.getDefense());
    } catch (Exception e) {
      fail("Valid player build() should not have failed.");
    }
  }

  @Test
  public void testValidAddHeadGear() {
    try {
      IPlayerBuilder builder = new PlayerBuilder(1, 10, 10)
              .addHeadGear(GearType.HAT, 10, "small", "hat")
              .addHeadGear(GearType.HAT, 10, "small", "hat");
    } catch (Exception e) {
      fail("Valid player build() should not have failed.");
    }
  }

  @Test
  public void testValidAddHeadGearAlreadyCombined() {
    try {
      IPlayerBuilder builder = new PlayerBuilder(1, 10, 10)
              .addHeadGear(GearType.HAT, 10, "small", "hat")
              .addHeadGear(GearType.HAT, 10, "small", "hat")
              .addHeadGear(GearType.HAT, 10, "small", "hat");
      fail("Invalid addHeadGear() should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidAddHeadGearWrongType() {
    try {
      IPlayerBuilder builder = new PlayerBuilder(1, 10, 10)
              .addHeadGear(GearType.SWORD, 10, "small", "hat");
      fail("Invalid addHeadGear() should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidAddHeadGearNegDefense() {
    try {
      IPlayerBuilder builder = new PlayerBuilder(1, 10, 10)
              .addHeadGear(GearType.SWORD, -10, "small", "hat");
      fail("Invalid addHeadGear() should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidAddHeadGearNoAdj() {
    try {
      IPlayerBuilder builder = new PlayerBuilder(1, 10, 10)
              .addHeadGear(GearType.SWORD, 10, "", "hat");
      fail("Invalid addHeadGear() should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidAddHeadGearNoNoun() {
    try {
      IPlayerBuilder builder = new PlayerBuilder(1, 10, 10)
              .addHeadGear(GearType.SWORD, 10, "small", "");
      fail("Invalid addHeadGear() should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testValidAddHandGear() {
    try {
      IPlayerBuilder builder = new PlayerBuilder(1, 10, 10)
              .addHandGear(GearType.GLOVE, 10, 10, "small", "glove")
              .addHandGear(GearType.GLOVE, 10, 10, "small", "glove");
    } catch (Exception e) {
      fail("Valid addHandGear() should not have failed.");
    }
  }

  @Test
  public void testValidAddHandGearAlreadyCombined() {
    try {
      IPlayerBuilder builder = new PlayerBuilder(1, 10, 10)
              .addHandGear(GearType.GLOVE, 10, 10, "small", "glove")
              .addHandGear(GearType.GLOVE, 10, 10, "small", "glove")
              .addHandGear(GearType.GLOVE, 10, 10, "small", "glove");
    } catch (Exception e) {
      fail("Valid addHandGear() should not have failed.");
    }
  }

  @Test
  public void testInvalidAddHandGearWrongType() {
    try {
      IPlayerBuilder builder = new PlayerBuilder(1, 10, 10)
              .addHandGear(GearType.HELMET, 10, 10, "small", "glove");
      fail("Invalid addHandGear() should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidAddHandGearNegAttack() {
    try {
      IPlayerBuilder builder = new PlayerBuilder(1, 10, 10)
              .addHandGear(GearType.GLOVE, -10, 10, "small", "glove");
      fail("Invalid addHandGear() should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidAddHandGearNegDefense() {
    try {
      IPlayerBuilder builder = new PlayerBuilder(1, 10, 10)
              .addHandGear(GearType.GLOVE, 10, -10, "small", "glove");
      fail("Invalid addHandGear() should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidAddHandGearNoAdj() {
    try {
      IPlayerBuilder builder = new PlayerBuilder(1, 10, 10)
              .addHandGear(GearType.GLOVE, 10, 10, "", "glove");
      fail("Invalid addHandGear() should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidAddHandGearNoNoun() {
    try {
      IPlayerBuilder builder = new PlayerBuilder(1, 10, 10)
              .addHandGear(GearType.GLOVE, 10, 10, "small", "");
      fail("Invalid addHandGear() should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testValidAddFootGear() {
    try {
      IPlayerBuilder builder = new PlayerBuilder(1, 10, 10)
              .addFootGear(GearType.SNEAKER, 10, 10, "small", "sneaker")
              .addFootGear(GearType.SNEAKER, 10, 10, "small", "sneaker");
    } catch (Exception e) {
      fail("Valid addFootGear() should not have failed.");
    }
  }

  @Test
  public void testValidAddFootGearAlreadyCombined() {
    try {
      IPlayerBuilder builder = new PlayerBuilder(1, 10, 10)
              .addFootGear(GearType.SNEAKER, 10, 10, "small", "sneaker")
              .addFootGear(GearType.SNEAKER, 10, 10, "small", "sneaker")
              .addFootGear(GearType.SNEAKER, 10, 10, "small", "sneaker");
    } catch (Exception e) {
      fail("Valid addFootGear() should not have failed.");
    }
  }

  @Test
  public void testInvalidAddFootGearWrongType() {
    try {
      IPlayerBuilder builder = new PlayerBuilder(1, 10, 10)
              .addFootGear(GearType.HELMET, 10, 10, "small", "sneaker");
      fail("Invalid addFootGear() should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidAddFootGearNegAttack() {
    try {
      IPlayerBuilder builder = new PlayerBuilder(1, 10, 10)
              .addFootGear(GearType.SNEAKER, -10, 10, "small", "sneaker");
      fail("Invalid addFootGear() should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidAddFootGearNegDefense() {
    try {
      IPlayerBuilder builder = new PlayerBuilder(1, 10, 10)
              .addFootGear(GearType.SNEAKER, 10, -10, "small", "sneaker");
      fail("Invalid addFootGear() should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidAddFootGearNoAdj() {
    try {
      IPlayerBuilder builder = new PlayerBuilder(1, 10, 10)
              .addFootGear(GearType.SNEAKER, 10, 10, "", "sneaker");
      fail("Invalid addFootGear() should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidAddFootGearNoNoun() {
    try {
      IPlayerBuilder builder = new PlayerBuilder(1, 10, 10)
              .addFootGear(GearType.SNEAKER, 10, 10, "small", "");
      fail("Invalid addFootGear() should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }
}
