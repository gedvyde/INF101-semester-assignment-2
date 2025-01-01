package no.uib.inf101.studentvslife;

import javax.swing.JFrame;


import no.uib.inf101.studentvslife.controller.Controller;

import no.uib.inf101.studentvslife.model.Board;
import no.uib.inf101.studentvslife.model.Model;
import no.uib.inf101.studentvslife.model.entity.life.RandomLifeFactory;
import no.uib.inf101.studentvslife.view.View;


/**
 * @author Gedvyde of all implemented methods
 */
public class studentvslifeMain {
  public static final String WINDOW_TITLE = "STUDENT VS LIFE";
	public static final int ROWS = 5;

	public static final int COLS = 9;
	

  public static void main(String[] args) {

		Board board = new Board(ROWS, COLS);
		RandomLifeFactory factory = new RandomLifeFactory(ROWS,COLS);
		Model model = new Model(board, factory);
		View view = new View(model);
		new Controller(model, view);
		
		JFrame frame = new JFrame(WINDOW_TITLE);
		frame.setContentPane(view);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
   
  }


}
