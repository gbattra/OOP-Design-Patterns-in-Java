package gui;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import htw.game.IHtwPlayer;

/**
 * View wrapper around each player view. Displays player status and arrow count.
 */
public class PlayerBar extends JPanel {
  public final List<PlayerView> playerViews;

  /**
   * Constructor for the player bar.
   *
   * @param players the players to display
   * @throws IllegalArgumentException if players is null
   */
  public PlayerBar(List<IHtwPlayer> players) {
    super();
    if (players == null) {
      throw new IllegalArgumentException("Cannot instantiate player bar. Players object is null.");
    }

    this.setBackground(Color.GREEN);
    this.setLayout(new GridLayout(0, 2));

    this.playerViews = new ArrayList<>();
    for (int i = 0; i < players.size(); i++) {
      PlayerView playerView = new PlayerView();
      playerView.setSize(LayoutConfigs.WIDTH / 2, LayoutConfigs.LARGE);
      playerView.setLocation((LayoutConfigs.WIDTH / 2) * i, 0);
      players.get(i).receive(playerView);
      this.playerViews.add(playerView);
      this.add(playerView);
    }
  }
}
