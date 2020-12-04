package gui;

import maze.Direction;

public interface IViewFeatures {
  void restart(RestartRequest restartRequest);
  void onMove(int id);
  void onMove(Direction direction);
  void onShoot(int id, int count);
}
