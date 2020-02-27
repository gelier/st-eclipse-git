package model;

import java.util.ArrayList;

public class TetrisBlock {

	private final int WIDTH = 4;
	private final int HEIGHT = 2;
	
	private ArrayList[][]shape;
	/**
	 * 
	 * 
	 */
	public TetrisBlock() {
		/* example of shapes
		 * [][][][]
		 * 			   []
		 * [][][][]  [][][]
		 * 		 []
		 * 
		 * []
		 * [][][][]
		 * 
		 * The width is always <= 4 and height is always <= 2
		 * */
		shape = new ArrayList[WIDTH][HEIGHT];
	}
	protected void generateShape(){
		
	
	}
	protected ArrayList[][] getShape(){
		
		return null;
	}
}
