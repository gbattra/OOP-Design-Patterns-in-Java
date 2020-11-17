package htw.game.commands.strategies;

import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import htw.game.IHtwGame;
import htw.level.nodes.IHtwNode;
import maze.utils.Direction;

public class DirActionStrategy implements IActionStrategy {
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
    Map<Direction, Integer> neighbors = curr.neighbors();
    return String.format(
            "You are in cave %s with tunnels to the %s",
            curr.getCoordinates().toString(),
            neighbors
                    .keySet()
                    .stream()
                    .map(Enum::toString)
                    .collect(Collectors.joining(", ")));
  }
}
