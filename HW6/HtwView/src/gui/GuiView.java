package gui;

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
}
