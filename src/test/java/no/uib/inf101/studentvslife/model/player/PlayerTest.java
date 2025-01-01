package no.uib.inf101.studentvslife.model.player;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlayerTest {

  Player p1;

  @BeforeEach

  void setUp() {
    p1 = new Player();
  }

  @Test
  void testGameover() {
    assertEquals(0, p1.getLvl());
    assertEquals(3, p1.getKonteAttempts());
    p1.gameover();
    assertEquals(0, p1.getLvl());
    assertEquals(2, p1.getKonteAttempts());
  }

  @Test
  void testLvlUp() {
    assertEquals(3, p1.getKonteAttempts());
    assertEquals(0, p1.getLvl());
    p1.lvlUp();
    p1.lvlUp();
    p1.lvlUp();
    p1.gameover();
    assertEquals(2, p1.getKonteAttempts());
    assertEquals(3, p1.getLvl());
    p1.gameover();
    p1.gameover();
    assertEquals(0, p1.getKonteAttempts());
    assertEquals(3, p1.getLvl());
    p1.gameover();
    assertEquals(3, p1.getKonteAttempts());
    assertEquals(0, p1.getLvl());

  }

  @Test
  void testMoney() {
    assertEquals(200, p1.getMoney());
    assertFalse(p1.money(-300));
    assertEquals(200, p1.getMoney());

    assertTrue(p1.money(25));
    assertEquals(225, p1.getMoney());

    p1.gameover();
    assertEquals(200, p1.getMoney());
  }
}
