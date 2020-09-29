package interfaces;

public interface IGear<T> {
  IGear<T> combine(IGear<T> gear);
}
