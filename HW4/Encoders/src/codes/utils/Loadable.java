package codes.utils;

import java.io.IOException;

public interface Loadable<T> {
  T load(String filepath) throws IOException;
}
