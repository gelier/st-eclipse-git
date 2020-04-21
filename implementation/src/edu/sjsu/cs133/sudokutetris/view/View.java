package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;


public class View extends JPanel implements ActionListener {
	
	
	JFrame startFrame; 
	JFrame gameFrame;
	JFrame scoreFrame;
	
	JPanel startContent;
	JPanel gameContent;
	JPanel scoreContent;
	JPanel scoreBoard;
	JPanel boardContent;
	JPanel blocksContent;
	
	
	JButton start; 
	JButton quit; 
	JButton view_score; 
	
	private ImageIcon logo = new ImageIcon(this.getClass().getResource("title.gif"));
	
	private JLabel gameName;
	private JLabel score;
	private JLabel lives;
	private JLabel grid[];
	
	public View() {
		
		startFrame = new JFrame();
		gameFrame =  new JFrame();
		scoreFrame =  new JFrame();
		startContent =  new JPanel();
		gameContent = new JPanel();
		scoreContent = new JPanel();
		scoreBoard = new JPanel();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void start(){
		 launchWindow();
		 gameWindow();
		 scoreWindow();
	}
	
	public void launchWindow() {
		
		start = new JButton("Start");
		quit  = new JButton("Quit");
		view_score  = new JButton("Scores");
		
		startFrame.setSize(600, 600);
		startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		startFrame.setResizable(false);
		startFrame.setTitle("Sudoku Tetris");
		// Create welcome screen panel.
		startContent = new JPanel();
		//add the sudokuteris logo
		gameName = new JLabel(logo);
		gameName.setSize(new Dimension(50,50));
		
		startContent.setLayout(new BoxLayout(startContent, BoxLayout.Y_AXIS));
		startContent.setBackground(Color.black);
		
		start = new JButton("Start");
		quit  = new JButton("Quit");
		view_score  = new JButton("Scores");
		
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startFrame.setVisible(false);
				gameFrame.setVisible(true);
			}	
		});
		view_score.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}	
		});
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}	
		});
		
		startContent.add(gameName);
		gameName.setAlignmentX(Component.CENTER_ALIGNMENT);
		start.setAlignmentX(Component.CENTER_ALIGNMENT);
		view_score.setAlignmentX(Component.CENTER_ALIGNMENT);
		quit.setAlignmentX(Component.CENTER_ALIGNMENT);
		startContent.add(Box.createRigidArea(new Dimension(100, 50)));
		
		startContent.add(start);
		startContent.add(view_score);
		startContent.add(quit);
		
		startFrame.add(startContent);
		startFrame.setVisible(true);
		
	}
	public void gameWindow() {
		
		gameFrame.setSize(600, 600);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//gameFrame.setResizable(false);
		gameFrame.setVisible(false);
		gameFrame.setTitle("Sudoku Tetris In Game");
		gameFrame.setBackground(Color.GRAY);
	
		score = new JLabel("00000  ");
		lives = new JLabel("000    ");
		
		gameContent.setBackground(Color.lightGray);
		
		gameContent.setLayout(new BoxLayout(gameContent, BoxLayout.X_AXIS));
		gameContent.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		score.setBorder(BorderFactory.createTitledBorder("SCORE"));
		lives.setBorder(BorderFactory.createTitledBorder("LIVES"));
		score.setAlignmentX(Component.TOP_ALIGNMENT);
		lives.setAlignmentX(Component.BOTTOM_ALIGNMENT);
	
		gameContent.add(score);
		gameContent.add(Box.createVerticalGlue());
		gameContent.add(Box.createVerticalGlue());
		
		gameContent.add(lives);

		gameContent.add(Box.createRigidArea(new Dimension(10, 50)));
		gameFrame.add(gameContent, BorderLayout.EAST);
		

		boardContent = new JPanel();
		boardContent.setLayout(new GridLayout(5,5));
		boardContent.setBackground(Color.white);

		
		
		createGrid();

		//actionContent.setBackground(Color.white.darker().darker());
		boardContent.add(Box.createRigidArea(new Dimension(10, 100)));
		gameFrame.add(boardContent, BorderLayout.CENTER);
		
		
		blocksContent = new JPanel();
		blocksContent.setBackground(Color.white.brighter());
		blocksContent.add(Box.createRigidArea(new Dimension(10, 100)));
		gameFrame.add(blocksContent, BorderLayout.SOUTH);
		
		
		
	}
	public void scoreWindow() {
		
		gameFrame.setSize(600, 600);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setResizable(false);
		gameFrame.setTitle("Sudoku Tetris In Game");
			
	}
	
	public void createGrid() {
		
		grid = new JLabel[16];
		for(int index = 0; index < 4 ;index++) {
			for(int i = 0; i < 4; i++) {
				grid[index] = new JLabel("x");
				Border border = BorderFactory.createLineBorder(Color.black, 2);
		        // set the border of this component
				grid[index].setBorder(border);
				grid[index].setBackground(Color.white);
				boardContent.add(grid[index]);
			}
		}
	}
	/* 
	 * 
	 * 
	 * */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		repaint(g);
		
	}
	private void repaint(Graphics g) {
		// TODO Auto-generated method stub
		
	}
	// TODO draw tetris blocks 
	public void drawBlocks() {
		
	}
	// TODO draw lives, needs image
	public void drawLives() {
		
	}
	// TODO draw timer
	public void drawTimer() {
		
	}
	// TODO Auto-generated method stub
	public void drawPlayerScore() {
		
	}
	// TODO Auto-generated method stub
	public void setPoints(int points) {
		
	}
	// TODO Auto-generated method stub
	public void setBlocksVisibility() {
		
	}
	// TODO Auto-generated method stub
	public void setLives() {
		
	}
	// TODO Auto-generated method stub
	public void setTimer() {
		
	}
	public void updateGrid() {
		
	}
	
	// TODO Auto-generated method stub
	@SuppressWarnings("unused")
	private class MouseHandler implements MouseMotionListener {

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
}
