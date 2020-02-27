package model;

public abstract class GameTemplate {
	
	protected abstract void startgame();
	protected abstract void newgame();
	protected abstract void nextgame();
	
	public final void play(){
		startgame();
	}
	public final void reset(){
		newgame();
	}
	public final void nextlevel(){
		nextgame();
	}
}
