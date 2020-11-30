package keyboard_maps_with_lambdas;

import model.IModel;
import model.Model;

/**
 * This example shows how to specify the (Key,Runnable) keyboard map using
 * shorter syntax using the ability of java 8 to support lambda expressions.
 */
public class Main {
  public static void main(String[] args) {
    // Create the model
    IModel model = new Model();
    // Create the controller with the model
    Controller controller = new Controller(model);
    // Create the view
    IView view = new JFrameView("Echo a string", controller);
    // Set the view in the controller
    controller.setView(view);
  }
}
