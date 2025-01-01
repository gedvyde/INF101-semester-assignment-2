package no.uib.inf101.studentvslife.view;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridDimension;
import no.uib.inf101.studentvslife.model.Model;

/**
 * The CellPositionToPixelConverter class converts cell positions to pixel
 * coordinates and vice versa for rendering elements on the game screen.
 */

public class CellPositionToPixelConverter {
  private final Rectangle2D box;
  private final double margin;
  private double cellW;
  private double cellH;
  private Model model;

  /**
   * Constructs a new CellPositionToPixelConverter with the specified parameters.
   *
   * @param box    The bounding box representing the area of the game screen.
   * @param gd     The grid dimensions indicating the number of rows and columns.
   * @param margin The margin between cells.
   * @param model  The model representing the game state.
   */
  CellPositionToPixelConverter(Rectangle2D box, GridDimension gd, double margin, Model model) {
    this.box = box;
    this.margin = margin;
    this.model = model;
    cellW = (box.getWidth() - margin * gd.cols() - margin) / gd.cols();
    cellH = (box.getHeight() - margin * gd.rows() - margin) / gd.rows();
  }

  /**
   * Gets the bounds for the specified cell position.
   *
   * @param cellPosition The position of the cell.
   * @return A rectangle representing the bounds of the cell in pixel coordinates.
   */
  Rectangle2D getBoundsForCell(CellPosition cellPosition) {
    double cellX = box.getX() + margin + (cellW + margin) * cellPosition.col();
    double cellY = box.getY() + margin + (cellH + margin) * cellPosition.row();
    return new Rectangle2D.Double(cellX, cellY, cellW, cellH);
  }

  /**
   * Gets the cell position for the specified point (pixel coordinates).
   *
   * @param x The x-coordinate of the point.
   * @param y The y-coordinate of the point.
   * @return The cell position corresponding to the point.
   * @throws IndexOutOfBoundsException If the point is outside the game screen.
   */
  public CellPosition getCellPositionForPoint(double x, double y) throws IndexOutOfBoundsException {
    if (!box.contains(x, y)) {
      throw new IndexOutOfBoundsException("Mouse click not on GameScreen");
    }
    int col = (int) ((x - box.getX() - margin) / (cellW + margin));
    int row = (int) ((y - box.getY() - margin) / (cellH + margin));
    return new CellPosition(row, col);
  }

  /**
   * Gets the bounds for a bullet at the specified position.
   *
   * @param x The x-coordinate of the bullet.
   * @param y The y-coordinate of the bullet.
   * @return An ellipse representing the bounds of the bullet in pixel
   *         coordinates.
   */
  Ellipse2D.Double getBoundsForBullet(int x, int y) {
    double cellX = box.getX() + margin + (cellW / model.getCellScale()) * (x);
    double cellY = box.getY() + margin + (cellH + margin) * y;
    return new Ellipse2D.Double.Double(cellX, cellY + cellH / 3, cellW / 3, cellH / 3);
  }
}
