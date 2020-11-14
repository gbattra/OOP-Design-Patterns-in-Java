package htw.game.commands;

import java.io.IOException;

public interface ICommand<T> {
  T execute(T receiver) throws IllegalArgumentException, IOException;
}
