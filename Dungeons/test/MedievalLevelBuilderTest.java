import org.junit.Test;

import dungeon.ILevelBuilder;
import dungeon.Level;
import dungeon.MedievalLevelBuilder;

import static org.junit.Assert.fail;

/**
 * Test class for MedievalLevelBuilder object.
 */
public class MedievalLevelBuilderTest {
  @Test
  public void testValidConstructor() {
    try {
      ILevelBuilder builder = new MedievalLevelBuilder(1, 10, 5, 10);
      // do nothing test passes
    } catch (Exception e) {
      fail("Valid instantiation of builder should not have failed.");
    }
  }

  @Test
  public void testInvalidConstructorNegativeLevelNumber() {
    try {
      ILevelBuilder builder = new MedievalLevelBuilder(-1, 10, 5, 10);
      fail("Invalid instantiation of builder should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidConstructorNegativeRoomCount() {
    try {
      ILevelBuilder builder = new MedievalLevelBuilder(1, -10, 5, 10);
      fail("Invalid instantiation of builder should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidConstructorNegativeMonsterCount() {
    try {
      ILevelBuilder builder = new MedievalLevelBuilder(1, 10, -5, 10);
      fail("Invalid instantiation of builder should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidConstructorNegativeTreasureCount() {
    try {
      ILevelBuilder builder = new MedievalLevelBuilder(1, 10, 5, -10);
      fail("Invalid instantiation of builder should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testValidAddRoom() {
    try {
      ILevelBuilder builder = new MedievalLevelBuilder(1, 1, 5, 10)
              .addRoom("This is the first room.");
    } catch (Exception e) {
      fail("Valid addRoom() call should not have failed.");
    }
  }

  @Test
  public void testInvalidAddRoomTooMany() {
    try {
      ILevelBuilder builder = new MedievalLevelBuilder(1, 1, 5, 10)
              .addRoom("This is the first room.")
              .addRoom("This is the second room.");  // this should throw
      fail(" Invalid addRoom() call should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testValidAddGoblin() {
    try {
      ILevelBuilder builder = new MedievalLevelBuilder(1, 10, 1, 10)
              .addRoom("This is the first room.")
              .addGoblins(0, 1);
      // do nothing, test passes
    } catch (Exception e) {
      fail("Valid addGoblin() call should not have failed.");
    }
  }

  @Test
  public void testInvalidAddGoblinRoomNotSet() {
    try {
      ILevelBuilder builder = new MedievalLevelBuilder(1, 10, 1, 10)
              .addGoblins(0, 1);
      fail("Invalid addGoblins() call should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidAddGoblinTooManyMonsters() {
    try {
      ILevelBuilder builder = new MedievalLevelBuilder(1, 10, 1, 10)
              .addRoom("This is the first room.")
              .addGoblins(0, 1)
              .addGoblins(0, 1);
      fail("Invalid addGoblins() call should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testValidAddOrc() {
    try {
      ILevelBuilder builder = new MedievalLevelBuilder(1, 10, 1, 10)
              .addRoom("This is the first room.")
              .addOrc(0);
      // do nothing, test passes
    } catch (Exception e) {
      fail("Valid addOrc() call should not have failed.");
    }
  }

  @Test
  public void testValidAddOrcRoomNotSet() {
    try {
      ILevelBuilder builder = new MedievalLevelBuilder(1, 10, 1, 10)
              .addOrc(0);
      fail("Invalid addOrc() call should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testValidAddOrcTooManyMonsters() {
    try {
      ILevelBuilder builder = new MedievalLevelBuilder(1, 10, 1, 10)
              .addRoom("This is the first room.")
              .addOrc(0)
              .addOrc(0);  // this should throw
      fail("Invalid addOrc() call should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testValidAddOgre() {
    try {
      ILevelBuilder builder = new MedievalLevelBuilder(1, 10, 1, 10)
              .addRoom("This is the first room.")
              .addOgre(0);
      // do nothing, test passes
    } catch (Exception e) {
      fail("Valid addOgre() call should not have failed.");
    }
  }

  @Test
  public void testValidAddOgreRoomNotSet() {
    try {
      ILevelBuilder builder = new MedievalLevelBuilder(1, 10, 1, 10)
              .addOgre(0);
      fail("Invalid addOgre() call should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testValidAddOgreTooManyMonsters() {
    try {
      ILevelBuilder builder = new MedievalLevelBuilder(1, 10, 1, 10)
              .addRoom("This is the first room.")
              .addOgre(0)
              .addOgre(0);  // this should throw
      fail("Invalid addOgre() call should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testValidAddHuman() {
    try {
      ILevelBuilder builder = new MedievalLevelBuilder(1, 10, 1, 10)
              .addRoom("This is the first room.")
              .addHuman(
                      0,
                      "Bob",
                      "Bob is a criminal and his punishment is to navigate this dungeon.",
                      10);
    } catch (Exception e) {
      fail("Valid addHuman() call should not have failed.");
    }
  }

  @Test
  public void testInvalidAddHumanNegativeHitPoints() {
    try {
      ILevelBuilder builder = new MedievalLevelBuilder(1, 10, 1, 10)
              .addRoom("This is the first room.")
              .addHuman(
                      0,
                      "Bob",
                      "Bob is a criminal and his punishment is to navigate this dungeon.",
                      -10);
      fail("Invalid addHuman() call should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidAddHumanRoomNotSet() {
    try {
      ILevelBuilder builder = new MedievalLevelBuilder(1, 10, 1, 10)
              .addHuman(
                      0,
                      "Bob",
                      "Bob is a criminal and his punishment is to navigate this dungeon.",
                      10);
      fail("Invalid addHuman() call should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidAddHumanTooManyMonsters() {
    try {
      ILevelBuilder builder = new MedievalLevelBuilder(1, 10, 1, 10)
              .addRoom("This is the first room.")
              .addHuman(
                      0,
                      "Bob",
                      "Bob is a criminal and his punishment is to navigate this dungeon.",
                      10)
              .addHuman(
                      0,
                      "Sally",
                      "Sally is here to rescue Bob from the dungeon.",
                      10);
      fail("Invalid addHuman() call should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testValidAddPotion() {
    try {
      ILevelBuilder builder = new MedievalLevelBuilder(1, 10, 1, 10)
              .addRoom("This is the first room")
              .addPotion(0);
      // do nothing, test passes
    } catch (Exception e) {
      fail("Valid addPotion() call should not have failed.");
    }
  }

  @Test
  public void testInvalidAddPotionRoomNotSet() {
    try {
      ILevelBuilder builder = new MedievalLevelBuilder(1, 10, 1, 10)
              .addPotion(0);
      fail("Invalid addPotion() call should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidAddPotionTooManyTreasures() {
    try {
      ILevelBuilder builder = new MedievalLevelBuilder(1, 10, 1, 1)
              .addRoom("This is the first room")
              .addPotion(0)
              .addPotion(0); // this throws
      fail("Invalid addPotion() call should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testValidAddGold() {
    try {
      ILevelBuilder builder = new MedievalLevelBuilder(1, 10, 1, 10)
              .addRoom("This is the first room")
              .addGold(0, 10);
      // do nothing, test passes
    } catch (Exception e) {
      fail("Valid addGold() call should not have failed.");
    }
  }

  @Test
  public void testInvalidAddGoldRoomNotSet() {
    try {
      ILevelBuilder builder = new MedievalLevelBuilder(1, 10, 1, 10)
              .addGold(0, 10);
      fail("Invalid addGold() call should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidAddGoldTooManyTreasures() {
    try {
      ILevelBuilder builder = new MedievalLevelBuilder(1, 10, 1, 1)
              .addRoom("This is the first room")
              .addGold(0, 10)
              .addGold(0, 10); // this throws
      fail("Invalid addGold() call should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidAddGoldNegativeValue() {
    try {
      ILevelBuilder builder = new MedievalLevelBuilder(1, 10, 1, 1)
              .addRoom("This is the first room")
              .addGold(0, -10);
      fail("Invalid addGold() call should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testValidAddWeapon() {
    try {
      ILevelBuilder builder = new MedievalLevelBuilder(1, 10, 1, 10)
              .addRoom("This is the first room")
              .addWeapon(0, "I am cool weapon!");
      // do nothing, test passes
    } catch (Exception e) {
      fail("Valid addWeapon() call should not have failed.");
    }
  }

  @Test
  public void testInvalidAddWeaponRoomNotSet() {
    try {
      ILevelBuilder builder = new MedievalLevelBuilder(1, 10, 1, 10)
              .addWeapon(0, "I am cool weapon!");
      fail("Invalid addWeapon() call should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidAddWeaponTooManyTreasures() {
    try {
      ILevelBuilder builder = new MedievalLevelBuilder(1, 10, 1, 1)
              .addRoom("This is the first room")
              .addWeapon(0, "I am cool weapon!")
              .addWeapon(0, "I am cool weapon!"); // this throws
      fail("Invalid addWeapon() call should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testValidAddSpecial() {
    try {
      ILevelBuilder builder = new MedievalLevelBuilder(1, 10, 1, 10)
              .addRoom("This is the first room")
              .addSpecial(0, "I am a special item!", 10);
      // do nothing, test passes
    } catch (Exception e) {
      fail("Valid addSpecial() call should not have failed.");
    }
  }

  @Test
  public void testInvalidAddSpecialRoomNotSet() {
    try {
      ILevelBuilder builder = new MedievalLevelBuilder(1, 10, 1, 10)
              .addSpecial(0, "I am a special item!", 10);
      fail("Invalid addSpecial() call should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidAddSpecialTooManyTreasures() {
    try {
      ILevelBuilder builder = new MedievalLevelBuilder(1, 10, 1, 1)
              .addRoom("This is the first room")
              .addSpecial(0, "I am a special item!", 10)
              .addSpecial(0, "I am a special item!", 10); // this throws
      fail("Invalid addSpecial() call should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidAddSpecialNegativeValue() {
    try {
      ILevelBuilder builder = new MedievalLevelBuilder(1, 10, 1, 1)
              .addRoom("This is the first room")
              .addSpecial(0, "I am a special item!", -10);
      fail("Invalid addSpecial() call should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testValidBuild() {
    try {
      Level level = new MedievalLevelBuilder(1, 1, 1, 1)
              .addRoom("This is the first room")
              .addGold(0, 1)
              .addGoblins(0, 1)
              .build();
      // do nothing, test passes
    } catch (Exception e) {
      fail("Valid build() should not have failed.");
    }
  }

  @Test
  public void testInvalidBuildNoMonsters() {
    try {
      Level level = new MedievalLevelBuilder(1, 1, 1, 1)
              .addRoom("This is the first room")
              .addGold(0, 1)
              .build();
      fail("Invalid build() should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidBuildNoTreasures() {
    try {
      Level level = new MedievalLevelBuilder(1, 1, 1, 1)
              .addRoom("This is the first room")
              .addGoblins(0, 1)
              .build();
      fail("Invalid build() should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidBuildNoRooms() {
    try {
      Level level = new MedievalLevelBuilder(1, 1, 1, 1)
              .build();
      fail("Invalid build() should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }
}
