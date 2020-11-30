package keyboard_maps;

import model.IModel;
import model.Model;

/**
 * Simple example using keyboard maps.
 */
public class Main {
  
  public static void main(String[] args) {
    // Create the model
    IModel model = new Model();
    // Create the view
    IView view = new JFrameView("Echo a string");
    // Create the controller with the model and view
    new Controller(model, view);
  }
}
