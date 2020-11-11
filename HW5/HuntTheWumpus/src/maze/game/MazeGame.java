package maze.game;

import maze.utils.Direction;
import maze.components.IMaze;
import maze.components.IPath;
import maze.components.Path;

/**
 * Game manager object. Moves player and maze pointer in sync.
 */
public class MazeGame implements IMazeGame {
  private final IMaze maze;

  private IMazePlayer player;
  private IPath path;
  private boolean isOver = false;

  /**
   * Standard constructor for GameMaze.
   *
   * @param player the player in the game
   * @param maze the maze in the game
   */
  public MazeGame(IMazePlayer player, IMaze maze) {
    this.maze = maze;
    this.player = player;
    this.path = new Path(maze.getGoal().getCoordinates());
  }

  @Override
  public IMazePlayer getPlayer() {
    return this.player;
  }

  @Override
  public IMaze getMaze() {
    return this.maze;
  }

  @Override
  public IPath getPath() {
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
