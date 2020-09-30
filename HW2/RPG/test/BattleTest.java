import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import rpg.interfaces.IBattle;
import rpg.interfaces.IPlayer;
import rpg.models.Battle;
import rpg.models.Player;

import static org.junit.Assert.assertEquals;
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
            2,
            new ArrayList<>(Arrays.asList(this.playerOne, this.playerTwo)));
    this.battleThreePlayer = new Battle(
            3,
            new ArrayList<>(Arrays.asList(this.playerOne, this.playerTwo, this.playerThree)));
  }

  @Test
  public void testValidConstructorNoPlayers() {
    try {
      IBattle battle = new Battle(2);
      // do nothing, test passes
    } catch (Exception e) {
      fail("Valid constructor should not have failed.");
    }
  }

  @Test
  public void testInvalidConstructorNoPlayersInvalidPlayerCount() {
    try {
      IBattle battle = new Battle(1);
      fail("Invalid constructor should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testValidConstructorWithPlayers() {
    try {
      IBattle battle = new Battle(
              2,
              new ArrayList<>(Arrays.asList(this.playerOne, this.playerTwo)));
      // do nothing, test passes
    } catch (Exception e) {
      fail("Valid constructor should not have failed.");
    }
  }

  @Test
  public void testInvalidConstructorWithPlayersInvalidPlayerCount() {
    try {
      IBattle battle = new Battle(
              1,
              new ArrayList<>(Arrays.asList(this.playerOne, this.playerTwo)));
      fail("Invalid constructor should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testInvalidConstructorWithPlayersTooManyPlayers() {
    try {
      IBattle battle = new Battle(
              2,
              new ArrayList<>(Arrays.asList(this.playerOne, this.playerTwo, this.playerThree)));
      fail("Invalid constructor should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }

  @Test
  public void testBattleTwoPlayer() {
    IPlayer victor = this.battleTwoPlayer.fight();
    assertEquals(this.playerTwo, victor);
  }

  @Test
  public void testBattleThreePlayer() {
    IPlayer victor = this.battleThreePlayer.fight();
    assertEquals(this.playerThree, victor);
  }

  @Test
  public void testGetPlayers() {
    assertEquals(this.playerOne, this.battleTwoPlayer.getPlayers().get(0));
    assertEquals(this.playerTwo, this.battleTwoPlayer.getPlayers().get(1));
  }

  @Test
  public void testValidAddPlayer() {
    try {
      IBattle battle = new Battle(2)
              .addPlayer(this.playerOne)
              .addPlayer(this.playerTwo);
    } catch (Exception e) {
      fail("Valid addPlayer() should not have failed.");
    }
  }

  @Test
  public void testInvalidAddPlayer() {
    try {
      IBattle battle = new Battle(2)
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
      IBattle battle = new Battle(2)
              .addPlayer(this.playerTwo)
              .addPlayer(this.playerTwo);
      fail("Invalid addPlayer() should have failed.");
    } catch (Exception e) {
      // do nothing, test passes
    }
  }
}