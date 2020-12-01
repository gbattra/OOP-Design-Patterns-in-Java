package gui;

import htw.game.IHtwGame;

public interface IView extends IContainerFeatures, Appendable {
  void populate(IHtwGame game);
}
