package model;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.*;

public class TetrisBlock {
	private final int WIDTH = 4;
	private final int HEIGHT = 2;
	private ArrayList<int[][]> shapes;
	
	
	//Different shapes of Tetris.

	
	private final Point[][][] Tetraminos = {
			// I-Piece
			{
				{ new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(3, 1) },
				{ new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(1, 3) },
				{ new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(3, 1) },
				{ new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(1, 3) }
			},
			
			// J-Piece
			{
				{ new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(2, 0) },
				{ new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(2, 2) },
				{ new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(0, 2) },
				{ new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(0, 0) }
			},
			
			// L-Piece
			{
				{ new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(2, 2) },
				{ new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(0, 2) },
				{ new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(0, 0) },
				{ new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(2, 0) }
			},
			
			// O-Piece
			{
				{ new Point(0, 0), new Point(0, 1), new Point(1, 0), new Point(1, 1) },
				{ new Point(0, 0), new Point(0, 1), new Point(1, 0), new Point(1, 1) },
				{ new Point(0, 0), new Point(0, 1), new Point(1, 0), new Point(1, 1) },
				{ new Point(0, 0), new Point(0, 1), new Point(1, 0), new Point(1, 1) }
			},
			
			// S-Piece
			{
				{ new Point(1, 0), new Point(2, 0), new Point(0, 1), new Point(1, 1) },
				{ new Point(0, 0), new Point(0, 1), new Point(1, 1), new Point(1, 2) },
				{ new Point(1, 0), new Point(2, 0), new Point(0, 1), new Point(1, 1) },
				{ new Point(0, 0), new Point(0, 1), new Point(1, 1), new Point(1, 2) }
			},
			
			// T-Piece
			{
				{ new Point(1, 0), new Point(0, 1), new Point(1, 1), new Point(2, 1) },
				{ new Point(1, 0), new Point(0, 1), new Point(1, 1), new Point(1, 2) },
				{ new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(1, 2) },
				{ new Point(1, 0), new Point(1, 1), new Point(2, 1), new Point(1, 2) }
			},
			
			// Z-Piece
			{
				{ new Point(0, 0), new Point(1, 0), new Point(1, 1), new Point(2, 1) },
				{ new Point(1, 0), new Point(0, 1), new Point(1, 1), new Point(0, 2) },
				{ new Point(0, 0), new Point(1, 0), new Point(1, 1), new Point(2, 1) },
				{ new Point(1, 0), new Point(0, 1), new Point(1, 1), new Point(0, 2) }
			}
	};
	
	private final Color[] tetraminoColors = {
		Color.cyan, Color.blue, Color.orange, Color.yellow, Color.green, Color.pink, Color.red
	};
	
	
	


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
