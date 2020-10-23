package generictree;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Implementation of the base case for the tree.
 * 
 * @param <T> the tyep of the element in the tree.
 */
public class LeafNode<T> extends AbstractTreeNode<T> {

  /**
   * Constructor.
   * 
   * @param data the data in this leaf.
   */
  public LeafNode(T data) {
    super(data);
  }

  @Override
  public TreeNode<T> addChild(Predicate<T> identifier, TreeNode<T> child) {
    if (identifier.test(data)) {
      // promote this to a group tree node
      GroupNode<T> newNode = new GroupNode<T>(this.data);
      newNode.addChild(identifier, child);
      return newNode;
    }
    return this;
  }

  @Override
  public List<T> toList() {
    List<T> result = new ArrayList<T>();
    result.add(this.data);
    return result;
  }

  @Override
  public <R> TreeNode<R> map(Function<T, R> transform) {
    return new LeafNode<R>(transform.apply(this.data));
  }

  @Override
  public T reduce(T initialValue, BiFunction<T, T, T> combiner) {
    return combiner.apply(initialValue, this.data);
  }
}
