package no.uib.inf101.studentvslife.view;

import java.util.ArrayList;
import java.util.List;

import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;
import no.uib.inf101.studentvslife.model.Gamestate;
import no.uib.inf101.studentvslife.model.entity.IEntity;
import no.uib.inf101.studentvslife.model.entity.bullet.Bullet;

/**
 * The ViewableModel interface represents the view component of the game model,
 * providing methods to retrieve information about the game state, entities, and
 * game board. This interface allows other components, such as the game view, to
 * access the necessary data for rendering the game and updating the user
 * interface.
 */

public interface ViewableModel {

  /**
   * Retrieves the remaining kont attemts of the player.
   *
   * @return The number of remaining kont attemts of the player.
   */
  int getKontAttempts();

  /**
   * Retrieves an iterable containing all cells of the game board.
   *
   * @return An iterable containing all cells of the game board.
   */
  Iterable<GridCell<Character>> getBoardTiles();

  /**
   * Retrieves the grid dimension of the game board.
   *
   * @return The grid dimension of the game board.
   */
  GridDimension getDimension();

  /**
   * Retrieves the scale of each cell on the game board.
   *
   * @return The scale of each cell on the game board.
   */
  int getCellScale();

  /**
   * Retrieves a list of all bullets fired by active students on the game board.
   *
   * @return A list of all bullets fired by active students on the game board.
   */
  List<Bullet> getBullets();

  /**
   * Retrieves a list of all attackers (life entities) on the game board.
   *
   * @return A list of all attackers (life entities) on the game board.
   */
  ArrayList<IEntity> getAttackers();

  /**
   * Retrieves the current level of the player.
   *
   * @return The current level of the player.
   */
  int getLvl();

  /**
   * Retrieves the current amount of money possessed by the player.
   *
   * @return The current amount of money possessed by the player.
   */
  int getMoney();

  /**
   * Retrieves the current game state.
   *
   * @return The current state of the game, represented by a Gamestate enum value.
   */
  Gamestate getGamestate();

}
