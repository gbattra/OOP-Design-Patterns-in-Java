import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rpg.enums.GearType;
import rpg.interfaces.IBattle;
import rpg.interfaces.IPlayer;
import rpg.models.Battle;
import rpg.models.Player;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Tests for the Battle class.
 */
public class BattleTest {
  private final IPlayer playerOne = new Player(1, 10, 5);
  private final IPlayer playerTwo = new Player(2, 5, 15);
  private final IPlayer playerThree = new Player(3, 17, 11);

  private IBattle battleTwoPlayer;
  private IBattle battleThreePlayer;

  @Before
  public void setup() {
    this.battleTwoPlayer = new Battle(
            2, 10,
            new ArrayList<>(Arrays.asList(this.playerOne, this.playerTwo)),
            new ArrayList<>())
            .addHeadGear(GearType.HELMET, 10, "steel", "helmet")
            .addHeadGear(GearType.HELMET, 5, "weak", "helmet")
            .addHeadGear(GearType.HELMET, 7, "kevlar", "hat")
            .addHandGear(GearType.SWORD, 15, 5, "steel", "sword")
            .addHandGear(GearType.SHIELD, 10, 15, "steel", "shield")
            .addHandGear(GearType.SHIELD, 10, 15, "steel", "shield")
            .addHandGear(GearType.GLOVE, 0, 3, "soft", "glove")
            .addHandGear(GearType.SHIELD, 2, 10, "light", "shield")
            .addFootGear(GearType.SNEAKER, 5, 5, "light-up", "sneaker")
            .addFootGear(GearType.BOOT, 7, 5, "steel-toed", "boot");
    this.battleThreePlayer = new Battle(
            3, 10,
            new ArrayList<>(Arrays.asList(this.playerOne, this.playerTwo, this.playerThree)),
            new ArrayList<>())
            .addHeadGear(GearType.HELMET, 10, "steel", "helmet")
            .addHeadGear(GearType.HELMET, 5, "weak", "helmet")
            .addHeadGear(GearType.HELMET, 7, "kevlar", "hat")
            .addHandGear(GearType.SWORD, 15, 5, "steel", "sword")
            .addHandGear(GearType.SHIELD, 10, 15, "steel", "shield")
            .addHandGear(GearType.SHIELD, 10, 15, "steel", "shield")
            .addHandGear(GearType.GLOVE, 0, 3, "soft", "glove")
            .addHandGear(GearType.SHIELD, 2, 10, "light", "shield")
            .addFootGear(GearType.SNEAKER, 5, 5, "light-up", "sneaker")
            .addFootGear(GearType.BOOT, 7, 5, "steel-toed", "boot");
  }

  @Test
  public void testValidConstructorNoPlayers() {
    try {
      IBattle battle = new Battle(2, 10);
      // do nothing, test passes
    } catch (Exception e) {
      fail("Valid constructor should not have failed.");
    }
  }

  @Test
  public void testInvalidConstructorNoPlayersInvalidPlayerCount() {
    try {
      IBattle battle = new Battle(1, 10);
      fail("Invalid constructor should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testValidConstructorWithPlayers() {
    try {
      IBattle battle = new Battle(
              2, 10,
              new ArrayList<>(Arrays.asList(this.playerOne, this.playerTwo)),
              new ArrayList<>());
      // do nothing, test passes
    } catch (Exception e) {
      fail("Valid constructor should not have failed.");
    }
  }

  @Test
  public void testInvalidConstructorWithPlayersInvalidPlayerCount() {
    try {
      IBattle battle = new Battle(
              1, 10,
              new ArrayList<>(Arrays.asList(this.playerOne, this.playerTwo)),
              new ArrayList<>());
      fail("Invalid constructor should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidConstructorWithPlayersTooManyPlayers() {
    try {
      IBattle battle = new Battle(
              2, 10,
              new ArrayList<>(Arrays.asList(this.playerOne, this.playerTwo, this.playerThree)),
              new ArrayList<>());
      fail("Invalid constructor should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testBattleTwoPlayer() {
    IPlayer victor = this.battleTwoPlayer
            .fight();
    assertEquals(this.battleTwoPlayer.getPlayers().get(1), victor);
  }

  @Test
  public void testBattleThreePlayer() {
    IPlayer victor = this.battleThreePlayer
            .fight();
    assertEquals(this.playerThree, victor);
  }

  @Test
  public void testInvalidFightTooFewPlayers() {
    try {
      IPlayer player = new Battle(2, 2)
              .addHeadGear(GearType.HELMET, 10, "steel", "helmet")
              .addHeadGear(GearType.HELMET, 5, "weak", "helmet")
              .fight();
      fail("Invalid fight() should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidFightNoGear() {
    try {
      IPlayer player = new Battle(2, 2)
              .addPlayer(this.playerOne)
              .addPlayer(this.playerTwo)
              .fight();
      fail("Invalid fight() should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testGetPlayers() {
    assertEquals(this.playerOne, this.battleTwoPlayer.getPlayers().get(0));
    assertEquals(this.playerTwo, this.battleTwoPlayer.getPlayers().get(1));
  }

  @Test
  public void testValidAddPlayer() {
    try {
      IBattle battle = new Battle(2, 10)
              .addPlayer(this.playerOne)
              .addPlayer(this.playerTwo);
    } catch (Exception e) {
      fail("Valid addPlayer() should not have failed.");
    }
  }

  @Test
  public void testInvalidAddPlayer() {
    try {
      IBattle battle = new Battle(2, 10)
              .addPlayer(this.playerOne)
              .addPlayer(this.playerTwo)
              .addPlayer(this.playerThree);
      fail("Invalid addPlayer() should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidAddPlayerDuplicate() {
    try {
      IBattle battle = new Battle(2, 10)
              .addPlayer(this.playerTwo)
              .addPlayer(this.playerTwo);
      fail("Invalid addPlayer() should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testValidAddGear() {
    try {
      IBattle battle = new Battle(2, 10)
              .addHeadGear(GearType.HAT, 10, "small", "hat")
              .addHeadGear(GearType.HAT, 10, "small", "hat");
    } catch (Exception e) {
      fail("Valid player build() should not have failed.");
    }
  }

  @Test
  public void testInvalidAddGearNegDefense() {
    try {
      IBattle battle = new Battle(2, 10)
              .addHandGear(GearType.SWORD, 0, -10, "small", "hat");
      fail("Invalid addHeadGear() should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidAddGearNoAdj() {
    try {
      IBattle battle = new Battle(2, 10)
              .addHandGear(GearType.SWORD, 0, 10, "", "hat");
      fail("Invalid addHeadGear() should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidAddGearAlreadyFull() {
    try {
      IBattle battle = new Battle(2, 1)
              .addHandGear(GearType.SWORD, 0, 10, "", "hat")
              .addHandGear(GearType.SWORD, 0, 10, "", "hat");
      fail("Invalid addHeadGear() should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidAddGearNoNoun() {
    try {
      IBattle battle = new Battle(2, 10)
              .addHandGear(GearType.SWORD, 0, 10, "small", "");
      fail("Invalid addHeadGear() should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testValidDressPlayers() {
    try {
      IBattle battle = new Battle(2, 10)
              .addPlayer(this.playerOne)
              .addPlayer(this.playerTwo)
              .addHeadGear(GearType.HELMET, 10, "steel", "helmet")
              .addHeadGear(GearType.HELMET, 5, "weak", "helmet")
              .addHeadGear(GearType.HELMET, 7, "kevlar", "hat")
              .addHandGear(GearType.SWORD, 15, 5, "steel", "sword")
              .addHandGear(GearType.SHIELD, 10, 15, "steel", "shield")
              .addHandGear(GearType.SHIELD, 10, 15, "steel", "shield")
              .addHandGear(GearType.GLOVE, 0, 3, "soft", "glove")
              .addHandGear(GearType.SHIELD, 2, 10, "light", "shield")
              .addFootGear(GearType.SNEAKER, 5, 5, "light-up", "sneaker")
              .addFootGear(GearType.BOOT, 7, 5, "steel-toed", "boot")
              .dressPlayers();
    } catch (Exception e) {
      fail("Valid dressPlayers() call should not have failed.");
    }
  }

  @Test
  public void testInvalidDressPlayersNoGear() {
    try {
      IPlayer player = new Battle(2, 2)
              .addPlayer(this.playerOne)
              .addPlayer(this.playerTwo)
              .fight();
      fail("Invalid fight() should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidDressPlayersNoPlayers() {
    try {
      IPlayer player = new Battle(2, 2)
              .addHeadGear(GearType.HELMET, 10, "steel", "helmet")
              .addHeadGear(GearType.HELMET, 5, "weak", "helmet")
              .fight();
      fail("Invalid fight() should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testDressPlayersWithUnlootableGear() {
    try {
      IBattle battle = new Battle(2, 10)
              .addPlayer(this.playerOne)
              .addPlayer(this.playerTwo)
              .addHeadGear(GearType.HELMET, 10, "steel", "helmet")
              .addHeadGear(GearType.HELMET, 5, "weak", "helmet")
              .addHeadGear(GearType.HELMET, 5, "weak", "helmet")
              .addHeadGear(GearType.HELMET, 5, "weak", "helmet")
              .addHeadGear(GearType.HELMET, 7, "kevlar", "hat")
              .addHandGear(GearType.SWORD, 15, 5, "steel", "sword")
              .addHandGear(GearType.SWORD, 15, 5, "steel", "sword")
              .addHandGear(GearType.SWORD, 15, 5, "steel", "sword")
              .addHandGear(GearType.SWORD, 15, 5, "steel", "sword")
              .addHandGear(GearType.SWORD, 15, 5, "steel", "sword")
              .dressPlayers();
    } catch (Exception e) {
      fail("Valid dressPlayers() call should not have failed.");
    }
  }

  @Test
  public void testDressPlayersUpdatesTheirGear() {
    try {
      IBattle battle = new Battle(2, 10)
              .addPlayer(this.playerOne)
              .addPlayer(this.playerTwo)
              .addHeadGear(GearType.HELMET, 10, "steel", "helmet")
              .addHeadGear(GearType.HELMET, 5, "weak", "helmet")
              .addHeadGear(GearType.HELMET, 7, "kevlar", "hat")
              .addHandGear(GearType.SWORD, 15, 5, "steel", "sword")
              .addHandGear(GearType.SHIELD, 10, 15, "steel", "shield")
              .addHandGear(GearType.SHIELD, 10, 15, "steel", "shield")
              .addHandGear(GearType.GLOVE, 0, 3, "soft", "glove")
              .addHandGear(GearType.SHIELD, 2, 10, "light", "shield")
              .addFootGear(GearType.SNEAKER, 5, 5, "light-up", "sneaker")
              .addFootGear(GearType.BOOT, 7, 5, "steel-toed", "boot")
              .dressPlayers();
      List<IPlayer> players = battle.getPlayers();
      assertTrue(players.stream().noneMatch(player -> player.getGear().size() == 0));

      assertEquals(52, players.get(0).getDefense());
      assertEquals(37, players.get(0).getAttack());
      assertEquals(48, players.get(1).getDefense());
      assertEquals(27, players.get(1).getAttack());
    } catch (Exception e) {
      fail("Valid dressPlayers() call should not have failed.");
    }
  }

  @Test
  public void testFightPlayersWithGear() {
    try {
      IBattle battle = new Battle(2, 10)
              .addPlayer(this.playerOne)
              .addPlayer(this.playerTwo)
              .addHeadGear(GearType.HELMET, 10, "steel", "helmet")
              .addHeadGear(GearType.HELMET, 5, "weak", "helmet")
              .addHeadGear(GearType.HELMET, 7, "kevlar", "hat")
              .addHandGear(GearType.SWORD, 15, 5, "steel", "sword")
              .addHandGear(GearType.SHIELD, 10, 15, "steel", "shield")
              .addHandGear(GearType.SHIELD, 10, 15, "steel", "shield")
              .addHandGear(GearType.GLOVE, 0, 3, "soft", "glove")
              .addHandGear(GearType.SHIELD, 2, 10, "light", "shield")
              .addFootGear(GearType.SNEAKER, 5, 5, "light-up", "sneaker")
              .addFootGear(GearType.BOOT, 7, 5, "steel-toed", "boot")
              .dressPlayers();
      IPlayer player = battle.fight();
      assertEquals(battle.getPlayers().get(0), player);
    } catch (Exception e) {
      fail("Valid dressPlayers() call should not have failed.");
    }
  }
}