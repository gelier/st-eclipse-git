package main;

import controller.Controller;
import model.Board;
import view.View;

/*
 * CS133: Project to create the game Sudoku Tetris, a combination of Sudoku and Tetris
 * 
 * SP20: CMPE-133 Sec 02 -Soft Eng II
 * Professor Yalda Edalat
 * 
 * @author Guiller Dalit, Mashawn Hall, Shruti Panchal, Kenneth Huang
 * 
 * Compiled in JAVA 11
 * 
 * Instruction on how to play: Player drag and drop the generated Tetris blocks to 
 * Strategically fill the Sudoku, if successful the the user one. The score then is 
 * determined by how fast the player solved or filled the Sudoku with Tetris Blocks. 
 * 
 * The game followed the MVC design pattern. 
 * 
 */
public class TetrisSudoku {
		
	public static void main(String args[]) throws Exception {
		
		View view = new View();		// Initiate View.
		Board board = new Board();
		Controller controller = new Controller(view, board);
		controller.launch();

	}
}