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
		this.shape = shape;
	}
	
	public TetrisBock(ArrayList[][] shape) {
		if(shape.length == HEIGHT) {
			if(shape[0].length == WIDTH) {
				this.shape = shape;
			}
		}
		else {
			throw new IOException("Shape Array doesn't fit the requirement"); 
		}
	}
	
	protected ArrayList[][] generateShape(){
		
	}
	
	protected void generateShapeOnArray() {
		
	}
	
	protected ArrayList[][] generateSquare() {
		ArrayList shape[][] = {{1,1,0,0},{1,1,0,0}};
		return shape;
	}
	
	protected ArrayList[][] generateStraight() {
		ArrayList shape[][] = {{1,1,1,1},{0,0,0,0}};
		return shape;
	}
	
	protected ArrayList[][] generateT() {
		ArrayList shape[][] = {{1,1,1,0},{0,1,0,0}};
		return shape;
	}
	
	protected ArrayList[][] generateL() {
		ArrayList shape[][] = {{1,0,0,0},{1,1,1,1}};
		return shape;
	}
	
	protected ArrayList[][] generateSkew() {
		ArrayList shape[][] = {{0,1,1,0},{1,1,0,0}};
		return shape;
	}
	
	protected ArrayList[][] generateFlipL() {
		ArrayList shape[][] = {{0,0,0,1},{1,1,1,1}};
		return shape;
	}
	
	protected ArrayList[][] generateFlipZ() {
		ArrayList shape[][] = {{1,1,0,0},{0,1,1,0}};
		return shape;
	}
	
	protected ArrayList[][] getShape(){
		
		return null;
	}
}
