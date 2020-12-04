package gui;

public interface IViewFeatures {
  void restart(RestartRequest restartRequest);
  void onMove(int id);
  void onShoot(int id, int count);
}
