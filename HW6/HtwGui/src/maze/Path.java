package maze;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A concrete representation of a path through a 2d maze. Takes a target destination. Tracks
 * the coordinates traversed, the gold looted or stolen at each node and a boolean indicating
 * whether or not the path reaches the target.
 */
public class Path implements IPath {
  private final boolean reachesTarget;
  private final List<ICoordinates> coordinatesTraversed;
  private final ICoordinates target;
  private final int totalGold;

  /**
   * The standard constructor for the path.
   *
   * @param target the destination for the path
   */
  public Path(ICoordinates target) {
    this.target = target;
    this.reachesTarget = false;
    this.coordinatesTraversed = new ArrayList<>();
    this.totalGold = 0;
  }

  /**
   * Private constructor for Path class. Used for updated gold, coordinates and reachesTarget.
   *
   * @param target the target coordinates
   * @param totalGold the total gold accumulated by the path
   * @param reachesTarget does this path reach the target?
   * @param coordinatesTraversed the coordinates traversed by the path
   * @throws IllegalArgumentException if totalGold is negative
   */
  private Path(
          ICoordinates target,
          List<ICoordinates> coordinatesTraversed,
          int totalGold,
          boolean reachesTarget) throws IllegalArgumentException {
    this.target = target;
    this.reachesTarget = reachesTarget;
    this.totalGold = totalGold;
    this.coordinatesTraversed = coordinatesTraversed;
  }

  @Override
  public boolean reachesTarget() {
    return this.reachesTarget;
  }

  @Override
  public int totalGold() {
    return this.totalGold;
  }

  @Override
  public ICoordinates getTarget() {
    return this.target;
  }

  @Override
  public List<ICoordinates> getCoordinatesTraversed() {
    return this.coordinatesTraversed;
  }

  @Override
  public IPath loot(Node node) {
    if (this.coordinatesTraversed.contains(node.getCoordinates())) {
      return new Path(
              this.target, this.coordinatesTraversed, this.totalGold, this.reachesTarget);
    }

    return new Path(
            this.target,
            this.coordinatesTraversed,
            node.loot(this.totalGold),
            this.reachesTarget);
  }

  @Override
  public IPath addCoordinates(ICoordinates coordinates) {
    List<ICoordinates> coordinatesCopy = new ArrayList<>(this.coordinatesTraversed);
    coordinatesCopy.add(coordinates);

    return new Path(
            this.target,
            coordinatesCopy,
            this.totalGold,
            this.reachesTarget);
  }

  @Override
  public IPath setReachesTarget(boolean doesReach) {
    return new Path(
            this.target,
            this.coordinatesTraversed,
            this.totalGold,
            doesReach);
  }

  @Override
  public String toString() {
    return String.format(
            "Maze: Target - %s, Does Reach: %s, Total Gold: %s, Coordinates: %s",
            this.target.toString(),
            this.reachesTarget,
            this.totalGold(),
            this.coordinatesTraversed
                    .stream().map(ICoordinates::toString).collect(Collectors.joining(",")));
  }

  @Override
  public IPath enter(Node node) {
    return this
            .loot(node)
            .addCoordinates(node.getCoordinates())
            .setReachesTarget(
                    this.reachesTarget || node.getCoordinates().equals(this.target));
  }

  @Override
  public int hashCode() {
    return this.toString().hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (obj instanceof IPath) {
      IPath o = (IPath) obj;
      return this.toString().equals(obj.toString());
    }

    return false;
  }
}
