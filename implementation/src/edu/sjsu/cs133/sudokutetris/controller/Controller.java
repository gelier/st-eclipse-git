package controller;

import adapter.GameInterface;
import model.Board;
import view.View;

public class Controller implements GameInterface {

	private View view;
	private Board board;
	private boolean gamewon;
	private boolean gameover;
	private int points;
	private int lives;
	
	/**
	 * @param view
	 * @param board
	 */
	public Controller(View view, Board board) {
		super();
		this.view = view;
		this.board = board;
	}
	
	@Override
	public void launch() {
		// TODO Auto-generated method stub
		initGame();

		mainLoop();
	}
	private void initGame(){
		
	}
	//update board, view, blocks, and so on
	private void gameInfo(){
		
	}
	private void mainLoop(){
		while(true){
			gameInfo();
		}
		
	}

	
	
}
