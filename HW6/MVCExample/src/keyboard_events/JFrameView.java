package keyboard_events;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

/**
 * Implementation of the view.
 */
public class JFrameView extends JFrame implements IView {
  private static final long serialVersionUID = 5883479994622814210L;

  private JLabel display;
  private JButton echoButton;
  private JButton exitButton;
  private JTextField input;

  /**
   * Constructor.
   * 
   * @param caption the caption for the view
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

    /*
     * In order to make this frame respond to keyboard events, it must be within
     * strong focus. Since there could be multiple components on the screen that
     * listen to keyboard events, we must set one as the "currently focussed"
     * one so that all keyboard events are passed to that component. This
     * component is said to have "strong focus".
     * 
     * We do this by first making the component focusable and then requesting
     * focus to it. Requesting focus makes the component have focus AND removes
     * focus from whoever had it before.
     */

    pack();
    setVisible(true);

  }

  @Override
  public void setListeners(ActionListener clicks, KeyListener keys) {
    this.addKeyListener(keys);
    this.echoButton.addActionListener(clicks);
    this.exitButton.addActionListener(clicks);
  }

  @Override
  public void resetFocus() {
    this.setFocusable(true);
    this.requestFocus();
  }

  @Override
  public void toggleColor() {
    if (this.display.getForeground().equals(Color.RED)) {
      this.display.setForeground(Color.BLACK);
    } else {
      this.display.setForeground(Color.RED);
    }
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
