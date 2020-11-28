package htw.view;

import htw.game.IGameEvent;
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
   * @param e the game event to render
   */
   void renderRestart(IGameEvent e);
}
