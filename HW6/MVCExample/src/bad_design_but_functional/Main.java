package bad_design_but_functional;

import model.IModel;
import model.Model;

/**
 * This is a complete program featuring a simple GUI. This program merely
 * illustrates how it works. It is designed rather badly. Read the comments in
 * the view to see why.
 */
public class Main {
  public static void main(String[] args) {
    // create the model
    IModel model = new Model();
    // create the view, and give it the model
    new JFrameView("Echo a string", model);
  }
}
