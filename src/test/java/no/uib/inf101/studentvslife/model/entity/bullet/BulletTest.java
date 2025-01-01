package no.uib.inf101.studentvslife.model.entity.bullet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import no.uib.inf101.grid.CellPosition;

public class BulletTest {

  Bullet bullet;

  @BeforeEach
  void setUp() {
    this.bullet = new Bullet(10, 20, new CellPosition(0, 0), 'G');
  }

  @Test
  void testConstructor() {
    try {
      new Bullet(10, 20, new CellPosition(0, 0), 'F');
    } catch (IllegalArgumentException e) {
      return;
    } catch (Exception e) {
      fail("Should throw an IllegalArgumentException");
    }
  }

  @Test
  void testGetChar() {
    assertEquals('C', bullet.getChar());
  }

  @Test
  void testGetDamage() {
    assertEquals(20, bullet.getDamage());
  }

  @Test
  void testGetPos() {
    assertEquals(new CellPosition(0, 0), bullet.getPos());
  }

  @Test
  void testGetPosInCell() {
    assertEquals(10, bullet.getPosInCell());
  }

  @Test
  void testIncreasePosInCell() {
    assertEquals(10, bullet.getPosInCell());
    assertEquals(new CellPosition(0, 0), bullet.getPos());
    bullet.increasePosInCell(10);
    assertEquals(11, bullet.getPosInCell());
    assertEquals(new CellPosition(0, 1), bullet.getPos());
  }
}
