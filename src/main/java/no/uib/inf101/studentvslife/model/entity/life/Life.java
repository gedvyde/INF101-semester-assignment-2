package no.uib.inf101.studentvslife.model.entity.life;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.studentvslife.model.entity.Entity;

/**
 * The Life class represents a life entity in the game, the games attackers. It
 * extends the Entity class and inherits its properties and behaviors.
 */

public class Life extends Entity {

  /**
   * Constructs a Life object with the specified position and character
   * representation.
   *
   * @param pos       The position of the life entity.
   * @param character The character representation of the life entity.
   */
  Life(CellPosition pos, char character) {
    super(pos, character);


  }

  /**
   * Creates a new Life object with its position shifted by one cell to the left
   * 
   * @return A new Life object with the shifted position.
   */
  public Life moveLife() {
    int col = this.pos.col() -1;
    CellPosition shiftedPos = new CellPosition(this.pos.row(), col);
    Life shiftedAttacker = new Life(shiftedPos, this.character);
    return shiftedAttacker;
  }

}
