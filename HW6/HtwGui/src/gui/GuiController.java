package gui;

import java.io.IOException;
import java.util.Random;

import htw.game.IHtwGame;
import htw.tools.HtwConfigurationBuilder;
import htw.tools.HtwGameBuilder;
import htw.tools.IHtwConfigurationBuilder;
import maze.Direction;

/**
 * Controller for the GUI mode of Hunt the Wumpus.
 */
public class GuiController implements IGuiController {
  private final IView view;
  private final Random random = new Random();

  private IHtwGame game;
  private IHtwConfigurationBuilder configurationBuilder;

  /**
   * Constructor for the controller.
   *
   * @param view the client view which will receive the game model on each update
   * @throws IllegalArgumentException if view is null
   */
  public GuiController(IView view) throws IllegalArgumentException {
    if (view == null) {
      throw new IllegalArgumentException("Cannot instantiate GuiController. View is null.");
    }

    this.view = view;
    this.view.setFeatures(this);
  }

  @Override
  public void startNew() {
    try {
      configurationBuilder = ((IHtwConfigurationBuilder) new HtwConfigurationBuilder()
              .setLogger(view)
              .setNumPlayers(2)
              .setRandomSeed(this.random.nextInt(1000)));
      game = new HtwGameBuilder(configurationBuilder.build()).build();
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
        game = new HtwGameBuilder(configurationBuilder.build()).build();
        game.start();
        this.view.populate(game);
        return;
      }

      configurationBuilder.setBatFrequency(restartRequest.batFrequency)
                          .setPitFrequency(restartRequest.pitFrequency)
                          .setArrowCount(restartRequest.arrowCount)
                          .setNumPlayers(restartRequest.isMultiplayer ? 2 : 1)
                          .setIsRoomMaze(restartRequest.isRoomMaze)
                          .setTargetEdgeCount(restartRequest.finalEdgeCount)
                          .setRowCount(restartRequest.rowCount)
                          .setColumnCount(restartRequest.columnCount)
                          .setRandomSeed(this.random.nextInt(1000));

      game = new HtwGameBuilder(configurationBuilder.build()).build();
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
