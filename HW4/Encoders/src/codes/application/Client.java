package codes.application;

/**
 * Generic interface for a client object. Has one method: run(), which starts the client program.
 */
public interface Client {
  /**
   * Starts the client.
   *
   * @return 1 if valid exit status, 0 otherwise
   */
  int run();
}
