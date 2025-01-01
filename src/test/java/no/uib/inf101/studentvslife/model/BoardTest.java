package no.uib.inf101.studentvslife.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.studentvslife.model.entity.life.Life;
import no.uib.inf101.studentvslife.model.entity.student.Student;

public class BoardTest {
 
	Board board;
  Student stu;
  Life life;
	

	@BeforeEach
	void setUp() {
		board = new Board(7, 4);
    stu = new Student(new CellPosition(0, 0), 'S');
	}

	@Test
	void testGameBoard() {
		assertEquals(7, board.rows());
		assertEquals(4, board.cols());
	}

	@Test
	void testSetCharacter() {
    board.set(stu.getPos(), stu.getChar());
		assertEquals(stu.getChar(), board.get(stu.getPos()));
	}
}
