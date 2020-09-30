package rpg.models;

import java.util.ArrayList;
import java.util.List;

import rpg.interfaces.IBattle;
import rpg.interfaces.IGear;
import rpg.interfaces.IPlayer;

/**
 * A class representing a battle between multiple players.
 */
public class Battle implements IBattle {
  private static final int PLAYER_COUNT_MIN = 2;
  private final List<IPlayer> players;
  private final List<IGear> gears;
  private final int playerCount;
  private final int gearCount;

  /**
   * Constructor for a battle instance.
   *
   * @param playerCount the number of players participating in the battle
   * @throws IllegalArgumentException when playerCount <= 0 is provided
   */
  public Battle(
          int playerCount,
          int gearCount) throws IllegalArgumentException {
    if (playerCount < PLAYER_COUNT_MIN) {
      throw new IllegalArgumentException(
              String.format("Player count must be at least %s", PLAYER_COUNT_MIN));
    }
    this.playerCount = playerCount;
    this.gearCount = gearCount;
    this.players = new ArrayList<>();
    this.gears = new ArrayList<>();
  }

  /**
   * Constructor for a battle instance.
   *
   * @param playerCount the number of players participating in the battle
   * @param players the players participating in the battle
   * @throws IllegalArgumentException when playerCount <= 0 is provided
   */
  private Battle(
          int playerCount,
          int gearCount,
          List<IPlayer> players,
          List<IGear> gears) throws IllegalArgumentException {
    if (playerCount <= 0) {
      throw new IllegalArgumentException("Player count must be greater than zero.");
    }

    if (players.size() > playerCount) {
      throw new IllegalArgumentException("Provided players list exceeds player count for battle.");
    }

    if (gears.size() > gearCount) {
      throw new IllegalArgumentException("Provided gear list exceeds gear count for battle.");
    }

    this.playerCount = playerCount;
    this.gearCount = gearCount;
    this.players = players;
    this.gears = gears;
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
   * Returns the list of gear items set on this battle instance.
   *
   * @return the list of IGear instances
   */
  public List<IGear> getGears() {
    return this.gears;
  }

  /**
   * Adds a gear to the battle's gear list. Will be used to dress players before
   * the fight.
   *
   * @param gear the IGear instance to add
   * @return a new updated IBattle instance with new gear list
   * @throws IllegalStateException when the max number of gears has already been reached
   */
  public IBattle addGear(IGear gear) throws IllegalStateException {
    if (this.gears.size() == this.gearCount) {
      throw new IllegalStateException(
              "Max number of gear for this battle has already been reached.");
    }

    List<IGear> gearsCopy = new ArrayList<>(this.gears);
    gearsCopy.add(gear);

    return new Battle(
            this.playerCount,
            this.gearCount,
            this.players,
            gearsCopy);
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
    return new Battle(
            this.playerCount,
            this.gearCount,
            newPlayers,
            this.gears);
  }

  /**
   * Dresses players from the list of gears provided to give each the best shot at winning.
   *
   * @return a new IBattle instance with dressed players
   */
  public IBattle dressPlayers() {
    List<IPlayer> dressedPlayers = this.dressPlayers(this.players, this.gears);

    return new Battle(
            this.playerCount,
            this.gearCount,
            dressedPlayers,
            this.gears);
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
   * Recursive function which pops the first player off the list, determines the best gear for
   * that player. Adds that gear to the player and removes it from the list. Then adds the player
   * back to the list. If more gears remain, call itself with updated lists. Else return players.
   *
   * @param players the players to dress
   * @param gears the gears with which to dress the players
   * @return the dressed players
   * @throws IllegalArgumentException when either players list or gears list is empty
   */
  private List<IPlayer> dressPlayers(
          List<IPlayer> players,
          List<IGear> gears) throws IllegalArgumentException {
    if (players.isEmpty() || gears.isEmpty()) {
      throw new IllegalArgumentException("Player list or gear list is empty.");
    }
    List<IPlayer> playersCopy = new ArrayList<>(players);
    List<IGear> gearsCopy = new ArrayList<>(gears);

    IPlayer player = playersCopy.get(0);
    playersCopy.remove(0);

    int bestGearIndex = this.getBestGearIndex(player, gearsCopy);
    IGear bestGear = gearsCopy.get(bestGearIndex);
    gearsCopy.remove(bestGearIndex);

    IPlayer updatedPlayer = player.addGear(bestGear);
    playersCopy.add(updatedPlayer);

    if (!gearsCopy.isEmpty()) {
      return this.dressPlayers(playersCopy, gearsCopy);
    }

    return playersCopy;
  }

  /**
   * Determines the best gear for the player given the list of IGear instances.
   *
   * @param player the player to dress
   * @param gears the gears from which to choose
   * @return the index of the best gear from the list of gears
   */
  private int getBestGearIndex(IPlayer player, List<IGear> gears) {
    return 0;
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
