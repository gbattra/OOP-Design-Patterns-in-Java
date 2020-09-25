package birds.interfaces;

/**
 * Interface for objects that need to provide a human-readable description of their contents.
 */
public interface IDescribable {
  /**
   * Describes the contents of the object in human-readable form.
   *
   * @return String the description of the object
   */
  String describe();
}
