package rpg.models;

import java.util.ArrayList;
import java.util.List;

import rpg.interfaces.IBattle;
import rpg.interfaces.IPlayer;

/**
 * A class representing a battle between multiple players.
 */
public class Battle implements IBattle {
  private static final int PLAYER_COUNT_MIN = 2;
  private final List<IPlayer> players;
  private final int playerCount;

  /**
   * Constructor for a battle instance.
   *
   * @param playerCount the number of players participating in the battle
   * @throws IllegalArgumentException when playerCount <= 0 is provided
   */
  public Battle(
          int playerCount) throws IllegalArgumentException {
    if (playerCount < PLAYER_COUNT_MIN) {
      throw new IllegalArgumentException(
              String.format("Player count must be at least %s", PLAYER_COUNT_MIN));
    }

    this.playerCount = playerCount;
    this.players = new ArrayList<>();
  }

  /**
   * Constructor for a battle instance.
   *
   * @param playerCount the number of players participating in the battle
   * @param players the players participating in the battle
   * @throws IllegalArgumentException when playerCount <= 0 is provided
   */
  public Battle(
          int playerCount,
          List<IPlayer> players) throws IllegalArgumentException {
    if (playerCount <= 0) {
      throw new IllegalArgumentException("Player count must be greater than zero.");
    }

    if (players.size() > playerCount) {
      throw new IllegalArgumentException("Provided players list exceeds player count for battle.");
    }

    this.players = players;
    this.playerCount = playerCount;
  }

  /**
   * Returns the list of players in the battle.
   *
   * @return the list of players
   */
  public List<IPlayer> getPlayers() {
    return this.players;
  }

  /**
   * Factory method which adds a player to the battle and returns the updated Battle instance.
   *
   * @param player the player to add
   * @return the updated battle instance
   * @throws IllegalStateException when the player count has already been met
   */
  public IBattle addPlayer(IPlayer player) throws IllegalStateException {
    if (this.players.stream().anyMatch(p -> p.getNumber() == player.getNumber())) {
      throw new IllegalStateException("A player with that number has already been added.");
    }

    if (this.players.size() == this.playerCount) {
      throw new IllegalStateException("Cannot add player to battle. Player limit already reached.");
    }

    List<IPlayer> newPlayers = new ArrayList<>(this.players);
    newPlayers.add(player);
    return new Battle(this.playerCount, newPlayers);
  }

  /**
   * Public method to initiate fight between players.
   *
   * @return the victor from the fight
   * @throws IllegalStateException when not enough players have been set before calling fight()
   */
  public IPlayer fight() throws IllegalStateException {
    if (this.players.size() < this.playerCount) {
      throw new IllegalStateException(
              "Cannot fight until all players have been added to the battle");
    }

    return this.fight(this.players);
  }

  /**
   * A recursive function which pops the first two players off the list, fights them against
   * one another, and determines a victor. If no more players remain, the victor from the fight
   * is returned. Otherwise, the victor is added to the end of the players list, and the function
   * calls itself with the new list until all players have fought and only one player remains.
   *
   * @param players the players fighting each other
   * @return the victor from the fight
   *
   * @throws IllegalArgumentException when less than 2 players are provided to the function
   */
  private IPlayer fight(List<IPlayer> players) throws IllegalArgumentException {
    if (players.size() < 2) {
      throw new IllegalArgumentException("Size of players list is less than two.");
    }

    List<IPlayer> playersCopy = new ArrayList<>(players);

    IPlayer playerOne = playersCopy.get(0);
    IPlayer playerTwo = playersCopy.get(1);
    playersCopy.remove(1);
    playersCopy.remove(0);

    IPlayer victor = this.duel(playerOne, playerTwo);

    if (!playersCopy.isEmpty()) {
      playersCopy.add(victor);
      return this.fight(playersCopy);
    }

    return victor;
  }

  /**
   * Fights two player instances, returning whichever received the least damage.
   *
   * @param playerOne the first contestant in the duel
   * @param playerTwo the second contestant in the duel
   * @return the victorious player
   */
  private IPlayer duel(IPlayer playerOne, IPlayer playerTwo) {
    int playerOneDamage = playerOne.getAttack() - playerTwo.getDefense();
    int playerTwoDamage = playerTwo.getAttack() - playerOne.getDefense();

    return playerOneDamage > playerTwoDamage ? playerOne : playerTwo;
  }
}
