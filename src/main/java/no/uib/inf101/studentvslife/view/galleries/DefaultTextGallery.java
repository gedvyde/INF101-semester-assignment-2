package no.uib.inf101.studentvslife.view.galleries;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A default implementation of the TextGallery interface that loads text content
 * from test files.
 * Each instance of DefaultTextGallery contains lists of strings representing
 * different types of text content,
 * such as welcome messages, information texts for various levels, game over
 * messages, and win messages.
 */
public class DefaultTextGallery implements TextGallery {

    private List<String> welcomeTxt;
    private List<String> inf0Txt;
    private List<String> inf1Txt;
    private List<String> inf3Txt;
    private List<String> inf2Txt;
    private List<String> gameOverTxt;
    private List<String> kontTxt;
    private List<String> winTxt;

    /**
     * Constructs a DefaultTextGallery object, initializing text lists by reading
     * from corresponding test files.
     */
    public DefaultTextGallery() {
        // Initialize text lists by reading from test files
        this.welcomeTxt = readTestFile("src/main/resources/WELCOME.txt");
        this.inf0Txt = readTestFile("src/main/resources/INF0.txt");
        this.inf1Txt = readTestFile("src/main/resources/INF1.txt");
        this.inf2Txt = readTestFile("src/main/resources/INF2.txt");
        this.inf3Txt = readTestFile("src/main/resources/INF3.txt");
        this.winTxt = readTestFile("src/main/resources/WIN.txt");
        this.gameOverTxt = readTestFile("src/main/resources/GAMEOVER.txt");
        this.kontTxt = readTestFile("src/main/resources/KONT.txt");
    }
  

    @Override
    public List<String> getWelcomeTxt() {
        return welcomeTxt;
    }

    @Override
    public List<String> getInfoTxt(int lvl) {
        switch (lvl) {
            case 0:
                return inf0Txt;
            case 1:
                return inf1Txt;
            case 2:
                return inf2Txt;
            case 3:
                return inf3Txt;
        }
        return inf0Txt;
    }

    @Override
    public List<String> getGameOverTxt(int lives) {
        if (lives > 0) {
            return kontTxt;
        } else {
            return gameOverTxt;
        }
    }

    @Override
    public List<String> getWinTxt() {
        return winTxt;
    }

    /**
     * Reads the content of a text file and returns it as a list of strings.
     * Each line in the text file is considered as a separate string element in the
     * list.
     *
     * @param filename The path of the text file to be read.
     * @return A list of strings containing the content of the text file.
     */
    private static List<String> readTestFile(String filename) {
        List<String> lines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
    }
}
