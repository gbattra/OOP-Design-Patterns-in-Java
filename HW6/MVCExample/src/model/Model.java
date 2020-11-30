package model;

/**
 * A simple model.
 */
public class Model implements IModel {
  private String input;

  /** Default constructor. */
  public Model() {
    input = "";
  }

  @Override
  public void setString(String i) {
    input = i;
  }

  @Override
  public String getString() {
    return input;
  }
}