package gui;

public class GuiController implements IGuiController {
  @Override
  public void onQuit() {
    System.out.print("Quitting");
  }

  @Override
  public void onRestart() {
    System.out.print("Restarting");
  }
}
