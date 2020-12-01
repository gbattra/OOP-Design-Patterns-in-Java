package tests.gui;

import org.junit.Test;

import gui.HtwMultiPlayer;
import gui.PlayerView;
import htw.game.IHtwPlayer;

import static org.junit.Assert.assertEquals;

public class PlayerViewTest {
  @Test
  public void testPlayerView() {
    PlayerView playerView = new PlayerView();
    IHtwPlayer player = new HtwMultiPlayer("Joe", 1, 6);
    player.kill();
    playerView.visitPlayer(player);
    assertEquals("PLAYER 1", playerView.playerLabel.getText());
    assertEquals("Arrows: 6", playerView.arrowCountLabel.getText());
    assertEquals("DEAD", playerView.isAliveLabel.getText());
  }
}
