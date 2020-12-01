package gui;

import java.io.IOException;

import htw.game.IHtwGame;

public class GuiView implements IView {
  private final Container container;

  private IViewFeatures features;

  public GuiView() {
    this.container = new Container("Container", this);
  }

  @Override
  public void setFeatures(IViewFeatures features) {
    this.features = features;
  }

  @Override
  public void populate(IHtwGame game) {
    game.receive(this.container);
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
