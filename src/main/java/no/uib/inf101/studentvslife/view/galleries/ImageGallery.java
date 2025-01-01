package no.uib.inf101.studentvslife.view.galleries;

import javax.swing.ImageIcon;

/**
 * The ImageGallery interface defines methods to retrieve image icons for
 * various entities used in the game.
 */

public interface ImageGallery {

  /**
   * Retrieves the image icon corresponding to the specified character.
   *
   * @param c The character representing the entity for which the image icon is
   *          requested.
   * @return The image icon corresponding to the specified character.
   * @throws IllegalArgumentException If no image icon is available for the
   *                                  specified character.
   */

  ImageIcon getImageIcon(Character c);

}