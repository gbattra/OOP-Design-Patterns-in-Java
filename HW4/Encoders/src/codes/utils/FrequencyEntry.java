package codes.utils;

public class FrequencyEntry<T> implements Frequency<T> {
  private final Integer frequency;
  private final T value;

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
