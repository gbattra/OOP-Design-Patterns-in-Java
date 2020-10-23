package generictree;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Interface the represents a node in the tree.
 * 
 * @param <T> the type of element in the tree
 */
public interface TreeNode<T> {
  /**
   * Add the given node as a child to a node in this tree identified by the
   * predicate. If no node is identified by the predicate the tree remains
   * unchanged.
   * 
   * @param identifier a predicate that is used to identify the node.
   * @param child      the node that must be added as a child
   * @return the resulting hierarchy starting at this node
   */
  TreeNode<T> addChild(Predicate<T> identifier, TreeNode<T> child);

  /**
   * Count the number of nodes in the tree whose data fulfill the given
   * predicate
   *
   * THIS IS A REDUNDANT METHOD. THIS METHOD CAN BE IMPLEMENTED AS A MAP
   * FOLLOWED BY A REDUCE. SEE {@link OrganizationImpl} for an example.
   *
   * @param condition the predicate that must be true in order to be counted
   * @return the number of employees in this hierarchy that fulfill the given
   *         predicate
   */
  int count(Predicate<T> condition);

  /**
   * A map higher-order function on this tree. Produces a tree with identical
   * structure but whose data is of the specified return type
   * 
   * @param transform the function that maps from the data in this tree to the
   *                  data in the resulting tree
   * @param <R>       the type of the resulting data
   * @return the root of the corresponding mapped tree
   */
  <R> TreeNode<R> map(Function<T, R> transform);

  /**
   * A reduce higher-order function on this tree. Produces a single value of
   * type T
   * 
   * @param initialValue the initial value of the accumulator of the reduce
   * @param combiner     a function that takes two values of type T and combines
   *                     them into a single value of type T
   * @return the result of the reduce, as an object of type T
   */

  T reduce(T initialValue, BiFunction<T, T, T> combiner);

  /**
   * Convert the tree into a list.
   * 
   * @return the resulting list
   */
  List<T> toList();
}
