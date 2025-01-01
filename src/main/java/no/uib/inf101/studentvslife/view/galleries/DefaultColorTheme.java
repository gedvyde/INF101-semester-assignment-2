package no.uib.inf101.studentvslife.view.galleries;

import java.awt.Color;

/**
 * A default implementation of the ColorTheme interface that provides predefined color schemes.
 * Each instance of DefaultColorTheme defines colors for various elements such as frame, background,
 * cell, title, and blur effect.
 */
public class DefaultColorTheme implements ColorTheme {

    @Override
    public Color getFrameColor() {
        return new Color(166, 207, 219);
    }

    @Override
    public Color getBackgroundColor() {
        return new Color(252, 201, 108);
    }
   
    @Override
    public Color getCellColor() {
        return new Color(68, 169, 195);
    }

    @Override
    public Color getTitleColor() {
        return Color.BLACK;
    }

    @Override
    public Color getBlur() {
        throw new UnsupportedOperationException("Unimplemented method 'getBlur'");
    }
}
