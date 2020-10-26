package generictree;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Implementation of the leaf nodes in the tree.
 *
 * @param <T> the type of element in the tree
 */
public class LeafNode<T> extends AbstractTreeNode<T> {

  public LeafNode(T data) {
    super(data);
  }

  @Override
  public List<TreeNode<T>> children() {
    return new ArrayList<>();
  }

  @Override
  public TreeNode<T> addChild(Predicate<T> identifier, TreeNode<T> child) {
    if (identifier.test(data)) {
      // promote this to a group tree node
      TreeNode<T> newNode = createNode(this.data);
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
    return createNode(transform.apply(this.data));
  }

  @Override
  public T reduce(T initialValue, BiFunction<T, T, T> combiner) {
    return combiner.apply(initialValue, this.data);
  }

  @Override
  protected <R> TreeNode<R> createNode(R data) {
    return new GroupNode<R>(data);
  }
}
