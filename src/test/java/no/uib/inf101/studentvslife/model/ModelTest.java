package no.uib.inf101.studentvslife.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.studentvslife.model.entity.bullet.Bullet;
import no.uib.inf101.studentvslife.model.entity.life.LifeFactory;
import no.uib.inf101.studentvslife.model.entity.life.RandomLifeFactory;
import no.uib.inf101.studentvslife.model.entity.student.ActiveStudent;
import no.uib.inf101.studentvslife.model.entity.student.Student;

public class ModelTest {

  int rows;
  int cols;
  Board board;
  LifeFactory fact;
  Model model;

  @BeforeEach
  void setUp() {
    this.rows = 1;
    this.cols = 3;
    this.board = new Board(rows, cols);
    this.fact = new RandomLifeFactory(rows, cols);
    this.model = new Model(board, fact);
  }

  @Test
  void testClockTickBullets() {
    // Generate attacker so one of the students generate a bullet
    model.clockTickLife();
    assertEquals(1, model.getAttackers().size());
    // Glue student
    int col = 0;
    CellPosition pos1 = new CellPosition(0, col);
    model.glueStudent(pos1);
    assertEquals(0, model.getBullets().size());
    // Genrate bullet
    model.clockTickStudent();
    assertEquals(1, model.getBullets().size());
    Bullet bullet = model.getBullets().get(0);
    assertEquals(pos1, bullet.getPos());
    // Bullet initial pos is always right infront of student cell
    int posIncell = (col + 1) * model.getCellScale();
    assertEquals(posIncell, bullet.getPosInCell());
    // Move bullet
    model.clockTickBullets();
    posIncell += 1;
    assertEquals(posIncell, bullet.getPosInCell());
    assertEquals(1, model.getBullets().size());
    // Bullet hits attacker
    int bulletEffect = bullet.getDamage();
    int initalTougness = model.getAttackers().get(0).getToughness();
    assertEquals(250, initalTougness);
    for (int i = 0; i < model.getCellScale(); i++) {
      assertEquals(posIncell + i, model.getBullets().get(0).getPosInCell());
      model.clockTickBullets();
    }
    int hitTougness = model.getAttackers().get(0).getToughness();
    assertEquals(0, model.getBullets().size());
    assertEquals(hitTougness + bulletEffect, initalTougness);
  }


  

  @Test
  void testClockTickLife() {
    // Generate attacker
    model.clockTickLife();
    assertEquals(1, model.getAttackers().size());
    // Glue student and count students on board
    CellPosition pos1 = new CellPosition(0, 0);
    model.glueStudent(pos1);
    int stuTougness = 300;
    int lifeDamage = 100;
    int prevStuCount = countStudents(model);
    // Moves attacker in front of student
    model.clockTickLife(); 
    int requiredNumAttacts = stuTougness / lifeDamage;
    for (int i = 0; i < requiredNumAttacts; i++) {
      model.clockTickLife();
    }
    assertEquals(prevStuCount - 1, countStudents(model));
  }

  private int countStudents(Model model) {
    int countStu = 0;
    Iterator<GridCell<Character>> it = model.getBoardTiles().iterator();
    while (it.hasNext()) {
      if (it.next().elem().equals('G')) {
        countStu += 1;
      }
    }
    return countStu;
  }

  @Test
  void testClockTickStudent() {
    // Initial money is correct
    int money = 200;
    char c = 'G';
    CellPosition pos = new CellPosition(0, 0);
    int studentPrice = new ActiveStudent(pos, c).getPrice();
    assertEquals(money, model.getMoney());

    // Money reduces after glue student
    model.setChar(c);
    model.glueStudent(pos);
    money -= studentPrice;
    assertEquals(money, model.getMoney());

    // No attackers on board, bullet count is zero
    int numBullets = model.getBullets().size();
    assertEquals(0, numBullets);
    model.clockTickLife(); // Need to be enemy in row for student to produce bullet
    model.clockTickStudent();
    numBullets = model.getBullets().size();
    assertEquals(1, numBullets);
 
    // Places new student
    c = 'B';
    pos = new CellPosition(0, 2);
    studentPrice = new Student(pos, c).getPrice();
    int studentEffect = new Student(pos, c).getEffect();

    model.setChar(c);
    model.glueStudent(pos);
    model.clockTickStudent(); // Produces sleep

    money = money - studentPrice + studentEffect;
    assertEquals(money, model.getMoney());
  }

  @Test
  void testGetAttackers() {
    assertEquals(0, model.getAttackers().size());
    model.clockTickLife();
    assertEquals(1, model.getAttackers().size());
  }

  @Test
  void testGetBoardTiles() {
    CellPosition pos = new CellPosition(0, 0);
    model.glueStudent(pos);
    assertEquals('G', model.getBoardTiles().iterator().next().elem());
  }

  @Test
  void testGetBullets() {
    model.clockTickLife();
    model.glueStudent(new CellPosition(0, 0));
    model.clockTickStudent();
    assertEquals(1, model.getBullets().size());

    try {
      model.getBullets().remove(0);
    } catch (UnsupportedOperationException e) {
      return;
    } catch (Exception e) {
      fail("Should throw an UnsupportedOperationException");
    }
  }

  @Test
  void testGetCellScale() {
    assertEquals(10, model.getCellScale());
  }

  @Test
  void testGetDimension() {
    assertEquals(cols, model.getDimension().cols());
    assertEquals(rows, model.getDimension().rows());
  }

  @Test
  void testGetGamestate() {
    Gamestate initialGamestate = Gamestate.WELCOME;
    assertEquals(initialGamestate, model.getGamestate());
  }

  @Test
  void testGetKontAttempts() {
    assertEquals(3, model.getKontAttempts());
    model.clockTickLife();
    model.clockTickLife();
    model.clockTickLife();
    model.clockTickLife();
    assertEquals(2, model.getKontAttempts());
  }

  @Test
  void testGetLvl() {
    assertEquals(0, model.getLvl());
  }

  @Test
  void testGetMoney() {
    assertEquals(200, model.getMoney());
    model.glueStudent(new CellPosition(0, 0));
    assertEquals(100, model.getMoney());
  }

  @Test
  void testGetTimerDelay() {
    assertEquals(2000, model.getTimerDelay('L'));
    assertEquals(200, model.getTimerDelay('S'));
    assertEquals(10, model.getTimerDelay('B'));
    assertEquals(0, model.getTimerDelay('Æ'));

  }

  @Test
  void testGlueStudent() {
    int stuCount = countStudents(model);
    model.glueStudent(new CellPosition(0, 0));
    stuCount += 1;

    assertEquals(stuCount, countStudents(model));

    // Cant glue student on cell pos outside board
    model.glueStudent(new CellPosition(5, 5));
    assertEquals(stuCount, countStudents(model));

    // Cant glue new student to same pos
    model.glueStudent(new CellPosition(0, 0));
    assertEquals(stuCount, countStudents(model));

    model.glueStudent(new CellPosition(0, 1));
    stuCount += 1;
    assertEquals(stuCount, countStudents(model));

    // Cant glue student without money
    assertEquals(0, model.getMoney());
    model.glueStudent(new CellPosition(0, 2));
    assertEquals(stuCount, countStudents(model));
  }

  @Test
  void testSetChar() {
    model.setChar('S');
    CellPosition pos = new CellPosition(0, 0);
    model.glueStudent(pos);
    assertEquals('S', model.getBoardTiles().iterator().next().elem());

    try {
      model.setChar('-');
    } catch (IllegalArgumentException e) {
      return;
    } catch (Exception e) {
      fail("Should throw an IllegalArgumentException");
    }
  }

  @Test
  void testNextGamestate() {
    model.nextGamestate();
    Gamestate secondGamestate = Gamestate.INFO;
    assertEquals(secondGamestate, model.getGamestate());
    model.nextGamestate();
    Gamestate thridGamestate = Gamestate.ACTIVE_GAME;
    assertEquals(thridGamestate, model.getGamestate());
  }
}
