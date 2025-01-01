package no.uib.inf101.studentvslife.model.entity.life;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import no.uib.inf101.grid.CellPosition;

public class LifeTest {

  // All method extended from Enitty class are tested in StudentTest
  
    @Test
    void testShiftedBy() {
      LifeFactory fact = new RandomLifeFactory(1, 3);
      Life life = fact.getNext();
      assertEquals(new CellPosition(0, 2), life.getPos());
      life = life.moveLife();
      assertEquals(new CellPosition(0, 1), life.getPos());
    }
}
