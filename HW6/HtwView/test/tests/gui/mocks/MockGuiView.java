package tests.gui.mocks;

import java.io.IOException;

import gui.IView;
import gui.IViewFeatures;
import gui.RestartRequest;
import htw.game.IHtwGame;

public class MockGuiView implements IView {
  private StringBuilder log;

  public MockGuiView(StringBuilder log) {
    this.log = log;
  }

  @Override
  public void populate(IHtwGame game) {

  }

  @Override
  public void setFeatures(IViewFeatures features) {

  }

  @Override
  public void onRestart(RestartRequest restartRequest) {
    this.log.append(
            String.format(
                    "%s - %s - %s - %s - %s - %s - %s - %s - %s",
                    restartRequest.useSameMaze,
                    restartRequest.isMultiplayer,
                    restartRequest.arrowCount,
                    restartRequest.rowCount,
                    restartRequest.columnCount,
                    restartRequest.isRoomMaze,
                    restartRequest.finalEdgeCount,
                    restartRequest.pitFrequency,
                    restartRequest.batFrequency));
  }

  @Override
  public Appendable append(char c) throws IOException {
    return null;
  }

  @Override
  public Appendable append(CharSequence csq) throws IOException {
    return null;
  }

  @Override
  public Appendable append(CharSequence csq, int start, int end) throws IOException {
    return null;
  }
}
