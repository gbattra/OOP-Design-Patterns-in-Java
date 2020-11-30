package model;

/**
 * Interface for a simple model.
 */
public interface IModel {
  /**
   * Mutator of the string in this model.
   * 
   * @param i the value for the string
   */
  void setString(String i);

  /**
   * Accessor for the string in this model.
   * 
   * @return the string in this model
   */
  String getString();
}
