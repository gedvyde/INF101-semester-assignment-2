package no.uib.inf101.studentvslife.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.studentvslife.model.Board;
import no.uib.inf101.studentvslife.model.Model;
import no.uib.inf101.studentvslife.model.entity.life.LifeFactory;
import no.uib.inf101.studentvslife.model.entity.life.RandomLifeFactory;

import java.awt.geom.Rectangle2D;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CellPositionToPixelConverterTest {

  int rows;
  int cols;
  Board board;
  LifeFactory fact;
  Model model;
  CellPositionToPixelConverter converter;
  int width = 9;
  int margin = 1;

  @BeforeEach
  void setUp() {
    this.rows = 1;
    this.cols = 1;
    this.board = new Board(rows, cols);
    this.fact = new RandomLifeFactory(rows, cols);
    this.model = new Model(board, fact);
    this.margin = 1;
    this.converter = new CellPositionToPixelConverter(new Rectangle2D.Double(0, 0, width, width), board, margin, model);
  }

  @Test
  void testGetBoundsForCell() {
    CellPosition cellPosition = new CellPosition(0, 0);
    Rectangle2D expected = new Rectangle2D.Double(1, 1, 7 , 7);
    Rectangle2D result = converter.getBoundsForCell(cellPosition);
    assertEquals(expected, result);
  }

  @Test
  void testGetCellPositionForPoint() {
    double x = 5;
    double y = 5;
    CellPosition expected = new CellPosition(0, 0);
    CellPosition result = converter.getCellPositionForPoint(x, y);
    assertEquals(expected, result);
    assertThrows(IndexOutOfBoundsException.class, () -> converter.getCellPositionForPoint(35, 35));
  }
}
