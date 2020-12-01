package gui;

/**
 * A plain-old Java object holding the input values from the restart menu form.
 */
public class RestartRequest {
  /** As this object
   * serves as a way of condensing form entries into a single object, I chose to leave the access
   * modifiers on the properties public and maintain immutability through the use of `final`.
   */
  public final boolean useSameMaze;
  public final boolean isMultiplayer;
  public final boolean isRoomMaze;
  public final int arrowCount;
  public final int finalEdgeCount;
  public final int rowCount;
  public final int columnCount;
  public final double pitFrequency;
  public final double batFrequency;

  public RestartRequest(
          boolean useSameMaze,
          boolean isMultiplayer,
          boolean isRoomMaze,
          int arrowCount,
          int finalRoomCount,
          int rowCount,
          int columnCount,
          double pitFrequency,
          double batFrequency) {
    this.useSameMaze = useSameMaze;
    this.isMultiplayer = isMultiplayer;
    this.isRoomMaze = isRoomMaze;
    this.arrowCount = arrowCount;
    this.finalEdgeCount = finalRoomCount;
    this.rowCount = rowCount;
    this.columnCount = columnCount;
    this.pitFrequency = pitFrequency;
    this.batFrequency = batFrequency;
  }
}
