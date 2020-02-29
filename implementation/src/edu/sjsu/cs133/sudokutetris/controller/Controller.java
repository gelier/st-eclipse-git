package controller;

import model.Board;
import view.View;

public class Controller{

	private View view;
	private Board board;
	
	
	private boolean gamewon;
	private boolean gameover;
	private int points;
	private int lives;
	//test
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
		// TODO Auto-generated method stub
		initGame();
		mainLoop();
	}
	private void initGame(){
		
		view.start();
		//board.start
		
	}
	//update board, view, blocks, and so on happens here by calling there functions
	private void gameInfo(){
		//new game
		//play	
	}
	// the loop for view and model happens here
	private void mainLoop(){
		
		do {
			if (gameover != true){
				gameInfo();
			}
			else{
				//reset game
			}
				
		} while(true);
		
	}

	
	
}
