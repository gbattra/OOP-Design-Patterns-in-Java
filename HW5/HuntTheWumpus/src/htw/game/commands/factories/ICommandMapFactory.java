package htw.game.commands.factories;

import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

import htw.game.commands.ICommand;
import htw.game.commands.strategies.IActionStrategy;

public interface ICommandMapFactory<T> {
  Map<String, Function<Scanner, ICommand<T>>> create(Appendable out, IActionStrategy strategy);
}
