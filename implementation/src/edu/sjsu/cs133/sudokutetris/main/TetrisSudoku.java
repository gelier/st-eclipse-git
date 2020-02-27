package main;

import model.Board;
import controller.Controller;
import view.View;

public class SudokuTetris {

		
	public static void main(String args[]) throws Exception {
		
		View view = new View();		// Initiate View.
		Board board = new Board();
		
		Controller controller = new Controller(view, board);
		controller.launch();
		
	}

}

