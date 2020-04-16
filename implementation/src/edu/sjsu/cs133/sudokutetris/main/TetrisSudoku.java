package main;

import controller.Controller;
import model.Board;
import view.View;

public class TetrisSudoku {

		
	public static void main(String args[]) throws Exception {
		
		View view = new View();		// Initiate View.
		Board board = new Board();
		
		Controller controller = new Controller(view, board);
		controller.launch();
		
	
	}
}