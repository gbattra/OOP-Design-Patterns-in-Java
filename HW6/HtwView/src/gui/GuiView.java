package gui;

import java.io.IOException;

public class GuiView implements IView {
  private final IGuiController controller;
  private final Container container;

  public GuiView(IGuiController controller) {
    if (controller == null) {
      throw new IllegalArgumentException("Cannot instantiate GuiView. Controller is null.");
    }

    this.controller = controller;
    this.container = new Container("Container", this);
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
