package htw.view;

import visitors.IGameEventVisitor;
import visitors.IRoundVisitor;

/**
 * Interface for the view of a Hunt the Wumpus game. The same interface is used for both GUI
 * and Console view implementations.
 */
public interface IView extends IGameEventVisitor, IRoundVisitor {
}
