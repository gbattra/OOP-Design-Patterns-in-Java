package tests.gui;

import org.junit.Test;

import gui.PlayerView;

import static org.junit.Assert.assertEquals;

public class PlayerViewTest {
  @Test
  public void testPlayerView() {
    PlayerView playerView = new PlayerView();
    playerView.visitPlayer(1, 6, false);
    assertEquals("PLAYER 1", playerView.playerLabel.getText());
    assertEquals("6", playerView.arrowCountLabel.getText());
    assertEquals("DEAD", playerView.isAliveLabel.getText());
  }
}
