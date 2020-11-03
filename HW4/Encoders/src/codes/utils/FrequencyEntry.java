package codes.utils;

/**
 * An entry in a frequency table.
 *
 * @param <T> the type of the value for this entry
 */
public class FrequencyEntry<T> implements Frequency<T> {
  private final Integer frequency;
  private final T value;

  /**
   * Standard constructor for the frequency entry. Takes an int of the frequency of a given value
   * in a presumed sequence, and that value itself.
   *
   * @param frequency how many times the value is present in a sequence
   * @param value that value
   */
  public FrequencyEntry(Integer frequency, T value) {
    this.frequency = frequency;
    this.value = value;
  }

  @Override
  public Integer getFrequency() {
    return this.frequency;
  }

  @Override
  public T getValue() {
    return this.value;
  }
}
