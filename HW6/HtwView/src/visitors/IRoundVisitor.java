package visitors;

import htw.game.IRound;

/**
 * Visitor interface for objects that visit IRound objects.
 */
public interface IRoundVisitor {
  /**
   * The entry point for the visitor into the IRound object.
   *
   * @param round the round to visit
   */
  void visitRound(IRound round);
}
