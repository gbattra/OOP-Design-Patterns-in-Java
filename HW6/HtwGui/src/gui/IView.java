package gui;

import htw.game.IHtwGame;

public interface IView extends IContainerFeatures, Appendable {
  void setFeatures(IViewFeatures features);
  void populate(IHtwGame game);
  void alert(String message);
}
