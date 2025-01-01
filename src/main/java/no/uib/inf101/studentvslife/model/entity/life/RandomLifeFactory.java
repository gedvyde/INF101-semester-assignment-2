package no.uib.inf101.studentvslife.model.entity.life;

import java.util.Random;

import no.uib.inf101.grid.CellPosition;

/**
 * The RandomLifeFactory class is responsible for creating random life entities
 * in the game. It implements the LifeFactory interface and provides a method to
 * generate the next random life entity.
 */

public class RandomLifeFactory implements LifeFactory {

  private int rows;
  private int cols;

  /**
   * Constructs a RandomLifeFactory with the specified number of rows and columns
   * in the grid.
   *
   * @param rows The number of rows in the grid.
   * @param cols The number of columns in the grid.
   */
  public RandomLifeFactory(int rows, int cols) {
    this.rows = rows;
    this.cols = cols;
  }

  @Override
  public Life getNext() {
    Random rnd = new Random();
    Character possibleChar = 'X';
    int row = rnd.nextInt(this.rows);
    Life life = new Life(new CellPosition(row, this.cols - 1), possibleChar);
    return life;

  }

}
