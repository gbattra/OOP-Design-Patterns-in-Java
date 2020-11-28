package visitors;

/**
 * Interface for a visitor of an IVisitable object.
 */
public interface IVisitor {
  /**
   * Initiates the "visit" to the visitable object.
   *
   * @param receiver the objected being visited
   * @param <R> the type of visitor the visitable object expects
   * @param <T> the type of object the visitor expects to visit
   */
  <R extends IVisitor, T extends IVisitable<R>> void visit(T receiver);
}
