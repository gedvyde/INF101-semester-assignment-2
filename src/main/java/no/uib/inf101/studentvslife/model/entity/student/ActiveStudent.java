package no.uib.inf101.studentvslife.model.entity.student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.studentvslife.model.entity.Entity;
import no.uib.inf101.studentvslife.model.entity.bullet.Bullet;

/**
 * The ActiveStudent class represents an active student entity in the game. It
 * extends the Entity class and inherits its properties and behaviors, in
 * addition to activly shooting bullets.
 * 
 * Notice in model Active students are restricted to only shoot one bullet at
 * time.
 */

public class ActiveStudent extends Entity {

  private List<Bullet> bullets;

  /**
   * Constructs an ActiveStudent object with the specified position and character
   * representation.
   * 
   * @param pos       The position of the active student.
   * @param character The character representation of the active student.
   * @throws IllegalArgumentException If the character representation is not 'E'
   *                                  or 'G'.
   */
  public ActiveStudent(CellPosition pos, char character) {
    super(pos, character);

    if (character != 'E' && character != 'G') {
      throw new IllegalArgumentException("ActiveStudent can only have character 'E' or 'G'");
    }
    this.bullets = new ArrayList<>();
  }

  /**
   * Retrieves the list of bullets fired by the active student.
   *
   * @return The list of bullets fired by the active student.
   */
  public List<Bullet> getBullets() {
    return Collections.unmodifiableList(this.bullets);
  }

   /**
   * Removes first bullet of student
   */

  public void removeBullet() {
    if (bullets.size() > 0) {
      this.bullets.remove(0);
    }

  }

  /**
   * Adds a new bullet fired by the active student.
   *
   * @param cellScale The scaling factor that cells in model are split into.
   */
  public void addBullet(int cellScale) {
    this.bullets.add(new Bullet((this.pos.col() + 1) * cellScale, this.effect, this.pos, this.character));
  }

}
