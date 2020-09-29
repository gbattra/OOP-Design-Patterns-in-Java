package rpg.interfaces;

import java.util.List;

/**
 * Interface for a battle object.
 */
public interface IBattle {
  /**
   * Get the players in the battle.
   *
   * @return the list of players
   */
  List<IPlayer> getPlayers();

  /**
   * Fight the players against one another and return the victor.
   *
   * @return the winning player
   */
  IPlayer fight() throws IllegalStateException;

  /**
   * Add a player to the battle.
   *
   * @return new battle instance with updated player list
   */
  IBattle addPlayer(IPlayer player) throws IllegalStateException;
}
