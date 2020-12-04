package htw.game;

import java.io.IOException;
import java.util.List;

import gui.IHtwGameVisitor;
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
  private int round = -1;

  /**
   * Constructor for the game.
   *
   * @param players the players for the game
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
    if (players.isEmpty()) {
      throw new IllegalArgumentException("Player list cannot be empty.");
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
  public void start() {
    this.round++;
    for (IHtwPlayer player : players) {
      player.setCurrentPosition(maze.randomCoordinates());
    }
  }

  @Override
  public String status(IActionStrategy strategy) {
    return this.maze.status(this.activePlayer(), strategy);
  }

  @Override
  public boolean move(Direction direction) throws IOException {
    boolean move = this.maze.move(this.activePlayer(), direction);
    if (move) {
      this.maze.receive(this.activePlayer());
    }
    this.round++;
    return move;
  }

  @Override
  public boolean move(int id) throws IOException {
    boolean move = this.maze.move(this.activePlayer(), id);
    if (move) {
      this.maze.receive(this.activePlayer());
    }
    this.round++;
    return move;
  }

  @Override
  public boolean shoot(Direction direction, int count) throws IOException {
    boolean hit = this.maze.shoot(this.activePlayer(), direction, count);
    this.activePlayer().decrementArrowCount();
    if (hit) {
      this.logger.append("Nice shot! You've slain the Wumpus! VICTORY!\n");
      this.wumpusSlain = true;
    } else {
      this.logger.append(
              "Miss... You have " + this.activePlayer().arrowCount() + " remaining arrows.");
    }
    this.round++;

    return hit;
  }

  @Override
  public boolean shoot(int id, int count) throws IOException {
    boolean hit = this.maze.shoot(this.activePlayer(), id, count);
    this.activePlayer().decrementArrowCount();
    if (hit) {
      this.logger.append("Nice shot! You've slain the Wumpus! VICTORY!\n");
      this.wumpusSlain = true;
    } else {
      this.logger.append(
              "Miss... You have " + this.activePlayer().arrowCount() + " remaining arrows.");
    }
    this.round++;

    return hit;
  }

  @Override
  public <R> R receive(IHtwGameVisitor<R> visitor) {
    return visitor.visitGame(this.players, this.maze, this.activePlayer().number());
  }

  private IHtwPlayer activePlayer() {
    if (this.isOver()) {
      return this.players.get(this.round % this.players.size());
    }
    
    IHtwPlayer player = this.players.get(this.round % this.players.size());
    if (!player.isAlive() || !(player.arrowCount() > 0)) {
      this.round++;
      return this.activePlayer();
    }

    return player;
  }
}
