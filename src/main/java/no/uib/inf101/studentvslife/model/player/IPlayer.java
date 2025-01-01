package no.uib.inf101.studentvslife.model.player;

/**
 * The IPlayer interface represents a player in the game. It defines methods for
 * managing the player's attributes such as money, level, and attempts.
 */
public interface IPlayer {

  /**
   * @return The number of attempts remaining for the player.
   */
  int getKonteAttempts();

  /**
   * Updates the player's attributes when the game is over. Resets the number of
   * attempts and sets the level to 0 if no attempts remaining; otherwise,
   * decrements the number of attempts.
   */
  void gameover();

  /**
   * Checks if the player can afford the specified price.
   *
   * @param price The price to check.
   * @return True if the player can afford the price; otherwise, false.
   */
  boolean money(int price);

  /**
   * Retrieves the amount of money the player has.
   *
   * @return The amount of money the player has.
   */
  int getMoney();

  /**
   * Retrieves the level of the player.
   *
   * @return The level of the player.
   */
  int getLvl();

  /**
   * Increases the player's level by 1.
   */
  void lvlUp();

  /**
   * Retrieves the maximum number of attackers allowed for the current level.
   *
   * @return The maximum number of attackers allowed for the current level.
   */
  int getMaxAttackers();

  /**
   * Retrieves the number of attackers present in the current level.
   *
   * @return The number of attackers present in the current level.
   */
  int getAttackersInLvl();
}
