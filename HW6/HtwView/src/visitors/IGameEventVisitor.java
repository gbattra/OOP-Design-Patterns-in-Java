package visitors;

import htw.game.IHtwGame;
import maze.components.ICoordinates;

public interface IGameEventVisitor {
  /**
   * Instructs visitor to handle a game restart event. Typically this would involve resetting the
   * GUI to reflect the new maze, initial player position, etc.
   *
   * @param game the game to render
   */
  void restart(IHtwGame game);

  /**
   * Instructs visitor to handle a move event.
   *
   * @param c the coordinates to which the player has moved
   */
  void move(ICoordinates c);

  /**
   * Instructs the visitor to handle an alert.
   *
   * @param message the alert message to render
   */
  void alert(String message);
}
