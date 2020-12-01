package gui;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import htw.game.IHtwPlayer;

public class PlayerBar extends JPanel {
  public final List<PlayerView> playerViews;

  public PlayerBar() {
    super();

    this.setBackground(Color.GREEN);
    this.setLayout(new GridLayout(0, 2));

    this.playerViews = new ArrayList<>();
    for (int i = 0; i < 2; i++) {
      PlayerView playerView = new PlayerView();
      playerView.setSize(LayoutConfigs.WIDTH / 2, LayoutConfigs.LARGE);
      playerView.setLocation((LayoutConfigs.WIDTH / 2) * i, 0);
      this.playerViews.add(playerView);
      this.add(playerView);
    }
  }

  public void populate(List<IHtwPlayer> players) throws IllegalArgumentException {
    if (players.size() > 2) {
      throw new IllegalArgumentException(
              "Cannot populate PlayerBar. Players list exceeds size of 2");
    }

    for (int i = 0; i < players.size(); i++) {
      players.get(i).receive(this.playerViews.get(i));
    }
  }
}
