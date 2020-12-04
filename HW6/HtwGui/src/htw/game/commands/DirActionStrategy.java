package htw.game.commands;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import htw.game.IHtwGame;
import htw.level.IHtwNode;
import maze.Direction;


/**
 * Controller takes actions using cardinal directions as input.
 */
public class DirActionStrategy implements IActionStrategy {
  @Override
  public void move(Scanner in, IHtwGame game) throws IOException {
    Direction dir = Direction.stringToDirection(in.next());
    game.move(dir);
  }

  @Override
  public void shoot(Scanner in, IHtwGame game) throws IOException {
    Direction dir = Direction.stringToDirection(in.next());
    int count = in.nextInt();
    game.shoot(dir, count);
  }

  @Override
  public String status(int playerNumber, IHtwNode curr) {
    Map<Direction, Integer> neighbors = curr.neighbors();
    return String.format(
            "Player %s: You are in cave %s with tunnels to the %s",
            playerNumber,
            curr.getCoordinates().toString(),
            neighbors
                    .keySet()
                    .stream()
                    .map(Enum::toString)
                    .collect(Collectors.joining(", ")));
  }
}
