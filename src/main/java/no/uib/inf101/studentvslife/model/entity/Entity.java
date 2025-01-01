package no.uib.inf101.studentvslife.model.entity;

import no.uib.inf101.grid.CellPosition;

/**
 * The abstract base class for entities in the game, both students and life game objects.
 * Entities represent objects with various characteristics such as toughness,
 * effect, price, position, and character representation.
 */

 public abstract class Entity implements IEntity {

    protected int toughness; 
    protected int effect; 
    protected char character; 
    protected CellPosition pos;
    private int price;

    /**
     * Constructs an Entity object with the given position and character representation.
     * Initializes the entity's attributes based on its character representation.
     *
     * @param pos       The position of the entity.
     * @param character The character representation of the entity.
     */

    protected Entity(CellPosition pos, char character) {
        int[] stats = getEntityStats(pos, character);

        this.toughness = stats[0];
        this.effect = stats[1];
        this.price = stats[2];
        this.character = character;
        this.pos = pos;
    }

    /**
     * Retrieves the statistics (toughness, effect, and price) of an entity based on its character representation.
     *
     * @param pos       The position of the entity.
     * @param character The character representation of the entity.
     * @return An array containing the entity's toughness, effect, and price.
     * @throws IllegalArgumentException If the character representation is not valid.
     */

    private static int[] getEntityStats(CellPosition pos, char character) {
        int toughness = 300;
        int effect = 0;
        int price = 0;
       
        switch(character) {
            case 'S':
                price = 50;
                break;
            case 'E':
                effect = 50;
                price = 150;
                break;
            case 'B':
                price = 50;
                effect = 1;
                break;
            case 'G':
                effect = 25;
                price = 100;
                break;
            case 'X':
                effect = 100;
                toughness = 250;
                break;
            default:
                throw new IllegalArgumentException("Entity not valid");
        }
        return new int[] {toughness, effect, price};
    }

    @Override
    public int getToughness() {
        return this.toughness;
    }

    @Override
    public int getEffect() {
        return this.effect;
    }

    @Override
    public CellPosition getPos() {
        return this.pos;
    }

    @Override
    public char getChar() {
        return this.character;
    }

    @Override
    public boolean takeDamage(int damage) {
    if (damage < 0) {
        throw new IllegalArgumentException("Damage cannot be negative");
    }
    this.toughness -= damage;
    return this.toughness > 0;
}

    @Override
    public int getPrice() {
        return this.price;
    }
}
