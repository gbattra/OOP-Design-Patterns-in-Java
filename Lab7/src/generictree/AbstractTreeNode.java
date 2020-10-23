package generictree;

import java.util.function.Predicate;

/**
 * Implementation of an abstract node to share code.
 * 
 * @param <T> the type of element in the node
 */
public abstract class AbstractTreeNode<T> implements TreeNode<T> {
  protected T data;

  /**
   * Constructor.
   * 
   * @param data the data for this node
   */
  public AbstractTreeNode(T data) {
    this.data = data;
  }

  @Override
  public int count(Predicate<T> condition) {
    if (condition.test(this.data)) {
      return 1;
    }
    return 0;
  }
}
