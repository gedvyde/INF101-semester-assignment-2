package no.uib.inf101.studentvslife.model.entity.life;

/**
 * The LifeFactory interface represents a factory for creating life entities in
 * the game. It defines a method for generating the next life entity.
 */

public interface LifeFactory {

  /**
   * Generates the next random life entity.
   *
   * @return A new Life object with a random position and shape.
   */
  Life getNext();

}
