package gui;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import htw.game.IHtwPlayer;

public class PlayerBar extends JPanel {
  public final List<PlayerView> playerViews;

  public PlayerBar(List<IHtwPlayer> players) {
    super();

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
