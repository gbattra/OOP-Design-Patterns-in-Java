package htw.view;

import htw.game.IHtwGame;
import htw.game.events.IGameEvent;
import maze.components.ICoordinates;
import visitors.IVisitor;

/**
 * Interface for the view of a Hunt the Wumpus game. The same interface is used for both GUI
 * and Console view implementations.
 */
public interface IView extends IVisitor {
  /**
   * Instructs view to render a game restart event. Typically this would involve resetting the
   * GUI to reflect the new maze, initial player position, etc.
   *
   * @param game the game to render
   */
   void handleRestart(IHtwGame game);

  /**
   * Instructs view to render a move event.
   *
   * @param c the coordinates to which the player has moved
   */
   void handleMove(ICoordinates c);

  /**
   * Renders and alert in the view.
   *
   * @param message the alert message to render
   */
  void handleAlert(String message);
}
