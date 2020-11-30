package bad_design_but_functional;

import javax.swing.*;

import model.IModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This is the view implementation. It directly converses with the model. This
 * design suffices only for very simple programs where there isn't much logic to
 * handle for every user action.
 *
 * In general this design is bad because the logic for handling user action is
 * embedded into the view. For example, you must write all the code that runs
 * upon pressing a button in this class. Most of that code will have nothing to
 * do with the GUI per se, but sequence of operations. There is no separation
 * between how the UI looks and how the program works. This means that if the UI
 * must be changed without changing the functionality of the program, logic must
 * also be replicated as the old UI contains the operations as well!
 */
public class JFrameView extends JFrame implements IView {
  private static final long serialVersionUID = 2048085200779607014L;
  
  private JLabel display;
  private JButton echoButton;
  private JButton exitButton;
  private JTextField input;

  private IModel model;

  /**
   * Constructor.
   * 
   * @param caption the caption for the frame
   * @param model   the model to use
   */
  public JFrameView(String caption, IModel model) {
    super(caption);

    this.model = model;

    setSize(100, 100);

    setLocation(200, 200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.setLayout(new FlowLayout());

    display = new JLabel("To be displayed");

    this.add(display);

    // the text field
    input = new JTextField(10);
    this.add(input);

    // echo button
    echoButton = new JButton("Echo what was typed");
    echoButton.addActionListener(new EchoButtonListener());
    this.add(echoButton);

    // exit button
    exitButton = new JButton("Exit");
    exitButton.addActionListener(l -> System.exit(0));
    this.add(exitButton);

    pack();
    setVisible(true);

  }

  /**
   * Implementation for the echo button.
   */
  private class EchoButtonListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      // 1. Read text from text field
      String text = JFrameView.this.input.getText();
      // 2. Give text to model
      JFrameView.this.model.setString(text);
      // 3. Read text from model
      String displayText = JFrameView.this.model.getString();
      // 4. Set text to label
      JFrameView.this.display.setText(displayText);
      // 5. Clear text field
      JFrameView.this.input.setText("");
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
