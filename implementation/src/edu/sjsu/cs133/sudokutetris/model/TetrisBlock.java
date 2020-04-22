package model;

import java.util.ArrayList;
import javax.swing.*;

public class TetrisBlock {
	private final int WIDTH = 4;
	private final int HEIGHT = 2;
	private ArrayList<int[][]> shapes;

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
		ArrayList<int[][]> shapes = new ArrayList<int[][]>();
		this.shapes = shapes;
	}
	
	protected JLabel generateShape(){
		return null;
	}
	
	//shapes
	//[][]
	//[][]
	protected int[][] generateSquare() {
		int[][] shape = {{1,1,0,0},{1,1,0,0}};
		return shape;
		
	}
	
	//shapes
	//[][][][]
	protected int[][] generateStraight() {
		int[][] shape = {{1,1,1,1},{0,0,0,0}};
		return shape;
	}
	
	//shapes
	//  []
	//[][][]
	protected int[][] generateT() {
		int[][] shape = {{0,1,0,0},{1,1,1,0}};
		return shape;
	}
	
	//shapes
	//[]
	//[][][][]
	protected int[][] generateL() {
		int[][] shape = {{1,0,0,0},{1,1,1,1}};
		return shape;
	}
	
	//shapes
	//  [][]
	//[][]
	protected int[][] generateSkew() {
		int[][] shape = {{0,1,1,0},{1,1,0,0}};
		return shape;
	}
	
	//shapes
	//      []
	//[][][][]
	protected int[][] generateFlipL() {
		int[][] shape = {{0,0,0,1},{1,1,1,1}};
		return shape;
	}
	
	//shapes
	//[][]
	//  [][]
	protected int[][] generateFlipZ() {
		int[][] shape = {{1,1,0,0},{0,1,1,0}};
		return shape;
	}
	
	protected ArrayList<int[][]> getShapes(){	
		return shapes;
	}
}
