package htw.game.commands;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import htw.game.IHtwGame;
import htw.game.commands.IActionStrategy;
import htw.level.IHtwNode;
import maze.Direction;

/**
 * Controller takes actions using node id's as input.
 */
public class IdActionStrategy implements IActionStrategy {
  @Override
  public void move(Scanner in, IHtwGame game) throws IOException {
    int id = in.nextInt();
    game.move(id);
  }

  @Override
  public void shoot(Scanner in, IHtwGame game) throws IOException {
    int id = in.nextInt();
    int count = in.nextInt();
    game.shoot(id, count);
  }

  @Override
  public String status(int playerNumber, IHtwNode curr) {
    Map<Direction, Integer> neighbors = curr.neighbors();
    return String.format(
            "Player %s: You are in cave %s with tunnels to node(s) %s",
            playerNumber,
            curr.getId().toString(),
            neighbors
                    .values()
                    .stream()
                    .map(Object::toString)
                    .collect(Collectors.joining(", ")));
  }
}
