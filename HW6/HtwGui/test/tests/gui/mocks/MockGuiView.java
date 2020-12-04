package tests.gui.mocks;

import java.io.IOException;

import gui.IView;
import gui.IViewFeatures;
import gui.RestartRequest;
import htw.game.IHtwGame;
import maze.Direction;

/**
 * Mock gui view for testing.
 */
public class MockGuiView implements IView {
  private StringBuilder log;

  /**
   * Constructor for the mock view.
   *
   * @param log the log for the mock
   */
  public MockGuiView(StringBuilder log) {
    this.log = log;
  }

  @Override
  public void alert(String message) {
    // unused method from interface
  }

  @Override
  public void populate(IHtwGame game) {
    // unused method from interface
  }

  @Override
  public void onShoot(int id, int count) {
    // unused method from interface
  }

  @Override
  public void setFeatures(IViewFeatures features) {
    // unused method from interface
  }

  @Override
  public void onQuit() {
    // unused method from interface
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
  public void onMove(int id) {
    // unused method from interface
  }

  @Override
  public void onMove(Direction dir) {
    // unused method from interface
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
