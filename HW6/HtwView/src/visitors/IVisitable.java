package visitors;

/**
 * Visitable interface which receives a type T object.
 *
 * @param <T> the type of the visitor which much extend IVisitor
 */
public interface IVisitable<T> {
  /**
   * The entrypoint to the visitable object.
   *
   * @param visitor the visitor to receive
   */
  void receive(T visitor);
}
