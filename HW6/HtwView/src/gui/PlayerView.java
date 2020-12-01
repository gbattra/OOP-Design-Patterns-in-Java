package gui;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import htw.game.IHtwPlayer;

public class PlayerView extends JPanel implements IHtwPlayerVisitor<Void> {
  public final JLabel playerLabel;
  public final JLabel arrowCountLabel;
  public final JLabel isAliveLabel;

  private static final Font headerFont = new Font("Courier", Font.BOLD, 14);

  public PlayerView() {
    super();
    this.setLayout(new GridLayout(3, 1));
    this.setBorder(
            BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.BLACK),
                    new EmptyBorder(10, 10, 10, 10)));

    this.playerLabel = new JLabel();
    this.playerLabel.setFont(headerFont);
    this.add(this.playerLabel);

    this.arrowCountLabel = new JLabel();
    this.add(arrowCountLabel);

    this.isAliveLabel = new JLabel();
    this.add(this.isAliveLabel);
  }

  @Override
  public Void visitPlayer(IHtwPlayer player) {
    this.playerLabel.setText(String.format("PLAYER %s", player.number()));
    this.arrowCountLabel.setText(String.format("Arrows: %s", player.arrowCount()));
    this.isAliveLabel.setText(player.isAlive() ? "ALIVE" : "DEAD");
    return null;
  }
}
