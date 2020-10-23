package generictree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Representation of an internal node in the tree.
 *
 * @param <T> the type of element in the tree
 */
public class GroupNode<T> extends AbstractTreeNode<T> {

  private List<TreeNode<T>> children;

  /**
   * Constructor for this internal node.
   * 
   * @param data the data stored in this node
   */
  public GroupNode(T data) {
    super(data);
    this.children = new LinkedList<TreeNode<T>>();

  }

  @Override
  public TreeNode<T> addChild(Predicate<T> identifier, TreeNode<T> child) {
    if (identifier.test(this.data)) {
      this.children.add(child);
      return this;
    }
    for (int i = 0; i < this.children.size(); i++) {
      this.children.set(i, this.children.get(i).addChild(identifier, child));
    }
    return this;
  }

  @Override
  public int count(Predicate<T> condition) {
    Stream<TreeNode<T>> stream = this.children.stream();
    return stream.mapToInt(b -> b.count(condition)).sum()
        + super.count(condition);
  }

  @Override
  public List<T> toList() {
    List<T> result = new ArrayList<T>();
    result.add(this.data);
    for (TreeNode<T> child : children) {
      result.addAll(child.toList());
    }
    return result;
  }

  @Override
  public <R> TreeNode<R> map(Function<T, R> transform) {
    GroupNode<R> newNode = new GroupNode<>(transform.apply(this.data));
    for (TreeNode<T> child : children) {
      newNode.children.add(child.map(transform));
    }
    return newNode;
  }

  @Override
  public T reduce(T initialValue, BiFunction<T, T, T> combiner) {
    T result = this.data;
    for (TreeNode<T> child : children) {
      result = child.reduce(result, combiner);
    }
    return combiner.apply(initialValue, result);
  }
}
