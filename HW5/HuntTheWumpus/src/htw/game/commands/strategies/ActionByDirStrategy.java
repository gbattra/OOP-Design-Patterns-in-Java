package htw.game.commands.strategies;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import htw.game.IHtwGame;
import htw.level.nodes.IHtwNode;
import maze.utils.Direction;

public class ActionByDirStrategy implements IActionStrategy {
  @Override
  public void move(Scanner in, IHtwGame game) {
    Direction dir = Direction.stringToDirection(in.next());
    game.move(dir);
  }

  @Override
  public void shoot(Scanner in, IHtwGame game) {
    Direction dir = Direction.stringToDirection(in.next());
    int count = in.nextInt();
    game.shoot(dir, count);
  }

  @Override
  public String status(IHtwNode curr) {
    List<IHtwNode> neighbors = curr.neighbors();
    return String.format(
            "You are in cave %s with tunnels to the %s",
            curr.getCoordinates().toString(),
            neighbors
                    .stream()
                    .map(n -> n.directionTo(curr.id()).opposite().toString())
                    .collect(Collectors.joining(", ")));
  }
}
