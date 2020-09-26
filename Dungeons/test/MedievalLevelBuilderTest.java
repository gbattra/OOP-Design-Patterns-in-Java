import org.junit.Test;

import dungeon.MedievalLevelBuilder;

import static org.junit.Assert.fail;

/**
 * Test class for MedievalLevelBuilder object.
 */
public class MedievalLevelBuilderTest {
  @Test
  public void testValidConstructor() {
    try {
      MedievalLevelBuilder builder = new MedievalLevelBuilder(1, 10, 5, 10);
      // do nothing test passes
    } catch (Exception e) {
      fail("Valid instantiation of builder should not have failed.");
    }
  }

  @Test
  public void testInvalidConstructorNegativeLevelNumber() {
    try {
      MedievalLevelBuilder builder = new MedievalLevelBuilder(-1, 10, 5, 10);
      fail("Invalid instantiation of builder should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidConstructorNegativeRoomCount() {
    try {
      MedievalLevelBuilder builder = new MedievalLevelBuilder(1, -10, 5, 10);
      fail("Invalid instantiation of builder should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidConstructorNegativeMonsterCount() {
    try {
      MedievalLevelBuilder builder = new MedievalLevelBuilder(1, 10, -5, 10);
      fail("Invalid instantiation of builder should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidConstructorNegativeTreasureCount() {
    try {
      MedievalLevelBuilder builder = new MedievalLevelBuilder(1, 10, 5, -10);
      fail("Invalid instantiation of builder should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testValidAddRoom() {
    try {
      MedievalLevelBuilder builder = new MedievalLevelBuilder(1, 1, 5, 10);
      builder.addRoom("This is the first room.");
    } catch (Exception e) {
      fail("Valid addRoom() call should not have failed.");
    }
  }

  @Test
  public void testInvalidAddRoomTooMany() {
    try {
      MedievalLevelBuilder builder = new MedievalLevelBuilder(1, 1, 5, 10);
      builder.addRoom("This is the first room.");
      builder.addRoom("This is the second room.");  // this should throw
      fail(" Invalid addRoom() call should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testValidAddGoblin() {
    try {
      MedievalLevelBuilder builder = new MedievalLevelBuilder(1, 10, 1, 10);
      builder.addRoom("This is the first room.");
      builder.addGoblin(0, 1);
      // do nothing, test passes
    } catch (Exception e) {
      fail("Valid addGoblin() call should not have failed.");
    }
  }

  @Test
  public void testInvalidAddGoblinRoomNotSet() {
    try {
      MedievalLevelBuilder builder = new MedievalLevelBuilder(1, 10, 1, 10);
      builder.addGoblin(0, 1);
      fail("Invalid addGoblin() call should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidAddGoblinTooManyMonsters() {
    try {
      MedievalLevelBuilder builder = new MedievalLevelBuilder(1, 10, 1, 10);
      builder.addRoom("This is the first room.");
      builder.addGoblin(0, 1);
      builder.addGoblin(0, 1);
      fail("Invalid addGoblin() call should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testValidAddOrc() {
    try {
      MedievalLevelBuilder builder = new MedievalLevelBuilder(1, 10, 1, 10);
      builder.addRoom("This is the first room.");
      builder.addOrc(0, 1);
      // do nothing, test passes
    } catch (Exception e) {
      fail("Valid addOrc() call should not have failed.");
    }
  }

  @Test
  public void testValidAddOrcRoomNotSet() {
    try {
      MedievalLevelBuilder builder = new MedievalLevelBuilder(1, 10, 1, 10);
      builder.addOrc(0, 1);
      fail("Invalid addOrc() call should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testValidAddOrcTooManyMonsters() {
    try {
      MedievalLevelBuilder builder = new MedievalLevelBuilder(1, 10, 1, 10);
      builder.addRoom("This is the first room.");
      builder.addOrc(0, 1);
      builder.addOrc(0, 1);  // this should throw
      fail("Invalid addOrc() call should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testValidAddOgre() {
    try {
      MedievalLevelBuilder builder = new MedievalLevelBuilder(1, 10, 1, 10);
      builder.addRoom("This is the first room.");
      builder.addOgre(0, 1);
      // do nothing, test passes
    } catch (Exception e) {
      fail("Valid addOgre() call should not have failed.");
    }
  }

  @Test
  public void testValidAddOgreRoomNotSet() {
    try {
      MedievalLevelBuilder builder = new MedievalLevelBuilder(1, 10, 1, 10);
      builder.addOgre(0, 1);
      fail("Invalid addOgre() call should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testValidAddOgreTooManyMonsters() {
    try {
      MedievalLevelBuilder builder = new MedievalLevelBuilder(1, 10, 1, 10);
      builder.addRoom("This is the first room.");
      builder.addOgre(0, 1);
      builder.addOgre(0, 1);  // this should throw
      fail("Invalid addOgre() call should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }
}
