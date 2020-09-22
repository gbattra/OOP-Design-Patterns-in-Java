package interfaces;

/**
 * Interface extending IBird for birds that live near water (i.e. waterfowl, shorebirds).
 */
public interface IWaterbird extends IBird {
  /**
   * Accessor for the bird instance nearest water body
   *
   * @return String name of the nearest water body
   */
  String getNearestWaterbody();
}
