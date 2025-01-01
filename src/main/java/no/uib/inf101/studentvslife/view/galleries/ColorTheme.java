package no.uib.inf101.studentvslife.view.galleries;

import java.awt.Color;

/**
 * The ColorTheme interface defines methods to retrieve colors for various elements
 * such as frame, background, cell, title, etc.
 */

public interface ColorTheme {

     /**
     * Retrieves the default color for the cell.
     *
     * @return The color for the cell.
     */
    Color getCellColor();

   /**
     * Retrieves the color for the frame.
     *
     * @return The color for the frame.
     */
    Color getFrameColor();

    /**
     * Retrieves the color for the background.
     *
     * @return The color for the background.
     */
    Color getBackgroundColor();


     /**
     * Retrieves the color for the title.
     *
     * @return The color for the title.
     */
    Color getTitleColor();

    /**
     * Retrieves the color for the blur effect.
     *
     * @return The color for the blur effect.
     */
    Color getBlur();
}

