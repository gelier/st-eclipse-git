package model;

import java.util.ArrayList;

/*
 * There should be no View methods here
 */

public class Board extends GameTemplate {
	
	
	private TetrisBlock tetrisblock;
	private Player player;
	
	private final int FIRST_LEVEL_LENGTH = 9;
	private final int FIRST_LEVEL_WIDTH = 9;
	
	private boolean gameover = false;
	private boolean gamewon = false;
	private boolean iskeyPressed = false; //may use an array of events than boolean
	
	private int score;
	private int level;
	private int lives;
	private int total_score;
	private int level_total_score;
	
	private int board_length;
	private int board_height;
	
	private ArrayList[][] board;
	
	
	/**
	 * 
	 */
	public Board() {
		super();
		// TODO Auto-generated constructor stub
		board = new ArrayList[FIRST_LEVEL_LENGTH][FIRST_LEVEL_WIDTH];
	}

	/**
	 * @param board_length
	 * @param board_height
	 */
	public Board(int board_length, int board_height) {
		super();
		this.board_length = board_length;
		this.board_height = board_height;
		board = new ArrayList[board_length][board_height];
	}
	
	@Override
	protected void startgame() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void newgame() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void nextgame() {
		// TODO Auto-generated method stub
		
	}
	public boolean iskeyPressed(){
		return iskeyPressed;
	}
	public void generateTetrisBlock(){
		
		tetrisblock.generateShape();
		
	}
	public void update(){
		
	}
	public void moveBlocks(){
		
	}
	public void checkCollision(){
		
	}
	public void gameStatus(){
		//may add more conditions 
		if (total_score == level_total_score){
			gamewon = true;
		}
		else{
			gameover = true;
		}
	}
	
	//accessors
	public Player getPlayer() {
		return player;
	}
	public boolean isGameover() {
		return gameover;
	}
	public boolean isGamewon() {
		return gamewon;
	}
	public int getLevel() {
		return level;
	}
	public int getLives() {
		return lives;
	}
	public int getTotal_score() {
		return total_score;
	}
	public int getLevel_total_score() {
		return level_total_score;
	}

}
