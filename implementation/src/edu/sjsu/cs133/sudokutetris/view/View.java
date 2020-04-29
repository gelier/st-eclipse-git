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
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;


public class View extends JPanel implements ActionListener {
	
	
	private JFrame startFrame; 
	private JFrame gameFrame;
	private JFrame scoreFrame;
	private JFrame inputScoreFrame;
	
	private JLayeredPane gameFrame1;
	
	private JPanel startContent;
	private JPanel gameContent;
	private JPanel scoreContent;
	private JPanel scoreBoard;
	private JPanel boardContent;
	private JPanel blocksContent;
	private JPanel usernamePanel;
	
	private JTextField username;
	
	private boolean game_quit = false;
	
	private JButton start; 
	private JButton quit; 
	private JButton view_score; 
	private JButton quit_game;
	private JButton save;
	private JButton main;
	private JButton submit;
	private JButton retGame;
	
	private ImageIcon logo = new ImageIcon(this.getClass().getResource("title.gif"));
	private ImageIcon scorelogo = new ImageIcon(this.getClass().getResource("highscores.gif"));
	private ImageIcon instructionlogo = new ImageIcon(this.getClass().getResource("instruction.gif"));

	
	private JLabel gameName;
	private JLabel score;
	private JLabel lives;
	private JLabel grid[];
	private JLabel scoreTitle;
	private JLabel instruction;
	

	private String replaceName = "";
	private String userName = "";
	private static final String NEWLINE = "\n";
	
	
	private String points = "800"; //String that holds the points, and parse to Integer to sort highscores 
	
	
	

	
	public View() {
		
		startFrame = new JFrame();
		gameFrame =  new JFrame();
		scoreFrame =  new JFrame();
		
		startContent =  new JPanel();
		gameContent = new JPanel();
		scoreContent = new JPanel();
		scoreBoard = new JPanel();
		
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
		
		instruction = new JLabel(instructionlogo);
		instruction.setSize(new Dimension(50,50));
		
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
				gameFrame.setVisible(false);
				scoreFrame.setVisible(true);
				usernamePanel.setVisible(false);
				drawScoreboard();

			}	
		});
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 System.exit(0);
			}	
		});
		
		startContent.add(gameName);
		instruction.setAlignmentX(Component.CENTER_ALIGNMENT);
		gameName.setAlignmentX(Component.CENTER_ALIGNMENT);
		start.setAlignmentX(Component.CENTER_ALIGNMENT);
		view_score.setAlignmentX(Component.CENTER_ALIGNMENT);
		quit.setAlignmentX(Component.CENTER_ALIGNMENT);
		startContent.add(Box.createRigidArea(new Dimension(100, 50)));
		
		startContent.add(start);
		startContent.add(view_score);
		startContent.add(quit);
		
		startContent.add(instruction);
		startFrame.add(startContent);
		startFrame.setVisible(true);
		
	}
	public void gameWindow() {
		
		quit_game = new JButton("Quit");
		save =  new JButton("Save");
		
		gameFrame.setSize(600, 600);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//gameFrame.setResizable(false);
		gameFrame.setVisible(false);
		gameFrame.setTitle("Sudoku Tetris In Game");
		gameFrame.setBackground(Color.GRAY);
	
		score = new JLabel("00000  ");
		lives = new JLabel("000    ");
		
		gameContent.setBackground(Color.lightGray);
		
		gameContent.setLayout(new BoxLayout(gameContent, BoxLayout.Y_AXIS));
		gameContent.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		score.setBorder(BorderFactory.createTitledBorder("SCORE"));
		lives.setBorder(BorderFactory.createTitledBorder("LIVES"));
		score.setAlignmentX(Component.CENTER_ALIGNMENT);
		lives.setAlignmentX(Component.CENTER_ALIGNMENT);
		quit_game.setAlignmentX(Component.CENTER_ALIGNMENT);
		save.setAlignmentX(Component.CENTER_ALIGNMENT);
	
		gameContent.add(score);
		gameContent.add(Box.createHorizontalGlue());
		gameContent.add(Box.createHorizontalGlue());
		
		
		gameContent.add(lives);
		
		gameContent.add(Box.createRigidArea(new Dimension(10, 320)));
		gameContent.add(save);
		gameContent.add(quit_game);
		
		quit_game.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameFrame.setVisible(false);
				scoreFrame.setVisible(true);
			}	
		});
	
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
		
		scoreFrame.setSize(600, 600);
		scoreFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		scoreFrame.setVisible(false);
		scoreFrame.setResizable(false);
		scoreFrame.setTitle("Sudoku Tetris Scores");
		scoreFrame.setBackground(Color.black);
		scoreContent.setLayout(new BoxLayout(scoreContent, BoxLayout.Y_AXIS));
		scoreContent.setBackground(Color.black);
		scoreContent.add(Box.createRigidArea(new Dimension(15, 15)));
		
	
		addUsername();
	
		scoreFrame.add(scoreContent);
		
	}
	/**
	 * Adding Username window for the score board
	 */
	private void addUsername() {
	
		submit = new JButton("Submit");
		usernamePanel = new JPanel();
		username = new JTextField();
		JLabel usernameTitle = new JLabel("Enter Username");
	
		usernamePanel.setLayout(new BoxLayout(usernamePanel, BoxLayout.Y_AXIS));
		usernamePanel.setBackground(Color.black);
		usernamePanel.setMaximumSize(new Dimension(500,350));
		usernamePanel.add(Box.createRigidArea(new Dimension(170, 190)));
		
		usernameTitle.setForeground(Color.white);
		usernameTitle.setFont(new Font("Serif", Font.BOLD, 40));
		usernameTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		usernamePanel.add(usernameTitle);
		usernamePanel.add(Box.createRigidArea(new Dimension(10, 10)));
		
		username.setAlignmentX(Component.CENTER_ALIGNMENT);
		username.setFont(username.getFont().deriveFont(Font.BOLD, 80f));
		username.setPreferredSize(new Dimension(150,20));
		usernamePanel.add(username);
		
		usernamePanel.add(Box.createRigidArea(new Dimension(10, 10)));
		submit.addActionListener(this);
		submit.setAlignmentX(Component.CENTER_ALIGNMENT);
		usernamePanel.add(submit);
		
		scoreContent.add(usernamePanel);

	
	}
	/**
	 * Method to get the score information from the Model package and display into a 
	 * grid format.
	 */
	private void drawScoreboard() {
		
		FileReader file;
		BufferedReader buff;
		//reset scoreboard, remove all content for the new game
		scoreBoard.removeAll();
		//repaint scoreContent to remove previous components
		scoreContent.revalidate();
		scoreContent.repaint();
		
		scoreBoard.setLayout(new BoxLayout(scoreBoard, BoxLayout.Y_AXIS));
		scoreBoard.setBackground(Color.black);
		scoreBoard.setAlignmentX(Component.CENTER_ALIGNMENT);
		scoreBoard.setMaximumSize(new Dimension(500,550));
	
		//scorelogo.getScaledInstance(300, 280, Image.SCALE_DEFAULT);
		scoreTitle = new JLabel(scorelogo);
		scoreTitle.setSize(50,50);
		scoreTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		scoreBoard.add(scoreTitle, BorderLayout.NORTH);
		scoreBoard.add(Box.createRigidArea(new Dimension(30, 30)));
	
		try {
			file = new FileReader(new File("src/controller/scoreboard.txt"));
			buff = new BufferedReader(file);
			String line;
			while ((line = buff.readLine()) != null) {
				String[] scores = line.split("=");
				
				if(scores[1] != null && scores[0] != null) {
					String score = scores[1];
					String name = scores[0];
					JLabel playerScore = new JLabel(name + " " + score);
					
					playerScore.setAlignmentX(Component.CENTER_ALIGNMENT);
					playerScore.setForeground(Color.white);
					playerScore.setFont(new Font("Serif", Font.BOLD, 30));
					
					scoreBoard.add(playerScore);
					scoreBoard.add(Box.createRigidArea(new Dimension(10, 10)));
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException ex){
			ex.printStackTrace();
		}	
		scoreContent.add(scoreBoard);
		scoreBoard.revalidate();
		scoreBoard.repaint();
		
		retGame = new JButton("Return to Start");
		retGame.setAlignmentX(Component.CENTER_ALIGNMENT);
		scoreContent.add(Box.createRigidArea(new Dimension(15, 15)));
		retGame.addActionListener(this);
		
		retGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scoreFrame.setVisible(false);
				startFrame.setVisible(true);
			}	
		});
		scoreBoard.add(Box.createRigidArea(new Dimension(20, 20)));
		scoreBoard.add(retGame);
	}
	/**
	 * write username into the file scoreboard.txt in a specified format
	 * @param username String
	 */
	private void storeNameToFile(String username){
		
		BufferedWriter writer = null;
		try {
			//check if username is invalid
			if (isInvalidName(username) == false) {
				//if not write the name into the file
				//src/edu/sjsu/cs133/sudokutetris/model/scoreboard.txt
				writer = new BufferedWriter(new FileWriter("src/controller/scoreboard.txt", true));
				writer.append(username+"="+this.points.strip()+NEWLINE);
				writer.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * the method checks the validity of the parameter String username,
	 * if the username is already in stored in the file. 
	 * @param username String
	 * @return a boolean invalid value
	 */
	private boolean isInvalidName(String username) {
		
		boolean invalid = false;
		//get all the scores from the file
		String[] array = getHighscores();
		//iterate through the array
		for(int index = 0; index < array.length; index++){ 
			if (array[index] != null) {
				String[] score = stringTokenize(array[index]);
				//check if the current name is equal to the current element
				if (score[0].equals(username)) {
					invalid = true;
					break;
				}
				else {
					invalid = false;
				} 
			}
		} 	
		return invalid;
	}
	private String[] stringTokenize(String splitstr) {
		
		String delim = "=";
		StringTokenizer st = new StringTokenizer(splitstr,delim);
		String[] array = new String[2];
		int index = 0 ;
		while (st.hasMoreElements()) {
			array[index] = (String) st.nextElement();
			index++;
		}
		return array;
	}
	/**
	 * Check if the final score of the player qualifies as a high score
	 * @param finalScore int
	 * @return a boolean value if the currentScore qualifies as a high score
	 */
	private boolean isHighscore(int finalScore) {
	
		boolean isHighScore = false;
		String[] array = getHighscores();
		int scoreToReplace = Integer.parseInt(points.strip());
		int scoreCount = 0;
		//iterate through the array that has the scores from the scoreboard.txt
		for(int index = 0; index < array.length; index++){ 
			if (array[index] != null) {
				scoreCount++;
				String[] score = array[index].split("=");
				//check if the current score beat at least one of the highscores
				if(Integer.parseInt(score[1].strip()) < scoreToReplace){ 
					   replaceName = score[0];
					   isHighScore = true;
				} 
			}
		} 
		if (scoreCount < 5) {
			isHighScore = true;
		}
		return isHighScore;
	}
	/**
	 * Read the scoreboard.txt and store the values into String array then return the array
	 * @return an array of String
	 */
	private String[] getHighscores() {
		
		FileReader file;
		BufferedReader buff;
		String[] array = new String[5];;
		//open file, read file, store content into an array
		try {
			file = new FileReader(new File("src/controller/scoreboard.txt"));
			buff = new BufferedReader(file);
			String line;
			int index = 0;
			while ((line = buff.readLine()) != null) {
				String[] scores = line.split("=");
				if(scores[1] != null && scores[0] != null)
					array[index]=line;
				index++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException ex){
			ex.printStackTrace();
		}	
		return array;
	}
	/**
	 * Iterate through the array and count how many elements in it
	 * @param an array of String
	 * @return the count of elements in the array
	 */
	public int getArrayCount(String[] array) {
		
		int count = 0;
		//count the element of the array
		for(int index = 0; index < array.length; index++) {
			if (array[index] != null)
				count++;
		}
		return count;
	}
	/**
	 * check if the name entered by the player is already in the scoreboard
	 * @param currentName String
	 * @return a boolean value that returns if needed replacing is need to the final score 
	 */
	public boolean replaceName(String currentName) {
		
		boolean replacing = false;
		String[] array = getHighscores();
		int count = getArrayCount(array);
		//iterate through the array
		if (isInvalidName(currentName) == false) {
			if (count == 5 ) {
				for(int index = 0; index < array.length; index++){ 
					if (array[index] != null) {
						count++;
						String[] score = array[index].split("=");
						//check if the name of the current element is equal to username
						if(replaceName.equals(score[0])) {
							replacing = true;
							array[index]= currentName+"="+Integer.parseInt(points.strip());
						}
					}
				} 
			}
			if (replacing == true) {
				setHighscoresTofile(replacing, array);
			}
		}
		return replacing;
	}
	/**
	 * write the replace score into the file in the correct order
	 * @param status boolean and array String[]
	 */
	public void setHighscoresTofile(boolean status, String[] array) {
		
		if (status == true) {
			BufferedWriter writer = null;
			try {
				sortArray(array);
				//store array content to the file
				writer = new BufferedWriter(new FileWriter("src/controller/scoreboard.txt", false));
				for(int index = 0; index < array.length; index++){ 
					if(array[index] != null) {
						writer.append(array[index]+NEWLINE);
					}
				}
				writer.close();	
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	/**
	 * sort the array in descending order
	 * @param status boolean and array String[]
	 */
	private void sortArray(String[] array) {
		
		int count = getArrayCount(array);
		String temp = "";
		for (int index = 0; index < count; index++) 
	    {
			String[] score = array[index].split("=");
	            for (int j = index + 1; j < count; j++) { 
	            	String[] score1 = array[j].split("=");
	                if (Integer.parseInt(score[1].strip()) < Integer.parseInt(score1[1].strip())) 
	                {
	                    temp =  array[index];
	                    array[index] = array[j];
	                    array[j] = temp;
	                }
	            }
	    }
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
		
		//update scores when submit button is pressed
		if (e.getSource() == submit) {
			System.out.println("in submit");
			int currentscore = Integer.parseInt(points.strip());
			if(isHighscore(currentscore) == true) {
				userName = username.getText();
				if(replaceName(userName) != true) {
					storeNameToFile(username.getText());
				}
			}
			usernamePanel.setVisible(false);
			drawScoreboard();
		}
		//update scores when return button is pressed
		if (e.getSource() == retGame) {
			//reset scoreContent
			scoreContent.removeAll();
			scoreWindow();	
			scoreContent.revalidate();
			scoreContent.repaint();
		}
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
		drawBlocks(g);
		
	}
	// TODO draw tetris blocks 
	public void drawBlocks(Graphics g) {
		
	}
	
	// TODO draw lives, needs image
	public void drawLives(Graphics g) {
		
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
