package htw.game.commands;

public interface IHtwCommand<T> {
  void execute(T receiver);
}
