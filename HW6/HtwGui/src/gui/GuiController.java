package gui;

import java.io.IOException;
import java.util.Random;

import htw.game.IHtwGame;
import htw.tools.HtwConfigurationBuilder;
import htw.tools.IHtwConfigurationBuilder;
import htw.tools.IHtwGameBuilder;
import maze.Direction;

/**
 * Controller for the GUI mode of Hunt the Wumpus.
 */
public class GuiController implements IGuiController {
  private final IView view;
  private final IHtwGameBuilder gameBuilder;
  private final Random random = new Random();

  private IHtwGame game;
  private IHtwConfigurationBuilder configurationBuilder;

  /**
   * Constructor for the controller.
   *
   * @param view the client view which will receive the game model on each update
   * @param gameBuilder factory to use to build the game
   * @throws IllegalArgumentException if view is null
   */
  public GuiController(IView view, IHtwGameBuilder gameBuilder) throws IllegalArgumentException {
    if (view == null || gameBuilder == null) {
      throw new IllegalArgumentException("Cannot instantiate GuiController. Args are null.");
    }

    this.gameBuilder = gameBuilder;
    this.view = view;
    this.view.setFeatures(this);
    this.configurationBuilder = new HtwConfigurationBuilder();
  }

  @Override
  public void startNew() {
    try {
      this.configurationBuilder
              .setLogger(view)
              .setNumPlayers(2)
              .setRandomSeed(this.random.nextInt(1000));
      this.gameBuilder.setConfiguration(this.configurationBuilder.build());
      game = this.gameBuilder.build();
      game.start();
      this.view.populate(game);
    } catch (IllegalArgumentException | IllegalStateException e) {
      this.view.alert(e.getMessage());
    }
  }

  @Override
  public void restart(RestartRequest restartRequest) {
    try {
      if (restartRequest.useSameMaze) {
        this.gameBuilder.setConfiguration(this.configurationBuilder.build());
        game = this.gameBuilder.build();
        game.start();
        this.view.populate(game);
        return;
      }

      this.configurationBuilder.setBatFrequency(restartRequest.batFrequency)
                          .setPitFrequency(restartRequest.pitFrequency)
                          .setArrowCount(restartRequest.arrowCount)
                          .setNumPlayers(restartRequest.isMultiplayer ? 2 : 1)
                          .setIsRoomMaze(restartRequest.isRoomMaze)
                          .setTargetEdgeCount(restartRequest.finalEdgeCount)
                          .setRowCount(restartRequest.rowCount)
                          .setColumnCount(restartRequest.columnCount)
                          .setRandomSeed(this.random.nextInt(1000));

      this.gameBuilder.setConfiguration(this.configurationBuilder.build());
      game = this.gameBuilder.build();
      game.start();
      this.view.populate(game);
    } catch (IllegalArgumentException | IllegalStateException e) {
      this.view.alert(e.getMessage());
    }
  }

  @Override
  public void onMove(Direction direction) {
    try {
      boolean move = game.move(direction);
      this.view.populate(game);
    } catch (IllegalArgumentException | IllegalStateException | IOException e) {
      this.view.alert(e.getMessage());
    }
  }

  @Override
  public void onMove(int id) {
    try {
      boolean move = game.move(id);
      this.view.populate(game);
    } catch (IllegalArgumentException | IllegalStateException | IOException e) {
      this.view.alert(e.getMessage());
    }
  }

  @Override
  public void onShoot(int id, int count) {
    try {
      boolean shoot = game.shoot(id, count);
      this.view.populate(game);
    } catch (IllegalArgumentException | IllegalStateException | IOException e) {
      this.view.alert(e.getMessage());
    }
  }
}
