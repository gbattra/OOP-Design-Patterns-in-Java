package keyboard_maps_with_lambdas;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Implementation of the view.
 */
public class JFrameView extends JFrame implements IView {
  private static final long serialVersionUID = -4496164073354832012L;

  private JLabel display;
  private JButton echoButton;
  private JButton exitButton;
  private JTextField input;

  /**
   * Constructor.
   * 
   * @param caption    the initial text to display
   * @param controller the controller to use
   */
  public JFrameView(String caption, Controller controller) {
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
    setVisible(true);

  }

  @Override
  public void addActionListener(ActionListener actionListener) {
    echoButton.addActionListener(actionListener);
    exitButton.addActionListener(actionListener);
  }

  /*
   * In order to make this frame respond to keyboard events, it must be within
   * strong focus. Since there could be multiple components on the screen that
   * listen to keyboard events, we must set one as the "currently focused" one
   * so that all keyboard events are passed to that component. This component is
   * said to have "strong focus".
   * 
   * We do this by first making the component focusable and then requesting
   * focus to it. Requesting focus makes the component have focus AND removes
   * focus from whoever had it before.
   */
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
