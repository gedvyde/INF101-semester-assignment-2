package no.uib.inf101.studentvslife.view.galleries;

import java.util.List;

/**
 * The TextGallery interface defines methods to access different types of text
 * content
 * used in the game, such as welcome messages, information texts for various
 * levels,
 * game over messages, and win messages.
 */
public interface TextGallery {

    /**
     * Retrieves the welcome text messages.
     *
     * @return A list of strings containing the welcome messages.
     */
    List<String> getWelcomeTxt();

    /**
     * Retrieves the information text messages for the specified level of player.
     *
     * @param lvl The level of player
     * @return A list of strings containing the information messages for the
     *         specified level.
     */
    List<String> getInfoTxt(int lvl);

    /**
     * Retrieves the game over text messages based on the remaining lives.
     *
     * @param lives The number of lives remaining.
     * @return A list of strings containing the game over messages.
     */
    List<String> getGameOverTxt(int lives);

    /**
     * Retrieves the win text messages.
     *
     * @return A list of strings containing the win messages.
     */
    List<String> getWinTxt();
}
