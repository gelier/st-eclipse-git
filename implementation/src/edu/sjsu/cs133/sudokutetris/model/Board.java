package model;

/*
 * There should be no View methods here
 */

public class Board extends GameTemplate {
	
	
	private TetrisBlock tetrisblock;
	private Player player;
	
	private boolean gameover = false;
	private boolean gamewon = false;
	private boolean iskeyPressed = false; //may use an array of events than boolean
	
	private int score;
	private int level;
	private int lives;
	private int total_score;
	private int level_total_score;
	
	
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
