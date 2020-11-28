package htw.game;

import htw.game.commands.IActionStrategy;
import htw.level.IHtwMaze;
import maze.Direction;

/**
 * Implementation of a Hunt the Wumpus game.
 */
public class HtwGame implements IHtwGame {
  private final IHtwPlayer player;
  private final IHtwMaze maze;
  private final Appendable logger;

  private boolean wumpusSlain = false;

  /**
   * Constructor for the game.
   *
   * @param player the player instance for the game
   * @param maze the maze to use in the game
   * @param logger the logger for game output
   * @throws IllegalArgumentException if params are null
   */
  public HtwGame(
          IHtwPlayer player,
          IHtwMaze maze,
          Appendable logger) throws IllegalArgumentException {
    if (player == null || maze == null || logger == null) {
      throw new IllegalArgumentException("Player and maze cannot be null.");
    }
    this.player = player;
    this.maze = maze;
    this.logger = logger;
  }

  @Override
  public boolean isOver() {
    return this.player.arrowCount() == 0 || !this.player.isAlive() || this.wumpusSlain;
  }

  @Override
  public String status(IActionStrategy strategy) {
    return this.maze.status(strategy);
  }

  @Override
  public boolean move(Direction direction) {
    try {
      boolean move = this.maze.move(direction);
      if (move) {
        this.maze.receive(this.player);
      }
      return move;
    } catch (Exception e) {
      return false;
    }
  }

  @Override
  public boolean move(int id) {
    try {
      boolean move = this.maze.move(id);
      if (move) {
        this.maze.receive(this.player);
      }
      return move;
    } catch (Exception e) {
      return false;
    }
  }

  @Override
  public boolean shoot(Direction direction, int count) {
    try {
      boolean hit = this.maze.shoot(direction, count);
      this.player.decrementArrowCount();
      if (hit) {
        this.logger.append("Nice shot! You've slain the Wumpus! VICTORY!\n");
        this.wumpusSlain = true;
      } else {
        this.logger.append("Miss... You have " + this.player.arrowCount() + " remaining arrows.");
      }

      return hit;
    } catch (Exception e) {
      return false;
    }
  }

  @Override
  public boolean shoot(int id, int count) {
    try {
      boolean hit = this.maze.shoot(id, count);
      this.player.decrementArrowCount();
      if (hit) {
        this.logger.append("Nice shot! You've slain the Wumpus! VICTORY!\n");
        this.wumpusSlain = true;
      } else {
        this.logger.append("Miss... You have " + this.player.arrowCount() + " remaining arrows.");
      }

      return hit;
    } catch (Exception e) {
      return false;
    }
  }

  @Override
  public boolean hasNext() {
    return !this.isOver();
  }

  @Override
  public IRound next() {
    return new Round();
  }
}
