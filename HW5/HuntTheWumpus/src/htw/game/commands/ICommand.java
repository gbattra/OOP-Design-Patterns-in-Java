package htw.game.commands;

public interface ICommand<T> {
  void execute(T receiver);
}
