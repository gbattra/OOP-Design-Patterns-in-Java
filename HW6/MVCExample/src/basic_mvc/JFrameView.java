package basic_mvc;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Implementation of the view.
 */
public class JFrameView extends JFrame implements IView {
  private static final long serialVersionUID = -7083924619099998893L;

  private JLabel display;
  private JButton echoButton;
  private JButton exitButton;
  private JTextField input;

  /**
   * Constructor.
   * 
   * @param caption the value of the caption
   */
  public JFrameView(String caption) {
    super(caption);

    setSize(500, 300);
    setLocation(200, 200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.setLayout(new FlowLayout());

    display = new JLabel("To be displayed");
    this.add(display);

    // the text field
    input = new JTextField(10);
    this.add(input);

    // echo button
    echoButton = new JButton("Echo");
    echoButton.setActionCommand("Echo Button");
    this.add(echoButton);

    // exit button
    exitButton = new JButton("Exit");
    exitButton.setActionCommand("Exit Button");
    this.add(exitButton);

    pack();
  }

  @Override
  public void display() {
    setVisible(true);
  }

  @Override
  public void setListener(ActionListener listener) {
    echoButton.addActionListener(listener);
    exitButton.addActionListener(listener);
  }

  @Override
  public void setEchoOutput(String s) {
    display.setText(s);
  }

  @Override
  public String getInputString() {
    return input.getText();
  }

  @Override
  public void clearInputString() {
    input.setText("");
  }
}
