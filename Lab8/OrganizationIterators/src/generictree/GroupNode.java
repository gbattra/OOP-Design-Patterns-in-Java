package generictree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Internal node for the tree.
 *
 * @param <T> the type of element in the tree
 */
public class GroupNode<T> extends AbstractTreeNode<T> {

  protected List<TreeNode<T>> children;

  public GroupNode(T data) {
    super(data);
    this.children = new LinkedList<TreeNode<T>>();

  }

  @Override
  public List<TreeNode<T>> children() {
    List<TreeNode<T>> result = new ArrayList<TreeNode<T>>();
    for (TreeNode<T> c : children) {
      result.add(c);
    }
    return result;
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
    TreeNode<R> newNode = createNode(transform.apply(this.data));
    if (!(newNode instanceof GroupNode)) {
      throw new IllegalStateException("New node formed is not group node");
    }
    GroupNode<R> groupNode = (GroupNode<R>) newNode;
    for (TreeNode<T> child : children) {
      groupNode.children.add(child.map(transform));
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

  @Override
  protected <R> TreeNode<R> createNode(R data) {
    return new GroupNode<>(data);
  }
}
