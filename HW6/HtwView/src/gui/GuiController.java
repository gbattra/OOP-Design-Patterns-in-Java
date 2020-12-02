package gui;

import java.util.Random;

import htw.game.IHtwGame;
import htw.tools.HtwConfigurationBuilder;
import htw.tools.HtwGameBuilder;
import htw.tools.IHtwConfigurationBuilder;

public class GuiController implements IGuiController {
  private final IView view;
  private final Random random = new Random();

  private IHtwGame game;
  private IHtwConfigurationBuilder configurationBuilder;

  public GuiController(IView view) throws IllegalArgumentException {
    if (view == null) {
      throw new IllegalArgumentException("Cannot instantiate GuiController. View is null.");
    }

    this.view = view;
    this.view.setFeatures(this);
  }

  @Override
  public void startNew() {
    configurationBuilder = ((IHtwConfigurationBuilder) new HtwConfigurationBuilder()
            .setLogger(view)
            .setRandomSeed(this.random.nextInt()));
    game = new HtwGameBuilder(configurationBuilder.build()).build();
    game.start();
    this.view.populate(game);
  }

  @Override
  public void restart(RestartRequest restartRequest) {
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
                        .setColumnCount(restartRequest.columnCount);

    game = new HtwGameBuilder(configurationBuilder.build()).build();
    game.start();
    this.view.populate(game);
  }
}
