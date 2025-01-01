package no.uib.inf101.studentvslife.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.Timer;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.studentvslife.model.Gamestate;
import no.uib.inf101.studentvslife.view.CellPositionToPixelConverter;
import no.uib.inf101.studentvslife.view.View;

/**
 * The Controller class acts as an intermediary between the model and the view,
 * handling user input and updating the model accordingly. It implements
 * ActionListener, KeyListener, and MouseListener interfaces to handle various
 * types of events.
 */

public class Controller implements MouseListener, ActionListener, KeyListener {

  private ControllableModel model;
  private View view;
  private Timer timerStudents;
  private Timer timerLife;
  private Timer timerBullets;

  /**
   * Constructs a Controller object with the specified model and view.
   *
   * @param model The controllable model.
   * @param view  The view associated with the model.
   */

  public Controller(ControllableModel model, View view) {
    this.model = model;
    this.view = view;

    for (JButton button : this.view.getButtons()) {
      button.addActionListener(this);
    }
    this.view.addMouseListener(this);
    this.view.addKeyListener(this);
    this.view.setFocusable(true);

    this.timerStudents = new Timer(model.getTimerDelay('S'), this::clockTickS);
    this.timerLife = new Timer(model.getTimerDelay('L'), this::clockTickL);
    this.timerBullets = new Timer(model.getTimerDelay('B'), this::clockTickB);
    this.timerStudents.start();
    this.timerLife.start();
    this.timerBullets.start();
  }

  @Override
  public void mouseClicked(MouseEvent arg0) { 
    if (model.getGamestate().equals(Gamestate.ACTIVE_GAME)) {
      int x = arg0.getX();
      int y = arg0.getY();

      CellPositionToPixelConverter converter = view.getConverter();
      // Glue new student based on mouseclick
      try {
        CellPosition pos = converter.getCellPositionForPoint(x, y);
        this.model.glueStudent(pos);
        this.view.repaint();

      } catch (Exception IndexOutOfBoundsException) {
      }
    }
    
  }

  @Override
  public void actionPerformed(ActionEvent arg0) {
    // Update model (student) character with buttons
    if (model.getGamestate().equals(Gamestate.ACTIVE_GAME)) {
      if (arg0.getSource() == view.getButtons().get(0)) {
        this.model.setChar('G');
      } else if (arg0.getSource() == view.getButtons().get(1)) {
        this.model.setChar('B');
      } else if (arg0.getSource() == view.getButtons().get(2)) {
        this.model.setChar('E');
      } else if (arg0.getSource() == view.getButtons().get(3)) {
        this.model.setChar('S');
      }
    }
    this.view.repaint();
    this.view.requestFocusInWindow();
  }

  @Override
  public void keyPressed(KeyEvent arg0) {
    // Switch gamestates with Enter
    if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
      model.nextGamestate();
    }
    this.view.repaint();
    this.view.requestFocusInWindow();
  }

  /**
   * Handles the timer for student entities. Also controls the visibility of
   * buttons based on player lvl.
   *
   * @param ActionEvent The event that triggers the clock tick.
   */
  private void clockTickS(ActionEvent ActionEvent) {
    if (model.getGamestate().equals(Gamestate.ACTIVE_GAME)) {
      this.model.clockTickStudent();
      this.updateDelay('S');
      this.view.repaint();
    }

    if (model.getGamestate().equals(Gamestate.ACTIVE_GAME)) {
      setButtonsVisible(true, model.getLvl());
    } else if (model.getGamestate().equals(Gamestate.INFO)) {
      setButtonsVisible(true, model.getLvl());
    } else {
      setButtonsVisible(false, model.getLvl());
    }
  }

  /**
   * Handles the ticking of the clock for life entities.
   */
  private void clockTickL(ActionEvent ActionEvent) {
    if (model.getGamestate().equals(Gamestate.ACTIVE_GAME)) {
      this.model.clockTickLife();
      this.updateDelay('L');
      this.view.repaint();
    }
  }

  /**
   * Handles the ticking of the clock for bullet entities.
   */
  private void clockTickB(ActionEvent ActionEvent) {
    if (model.getGamestate().equals(Gamestate.ACTIVE_GAME)) {
      this.model.clockTickBullets();
      this.updateDelay('B');
      this.view.repaint();
    }
  }

  /**
   * Retrieves the delay for the specified character's timer.
   *
   * @param character The character representing the timer
   * @return The delay for the specified character's timer.
   */
  private int getDelay(char character) {
    return model.getTimerDelay(character);
  }

  /**
   * Updates the delay for the specified character's timer.
   *
   * @param character The character representing the timer
   */
  private void updateDelay(char character) {
    if (character == 'S') {
      timerStudents.setDelay(getDelay(character));
      timerStudents.setInitialDelay(getDelay(character));
    } else if (character == 'A') {
      timerLife.setDelay(getDelay(character));
      timerLife.setInitialDelay(getDelay(character));
    } else if ((character == 'B')) {
      timerBullets.setDelay(getDelay(character));
      timerBullets.setInitialDelay(getDelay(character));
    }
  }

  /**
   * Sets the visibility of buttons based on player level.
   *
   * @param visible   boolean indicating whether buttons should be visible or not.
   * @param playerlvl The level of the player.
   */
  private void setButtonsVisible(boolean visible, int playerlvl) {
    if (playerlvl > 2) {
      for (JButton button : view.getButtons()) {
        button.setVisible(visible);
      }
    } else if (playerlvl == 2) {
      for (int i = 0; i < 3; i++) {
        view.getButtons().get(i).setVisible(visible);
      }
      view.getButtons().get(3).setVisible(false);
    } else {
      for (int i = 0; i < 2; i++) {
        view.getButtons().get(i).setVisible(visible);
        view.getButtons().get(i + 2).setVisible(false);
      }
    }
  }

  @Override public void keyReleased(KeyEvent arg0)     { /* ignore */ }
  @Override public void keyTyped(KeyEvent arg0)        { /* ignore */ }
  @Override public void mouseEntered(MouseEvent arg0)  { /* ignore */ }
  @Override public void mouseExited(MouseEvent arg0)   { /* ignore */ }
  @Override public void mousePressed(MouseEvent arg0)  { /* ignore */ }
  @Override public void mouseReleased(MouseEvent arg0) { /* ignore */ }

}
