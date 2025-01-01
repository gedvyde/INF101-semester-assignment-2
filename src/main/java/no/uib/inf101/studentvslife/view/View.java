package no.uib.inf101.studentvslife.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import no.uib.inf101.grid.GridCell;
import no.uib.inf101.studentvslife.model.Model;
import no.uib.inf101.studentvslife.model.entity.IEntity;
import no.uib.inf101.studentvslife.model.entity.bullet.Bullet;
import no.uib.inf101.studentvslife.view.galleries.ColorTheme;
import no.uib.inf101.studentvslife.view.galleries.DefaultColorTheme;
import no.uib.inf101.studentvslife.view.galleries.DefaultImageGallery;
import no.uib.inf101.studentvslife.view.galleries.DefaultTextGallery;
import no.uib.inf101.studentvslife.view.galleries.ImageGallery;
import no.uib.inf101.studentvslife.view.galleries.TextGallery;

/**
 * The View class represents the graphical user interface of the game. It
 * displays the game grid, buttons, and other visual elements.
 */

public class View extends JPanel {

  private Model model;
  private CellPositionToPixelConverter converter;
  private ImageGallery imageGallery;
  private TextGallery textGallery;
  private ColorTheme colorTheme;
  private ImageGallery imageGalleryB;
  private Rectangle2D gameBox;
  private List<JButton> buttons;

  private final int width = 990;
  private final int height = 600;
  private final int TEXTSPACE = height / 5;
  private final int OUTERMARGIN = width / 33;
  private final int SIDESPACE = OUTERMARGIN * 5;
  private final int INNERMARGIN = 3;
  private final String FONT = "Copperplate";
  private final int sizeScalar;
  

  /**
   * Constructs a new View object with the specified model.
   *
   * @param model The model representing the game state and logic.
   */
  public View(Model model) {
    this.imageGallery = new DefaultImageGallery(3 * OUTERMARGIN);
    this.imageGalleryB = new DefaultImageGallery(OUTERMARGIN);
    this.colorTheme = new DefaultColorTheme();
    this.textGallery = new DefaultTextGallery();
    this.model = model;

    this.setPreferredSize(new Dimension(width, height));
    this.setBackground(colorTheme.getBackgroundColor());
    this.initializeButtons();

    this.sizeScalar = (int) (Math.pow((Math.pow(width, 2) + Math.pow(TEXTSPACE, 2)), 0.3));
    this.gameBox = new Rectangle2D.Double(SIDESPACE, TEXTSPACE, width - OUTERMARGIN - SIDESPACE,
        height - OUTERMARGIN - TEXTSPACE);

    this.converter = new CellPositionToPixelConverter(gameBox, model.getDimension(), INNERMARGIN, this.model);
  
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    drawGame(g2);
  }

  /**
   * Retrieves the list of buttons in the view.
   *
   * @return The list of buttons.
   */
  public List<JButton> getButtons() {
    return buttons;
  }

  /**
   * Retrieves the cell position to pixel converter used in the view.
   *
   * @return The cell position to pixel converter.
   */
  public CellPositionToPixelConverter getConverter() {
    return converter;
  }

  private void drawGame(Graphics2D g2) {
    switch (model.getGamestate()) {
    case WELCOME:
      drawStart(g2);
      break;
    case ACTIVE_GAME:
      drawGrid(g2);
      break;
    case GAME_OVER:
      drawGameOver(g2);
      break;
    case KONT:
      drawGameOver(g2);
      break;
    case INFO:
      drawInfo(g2);
      break;
    case WIN:
      drawWin(g2);
      break;
    default:
      throw new IllegalArgumentException("Invalid game state");
    }
  }

  private void drawGrid(Graphics2D g2) {
    g2.setColor(colorTheme.getFrameColor());
    g2.fill(gameBox);

    writePrice(model.getLvl(), g2);
    drawTitle(g2);
    drawCells(g2, model.getBoardTiles(), converter, imageGallery, colorTheme);
    drawBullets(g2, model.getBullets(), converter, this.imageGalleryB);
    drawEntity(g2, model.getAttackers(), converter, this.imageGallery, colorTheme);

  }

  private void drawTitle(Graphics2D g2) {
    g2.setColor(colorTheme.getTitleColor());
    g2.setFont(new Font(FONT, Font.BOLD, sizeScalar));
    g2.drawString("Student VS Life", OUTERMARGIN, (int) TEXTSPACE * 2 / 3);
    String score = "zZ: " + roundToNearest25(model.getMoney()) + " Kont: " + model.getKontAttempts();
    if (0 < model.getLvl() && model.getLvl() <= 3) {
      score += " Year: " + model.getLvl();
    }
    g2.setFont(new Font(FONT, Font.PLAIN, sizeScalar / 2));
    g2.drawString(score, getWidth() * 3 / 5, (int) TEXTSPACE * 2 / 3);

  }



  private int roundToNearest25(int number) {
    return Math.round(number / 25) * 25;
  }

  private void drawEntity(Graphics2D g2, ArrayList<IEntity> entities, CellPositionToPixelConverter converter,
      ImageGallery gallery, ColorTheme color) {

    for (IEntity ent : entities) {
      Rectangle2D box = converter.getBoundsForCell(ent.getPos());
      ImageIcon image = gallery.getImageIcon(ent.getChar());
      image.paintIcon(this, g2, (int) box.getMinX(), (int) box.getMinY());
    }
  }

  private void drawCells(Graphics2D g2, Iterable<GridCell<Character>> cellCollection,
      CellPositionToPixelConverter converter, ImageGallery gallery, ColorTheme color) {

    for (GridCell<Character> cell : cellCollection) {
      Rectangle2D box = converter.getBoundsForCell(cell.pos());
      if (cell.elem() == '-') {
        g2.setColor(color.getCellColor());
        g2.fill(box);
      } else {
        ImageIcon image = gallery.getImageIcon(cell.elem());
        image.paintIcon(this, g2, (int) box.getMinX(), (int) box.getMinY());
      }
    }
  }

  private void drawBullets(Graphics2D g2, List<Bullet> bullets, CellPositionToPixelConverter converter,
      ImageGallery gallery) {

    for (Bullet bullet : bullets) {
      ImageIcon imageIcon = gallery.getImageIcon(bullet.getChar());
      Ellipse2D.Double box = converter.getBoundsForBullet(bullet.getPosInCell(), bullet.getPos().row());
      imageIcon.paintIcon(this, g2, (int) box.getCenterX(), (int) box.getCenterY());
    }
  }

  private void drawStart(Graphics2D g2) {
    List<String> welcomeText = textGallery.getWelcomeTxt();
    drawText(g2, welcomeText);
  }

  private void drawInfo(Graphics2D g2) {
    int lvl = model.getLvl();
    List<String> infoText = textGallery.getInfoTxt(lvl);
    drawText(g2, infoText);
    if (lvl == 0) {
      
    }
  }

  private void drawWin(Graphics2D g2) {
    List<String> winText = textGallery.getWinTxt();
    drawText(g2, winText);
  }

  private void drawGameOver(Graphics2D g2) {
    List<String> gameOverText = textGallery.getGameOverTxt(model.getKontAttempts());
    drawText(g2, gameOverText);
  }

  private void drawText(Graphics2D g2, List<String> textLines) {
    int lineCount = textLines.size();

    // Draw title
    String firstLine = textLines.get(0);
    g2.setColor(colorTheme.getTitleColor());
    g2.setFont(new Font(FONT, Font.BOLD, sizeScalar * 9 / 10));
    g2.drawString(firstLine, OUTERMARGIN, (int) TEXTSPACE * 2 / 3);

    // Draw text
    int y = getHeight() / 2 - (lineCount / 2) * OUTERMARGIN;
    y = Math.max(y, TEXTSPACE);

    g2.setColor(colorTheme.getTitleColor());
    g2.setFont(new Font(FONT, Font.PLAIN, sizeScalar * (4 + lineCount % 5) / lineCount));
    for (int i = 1; i < lineCount - 1; i++) {
      String line = textLines.get(i);
      g2.drawString(line, SIDESPACE + OUTERMARGIN * 2, y);
      y += OUTERMARGIN;
    }

    // Draw last line
    g2.setColor(colorTheme.getTitleColor());
    g2.setFont(new Font(FONT, Font.ITALIC, sizeScalar / 3));
    String lastLine = textLines.get(lineCount - 1);
    g2.drawString(lastLine, SIDESPACE * 3, getHeight() - OUTERMARGIN);

  }

  private void initializeButtons() {
    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    this.add(Box.createVerticalStrut((int) TEXTSPACE));
    String buttonLetters = "GBES";
    List<JButton> buttons = new ArrayList<>();

    for (int i = 0; i < 4; i++) {
      ImageIcon icon = imageGallery.getImageIcon((buttonLetters.charAt(i)));
      JButton button = new JButton(icon);
      button.setPreferredSize(new Dimension(OUTERMARGIN * 3, OUTERMARGIN * 3));
      button.setBorder(new EmptyBorder(0, OUTERMARGIN, 0, 0));
      this.add(button);
      this.add(Box.createVerticalStrut(OUTERMARGIN));
      buttons.add(button);
    }
    this.buttons = buttons;
  }

  private void writePrice(int playerlvl, Graphics2D g2) {
    g2.setColor(colorTheme.getTitleColor());
    g2.setFont(new Font(FONT, Font.BOLD, sizeScalar / 3));

    

 
    for (int i = 1; i < 5; i++) {
      String price = switch (i) {
        case 1 -> "zZ : 100";
        case 3 -> "zZ : 150";
        default -> "zZ : 50";
      };

      if ((playerlvl < 3) && (i > 3)) {
        break;
      } else if ((playerlvl < 2) && (i > 2)) {
        break;
      }
      int y = TEXTSPACE + (4*OUTERMARGIN)* i  - OUTERMARGIN/2;
      g2.drawString(price, OUTERMARGIN*3/2, y);
  }
 }
}
