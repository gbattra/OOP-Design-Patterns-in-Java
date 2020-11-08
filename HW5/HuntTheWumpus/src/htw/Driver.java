package htw;

import htw.game.Game;
import htw.game.HTWGame;
import htw.game.Round;

public class Driver {
  public static void main(String[] args) {
    Game game = new HTWGame();
    int loops = 3;
    while(game.hasNext()) {
      Round round = game.next();
      if (loops == 0) {
        System.out.printf("Setting");
        game.set(true);
      }
      loops--;
    }
  }
}
