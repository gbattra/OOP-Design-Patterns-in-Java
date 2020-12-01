package tests.gui.mocks;

import java.io.IOException;

import gui.IView;

public class MockGuiView implements IView {
  private StringBuilder log;

  public MockGuiView(StringBuilder log) {
    this.log = log;
  }

  @Override
  public void onRestart(
          boolean sameMaze,
          boolean multiPlayer,
          int rowCount,
          int columnCount,
          double batFreq,
          double pitFreq) {
    this.log.append(
            String.format(
                    "%s - %s - %s - %s - %s - %s",
                    sameMaze,
                    multiPlayer,
                    rowCount,
                    columnCount,
                    batFreq,
                    pitFreq));
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
