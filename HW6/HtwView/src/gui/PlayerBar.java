package gui;

import java.awt.*;
import java.util.List;

import javax.swing.*;

import htw.game.IHtwPlayer;

public class PlayerBar extends JPanel {
  public PlayerBar() {
    super();

    this.setBackground(Color.GREEN);
    this.setLayout(new GridLayout(0, 2));

    for (int i = 0; i < 2; i++) {
      PlayerView playerView = new PlayerView();
      playerView.setSize(LayoutConfigs.WIDTH / 2, LayoutConfigs.LARGE);
      playerView.setLocation((LayoutConfigs.WIDTH / 2) * i, 0);
      this.add(playerView);
    }
  }

  public void populate(List<IHtwPlayer> players) throws IllegalArgumentException {
    if (players.size() > 2) {
      throw new IllegalArgumentException(
              "Cannot populate PlayerBar. Players list exceeds size of 2");
    }

    for (int i = 0; i < players.size(); i++) {
      PlayerView playerView = new PlayerView();
      int width = LayoutConfigs.WIDTH / players.size();
      playerView.setSize(width, LayoutConfigs.LARGE);
      playerView.setLocation(width * i, 0);
      players.get(i).receive(playerView);
    }
  }
}
