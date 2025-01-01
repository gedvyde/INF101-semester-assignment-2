package no.uib.inf101.studentvslife.model.entity.student;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;

import no.uib.inf101.grid.CellPosition;

  // All method extended from Enitty class are tested in StudentTest

public class ActiveStudentTest {

  ActiveStudent stu;

  @BeforeEach
  void setUp() {
    stu = new ActiveStudent(new CellPosition(0, 3), 'E');
  }

  @Test
  void testConstructor() {
    try {
      new ActiveStudent(new CellPosition(0, 0), '-');
    } catch (IllegalArgumentException e) {
      return;
    } catch (Exception e) {
      fail("Should throw an IllegalArgumentException");
    }
  }

  @Test
  void testAddBullet() {
    stu.addBullet(10);
    assertEquals(1, stu.getBullets().size());
    stu.addBullet(10);
    assertEquals(2, stu.getBullets().size());
  }

  @Test
  void testBulletPosInCell() {
    int cellScale = 20;
    stu.addBullet(cellScale);
    int initialBulletPosInCell = cellScale * (stu.getPos().col()+1);
    assertEquals(initialBulletPosInCell, stu.getBullets().get(0).getPosInCell());
  }

  @Test
  void testRemoveBullet() {
    int b1 = 20;
    int b2 = 10;
    stu.addBullet(b1);
    stu.addBullet(b2);
    stu.removeBullet();
    
    int bulletPosInCell = b2 * (stu.getPos().col()+1);
    assertEquals(1, stu.getBullets().size());
    assertEquals(bulletPosInCell, stu.getBullets().get(0).getPosInCell());

    stu.removeBullet();
    stu.removeBullet();
    stu.removeBullet();
    assertEquals(0, stu.getBullets().size());
  }
}