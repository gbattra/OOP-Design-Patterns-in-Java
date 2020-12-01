package gui;

import java.awt.*;

import javax.swing.*;

public class PlayerView extends JPanel implements IHtwPlayerVisitor<Void> {
  public PlayerView() {
    super();
    this.setLayout(new BorderLayout());

    this.setBackground(Color.ORANGE);
  }

  @Override
  public Void visitPlayer(int playerId, int arrowCount, boolean isAlive) {
    return null;
  }
}
