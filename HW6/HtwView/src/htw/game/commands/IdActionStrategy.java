package htw.game.commands;

import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import htw.game.IHtwGame;
import htw.game.IRound;
import htw.level.IHtwNode;
import maze.Direction;

/**
 * Controller takes actions using node id's as input.
 */
public class IdActionStrategy implements IActionStrategy {
  @Override
  public void move(Scanner in, IHtwGame game, IRound round) {
    int id = in.nextInt();
    game.move(id);
  }

  @Override
  public void shoot(Scanner in, IHtwGame game, IRound round) {
    int id = in.nextInt();
    int count = in.nextInt();
    game.shoot(id, count);
  }

  @Override
  public String status(IHtwNode curr) {
    Map<Direction, Integer> neighbors = curr.neighbors();
    return String.format(
            "You are in cave %s with tunnels to node(s) %s",
            curr.getId().toString(),
            neighbors
                    .values()
                    .stream()
                    .map(Object::toString)
                    .collect(Collectors.joining(", ")));
  }
}
