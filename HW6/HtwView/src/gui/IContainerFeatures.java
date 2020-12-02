package gui;

public interface IContainerFeatures {
  void onQuit();
  void onRestart(RestartRequest restartRequest);
  void onMove(int id);
}
