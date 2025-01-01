package no.uib.inf101.studentvslife.model.entity.student;

/**
 * The Student class represents a student entity in the game.
 * It extends the Entity class and inherits its properties and behaviors.
 */

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.studentvslife.model.entity.Entity;

public class Student extends Entity {

  /**
   * Constructs a Student object with the specified position and character
   * representation.
   * 
   * @param pos       The position of the student.
   * @param character The character representation of the student.
   * @throws IllegalArgumentException If the character representation is not 'S'
   *                                  or 'B'.
   */

  public Student(CellPosition pos, char character) {
    super(pos, character);
    if (character != 'S' && character != 'B') {
      throw new IllegalArgumentException("Character representation must be 'S' or 'B'.");
    }
  }
}
