package no.uib.inf101.studentvslife.model.entity.bullet;

import no.uib.inf101.grid.CellPosition;

/**
 * The Bullet class represents a bullet entity in the game. It implements the
 * IBullet interface.
 */

public class Bullet implements IBullet {
  private int posInCell;
  private int damge;
  private CellPosition pos;
  private char character;

  /**
   * Constructs a Bullet object with the specified parameters.
   *
   * @param posInCell The initial x-coordinate of the bullet.
   * @param damage    The damage inflicted by the bullet.
   * @param pos       The initial position of the bullet.
   * @param character The character representing the type of bullet.
   */
  public Bullet(int posInCell, int damage, CellPosition pos, char character) {
    this.posInCell = posInCell;
    this.damge = damage;
    this.pos = pos;
    this.character = switch (character) {
    case 'G' -> 'C';
    case 'E' -> 'P';
    default -> throw new IllegalArgumentException("No available bullet for '" + character + "'");
    };
  }

  private void updatePos(int cellScale) {
    CellPosition newPos = new CellPosition(this.pos.row(), this.posInCell / cellScale);
    this.pos = newPos;
  }

  @Override
  public void increasePosInCell(int cellScale) {
    this.posInCell += 1;
    updatePos(cellScale);
  }

  @Override
  public int getDamage() {
    return this.damge;
  }

  @Override
  public int getPosInCell() {
    return this.posInCell;
  }

  @Override
  public CellPosition getPos() {
    return this.pos;
  }

  @Override
  public char getChar() {
    return this.character;
  }
}
