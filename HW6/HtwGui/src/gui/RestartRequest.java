package gui;

/**
 * A plain-old Java object holding the input values from the restart menu form.
 */
public class RestartRequest {
  /** As this object serves as a way of condensing form entries into a single object, I chose
   * to leave the access modifiers on the properties public and maintain immutability through
   * the use of `final`.
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

  /**
   * Constructor for the restart request.
   *
   * @param useSameMaze should the new game use the same maze as the current game
   * @param isMultiplayer is the new game multiplayer
   * @param isRoomMaze is the new maze a room maze
   * @param arrowCount starting arrow count for the players
   * @param finalEdgeCount final edge count if new maze is a room maze
   * @param rowCount the row size of the new maze
   * @param columnCount the column size of the new maze
   * @param pitFrequency how frequently should pit caves appear
   * @param batFrequency how frequently should bat caves appear
   */
  public RestartRequest(
          boolean useSameMaze,
          boolean isMultiplayer,
          boolean isRoomMaze,
          int arrowCount,
          int finalEdgeCount,
          int rowCount,
          int columnCount,
          double pitFrequency,
          double batFrequency) {
    this.useSameMaze = useSameMaze;
    this.isMultiplayer = isMultiplayer;
    this.isRoomMaze = isRoomMaze;
    this.arrowCount = arrowCount;
    this.finalEdgeCount = finalEdgeCount;
    this.rowCount = rowCount;
    this.columnCount = columnCount;
    this.pitFrequency = pitFrequency;
    this.batFrequency = batFrequency;
  }
}
