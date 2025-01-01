package no.uib.inf101.studentvslife.view.galleries;

import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * A default implementation of the ImageGallery interface that loads and
 * provides image icons.
 * Each instance of DefaultImageGallery contains image icons for various
 * entities used in the game.
 */

public class DefaultImageGallery implements ImageGallery {

  private int imageWidth;
  private int imageHeight;
  private ImageIcon stipend;
  private ImageIcon bed;
  private ImageIcon echo;
  private ImageIcon stop;
  private ImageIcon exam;
  private ImageIcon pizza;
  private ImageIcon coin;
  private ImageIcon grid;

  /**
   * Constructs a DefaultImageGallery object with a specified image width.
   * The image height is set to match the image width.
   *
   * @param imageWidth The width of the image icons.
   */
  public DefaultImageGallery(int imageWidth) {
    this.imageWidth = imageWidth;
    this.imageHeight = imageWidth;
    loadAllImages();
  }

  /**
   * Constructs a DefaultImageGallery object with specified image width and
   * height.
   *
   * @param imageWidth  The width of the image icons.
   * @param imageHeight The height of the image icons.
   */
  public DefaultImageGallery(int imageWidth, int imageHeight) {
    this.imageWidth = imageWidth;
    this.imageHeight = imageHeight;
    loadAllImages();
  }

  /**
   * Loads all image icons from files and scales them to the specified width and
   * height.
   */
  private void loadAllImages() {
    stipend = loadImageIcon("STIPEND.jpeg");
    bed = loadImageIcon("BED.jpg");
    echo = loadImageIcon("ECHO.jpg");
    stop = loadImageIcon("STOP.jpg");
    exam = loadImageIcon("EXAM.png");
    pizza = loadImageIcon("PIZZA.png");
    coin = loadImageIcon("COIN.png");
    grid = loadImageIcon("HEI.png");
  }

  /**
   * Loads an image icon from the specified file path and scales it to the
   * specified width and height.
   *
   * @param path The path of the image file.
   * @return The scaled image icon.
   */
  private ImageIcon loadImageIcon(String path) {
    ImageIcon originalIcon = new ImageIcon(ClassLoader.getSystemResource(path));
    Image originalImage = originalIcon.getImage();
    Image scaledImage = originalImage.getScaledInstance(imageWidth, imageHeight, Image.SCALE_SMOOTH);
    return new ImageIcon(scaledImage);
  }

  @Override
  public ImageIcon getImageIcon(Character c) {
    ImageIcon image = switch (c) {
      case 'G' -> stipend;
      case 'B' -> bed;
      case 'E' -> echo;
      case 'S' -> stop;
      case 'X' -> exam;
      case 'P' -> pizza;
      case 'C' -> coin;
      case 'I' -> grid;

      default -> throw new IllegalArgumentException(
          "No available image for '" + c + "'");

    };
    return image;
  }
}
