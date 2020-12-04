package gui;

import maze.Direction;

public interface IContainerFeatures {
  void onQuit();
  void onRestart(RestartRequest restartRequest);
  void onMove(int id);
  void onMove(Direction dir);
  void onShoot(int id, int count);
}
