package interfaces;

import java.util.List;
import java.util.Optional;

public interface ICombinable<T> {
  T combine(T other) throws IllegalStateException;
  Optional<List<T>> combinedWith();
}
