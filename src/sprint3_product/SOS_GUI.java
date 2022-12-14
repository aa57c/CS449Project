package sprint3_product;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import sprint3_product.Interactive_Board.GameState;
@SuppressWarnings("serial")

public class SOS_GUI extends JFrame {
	public static final int CELL_SIZE = 60; 
	public static final int GRID_WIDTH = 8; 
	public static final int GRID_WIDHT_HALF = GRID_WIDTH / 2; 

	public static final int CELL_PADDING = CELL_SIZE / 6;
	public static final int SYMBOL_SIZE = CELL_SIZE - CELL_PADDING * 2; 
	public static final int SYMBOL_STROKE_WIDTH = 8;
	
	private int CANVAS_WIDTH;
	private int CANVAS_HEIGHT;
	
	private Canvas canvas;
	private Interactive_Board board;

	//Panel for top of game window (game options)
	JPanel gameOptions = new JPanel(new FlowLayout());
	JTextField tf = new JTextField(3);
	JLabel sizeFieldTxt = new JLabel("Board Size");
	JLabel title = new JLabel("SOS");
	ButtonGroup gameType = new ButtonGroup();
	JRadioButton simple = new JRadioButton();
	JRadioButton general = new JRadioButton();

	
	//
	//Panel for left side of game window (blue player controls)
	JPanel bluePlayerOptions = new JPanel();
	//creates label for blue player buttons
	JLabel bluePlayerTxt = new JLabel("Blue Player");
	//button group for S and O buttons
	ButtonGroup SOS_Buttons_B = new ButtonGroup();
	//s button for blue player
	JRadioButton Blue_S = new JRadioButton();
	//o button for blue player
	JRadioButton Blue_O = new JRadioButton();
	

	
	//
	//Panel for right of game window (red player controls)
	JPanel redPlayerOptions = new JPanel();
	//creates label for blue player buttons
	JLabel redPlayerTxt = new JLabel("Red Player");
	//button group for S and O buttons
	ButtonGroup SOS_Buttons_R = new ButtonGroup();
			
	JRadioButton Red_S = new JRadioButton();
	JRadioButton Red_O = new JRadioButton();
	
	//
	//Panel for bottom of game window
	JPanel gameStatusBar = new JPanel(new FlowLayout());
	JLabel gameStatusText = new JLabel();
	
	//JLabel playerSymbol = new JLabel();
	
	//JPanel newGame = new JPanel(new FlowLayout());
	JButton newGameButton = new JButton();
	
	
	
	
	
	
	
	
	public SOS_GUI(Interactive_Board board) {
		this.board = board;
		//set simple and general game button texts
		//simple.setText("Simple Game");
		//general.setText("General Game");
		//disable blue player buttons because red player goes first
		//set default symbol for blue player
		//Blue_S.setSelected(true);
		//set default symbol for red player
		//Red_S.setSelected(true);
		//board.setPlayerSymbol(Red_S.getText().charAt(0));
		//default game type
		//simple.setSelected(true);
		//board.setGameMode(simple.getText());
		setContentPane();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack(); 
		setTitle("SOS Game");
		setVisible(true);  
	}
	
	public Interactive_Board getBoard() {
		return board;
	}
	
	
	public void updateTurns(String playerColor, char playerSym) {
		//display current turn
		gameStatusText.setText("Current Turn: " + playerColor);
		//debug code
		//playerSymbol.setText("Current Symbol: " + playerSym);
		//disable radio buttons for other player
		if(playerColor == "red") {
			Blue_S.setEnabled(false);
			Blue_O.setEnabled(false);
			Red_S.setEnabled(true);
			//Red_S.setSelected(true);
			Red_O.setEnabled(true);
			//board.setPlayerSymbol(playerSym);
		}
		else {
			Blue_S.setEnabled(true);
			//Blue_S.setSelected(true);
			Blue_O.setEnabled(true);
			Red_S.setEnabled(false);
			Red_O.setEnabled(false);
			//board.setPlayerSymbol(playerSym);
		}
	}


	
	private void setContentPane(){
		
		//
		newGameButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//set the board size and repaint grid canvas
				
				Red_S.setSelected(true);
				Blue_S.setSelected(true);
				
				if(Integer.parseInt(tf.getText()) < 0) {
					board.setBoardsize(0);
				}
				else {
					board.setBoardsize(Integer.parseInt(tf.getText()));
				}
				
				//set the game mode
				if(simple.isSelected()) {
					board.setGameMode(simple.getText());
				}
				else if(general.isSelected()) {
					board.setGameMode(general.getText());
				}
				if(board.getPlayerColor() == "red") {
					Blue_S.setEnabled(false);
					//Red_S.setSelected(true);
					board.setPlayerSymbol(Red_S.getText().charAt(0));
					Blue_O.setEnabled(false);
					
				}
				else if(board.getPlayerColor() == "blue") {
					Red_S.setEnabled(false);
					//Blue_S.setSelected(true);
					board.setPlayerSymbol(Blue_S.getText().charAt(0));
					Red_O.setEnabled(false);
				}
				canvas.paintComponent(canvas.getGraphics());
				//resize JFrame (window) according to contents
				pack();
				//removes focus from text box
				tf.getRootPane().requestFocus();
			}
			
			
		});
		
		/*
		simple.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				board.setGameMode(simple.getText());
				
			}
			
		});
		
		general.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				board.setGameMode(general.getText());	
			}
			
		});
		*/
		
		simple.setText("Simple Game");
		general.setText("General Game");



		newGameButton.setText("New Game");
		gameType.add(simple);
		gameType.add(general);
		gameOptions.add(title);
		gameOptions.add(simple);
		gameOptions.add(general);
		gameOptions.add(sizeFieldTxt);
		gameOptions.add(tf);
		gameOptions.add(newGameButton);
		//
		//
		
		
		bluePlayerOptions.setLayout(new BoxLayout(bluePlayerOptions, BoxLayout.Y_AXIS));
		bluePlayerOptions.setBorder(new EmptyBorder(10, 10, 10, 10));
		

		Blue_S.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				board.setPlayerSymbol(Blue_S.getText().charAt(0));
			}	
		});
		
		
		Blue_O.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				board.setPlayerSymbol(Blue_O.getText().charAt(0));
				
			}
			
		});
		
		Blue_S.setText("S");
		Blue_O.setText("O");
		
		SOS_Buttons_B.add(Blue_S);
		SOS_Buttons_B.add(Blue_O);
		
		bluePlayerOptions.add(bluePlayerTxt);
		bluePlayerOptions.add(Blue_S);
		bluePlayerOptions.add(Blue_O);
		
		
		redPlayerOptions.setLayout(new BoxLayout(redPlayerOptions, BoxLayout.Y_AXIS));
		redPlayerOptions.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		
		Red_S.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				board.setPlayerSymbol(Red_S.getText().charAt(0));
				
			}
			
		});

		Red_O.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				board.setPlayerSymbol(Red_O.getText().charAt(0));
				
			}
			
		});
		Red_S.setText("S");
		Red_O.setText("O");
		
		SOS_Buttons_R.add(Red_S);
		SOS_Buttons_R.add(Red_O);
		
		redPlayerOptions.add(redPlayerTxt);
		redPlayerOptions.add(Red_S);
		redPlayerOptions.add(Red_O);
		//
		//
		
		gameStatusText.setText("Current Turn: " + board.getPlayerColor());
		
		//debug code
		//playerSymbol.setText("Current Symbol: " + board.getPlayerSymbol());

		gameStatusBar.add(gameStatusText);
		//debug
		//gameStatusBar.add(playerSymbol);

		//
		//
		//Panel for center of game window (game board)
		canvas = new Canvas();
		

		//newGame.add(newGameButton);
		
	

		//
		//Container for entire game window
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		//add board to center of window and game options to top of window
		contentPane.add(canvas, BorderLayout.CENTER);
		contentPane.add(gameOptions, BorderLayout.NORTH);
		contentPane.add(bluePlayerOptions, BorderLayout.WEST);
		contentPane.add(redPlayerOptions, BorderLayout.EAST);
		contentPane.add(gameStatusBar, BorderLayout.SOUTH);
		//
		//
	}

	class Canvas extends JPanel {
		
		Canvas(){
			addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if(board.getGameState() == GameState.PLAYING) {
						int rowSelected = e.getY() / CELL_SIZE;
						int colSelected = e.getX() / CELL_SIZE;
						board.makeMove(rowSelected, colSelected);
						updateTurns(board.getPlayerColor(), board.getPlayerSymbol());
						
					}
					else {
						board.InitializeGrid();
					}
					repaint();

				}
				
			});
		}

		
		@Override
		public void paintComponent(Graphics g) {
			CANVAS_WIDTH = CELL_SIZE * board.getBoardsize();  
			CANVAS_HEIGHT = CELL_SIZE * board.getBoardsize();
			setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
			super.paintComponent(g);   
			setBackground(Color.WHITE);
			drawGridLines(g);
			drawBoard(g);
			printStatusBar();
		}
		
		private void drawGridLines(Graphics g){
			g.setColor(Color.LIGHT_GRAY);
			for (int row = 1; row < board.getBoardsize(); row++) {
				g.fillRoundRect(0, CELL_SIZE * row - GRID_WIDHT_HALF,
						CANVAS_WIDTH-1, GRID_WIDTH, GRID_WIDTH, GRID_WIDTH);
			}
			for (int col = 1; col < board.getBoardsize(); col++) {
				g.fillRoundRect(CELL_SIZE * col - GRID_WIDHT_HALF, 0,
						GRID_WIDTH, CANVAS_HEIGHT-1, GRID_WIDTH, GRID_WIDTH);
			}

		}

		private void drawBoard(Graphics g){
			Graphics2D g2d = (Graphics2D)g;
			g2d.setStroke(new BasicStroke(SYMBOL_STROKE_WIDTH, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND)); 
			for (int row = 0; row < board.getBoardsize(); row++) {
				for (int col = 0; col < board.getBoardsize(); col++) {
					int x1 = col * CELL_SIZE + CELL_PADDING;
					int y1 = row * CELL_SIZE + CELL_PADDING;
					if(board.getCellClr(row, col) == "red") {
						g2d.setColor(Color.RED);
					}
					else {
						g2d.setColor(Color.BLUE);
					}
					if(board.getCellSym(row, col) == 1) {
						int y2 = (row + 1) * CELL_SIZE - CELL_PADDING;
						g2d.setFont(new Font("TimesRoman", Font.BOLD, CELL_SIZE));
						//g2d.setColor(Color.BLACK);
						g2d.drawString("S", x1, y2);	
					}
					else if(board.getCellSym(row,col) == 2) {
						//g2d.setColor(Color.BLACK);
						g2d.drawOval(x1, y1, SYMBOL_SIZE, SYMBOL_SIZE);
					}			
				}
			}
		}
		private void printStatusBar() {
			if(board.getGameState() == GameState.PLAYING) {
				gameStatusText.setForeground(Color.BLACK);
				updateTurns(board.getPlayerColor(), board.getPlayerSymbol());
			}
			else if(board.getGameState() == GameState.DRAW) {
				gameStatusText.setForeground(Color.MAGENTA);
				gameStatusText.setText("It's a Draw! Click to play again.");
			}
			else if(board.getGameState() == GameState.RED_WON) {
				gameStatusText.setForeground(Color.RED);
				gameStatusText.setText("Red Player Won! Click to play again.");
			}
			else if(board.getGameState() == GameState.BLUE_WON) {
				gameStatusText.setForeground(Color.BLUE);
				gameStatusText.setText("Blue Player Won! Click to play again.");
			}
		}

	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new SOS_GUI(new Interactive_Board()); 
			}
		});
	}
}


	
	

	
	



