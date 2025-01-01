package no.uib.inf101.studentvslife.model.entity.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import no.uib.inf101.grid.CellPosition;

// Test all public method from abstract entity class.

public class StudentTest {

  Student stu;

  @BeforeEach
  void setUp() {
    stu = new Student(new CellPosition(0, 3), 'B');
  }

  @Test
  void testConstructor() {
    try {
      new ActiveStudent(new CellPosition(0, 0), 'Ø');
    } catch (IllegalArgumentException e) {
      return;
    } catch (Exception e) {
      fail("Should throw an IllegalArgumentException");
    }
  }


  @Test
  void testGetChar() {
    assertEquals('B', stu.getChar());

  }
  @Test
  void testTakeDamage() {
    assertEquals(300, stu.getToughness());
    stu.takeDamage(50);
    assertEquals(250, stu.getToughness());
    try {
      stu.takeDamage(-50);
    } catch (IllegalArgumentException e) {
      return;
    } catch (Exception e) {
      fail("Should throw an IllegalArgumentException");
    }
  }

  @Test
  void testGetEffect() {
    assertEquals(1, stu.getEffect());
  }

  @Test
  void testGetPos() {
    assertEquals(new CellPosition(0, 3), stu.getPos());

  }

  @Test
  void testGetPrice() {
    assertEquals(50, stu.getPrice());
  }

  @Test
  void testGetToughness() {
    assertEquals(300, stu.getToughness());
  }
}
