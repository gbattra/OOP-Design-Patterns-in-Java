package gui;

public interface IContainerFeatures {
  void onRestart(
          boolean sameMaze,
          boolean multiPlayer,
          int rowCount,
          int columnCount,
          double batFreq,
          double pitFreq);
}
