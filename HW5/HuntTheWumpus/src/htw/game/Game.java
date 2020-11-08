package htw.game;

import java.util.Iterator;

public interface Game extends Iterator<Round> {
  void set(boolean done);
  boolean getDone();
}
