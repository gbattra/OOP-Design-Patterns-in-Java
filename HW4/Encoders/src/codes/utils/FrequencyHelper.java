package codes.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.function.Function;

/**
 * Helper class for converting sequences to frequency tables.
 */
public class FrequencyHelper {
  /**
   * Converts the sequence to a stack of frequencies, each of type R.
   *
   * @param sequence the sequence to process
   * @param converter the function to apply to each element in the sequence
   * @param <T> the type of the sequence
   * @param <R> the type of element held in each frequency
   * @return the stack of frequency entries
   */
  public static <T, R> Stack<Frequency<R>> toStack(T[] sequence, Function<T, R> converter) {
    Map<T, Integer> table = new HashMap<>();
    for (T c : sequence) {
      table.merge(c, 1, Integer::sum);
    }

    Stack<Frequency<R>> items = new Stack<>();
    for (Map.Entry<T, Integer> entry : table.entrySet()) {
      Frequency<R> item = new FrequencyEntry<>(
              entry.getValue(),
              converter.apply(entry.getKey()));
      items.add(item);
    }

    return items;
  }
}
