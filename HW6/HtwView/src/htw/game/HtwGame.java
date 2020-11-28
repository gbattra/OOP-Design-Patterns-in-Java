package htw.game;

import java.util.List;

import htw.game.commands.IActionStrategy;
import htw.level.IHtwMaze;
import maze.Direction;

/**
 * Implementation of a Hunt the Wumpus game.
 */
public class HtwGame implements IHtwGame {
  private final List<IHtwPlayer> players;
  private final IHtwMaze maze;
  private final Appendable logger;

  private boolean wumpusSlain = false;
  private int roundNumber = -1;

  /**
   * Constructor for the game.
   *
   * @param players the players of the game
   * @param maze the maze to use in the game
   * @param logger the logger for game output
   * @throws IllegalArgumentException if params are null
   */
  public HtwGame(
          List<IHtwPlayer> players,
          IHtwMaze maze,
          Appendable logger) throws IllegalArgumentException {
    if (players == null || maze == null || logger == null) {
      throw new IllegalArgumentException("Player and maze cannot be null.");
    }
    this.players = players;
    this.maze = maze;
    this.logger = logger;
  }

  @Override
  public boolean isOver() {
    return this.players.stream().allMatch(p -> p.arrowCount() == 0)
        || this.players.stream().noneMatch(IHtwPlayer::isAlive)
        || this.wumpusSlain;
  }

  @Override
  public String status(IActionStrategy strategy) {
    return this.maze.status(strategy);
  }

  @Override
  public boolean move(Direction direction, IRound round) {
    try {
      boolean move = this.maze.move(direction);
      if (move) {
        this.maze.receive(this.activePlayer());
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
        this.maze.receive(this.activePlayer());
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
      this.activePlayer().decrementArrowCount();
      if (hit) {
        this.logger.append("Nice shot! You've slain the Wumpus! VICTORY!\n");
        this.wumpusSlain = true;
      } else {
        this.logger.append("Miss... You have " + this.activePlayer().arrowCount() + " remaining arrows.");
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
      this.activePlayer().decrementArrowCount();
      if (hit) {
        this.logger.append("Nice shot! You've slain the Wumpus! VICTORY!\n");
        this.wumpusSlain = true;
      } else {
        this.logger.append("Miss... You have " + this.activePlayer().arrowCount() + " remaining arrows.");
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
    this.roundNumber++;
    return new Round(this.roundNumber);
  }

  private IHtwPlayer activePlayer() {
    return this.players.get(this.roundNumber % this.players.size());
  }
}
