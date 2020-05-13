package controller;

import model.Board;
import view.View;

public class Controller{

	private View view;
	private Board board;
	
	/**
	 * @param view
	 * @param board
	 */
	public Controller(View view, Board board) {
		super();
		this.view = view;
		this.board = board;
	}
	
	public void launch() {
		// TODO start view and board
		initGame();
		// TODO current, newgame, resetgame,
		//mainLoop();
	}
	private void initGame(){
		view.start();
		//board.start
		
	}
}
