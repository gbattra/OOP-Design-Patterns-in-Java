package codes.application.commands;

/**
 * Interface for a command object. Follows the command design pattern.
 *
 * @param <T> the type of the receiver of the command
 */
public interface Command<T> {
  /**s
   * Call to execute the command.
   *
   * @param receiver the receiver which will be called by the command
   */
  void execute(T receiver) throws Exception;
}
