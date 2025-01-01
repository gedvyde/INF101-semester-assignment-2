package no.uib.inf101.studentvslife.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;
import no.uib.inf101.studentvslife.controller.ControllableModel;
import no.uib.inf101.studentvslife.model.entity.IEntity;
import no.uib.inf101.studentvslife.model.entity.bullet.Bullet;
import no.uib.inf101.studentvslife.model.entity.life.Life;
import no.uib.inf101.studentvslife.model.entity.life.LifeFactory;
import no.uib.inf101.studentvslife.model.entity.student.ActiveStudent;
import no.uib.inf101.studentvslife.model.entity.student.Student;
import no.uib.inf101.studentvslife.model.player.Player;
import no.uib.inf101.studentvslife.view.ViewableModel;

public class Model implements ViewableModel, ControllableModel {

  private Board board;
  private ArrayList<Student> students;
  private ArrayList<ActiveStudent> activeStudents;
  private ArrayList<ArrayList<Life>> attackers;
  private char character;

  private int cellScale;
  private LifeFactory factory;
  private int attackersOnBoard;
  private Player player;
  private Gamestate gamestate;
  private int attackerCountDown;

  public Model(Board board, LifeFactory factory) {
    this.board = board;
    this.factory = factory;
    this.player = new Player();
    this.gamestate = Gamestate.WELCOME;
    initializeModel();
  }

  /////////////
  // CONTROLLER
  /////////////

  @Override
  public void glueStudent(CellPosition pos) {
    if (legalMove(pos)) {
      if (character == 'E' || character == 'G') {
        ActiveStudent student = new ActiveStudent(pos, character);
        if (player.money(-student.getPrice())) {
          activeStudents.add(student);
          board.set(pos, character);
        }
      } else {
        Student student = new Student(pos, character);
        if (player.money(-student.getPrice())) {
          students.add(student);
          board.set(pos, character);
        }
      }
    }
  }

  @Override
  public void setChar(char character) {
  String legalStudents = "GBES";
  if (legalStudents.contains("" + character)) {
      this.character = character;
  } else {
      throw new IllegalArgumentException("Character is not legal");
  }
}

  @Override
  public void clockTickLife() {
    moveAttackers();

    if (attackerCountDown <= 0) {
      lvlUp();
    }
  }

  @Override
  public void clockTickStudent() {
    generateSleep();
    generateBullets();
  }

  @Override
  public void clockTickBullets() {
    moveBullets();
  }

  @Override
  public int getTimerDelay(char character) {
    switch (character) {
    case 'S':
      return 200;
    case 'L':
      return 2000;
    case 'B':
      return 10;
    default:
      return 0;
    }
  }

  @Override
  public void nextGamestate() {
    if (getGamestate().equals(Gamestate.WELCOME)) {
      this.gamestate = Gamestate.INFO;
    } else if (getGamestate().equals(Gamestate.INFO)) {
      initializeModel();
      this.gamestate = Gamestate.ACTIVE_GAME;
    } else if (getGamestate().equals(Gamestate.GAME_OVER)) {
      initializeModel();
      this.gamestate = Gamestate.ACTIVE_GAME;
    } else if (getGamestate().equals(Gamestate.WIN)) {
      initializeModel();
      this.gamestate = Gamestate.ACTIVE_GAME;
    } else if (getGamestate().equals(Gamestate.KONT)) {
      initializeModel();
      this.gamestate = Gamestate.ACTIVE_GAME;
    }
  }

  //////////
  // VIEW //
  //////////

  @Override
  public Gamestate getGamestate() {
    return this.gamestate;
  }

  @Override
  public int getLvl() {
    return player.getLvl();
  }

  @Override
  public int getMoney() {
    return player.getMoney();
  }

  @Override
  public int getKontAttempts() {
    return player.getKonteAttempts();
  }

  @Override
  public GridDimension getDimension() {
    return this.board;
  }

  @Override
  public Iterable<GridCell<Character>> getBoardTiles() {
    return board.getCells();
  }


  @Override
  public ArrayList<IEntity> getAttackers() {
    ArrayList<IEntity> cells = new ArrayList<>();
    for (int i = 0; i < attackers.size(); i++) {
      cells.addAll(attackers.get(i));

    }
    return cells;
  }

  @Override
  public List<Bullet> getBullets() {
    List<Bullet> bullets = new ArrayList<>();
    for (ActiveStudent stu : activeStudents) {
      bullets.addAll(stu.getBullets());
    }
    return Collections.unmodifiableList(bullets);
  }

  @Override
  public int getCellScale() {
    return this.cellScale;
  }

  ///////////////
  // HELP METHODS
  ///////////////

  // Adds game currency to player money
  private void generateSleep() {
    for (Student stu : students) {
      player.money(stu.getEffect());
    }
  }

  private boolean bulletHitAttacker(Bullet bullet) {
    int row = bullet.getPos().row();
    int col = bullet.getPos().col();
    Iterator<Life> attackersInRow = attackers.get(row).iterator();

    while (attackersInRow.hasNext()) {
      Life attacker = attackersInRow.next();
      if (col == attacker.getPos().col()) {
        if (!attacker.takeDamage(bullet.getDamage())) {
          attackersInRow.remove();
          attackersOnBoard -= 1;
          attackerCountDown -= 1;
        }
        return true;
      }
    }
    return false;
  }

  private void generateBullets() {
    for (ActiveStudent stu : activeStudents) {
      int row = stu.getPos().row();
      List<Bullet> bullets = stu.getBullets();
      if (!attackers.get(row).isEmpty()) {
        if (bullets.isEmpty()) {
          stu.addBullet(this.cellScale);
        }
      }
    }
  }

  private void moveBullets() {
    for (ActiveStudent stu : activeStudents) {

      List<Bullet> bullets = stu.getBullets();

      if (!bullets.isEmpty()) {
        Bullet bullet = bullets.get(0);

        if (bulletHitAttacker(bullet)) {
          stu.removeBullet();
        } else if (bullet.getPosInCell() >= (this.board.cols() * cellScale)) {
          stu.removeBullet();
        } else {
          bullet.increasePosInCell(this.cellScale);
        }

      }
    }
  }

  private void initializeAttackers() {
    for (int i = 0; i < this.board.rows(); i++) {
      attackers.add(new ArrayList<>());
    }
  }

  /**
   * This method checks if char in game can place on a given location.
   * 
   * @param pos - what position
   * @return true if it is a valid move, false otherwise.
   */
  private boolean legalMove(CellPosition pos) {
    if (!board.positionIsOnGrid(pos)) {
      return false;
    } else if (board.get(pos) != '-') {
      return false;
    }
    return true;
  }

  /**
   * Moves all attackers on the game board, handling collisions with students,
   * reaching the end of the board, and generating new attackers.
   */
  private void moveAttackers() {
    ArrayList<ArrayList<Life>> movedattackers = new ArrayList<>();

    for (int row = 0; row < attackers.size(); row++) {
      movedattackers.add(new ArrayList<>());

      for (Life currentA : attackers.get(row)) {
        Life movedA = currentA.moveLife();

        if (legalMove(movedA.getPos())) {
          movedattackers.get(row).add(movedA);
        } else if (isStudentHit(movedA.getPos(), movedA.getEffect())) {
          movedattackers.get(row).add(currentA);
        } else if (movedA.getPos().col() == -1) {
          gameover();
        } else {
          movedattackers.get(row).add(currentA);
        }
      }
    }
    this.attackers = movedattackers;

    if (attackersOnBoard < Math.min(player.getMaxAttackers(), attackerCountDown)) {
      getNewWalkingAttacker();
    }
  }

  private void gameover() {
    player.gameover();
    if (player.getKonteAttempts() > 0) {
      gamestate = Gamestate.KONT;
    } else {
      gamestate = Gamestate.GAME_OVER;
    }
  }

  /**
   * Checks for students on given pos, takes damage. Removes student if students
   * is dead.
   * 
   * @param pos    what position
   * @param damage from attacker
   * @return
   */
  private boolean isStudentHit(CellPosition pos, int damage) {
    for (ActiveStudent student : activeStudents) {
      if (student.getPos().equals(pos)) {
        if (student.takeDamage(damage)) {
          board.set(pos, '-');
          activeStudents.remove(student);
        }
        return true;
      }
    }
    for (Student student : students) {
      if (student.getPos().equals(pos)) {
        if (!student.takeDamage(damage)) {
          board.set(pos, '-');
          students.remove(student);
        }
        return true;
      }
    }
    return false;
  }

  /**
   * Generates a new walking attacker and places it on a valid position on the
   * game board.
   */
  private void getNewWalkingAttacker() {
    Life newAttacker = factory.getNext();
    CellPosition pos = newAttacker.getPos();
    if (legalMove(pos)) {
      attackers.get(pos.row()).add(newAttacker);
      attackersOnBoard += 1;
    } else {
      getNewWalkingAttacker();
    }
  }

  private void lvlUp() {
    if (player.getLvl() >= 3) {
      gamestate = Gamestate.WIN;
    } else {
      gamestate = Gamestate.INFO;
    }
    player.lvlUp();
  }

  private void initializeModel() {
    this.board = new Board(board.rows(), board.cols());
    this.students = new ArrayList<>();
    this.activeStudents = new ArrayList<>();
    this.attackers = new ArrayList<>();
    initializeAttackers();
    this.cellScale = 10;
    this.character = 'G';
    this.attackersOnBoard = 0;
    this.attackerCountDown = player.getAttackersInLvl();
  }
}
