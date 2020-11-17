package htw.game.commands.factories;

import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

import htw.game.commands.ICommand;
import htw.game.commands.strategies.IActionStrategy;

/**
 * Interface for a factory of controller command maps.
 *
 * @param <T> the type of receiver used by the commands in the map
 */
public interface ICommandMapFactory<T> {
  Map<String, Function<Scanner, ICommand<T>>> create(Appendable out, IActionStrategy strategy);
}
