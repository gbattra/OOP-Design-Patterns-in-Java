package htw.level.strategies;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import htw.game.IHtwPlayer;
import htw.level.nodes.IHtwNode;
import maze.components.ICoordinates;
import maze.utils.Direction;

/**
 * The base strategy for a cave in the maze. All other strategies extend this strategy and
 * typically override only one or two of its methods.
 */
public class StandardStrategy implements IHtwNodeStrategy {
  @Override
  public IHtwNode enter(Direction from, IHtwNode curr) throws IOException {
    curr.logger().append("You enter the cave and...\n");
    return curr;
  }

  @Override
  public boolean shoot(Direction direction, int count, IHtwNode curr) {
    if (count == 0) {
      return false;
    }
    return ((IHtwNode) curr.getNode(direction)).shoot(direction, count - 1);
  }

  @Override
  public void receive(IHtwPlayer player, IHtwNode curr)
          throws IllegalArgumentException, IOException {
    if (player == null) {
      throw new IllegalArgumentException("Player cannot be null.");
    }

    boolean drafty = false;
    boolean smelly = false;
    List<Direction> exits = new ArrayList<>(
            Arrays.asList(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST));
    for (Direction exit : exits) {
      IHtwNode node = (IHtwNode) curr.getNode(exit);
      drafty |= node.drafty(exit.opposite());
      smelly |= node.smelly(exit.opposite());
    }
    if (drafty) {
      curr.logger().append("You feel a draft\n");
    }
    if (smelly) {
      curr.logger().append("You smell a Wumpus\n");
    }
    if (!drafty && !smelly) {
      curr.logger().append("The cave is empty\n");
    }
  }

  @Override
  public boolean smelly(Direction from, IHtwNode curr) {
    return false;
  }

  @Override
  public boolean drafty(Direction from, IHtwNode curr) {
    return false;
  }

  @Override
  public IHtwNode adjacent(List<ICoordinates> traversed, IHtwNode curr) {
    if (traversed.contains(curr.getCoordinates())) {
      throw new IllegalStateException("Already traversed this node when finding adjacent.");
    }
    return curr;
  }

  @Override
  public String toString() {
    return "Standard";
  }
}
