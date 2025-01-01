package no.uib.inf101.studentvslife.model.entity;

import no.uib.inf101.grid.CellPosition;

public interface IEntity {

  /**
   * Retrieves the toughness of the entity.
   *
   * @return The toughness of the entity.
   */
  public int getToughness();

  /**
   * Retrieves the effect of the entity.
   * 
   * @return The effect of the entity.
   */
  public int getEffect();

  /**
   * Retrieves the position of the entity.
   *
   * @return The position of the entity.
   */
  public CellPosition getPos();

  /**
   * Retrieves the character representation of the entity.
   *
   * @return The character representation of the entity.
   */
  public char getChar();

  /**
   * Inflicts damage on the entity, reducing its toughness. Throws an
   * IllegalArgumentException if the damage is negative (attempt to increase
   * toughness).
   *
   * @param damage The amount of damage to be inflicted.
   * @return True if the entity is still alive (toughness > 0), false otherwise.
   * @throws IllegalArgumentException If the damage is negative.
   */
  public boolean takeDamage(int damage);

  /**
   * Retrieves the price of the entity.
   *
   * @return The price of the entity.
   */
  public int getPrice();
}
