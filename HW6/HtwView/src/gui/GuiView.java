package gui;

import java.io.IOException;
import java.util.List;

import htw.game.IHtwGame;
import htw.game.IHtwPlayer;
import htw.level.IHtwMaze;

public class GuiView implements IView, IHtwGameVisitor<Void> {
  private Container container;

  private IViewFeatures features;

  @Override
  public void setFeatures(IViewFeatures features) {
    this.features = features;
  }

  @Override
  public void populate(IHtwGame game) {
    game.receive(this);
  }

  @Override
  public Void visitGame(List<IHtwPlayer> players, IHtwMaze maze) {
    this.container = new Container("Container", this, players, maze);
    return null;
  }

  @Override
  public void onRestart(RestartRequest restartRequest) {

  }

  @Override
  public Appendable append(CharSequence csq) throws IOException {
    for (Character c : csq.toString().toCharArray()) {
      this.append(c);
    }
    return this;
  }

  @Override
  public Appendable append(CharSequence csq, int start, int end) throws IOException {
    for (int i = start; i < end; i++) {
      this.append(csq.charAt(i));
    }
    return this;
  }

  @Override
  public Appendable append(char c) throws IOException {
    // append char to logger view
    return this;
  }
}
