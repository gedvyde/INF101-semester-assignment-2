package no.uib.inf101.studentvslife.model;

import no.uib.inf101.grid.Grid;

/**
 * The Board class represents the game board for the "Student vs Life" game.
 */

public class Board extends Grid<Character> {

  /**
   * Constructs a new empty Board object with the specified number of rows and
   * columns.
   *
   * @param rows The number of rows in the board.
   * @param cols The number of columns in the board.
   */

  public Board(int rows, int cols) {
    super(rows, cols, '-');
  }


}
