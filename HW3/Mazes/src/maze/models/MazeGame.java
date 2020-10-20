package maze.models;

import maze.enums.Direction;
import maze.interfaces.Game;
import maze.interfaces.Maze;
import maze.interfaces.Path;
import maze.interfaces.Player;

/**
 * Game manager object. Moves player and maze pointer in sync.
 */
public class MazeGame implements Game {
  private final Maze maze;

  private Player player;
  private Path path;
  private boolean isOver = false;

  /**
   * Standard constructor for GameMaze.
   *
   * @param player the player in the game
   * @param maze the maze in the game
   */
  public MazeGame(Player player, Maze maze) {
    this.maze = maze;
    this.player = player;
    this.path = new MazePath(maze.getGoal().getCoordinates());
  }

  @Override
  public Player getPlayer() {
    return this.player;
  }

  @Override
  public Maze getMaze() {
    return this.maze;
  }

  @Override
  public Path getPath() {
    return this.path;
  }

  @Override
  public int getScore() {
    return this.player.getGold();
  }

  @Override
  public boolean isOver() {
    return this.isOver;
  }

  @Override
  public boolean movePlayer(Direction direction) {
    boolean moved = maze.move(direction);
    if (moved && !path.getCoordinatesTraversed().contains(maze.getCurrent().getCoordinates())) {
      path.enter(maze.getCurrent());
      this.player = player.loot(maze.getCurrent());
    }

    this.isOver |= maze.getCurrent().isGoal();

    return moved;
  }

  @Override
  public void start() {
    path.enter(maze.getCurrent());
    player.loot(maze.getCurrent());
    this.isOver &= !maze.getCurrent().isGoal();
  }
}
