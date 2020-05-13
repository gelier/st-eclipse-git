package view;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Random;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.awt.geom.Rectangle2D;

public class View extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JFrame startFrame;
	private JFrame gameFrame;
	private JFrame scoreFrame;

	private JPanel startContent;
	private JPanel gameContent;
	private JPanel scoreContent;
	private JPanel scoreBoard;
	private JPanel instructionContent;
	private JPanel usernamePanel;

	private JTextField username;

	private JButton start;
	private JButton quit;
	private JButton view_score;
	private JButton quit_game;
	private JButton submit;
	private JButton retGame;

	private final int LEVEL_1_TIME = 60;

	private final int X_DEFAULT = 25;
	private final int Y_DEFAULT = 450;

	private final int X_CHOSEN = 250;
	private final int Y_CHOSEN = 400;

	private ImageIcon logo = new ImageIcon(new ImageIcon(this.getClass().getResource("gametitle.gif")).getImage()
			.getScaledInstance(590, 90, Image.SCALE_DEFAULT));
	private ImageIcon scorelogo = new ImageIcon(this.getClass().getResource("highscores.gif"));
	private ImageIcon instructionlogo = new ImageIcon(new ImageIcon(this.getClass().getResource("game_ins.gif"))
			.getImage().getScaledInstance(490, 60, Image.SCALE_DEFAULT));

	private ImageIcon t1 = new ImageIcon(new ImageIcon(this.getClass().getResource("t1.png")).getImage()
			.getScaledInstance(75, 75, Image.SCALE_DEFAULT));
	private ImageIcon t2 = new ImageIcon(new ImageIcon(this.getClass().getResource("t2.png")).getImage()
			.getScaledInstance(112, 37, Image.SCALE_DEFAULT));
	private ImageIcon t3 = new ImageIcon(new ImageIcon(this.getClass().getResource("t3.png")).getImage()
			.getScaledInstance(37, 75, Image.SCALE_DEFAULT));
	private ImageIcon t4 = new ImageIcon(new ImageIcon(this.getClass().getResource("t4.png")).getImage()
			.getScaledInstance(112, 37, Image.SCALE_DEFAULT));
	private ImageIcon t5 = new ImageIcon(new ImageIcon(this.getClass().getResource("t5.png")).getImage()
			.getScaledInstance(37, 75, Image.SCALE_DEFAULT));
	private ImageIcon t6 = new ImageIcon(new ImageIcon(this.getClass().getResource("t6.png")).getImage()
			.getScaledInstance(37, 112, Image.SCALE_DEFAULT));
	private ImageIcon t7 = new ImageIcon(new ImageIcon(this.getClass().getResource("t7.png")).getImage()
			.getScaledInstance(37, 75, Image.SCALE_DEFAULT));
	private ImageIcon gr = new ImageIcon(new ImageIcon(this.getClass().getResource("mygrid.png")).getImage()
			.getScaledInstance(240, 240, Image.SCALE_DEFAULT));

	private Image block_1_img = t1.getImage();
	private Image block_2_img = t2.getImage();
	private Image block_3_img = t3.getImage();
	private Image block_4_img = t4.getImage();
	private Image block_5_img = t5.getImage();
	private Image block_6_img = t6.getImage();
	private Image block_7_img = t7.getImage();

	private Image grid_img = gr.getImage();

	private MoveableImage block_1 = new MoveableImage(X_DEFAULT, Y_DEFAULT, block_1_img);
	private MoveableImage block_2 = new MoveableImage(X_DEFAULT, Y_DEFAULT, block_2_img);
	private MoveableImage block_3 = new MoveableImage(X_DEFAULT, Y_DEFAULT, block_3_img);
	private MoveableImage block_4 = new MoveableImage(X_DEFAULT, Y_DEFAULT, block_4_img);
	private MoveableImage block_5 = new MoveableImage(X_DEFAULT, Y_DEFAULT, block_5_img);
	private MoveableImage block_6 = new MoveableImage(X_DEFAULT, Y_DEFAULT, block_6_img);
	private MoveableImage block_7 = new MoveableImage(X_DEFAULT, Y_DEFAULT, block_7_img);

	private MoveableImage grid_ = new MoveableImage(X_DEFAULT, Y_DEFAULT, grid_img);

	private JLabel gameName;
	private JLabel scoreTitle;
	private JLabel instruction;

	private String replaceName = "";
	private String userName = "";
	private static final String NEWLINE = "\n";
	private String points = "2000"; // String that holds the points, and parse to Integer to sort highscores

	long starttime = System.currentTimeMillis();
	long timepassed;
	long secondspassed;

	boolean gameOver = false;

	private int g_y = 0;
	private int g_x = 0;
	int b_index = 0;
	boolean[] placed = new boolean[9];
	boolean block_released = false;
	int x = 60, y = 470;
	boolean dragging = false;
	boolean new_game = false;

	private Timer timer = new Timer(50, this);
	ArrayList<MoveableImage> arr = new ArrayList<MoveableImage>();

	JLabel l = new JLabel(
			"<html>1. Start the Game<br><br>2. A 6 by 6 grid would be generated with a couple of numbers.<br><br>3. Random Tetris Blocks are generated one at a time in the left corner.<br><br>4. The score would be calculated according to time used by the player to solve the sudoku<br><br>5. Place each tetris block inside the grid by considering the following rules : <ul>\r\n"
					+ "  <br><li>no repetition of numbers in the sub-grid</li>\r\n"
					+ "  <br><li>no repetition of the numbers in any of the rows</li>\r\n"
					+ "  <br><li>no repetition of the numbers in any of the rows</li></html>");
	Popup p;
	PopupFactory pf;
	JButton close_ins;

	public View() {

		startFrame = new JFrame();
		gameFrame = new JFrame();
		scoreFrame = new JFrame();

		startContent = new JPanel();
		gameContent = new JPanel();
		scoreContent = new JPanel();
		scoreBoard = new JPanel();
		instructionContent = new JPanel();

		pf = new PopupFactory();
		close_ins = new JButton("X");
	}

	public void start() {
		launchWindow();
		gameWindow();
		scoreWindow();
		timer.start();
	}

	public void launchWindow() {

		start = new JButton("Start");
		quit = new JButton("Quit");
		view_score = new JButton("Scores");

		startFrame.setSize(600, 600);
		startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		startFrame.setResizable(false);
		startFrame.setTitle("Sudoku Tetris");
		// Create welcome screen panel.
		startContent = new JPanel();
		// add the sudokuteris logo
		gameName = new JLabel(logo);
		gameName.setSize(new Dimension(50, 100));

		instruction = new JLabel(instructionlogo);
		instruction.setSize(new Dimension(50, 50));

		startContent.setLayout(new BoxLayout(startContent, BoxLayout.Y_AXIS));
		startContent.setBackground(Color.orange);

		start = new JButton("Start");
		quit = new JButton("Quit");
		view_score = new JButton("Scores");

		// instruction

		instructionContent.setBackground(Color.white);
		instructionContent.add(l);
		instructionContent.add(close_ins);
		instructionContent.setLayout(new BoxLayout(instructionContent, BoxLayout.X_AXIS));
		p = pf.getPopup(startFrame, instructionContent, 180, 100);
		JButton b = new JButton("Instructions");

		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p.show();
			}
		});
		close_ins.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p.hide();
			}
		});
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newGame();
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
		startContent.add(Box.createRigidArea(new Dimension(100, 50)));
		startContent.add(gameName);
		instruction.setAlignmentX(Component.CENTER_ALIGNMENT);
		gameName.setAlignmentX(Component.CENTER_ALIGNMENT);
		start.setAlignmentX(Component.CENTER_ALIGNMENT);
		view_score.setAlignmentX(Component.CENTER_ALIGNMENT);
		b.setAlignmentX(Component.CENTER_ALIGNMENT);
		quit.setAlignmentX(Component.CENTER_ALIGNMENT);
		startContent.add(Box.createRigidArea(new Dimension(100, 50)));

		startContent.add(start);
		startContent.add(view_score);

		startContent.add(b);

		startContent.add(quit);
		startContent.add(Box.createRigidArea(new Dimension(100, 50)));
		startFrame.add(startContent);

		startFrame.setVisible(true);

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

	@SuppressWarnings("deprecation")
	public void gameWindow() {

		quit_game = new JButton("Quit");

		gameFrame.setSize(600, 600);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setBackground(Color.pink);
		gameFrame.setVisible(false);
		gameFrame.setResizable(false);

		quit_game.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameFrame.setVisible(false);
				scoreFrame.setVisible(true);
			}
		});
		quit_game.setAlignmentX(Component.RIGHT_ALIGNMENT);

		gameContent.setLayout(new BoxLayout(gameContent, BoxLayout.X_AXIS));
		gameContent.add(Box.createRigidArea(new Dimension(520, 10)));
		gameContent.add(quit_game);
		gameFrame.add(gameContent, BorderLayout.SOUTH);

		new_game = true;

		setBlocks();

		this.setBackground(Color.CYAN.darker());
		gameFrame.add(this);
		timer = new Timer(50, this);

	}

	public void setBlocks() {

		arr.add(block_1);
		arr.add(block_2);
		arr.add(block_3);
		arr.add(block_4);
		arr.add(block_5);
		arr.add(block_6);
		arr.add(block_7);

		arr.get(0).setName("t1");
		arr.get(1).setName("t2");
		arr.get(2).setName("t3");
		arr.get(3).setName("t4");
		arr.get(4).setName("t5");
		arr.get(5).setName("t6");
		arr.get(6).setName("t7");

		// Collections.shuffle(arr);

		arr.get(0).setX(X_CHOSEN);
		arr.get(0).setY(Y_CHOSEN);
	}

	public MoveableImage getBlock(int index) {

		MoveableImage block = null;
		switch (index) {
		case 1:
			block = arr.get(0);
			break;
		case 2:
			block = arr.get(1);
			break;
		case 3:
			block = arr.get(2);
			break;
		case 4:
			block = arr.get(3);
			break;
		case 5:
			block = arr.get(4);
			break;
		case 6:
			block = arr.get(5);
			break;
		case 7:
			block = arr.get(6);
			break;
		}
		return block;
	}

	public void drawChoices(Graphics g) {
		g.setFont(new Font("Serif", Font.BOLD, 20));
		g.setColor(Color.white);
		g.drawString("NEXT BLOCK: ", 10, 405);
		g.drawRect(10, 410, 150, 130);
	}

	public void drawMyGrid(Graphics g) {
		grid_.draw(g, this);
		grid_.setX(170);
		grid_.setY(100);
	}

	/*
	 * Will generate four random numbers
	 * 
	 */
	@SuppressWarnings("deprecation")
	public ArrayList<Integer> generateChoices() {

		ArrayList<Integer> blocks = new ArrayList<Integer>();

		for (int i = 0; i < 7; i++) {
			blocks.add(new Integer(i));
		}
		Collections.shuffle(blocks);
		return blocks;
	}

	public void drawGrid(Graphics g) {

		for (int index = 0, x1 = 170, y1 = 100; index < 6; index++) {
			for (int i = 0; i < 6; i++, x1 += 40) {

				Graphics2D g2 = (Graphics2D) g;
				g2.setColor(Color.white);
				g2.fillRect(x1, y1, 40, 40);
				g2.setColor(Color.orange);

				g2.setColor(Color.orange);
				g2.drawRect(x1, y1, 39, 39);

			}
			y1 += 40;
			x1 = 170;
		}
	}

	public void handle(Graphics g) {

		drawChoices(g);
		drawMyGrid(g);

		if (b_index == 0) {

			drawBlock(g, b_index);
			if (arr.get(b_index).isPlaced() == false) {
				mouseHandler(g, b_index);
			}
			drawBlock(g, b_index + 1);

			if (arr.get(b_index).isPlaced()) {
				arr.get(b_index + 1).setX(X_CHOSEN);
				arr.get(b_index + 1).setY(Y_CHOSEN);
				placed[0] = true;
				b_index++;
			}
		}
		if (b_index == 1) {

			drawBlock(g, b_index);
			if (arr.get(b_index).isPlaced() == false) {
				mouseHandler(g, b_index);
			}
			drawBlock(g, b_index + 1);

			if (arr.get(b_index).isPlaced()) {
				arr.get(b_index + 1).setX(X_CHOSEN);
				arr.get(b_index + 1).setY(Y_CHOSEN);
				placed[1] = true;
				b_index++;
			}
		}
		if (b_index == 2) {
			drawBlock(g, b_index);
			if (arr.get(b_index).isPlaced() == false) {
				mouseHandler(g, b_index);
			}
			drawBlock(g, b_index + 1);

			if (arr.get(b_index).isPlaced()) {
				arr.get(b_index + 1).setX(X_CHOSEN);
				arr.get(b_index + 1).setY(Y_CHOSEN);
				placed[2] = true;
				b_index++;
			}
		}
		if (b_index == 3) {
			drawBlock(g, b_index);
			if (arr.get(b_index).isPlaced() == false) {
				mouseHandler(g, b_index);
			}
			drawBlock(g, b_index + 1);

			if (arr.get(b_index).isPlaced()) {
				arr.get(b_index + 1).setX(X_CHOSEN);
				arr.get(b_index + 1).setY(Y_CHOSEN);
				placed[3] = true;
				b_index++;
			}
		}
		if (b_index == 4) {
			drawBlock(g, b_index);
			if (arr.get(b_index).isPlaced() == false) {
				mouseHandler(g, b_index);
			}
			drawBlock(g, b_index + 1);

			if (arr.get(b_index).isPlaced()) {
				arr.get(b_index + 1).setX(X_CHOSEN);
				arr.get(b_index + 1).setY(Y_CHOSEN);
				placed[4] = true;
				b_index++;
			}

		}
		if (b_index == 5) {
			drawBlock(g, b_index);
			if (arr.get(b_index).isPlaced() == false) {
				mouseHandler(g, b_index);
			}
			drawBlock(g, b_index + 1);

			if (arr.get(b_index).isPlaced()) {
				arr.get(b_index + 1).setX(X_CHOSEN);
				arr.get(b_index + 1).setY(Y_CHOSEN);
				placed[5] = true;
				b_index++;
			}
		}
		if (b_index == 6) {
			drawBlock(g, b_index);
			if (arr.get(b_index).isPlaced() == false) {

				mouseHandler(g, b_index);
			}
			if (arr.get(b_index).isPlaced()) {
				placed[6] = true;
			}
		}

		if (placed[0] == true) {
			drawBlock(g, 0);
		}
		if (placed[1] == true) {
			drawBlock(g, 1);
		}
		if (placed[2] == true) {
			drawBlock(g, 2);
		}
		if (placed[3] == true) {
			drawBlock(g, 3);
		}
		if (placed[4] == true) {
			drawBlock(g, 4);
		}
		if (placed[5] == true) {
			drawBlock(g, 5);
		}
		if (placed[6] == true) {
			drawBlock(g, 6);
		}

		if (b_index == 6 && arr.get(6).isPlaced()) {
			System.out.println("Won " + b_index);
			if (secondspassed > 0) {

				gameWon = true;
			}
		}
		if (secondspassed < 0) {

			gameOver = true;
		}

	}

	public void newGame() {

		gameFrame.revalidate();
		gameFrame.repaint();
		gameContent.revalidate();
		gameContent.repaint();
		this.revalidate();
		b_index = 0;
		gameWon = false;
		gameOver = false;
	}

	public void mouseHandler(Graphics g, int index) {

		if (arr.get(index).getName().equals("t1")) {
			arr.get(index).setImageHeight(t1.getIconHeight());
			arr.get(index).setImageWidth(t1.getIconWidth());
			mouse_t1(g, index);
			// drawBlock(g, index);
		}

		if (arr.get(index).getName().equals("t2")) {
			arr.get(index).setImageHeight(t2.getIconHeight());
			arr.get(index).setImageWidth(t2.getIconWidth());
			mouse_t2(g, arr.get(index), index);
			// drawBlock(g, index);
		}

		if (arr.get(index).getName().equals("t3")) {
			arr.get(index).setImageHeight(t3.getIconHeight());
			arr.get(index).setImageWidth(t3.getIconWidth());
			mouse_t3(g, index);
			// drawBlock(g, index);
		}

		if (arr.get(index).getName().equals("t4")) {
			arr.get(index).setImageHeight(t4.getIconHeight());
			arr.get(index).setImageWidth(t4.getIconWidth());
			mouse_t4(g, index);
			// drawBlock(g, index);
		}

		if (arr.get(index).getName().equals("t5")) {
			arr.get(index).setImageHeight(t5.getIconHeight());
			arr.get(index).setImageWidth(t5.getIconWidth());
			mouse_t5(g, index);
			// drawBlock(g, index);
		}

		if (arr.get(index).getName().equals("t6")) {

			arr.get(index).setImageHeight(t6.getIconHeight());
			arr.get(index).setImageWidth(t6.getIconWidth());
			mouse_t6(g, index);
			// drawBlock(g, index);
		}

		if (arr.get(index).getName().equals("t7")) {

			arr.get(index).setImageHeight(t7.getIconHeight());
			arr.get(index).setImageWidth(t7.getIconWidth());
			mouse_t7(g, index);
			// drawBlock(g, index);
		}

	}

	public void drawBlock(Graphics g, int index) {

		arr.get(index).setVisible(true);
		if (arr.get(index).getVisible()) {
			if (arr.get(index).isPlaced() == true) {
				arr.get(index).draw(g, this);
				arr.get(index).setX(arr.get(index).getX());
				arr.get(index).setY(arr.get(index).getY());
			} else {
				arr.get(index).draw(g, this);
				arr.get(index).setX(arr.get(index).getX());
				arr.get(index).setY(arr.get(index).getY());
			}

		}
	}

	boolean gameWon = false;

	public void checkIfsolve() {

		for (int index = 0; index < 7; index++) {
			if (arr.get(index).isPlaced() == true) {
				gameWon = true;
			}
		}
	}

	public boolean check_collision(int x, int y, int index) {
		boolean collision = false;
		boolean isPlaced = false;
		int collision_x = arr.get(index).getImageWitdh();
		int collision_y = arr.get(index).getImageHeight();

		if (arr.get(index).getDragging() == true && arr.get(index).isPlaced() == false) {

			collision_y += y;
			collision_x += x;

			// next X block collision first row
			if (y >= 100 && y <= 140) {
				// [3][5] collision

				if (collision_x >= 360) {
					System.out.println("X= " + x + " Y=" + y);
					System.out.println("Collision X= " + collision_x + " YWidth=" + arr.get(index).getImageWitdh());
					System.out.println("Collision X= " + collision_x + " YWidth=" + arr.get(index).getImageHeight());
					System.out.println("Collision X1");
					collision = true;
				}
			}
			// next X block collision second row
			if (y >= 140 && y <= 180) {
				// [4][2]

				if (collision_x >= 330 && collision_x <= 400 + 40) {
					System.out.println("Collision X2");
					collision = true;
				}
			}
			// next X block collision third row
			if (y >= 180 && y <= 220) {
				// [6][5]

			}
			// next X block collision fourth row
			if (y >= 220 && y <= 260) {
				// [6][4]

				if (collision_x >= 360 && collision_x <= 400) {
					System.out.println("X= " + x + " Y=" + y);
					System.out.println("cX= " + collision_x + " x1=" + collision_x);
					System.out.println("Collision X3");
					System.out.println("Collision X4");
					collision = true;
				}
			}
			// next X block collision fifth row
			if (y >= 260 && y <= 300) {
				// [6] and [5]
				if (collision_x >= 420) {
					System.out.println("Colliassion X6");
					collision = true;
				}

			}

			// next X block collision sixth row
			if (y >= 300 && y <= 340) {
				// [5][3][1] and [2]

				if (collision_x >= 330 && collision_x <= 360 + 70) {
					System.out.println("Collision X7");
					collision = true;
				}
			}

			// next Y block collision first column
			if (x >= 170 && x <= 210) {
				// [6]
				// [5]

				if (collision_y >= 260 && collision_y <= 360 + 40) {
					System.out.println("Collision Y1 " + t2.getIconHeight() + " y =" + y);
					collision = true;
				}
			}
			// next Y block collision second column
			if (x >= 210 && x <= 250) {
				// [6]

				if (collision_y >= 180 && collision_y <= 240) {
					System.out.println("Collision Y2.1");
					collision = true;
				}
				if (collision_y >= 300) {
					System.out.println("Collision Y2.2");
					collision = true;
				}
			}
			// next Y block collision third column
			if (x >= 250 && x <= 290) {
				// [5] and [1]

				if (collision_y >= 180 && collision_y <= 220) {
					System.out.println("Collision Y3");
					collision = true;
				}
				if (collision_y >= 300) {
					System.out.println("Collision Y3.3");
					collision = true;
				}
			}
			// next Y block collision fourth column
			if (x >= 290 && x <= 330) {

			}
			// next Y block collision fifth column
			if (x >= 330 && x <= 360) {
				// [6]
				// [5]
				// [2]
				if (collision_y >= 220 && collision_y <= 340) {
					System.out.println("Collision Y3.4");
					collision = true;
				}
				if (collision_x >= 360) {
					System.out.println("Collision y3.5");
					collision = true;
				}
			}
			// next Y block collision sixth column
			if (x >= 360 && x <= 400) {

				if (collision_y >= 340) {
					System.out.println("Collision Y4");
					collision = true;
				}
			}
			// standard collision
			if (x >= 170 && x <= 400) {

				// [2][1][4]
				if (x >= 170 && x <= 290) {

					// [2] [1] [4] Y
					if (y >= 100 && y <= 140) {
						System.out.println("Collision assigend gx " + collision);
						collision = true;
					}

				}
				// [3][5]
				// [4][2]
				if (x >= 330 && x <= 400) {

					if (y >= 100 && y <= 180) {
						System.out.println("Collision S1");
						collision = true;
					}
				}
				// [6][5]
				if (x >= 210 && x <= 290) {

					if (y >= 180 && y <= 220) {
						System.out.println("Collision S2");
						collision = true;
					}
				}
				// [6][4]

				if (x >= 330 && x <= 400) {

					if (y >= 220 && y <= 260) {
						System.out.println("Collision S3");
						collision = true;
					}
				}
				// [6]
				// [5]
				// [2]
				if (x >= 330 && x <= 360) {

					if (y >= 220 && y <= 340) {
						System.out.println("Collision S4");
						collision = true;
					}
				}
				// [6]
				// [5]
				if (x >= 170 && x <= 210) {

					if (y >= 260 && y <= 340) {
						System.out.println("Collision S5");
						collision = true;
					}
				}
				// [5][3][1]
				if (x >= 170 && x <= 290) {

					if (y >= 300 && y <= 340) {
						System.out.println("Collision S6");
						collision = true;
					}
				}
			}
			if (arr.get(0).isPlaced() == true) {

				if (x >= arr.get(0).getX() && x <= (arr.get(0).getX() + arr.get(0).getImageWitdh())) {

					if (y >= arr.get(0).getY() && y <= (arr.get(0).getY() + arr.get(0).getImageHeight())) {
						System.out.println("box collsssision S6");
						collision = true;
					}
				}
			}
			if (arr.get(1).isPlaced() == true) {

				if (x >= arr.get(1).getX() && x <= (arr.get(1).getX() + arr.get(1).getImageWitdh())) {

					if (y >= arr.get(1).getY() && y <= (arr.get(1).getY() + arr.get(1).getImageHeight())) {
						System.out.println("box collsssision S6");
						collision = true;
					}
				}
			}
			if (arr.get(2).isPlaced() == true) {

				if (x >= arr.get(2).getX() && x <= (arr.get(2).getX() + arr.get(2).getImageWitdh())) {

					if (y >= arr.get(2).getY() && y <= (arr.get(2).getY() + arr.get(2).getImageHeight())) {
						System.out.println("box collsssision S6");
						collision = true;
					}
				}
			}
			if (arr.get(3).isPlaced() == true) {

				if (x >= arr.get(3).getX() && x <= (arr.get(3).getX() + arr.get(3).getImageWitdh())) {

					if (y >= arr.get(3).getY() && y <= (arr.get(3).getY() + arr.get(3).getImageHeight())) {
						System.out.println("box collsssision S6");
						collision = true;
					}
				}
			}
			if (arr.get(4).isPlaced() == true) {

				if (x >= arr.get(4).getX() && x <= (arr.get(4).getX() + arr.get(4).getImageWitdh())) {

					if (y >= arr.get(4).getY() && y <= (arr.get(4).getY() + arr.get(4).getImageHeight())) {
						System.out.println("box collsssision S6");
						collision = true;
					}
				}
			}
			if (arr.get(5).isPlaced() == true) {

				if (x >= arr.get(5).getX() && x <= (arr.get(5).getX() + arr.get(5).getImageWitdh())) {

					if (y >= arr.get(5).getY() && y <= (arr.get(5).getY() + arr.get(5).getImageHeight())) {
						System.out.println("box collsssision S6");
						collision = true;
					}
				}
			}
			if (arr.get(6).isPlaced() == true) {

				if (x >= arr.get(6).getX() && x <= (arr.get(6).getX() + arr.get(6).getImageWitdh())) {

					if (y >= arr.get(6).getY() && y <= (arr.get(6).getY() + arr.get(6).getImageHeight())) {
						System.out.println("box collsssision S6");
						collision = true;
					}
				}
			}

			for (g_x = 170; g_x <= 370; g_x += 40) {
				if (x >= g_x && x < g_x + 40) {
					for (g_y = 100; g_y <= 300; g_y += 40) {
						if ((y >= g_y && y <= g_y + 40) && collision == false) {
							arr.get(index).setX(g_x + 4);
							arr.get(index).setY(g_y + 1);
							arr.get(index).setDragging(false);
							arr.get(index).setIsplaced(true);
							isPlaced = true;
						}
					}
				}
			}

		}
		return isPlaced;
	}

	public void mouse_t1(Graphics g, int index) {

		addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent e) {

			}

			public void mouseReleased(MouseEvent e) {

				x = e.getX();
				y = e.getY();
				if (arr.get(index).isPlaced() == false) {
					if (check_collision(x, y, index) == true) {
						arr.get(index).setIsplaced(true);
						arr.get(index).setX(arr.get(index).getX());
						arr.get(index).setY(arr.get(index).getY());
					} else {
						arr.get(index).setX(X_CHOSEN);
						arr.get(index).setY(Y_CHOSEN);
					}
				}

				block_released = true;
				dragging = false;
			}
		});
		addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent e) {

				block_released = false;
				arr.get(index).setDragging(true);
				if (arr.get(index).isPlaced() == false) {
					arr.get(index).setX(e.getX());
					arr.get(index).setY(e.getY());
					dragging = true;
				}

				block_released = false;

			}
		});
	}

	public void mouse_t2(Graphics g, MoveableImage b, int index) {

		addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				x = e.getX();
				y = e.getY();
				if (arr.get(index).isPlaced() == false) {
					if (check_collision(x, y, index) == true) {
						arr.get(index).setIsplaced(true);
						arr.get(index).setX(arr.get(index).getX());
						arr.get(index).setY(arr.get(index).getY());
					} else {
						arr.get(index).setX(X_CHOSEN);
						arr.get(index).setY(Y_CHOSEN);
					}
				}

				block_released = true;
				dragging = false;
			}
		});
		addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent e) {
				block_released = false;
				arr.get(index).setDragging(true);
				if (arr.get(index).isPlaced() == false) {
					arr.get(index).setX(e.getX());
					arr.get(index).setY(e.getY());
					dragging = true;
				}

				block_released = false;

			}
		});
	}

	public void mouse_t3(Graphics g, int index) {

		addMouseListener(new MouseAdapter() {

			public void mouseReleased(MouseEvent e) {

				x = e.getX();
				y = e.getY();
				if (arr.get(index).isPlaced() == false) {
					if (check_collision(x, y, index) == true) {
						arr.get(index).setIsplaced(true);
						arr.get(index).setX(arr.get(index).getX());
						arr.get(index).setY(arr.get(index).getY());
					} else {
						arr.get(index).setX(X_CHOSEN);
						arr.get(index).setY(Y_CHOSEN);
					}
				}

				block_released = true;
				dragging = false;
			}
		});
		addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent e) {

				block_released = false;
				arr.get(index).setDragging(true);
				if (arr.get(index).isPlaced() == false) {
					arr.get(index).setX(e.getX());
					arr.get(index).setY(e.getY());
					dragging = true;
				}

				block_released = false;

			}
		});
	}

	public void mouse_t4(Graphics g, int index) {

		addMouseListener(new MouseAdapter() {

			public void mouseReleased(MouseEvent e) {

				x = e.getX();
				y = e.getY();
				if (arr.get(index).isPlaced() == false) {
					if (check_collision(x, y, index) == true) {
						arr.get(index).setIsplaced(true);
						arr.get(index).setX(arr.get(index).getX());
						arr.get(index).setY(arr.get(index).getY());
					} else {
						arr.get(index).setX(X_CHOSEN);
						arr.get(index).setY(Y_CHOSEN);
					}
				}

				block_released = true;
				dragging = false;
			}
		});
		addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent e) {

				block_released = false;
				arr.get(index).setDragging(true);
				if (arr.get(index).isPlaced() == false) {
					arr.get(index).setX(e.getX());
					arr.get(index).setY(e.getY());
					dragging = true;
				}

				block_released = false;

			}
		});
	}

	public void mouse_t5(Graphics g, int index) {

		addMouseListener(new MouseAdapter() {

			public void mouseReleased(MouseEvent e) {

				x = e.getX();
				y = e.getY();
				if (arr.get(index).isPlaced() == false) {
					if (check_collision(x, y, index) == true) {
						arr.get(index).setIsplaced(true);
						arr.get(index).setX(arr.get(index).getX());
						arr.get(index).setY(arr.get(index).getY());
					} else {
						arr.get(index).setX(X_CHOSEN);
						arr.get(index).setY(Y_CHOSEN);
					}
				}

				block_released = true;
				dragging = false;
			}
		});
		addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent e) {

				block_released = false;
				arr.get(index).setDragging(true);
				if (arr.get(index).isPlaced() == false) {
					arr.get(index).setX(e.getX());
					arr.get(index).setY(e.getY());
					dragging = true;
				}

				block_released = false;

			}
		});
	}

	public void mouse_t6(Graphics g, int index) {

		addMouseListener(new MouseAdapter() {

			public void mouseReleased(MouseEvent e) {

				x = e.getX();
				y = e.getY();
				if (arr.get(index).isPlaced() == false) {
					if (check_collision(x, y, index) == true) {
						arr.get(index).setIsplaced(true);
						arr.get(index).setX(arr.get(index).getX());
						arr.get(index).setY(arr.get(index).getY());
					} else {
						arr.get(index).setX(X_CHOSEN);
						arr.get(index).setY(Y_CHOSEN);
					}
				}

				block_released = true;
				dragging = false;
			}
		});
		addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent e) {

				block_released = false;
				arr.get(index).setDragging(true);
				if (arr.get(index).isPlaced() == false) {
					arr.get(index).setX(e.getX());
					arr.get(index).setY(e.getY());
					dragging = true;
				}

				block_released = false;

			}
		});
	}

	public void mouse_t7(Graphics g, int index) {

		addMouseListener(new MouseAdapter() {

			public void mouseReleased(MouseEvent e) {

				x = e.getX();
				y = e.getY();
				if (arr.get(index).isPlaced() == false) {
					if (check_collision(x, y, index) == true) {
						arr.get(index).setIsplaced(true);
						arr.get(index).setX(arr.get(index).getX());
						arr.get(index).setY(arr.get(index).getY());
					} else {
						arr.get(index).setX(X_CHOSEN);
						arr.get(index).setY(Y_CHOSEN);
					}
				}

				block_released = true;
				dragging = false;
			}
		});
		addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent e) {

				block_released = false;
				arr.get(index).setDragging(true);
				if (arr.get(index).isPlaced() == false) {
					arr.get(index).setX(e.getX());
					arr.get(index).setY(e.getY());
					dragging = true;
				}

				block_released = false;

			}
		});
	}

	private void repaint(Graphics g) {

		if (gameWon) {
			drawGameWon(g);
			drawFinalScore(g);
		} else if (gameOver) {
			drawGameOver(g);
			drawFinalScore(g);
		} else {
			handle(g);
			drawGameLives(g);
			drawGameScore(g);
		}
	}

	private void drawGameWon(Graphics g) {
		g.setFont(new Font("Serif", Font.BOLD, 50));
		g.setColor(Color.white);
		g.drawString("YOU WON!", 155, 200);
	}

	public void setPoints(int points) {
		this.points = String.format("%4d", (6221 + LEVEL_1_TIME) - (secondspassed * 10));
	}

	private void drawGameScore(Graphics g) {

		points = String.format("%4d", (timepassed / 50) + (1161 + LEVEL_1_TIME));
		g.setFont(new Font("Serif", Font.BOLD, 20));
		g.setColor(Color.white);
		if (((timepassed / 50) + (1161 + LEVEL_1_TIME)) > 0) {
			g.drawString("SCORE: " + points, 10, 30);
		}
		else {
			g.drawString("SCORE: ", 10, 30);
		}
		
	}

	private void drawGameLives(Graphics g) {
		timepassed = starttime - System.currentTimeMillis();
		secondspassed = (timepassed / 1000) + LEVEL_1_TIME;
		g.setFont(new Font("Serif", Font.BOLD, 20));
		g.setColor(Color.white);
		g.drawString("TIMER: " + secondspassed, 480, 30);
	}

	private void drawFinalScore(Graphics g) {
		g.setFont(new Font("Serif", Font.BOLD, 50));
		g.setColor(Color.white);
		g.drawString(points, 240, 270);

	}

	private void drawGameOver(Graphics g) {
		g.setFont(new Font("Serif", Font.BOLD, 50));
		g.setColor(Color.white);
		g.drawString("YOU LOSE!", 155, 200);
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
		usernamePanel.setMaximumSize(new Dimension(500, 350));
		usernamePanel.add(Box.createRigidArea(new Dimension(170, 190)));

		usernameTitle.setForeground(Color.white);
		usernameTitle.setFont(new Font("Serif", Font.BOLD, 40));
		usernameTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		usernamePanel.add(usernameTitle);
		usernamePanel.add(Box.createRigidArea(new Dimension(10, 10)));

		username.setAlignmentX(Component.CENTER_ALIGNMENT);
		username.setFont(username.getFont().deriveFont(Font.BOLD, 80f));
		username.setPreferredSize(new Dimension(150, 20));
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
		// reset scoreboard, remove all content for the new game
		scoreBoard.removeAll();
		// repaint scoreContent to remove previous components
		scoreContent.revalidate();
		scoreContent.repaint();

		scoreBoard.setLayout(new BoxLayout(scoreBoard, BoxLayout.Y_AXIS));
		scoreBoard.setBackground(Color.black);
		scoreBoard.setAlignmentX(Component.CENTER_ALIGNMENT);
		scoreBoard.setMaximumSize(new Dimension(500, 550));

		// scorelogo.getScaledInstance(300, 280, Image.SCALE_DEFAULT);
		scoreTitle = new JLabel(scorelogo);
		scoreTitle.setSize(50, 50);
		scoreTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		scoreBoard.add(scoreTitle, BorderLayout.NORTH);
		scoreBoard.add(Box.createRigidArea(new Dimension(30, 30)));

		try {
			file = new FileReader(new File("src/controller/scoreboard.txt"));
			buff = new BufferedReader(file);
			String line;
			while ((line = buff.readLine()) != null) {
				String[] scores = line.split("=");

				if (scores[1] != null && scores[0] != null) {
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
		} catch (IOException ex) {
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
	 * 
	 * @param username String
	 */
	private void storeNameToFile(String username) {

		BufferedWriter writer = null;
		try {
			// check if username is invalid
			if (isInvalidName(username) == false) {
				// if not write the name into the file
				// src/edu/sjsu/cs133/sudokutetris/model/scoreboard.txt
				writer = new BufferedWriter(new FileWriter("src/controller/scoreboard.txt", true));
				writer.append(username + "=" + this.points.strip() + NEWLINE);
				writer.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * the method checks the validity of the parameter String username, if the
	 * username is already in stored in the file.
	 * 
	 * @param username String
	 * @return a boolean invalid value
	 */
	private boolean isInvalidName(String username) {

		boolean invalid = false;
		// get all the scores from the file
		String[] array = getHighscores();
		// iterate through the array
		for (int index = 0; index < array.length; index++) {
			if (array[index] != null) {
				String[] score = stringTokenize(array[index]);
				// check if the current name is equal to the current element
				if (score[0].equals(username)) {
					invalid = true;
					break;
				} else {
					invalid = false;
				}
			}
		}
		return invalid;
	}

	private String[] stringTokenize(String splitstr) {

		String delim = "=";
		StringTokenizer st = new StringTokenizer(splitstr, delim);
		String[] array = new String[2];
		int index = 0;
		while (st.hasMoreElements()) {
			array[index] = (String) st.nextElement();
			index++;
		}
		return array;
	}

	/**
	 * Check if the final score of the player qualifies as a high score
	 * 
	 * @param finalScore int
	 * @return a boolean value if the currentScore qualifies as a high score
	 */
	private boolean isHighscore(int finalScore) {

		boolean isHighScore = false;
		String[] array = getHighscores();
		int scoreToReplace = Integer.parseInt(points.strip());
		int scoreCount = 0;
		// iterate through the array that has the scores from the scoreboard.txt
		for (int index = 0; index < array.length; index++) {
			if (array[index] != null) {
				scoreCount++;
				String[] score = array[index].split("=");
				// check if the current score beat at least one of the highscores
				if (Integer.parseInt(score[1].strip()) < scoreToReplace) {
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
	 * Read the scoreboard.txt and store the values into String array then return
	 * the array
	 * 
	 * @return an array of String
	 */
	private String[] getHighscores() {

		FileReader file;
		BufferedReader buff;
		String[] array = new String[5];
		;
		// open file, read file, store content into an array
		try {
			file = new FileReader(new File("src/controller/scoreboard.txt"));
			buff = new BufferedReader(file);
			String line;
			int index = 0;
			while ((line = buff.readLine()) != null) {
				String[] scores = line.split("=");
				if (scores[1] != null && scores[0] != null)
					array[index] = line;
				index++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return array;
	}

	/**
	 * Iterate through the array and count how many elements in it
	 * 
	 * @param an array of String
	 * @return the count of elements in the array
	 */
	public int getArrayCount(String[] array) {

		int count = 0;
		// count the element of the array
		for (int index = 0; index < array.length; index++) {
			if (array[index] != null)
				count++;
		}
		return count;
	}

	/**
	 * check if the name entered by the player is already in the scoreboard
	 * 
	 * @param currentName String
	 * @return a boolean value that returns if needed replacing is need to the final
	 *         score
	 */
	public boolean replaceName(String currentName) {

		boolean replacing = false;
		String[] array = getHighscores();
		int count = getArrayCount(array);
		// iterate through the array
		if (isInvalidName(currentName) == false) {
			if (count == 5) {
				for (int index = 0; index < array.length; index++) {
					if (array[index] != null) {
						count++;
						String[] score = array[index].split("=");
						// check if the name of the current element is equal to username
						if (replaceName.equals(score[0])) {
							replacing = true;
							array[index] = currentName + "=" + Integer.parseInt(points.strip());
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
	 * 
	 * @param status boolean and array String[]
	 */
	public void setHighscoresTofile(boolean status, String[] array) {

		if (status == true) {
			BufferedWriter writer = null;
			try {
				sortArray(array);
				// store array content to the file
				writer = new BufferedWriter(new FileWriter("src/controller/scoreboard.txt", false));
				for (int index = 0; index < array.length; index++) {
					if (array[index] != null) {
						writer.append(array[index] + NEWLINE);
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
	 * 
	 * @param status boolean and array String[]
	 */
	private void sortArray(String[] array) {

		int count = getArrayCount(array);
		String temp = "";
		for (int index = 0; index < count; index++) {
			String[] score = array[index].split("=");
			for (int j = index + 1; j < count; j++) {
				String[] score1 = array[j].split("=");
				if (Integer.parseInt(score[1].strip()) < Integer.parseInt(score1[1].strip())) {
					temp = array[index];
					array[index] = array[j];
					array[j] = temp;
				}
			}
		}
	}

	/* 
	 * 
	 * 
	 * */
	@Override
	public void actionPerformed(ActionEvent e) {

		gameContent.repaint();
		repaint();
		// update scores when submit button is pressed
		if (e.getSource() == submit) {
			int currentscore = Integer.parseInt(points.strip());
			if (isHighscore(currentscore) == true) {
				userName = username.getText();
				if (replaceName(userName) != true) {
					storeNameToFile(username.getText());
				}
			}
			usernamePanel.setVisible(false);
			drawScoreboard();
		}
		// update scores when return button is pressed
		if (e.getSource() == retGame) {
			// reset scoreContent
			scoreContent.removeAll();
			scoreWindow();
			scoreContent.revalidate();
			scoreContent.repaint();
		}

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		repaint(g);
	}

}


