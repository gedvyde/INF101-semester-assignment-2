package no.uib.inf101.studentvslife.model.entity.bullet;

import no.uib.inf101.grid.CellPosition;

public interface IBullet {

  /**
   * Retrieves the position of the bullet.
   *
   * @return The position of the bullet.
   */
  public CellPosition getPos();

  /**
   * Retrieves the position of the bullet in the grid cell.
   *
   * @return The position of the bullet in the grid cell.
   */
  public int getPosInCell();

  /**
   * Retrieves the character representation of bullet
   *
   * @return The character representation of bullet.
   */
  public char getChar();

  /**
   * Retrieves the damage inflicted by the bullet.
   *
   * @return The damage inflicted by the bullet.
   */
  public int getDamage();

  /**
   * Increases the position of the bullet in the grid cell by one unit and updates
   * its position according to cellScale
   *
   * @param cellScale The scaling factor that cells in model are split into.
   */
  public void increasePosInCell(int cellScale);

}
