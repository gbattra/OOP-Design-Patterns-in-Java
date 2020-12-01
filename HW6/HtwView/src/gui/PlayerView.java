package gui;

import java.awt.*;

import javax.swing.*;

public class PlayerView extends JPanel implements IHtwPlayerVisitor<Void> {
  public final JLabel playerLabel;
  public final JLabel arrowCountLabel;
  public final JLabel isAliveLabel;

  private static final Font headerFont = new Font("Courier", Font.BOLD, 14);

  public PlayerView() {
    super();
    this.setLayout(new GridLayout(3, 1));

    this.setBackground(Color.ORANGE);

    this.playerLabel = new JLabel("PLAYER X");
    this.playerLabel.setFont(headerFont);
    this.add(this.playerLabel);

    this.arrowCountLabel = new JLabel(String.format("%s", 10));
    this.add(arrowCountLabel);

    this.isAliveLabel = new JLabel("ALIVE");
    this.add(this.isAliveLabel);
  }

  @Override
  public Void visitPlayer(int playerId, int arrowCount, boolean isAlive) {
    this.playerLabel.setText(String.format("PLAYER %s", playerId));
    this.arrowCountLabel.setText(String.format("%s", arrowCount));
    this.isAliveLabel.setText(isAlive ? "ALIVE" : "DEAD");
    return null;
  }
}
