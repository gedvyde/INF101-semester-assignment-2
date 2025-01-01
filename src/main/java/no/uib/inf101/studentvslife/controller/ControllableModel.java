package no.uib.inf101.studentvslife.controller;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.studentvslife.model.Gamestate;

/**
 * The ControllableModel interface defines methods that allow controlling the
 * game model. These methods are used by the controller to interact with the
 * game model.
 */

public interface ControllableModel {

  /**
   * Retrieves the timer delay for a specific entity. S : Student L : Life B :
   * Bullet
   *
   * @param character The character representing first letter in entity.
   * @return The timer delay in milliseconds.
   */
  int getTimerDelay(char c);

  /**
   * The method will be called each time the clock ticks. Moves Life entity one
   * col left if possible, spawns new life entities and triggers both level
   * upgrades and gameovers.
   */
  void clockTickLife();

  /**
   * The method will be called each time the clock ticks. All students generate
   * sleep (player money) and create bullets fired by active students.
   */

  void clockTickStudent();

  /**
   * The method will be called each time the clock ticks. Moves bullets of all
   * active students in sync with cellScale. Detects collision with attackers.
   */

  void clockTickBullets();

  /**
   * Sets the character type to be used for placing entities on the game board.
   *
   * @param character The character representing the entity type.
   * @throws IllegalArgumentException if character is not legal.
   */
  void setChar(char character);

  /**
   * Glues a student entity to the specified cell position on the game board.
   *
   * @param pos The cell position where the student should be placed.
   */

  void glueStudent(CellPosition pos);

  /**
   * Changes gamestate to next appropriate gamestate
   */
  void nextGamestate();

  /**
   * Retrieves the current game state.
   *
   * @return The current state of the game, represented by a Gamestate enum value.
   */
  Gamestate getGamestate();



  /**
   * Retrieves the current level of the player.
   *
   * @return The current level of the player.
   */
  int getLvl();
}
