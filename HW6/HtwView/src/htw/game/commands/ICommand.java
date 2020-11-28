package htw.game.commands;

import java.io.IOException;

/**
 * Generic interface for a command object.
 *
 * @param <T> the type of receiver for the command
 */
public interface ICommand<T> {
  /**
   * Executes the command.
   *
   * @param receiver the receiver to use in execution
   * @return the same type as the receiver
   * @throws IllegalArgumentException if receiver is null
   * @throws IOException if fails writing to out
   */
  T execute(T receiver) throws IllegalArgumentException, IOException;
}
