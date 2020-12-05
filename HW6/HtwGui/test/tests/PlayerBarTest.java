package tests;

import org.junit.Test;

import java.util.Arrays;

import gui.PlayerBar;
import gui.PlayerView;
import htw.HtwMultiPlayer;
import htw.IHtwPlayer;

import static org.junit.Assert.assertEquals;

/**
 * Tests for PlayerBar.
 */
public class PlayerBarTest {
  @Test
  public void testPopulate() {
    IHtwPlayer playerOne = new HtwMultiPlayer("Joe", 1, 6);
    playerOne.kill();
    IHtwPlayer playerTwo = new HtwMultiPlayer("Sally", 2, 3);
    PlayerBar playerBar = new PlayerBar(Arrays.asList(playerOne, playerTwo));

    PlayerView playerOneView = playerBar.playerViews.get(0);
    assertEquals("PLAYER 1", playerOneView.playerLabel.getText());
    assertEquals("Arrows: 6", playerOneView.arrowCountLabel.getText());
    assertEquals("DEAD", playerOneView.isAliveLabel.getText());

    PlayerView playerTwoView = playerBar.playerViews.get(1);
    assertEquals("PLAYER 2", playerTwoView.playerLabel.getText());
    assertEquals("Arrows: 3", playerTwoView.arrowCountLabel.getText());
    assertEquals("ALIVE", playerTwoView.isAliveLabel.getText());
  }
}
