package htw.game.commands.strategies;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import htw.game.IHtwGame;
import htw.level.nodes.IHtwNode;

public class ActionByIdStrategy implements IActionStrategy {
  @Override
  public void move(Scanner in, IHtwGame game) {
    int id = in.nextInt();
    game.move(id);
  }

  @Override
  public void shoot(Scanner in, IHtwGame game) {
    int id = in.nextInt();
    int count = in.nextInt();
    game.shoot(id, count);
  }

  @Override
  public String status(IHtwNode curr) {
    List<IHtwNode> neighbors = curr.neighbors();
    return String.format(
            "You are in cave %s with tunnels to node(s) %s",
            curr.id().toString(),
            neighbors
                    .stream()
                    .map(n -> n.id().toString())
                    .collect(Collectors.joining(", ")));
  }
}
