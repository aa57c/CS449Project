package sprint5_product;

import java.awt.BasicStroke;






import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import sprint5_product.Board.GameState;


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
	
	private ArrayList<String> fileContent = new ArrayList<String>();
	private Canvas canvas;
	private Board board;
	private File file;
	private String fileName;
	private Boolean isFileCreated;
	private FileWriter myWriter;

	//Panel for top of game window (game options)
	JPanel gameOptions = new JPanel(new FlowLayout());
	JTextField tf = new JTextField(3);
	JLabel sizeFieldTxt = new JLabel("Board Size");
	JLabel title = new JLabel("SOS");
	ButtonGroup gameType = new ButtonGroup();
	JRadioButton simple = new JRadioButton();
	JRadioButton general = new JRadioButton();
	JCheckBox recordGame = new JCheckBox();
	JButton newGameButton = new JButton();

	
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
	
	//button group for human and computer buttons (blue player)
	ButtonGroup B_PlayerType = new ButtonGroup();
	//computer player button (blue player)
	JRadioButton B_Computer = new JRadioButton();
	//button for human (blue player)
	JRadioButton B_Human = new JRadioButton();
	

	
	//
	//Panel for right of game window (red player controls)
	JPanel redPlayerOptions = new JPanel();
	//creates label for blue player buttons
	JLabel redPlayerTxt = new JLabel("Red Player");
	//button group for S and O buttons
	ButtonGroup SOS_Buttons_R = new ButtonGroup();
			
	JRadioButton Red_S = new JRadioButton();
	JRadioButton Red_O = new JRadioButton();
	
	//button group for human and computer buttons
	ButtonGroup R_PlayerType = new ButtonGroup();
	
	//button for human player
	JRadioButton R_Human = new JRadioButton();
	
	//button for computer player
	JRadioButton R_Computer = new JRadioButton();
	
	
	public JCheckBox returnRecordGameComponent() {
		return recordGame;
	}
	
	public JRadioButton returnRedHumanButton() {
		return R_Human;
	}
	public JRadioButton returnRedComputerButton() {
		return R_Computer;
	}
	public JRadioButton returnBlueHumanButton() {
		return B_Human;
	}
	public JRadioButton returnBlueComputerButton() {
		return B_Computer;
	}
	
	public JRadioButton returnRedS() {
		return Red_S;
	}
	public JRadioButton returnRedO() {
		return Red_O;
	}
	
	public JRadioButton returnBlueS() {
		return Blue_S;
	}
	public JRadioButton returnBlueO() {
		return Blue_O;
	}
	
	
	public JButton returnNewGameButton() {
		return newGameButton;
	}
	
	public JRadioButton returnSimpleGameButton() {
		return simple;
	}
	public JRadioButton returnGeneralGameButton() {
		return general;
	}
	
	public JTextField returnTextField() {
		return tf;
	}
	
	public void addFileContent(String content) {
		System.out.println("file content added");
		this.fileContent.add(content);
	}
	
	

	
	//
	//Panel for bottom of game window
	JPanel gameStatusBar = new JPanel(new FlowLayout());
	JLabel gameStatusText = new JLabel();

	

	
	
	
	public SOS_GUI(Board board) {
		this.board = board;
		System.out.println("Creating SOS Board");
		setContentPane();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack(); 
		setTitle("SOS Game");
		setVisible(true);  
	}

	public Board getBoard() {
		return board;
	}
	
	public Canvas getCanvas() {
		return canvas;
	}
	
	
	public void updateTurns(String playerColor) {
		//display current turn
		gameStatusText.setText("Current Turn: " + playerColor);
		//debug code
		//playerSymbol.setText("Current Symbol: " + playerSym);
		//disable radio buttons for other player
		if(!isComputer(playerColor)) {
			if(playerColor == "red") {
				Blue_S.setEnabled(false);
				Blue_O.setEnabled(false);
				Red_S.setEnabled(true);
				Red_S.setSelected(true);
				Red_O.setEnabled(true);
				board.setPlayerSymbol(Red_S.getText().charAt(0));
			}
			else {
				Blue_S.setEnabled(true);
				Blue_S.setSelected(true);
				Blue_O.setEnabled(true);
				Red_S.setEnabled(false);
				Red_O.setEnabled(false);
				board.setPlayerSymbol(Blue_S.getText().charAt(0));
			}
			
		}

	}
	
	//enable new game button when both computer and human buttons are clicked for BOTH players
	public void enableNewGameButton() {
		if((R_Human.isSelected() || R_Computer.isSelected()) && 
				(B_Human.isSelected() || B_Computer.isSelected())){
			newGameButton.setEnabled(true);
			recordGame.setEnabled(true);
		}
		
	}
	//checks to see if either player is a computer
	public Boolean isComputer(String playerClr) {
		if(playerClr == "red" && R_Computer.isSelected()) {
			return true;
		}
		else if(playerClr == "blue" && B_Computer.isSelected()) {
			return true;
		}
		return false;		
	}
	//changes the symbol of computer player during playing mode
	public void changeSym(String playerClr) {
		//System.out.println("Blue S = " + Blue_S.isSelected());
		//System.out.println("Blue O = " + Blue_O.isSelected());
		//System.out.print("Player Color: " + playerClr);
		if(playerClr == "red" && Red_S.isSelected()) {
			//System.out.println(" is S, changing to O");
			Red_O.setSelected(true);
			board.setPlayerSymbol(Red_O.getText().charAt(0));
		}
		else if(playerClr == "red" && Red_O.isSelected()) {
			//System.out.println(" is O, changing to S");
			Red_S.setSelected(true);
			board.setPlayerSymbol(Red_S.getText().charAt(0));
		}
		else if(playerClr == "blue" && Blue_S.isSelected()) {
			//System.out.println(" is S, changing to O");
			Blue_O.setSelected(true);
			board.setPlayerSymbol(Blue_O.getText().charAt(0));
		}
		else if(playerClr == "blue" && Blue_O.isSelected()) {
			//System.out.println(" is O, changing to S");
			Blue_S.setSelected(true);
			board.setPlayerSymbol(Blue_S.getText().charAt(0));
		}
	}
	//makes the move for computer player
	public void computerTurn() {
		if(board.getGameState() == GameState.PLAYING && isComputer(board.getPlayerColor())) {
			System.out.println("current player is computer");
			int randRow;
			int randCol;
			do {
				randRow = ThreadLocalRandom.current().nextInt(0, board.getBoardsize());
				randCol = ThreadLocalRandom.current().nextInt(0, board.getBoardsize());
			}while(board.getCellContents(randRow, randCol).getSym() != 0);
			changeSym(board.getPlayerColor());
			board.makeMove(randRow, randCol);
			recordMove(randRow, randCol);
			canvas.paintComponent(canvas.getGraphics());
		}
		
	}
	public String createDate() {
		LocalDateTime date = LocalDateTime.now();
		DateTimeFormatter date_format = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss");
		String formatted_date = date.format(date_format);
		return formatted_date;
	}
	public void createFile() {
		this.fileName = createDate() + "_GameRecord.txt";
		try {
			this.file = new File("src/sprint5_product/GameRecords/", this.fileName);
			if(this.file.createNewFile()) {
				this.setIsFileCreated(true);
				writeFile();	
			}
			else {
				this.setIsFileCreated(false);
				writeFile();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void writeFile() {
		try {
			myWriter = new FileWriter(this.file);
			for(String s : this.fileContent) {
				myWriter.write(s + "\n");
				System.out.println("writing to file");
			}
			System.out.println("closed file");
			myWriter.flush();
			myWriter.close();
			this.fileContent.clear();
			recordGame.setSelected(false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void recordSettings()
	{
		if(recordGame.isSelected()) {
			String gameSettings = "gm=";
			//add game mode to settings string
			if(simple.isSelected()) {
				gameSettings += "s;";
			}
			else {
				gameSettings += "g;";
			}
			//add board size to settings string
			gameSettings += ("bs=" + board.getBoardsize() + ";");
			
			//add red player to settings string
			gameSettings += "rp=";
			if(R_Human.isSelected()) {
				gameSettings += "h;";
			}	
			else if(R_Computer.isSelected()) {
				gameSettings += "c;";
			}
			//add blue player to settings string
			gameSettings += "bp=";
			if(B_Human.isSelected()) {
				gameSettings += "h;";
			}
			else if(B_Computer.isSelected()) {
				gameSettings += "c;";
			}
			addFileContent(gameSettings);
		}
	}
	public void recordMove(int row, int col) {
		if(recordGame.isSelected()) {
			String move = "plr=";
			//add player color to move string
			if(board.getCellClr(row, col) == "red") {
				move += "r;";
			}
			else if(board.getCellClr(row, col) == "blue") {
				move += "b;";
			}
			//add player symbol to move string
			move += "sym=";
			if(board.getCellSym(row, col) == 1) {
				move += "S;";
			}
			else if(board.getCellSym(row, col) == 2) {
				move += "O;";
			}
			//add row of move
			move += ("row=" + row + ";");
			//add column of move
			move += ("col=" + col + ";");
			
			addFileContent(move);
			
		}
	}
	
	public void recordResult(GameState gameState) {
		if(recordGame.isSelected()) {
			String result = "res=";
			if(gameState == GameState.RED_WON) {
				result += "r;";
			}
			else if(gameState == GameState.BLUE_WON) {
				result += "b;";
			}
			else if(gameState == GameState.DRAW) {
				result += "d;";
			}
			addFileContent(result);
		}
	}



	
	private void setContentPane(){
		//
		newGameButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//set the board size and repaint grid canvas
				
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
				this.fileContent.clear();
				recordSettings();

				
				//disabling game mode, board size, human/computer buttons, record game box
				simple.setEnabled(false);
				general.setEnabled(false);
				tf.setEnabled(false);
				B_Human.setEnabled(false);
				B_Computer.setEnabled(false);
				R_Human.setEnabled(false);
				R_Computer.setEnabled(false);
				recordGame.setEnabled(false);
				
				//enabling S and O buttons
				Red_S.setEnabled(true);
				Red_S.setSelected(true);
				Red_O.setEnabled(true);
				Blue_S.setEnabled(true);
				Blue_S.setSelected(true);
				Blue_O.setEnabled(true);
				
				
		
				canvas.paintComponent(canvas.getGraphics());
				//resize JFrame (window) according to contents
				pack();
				//removes focus from text box
				tf.getRootPane().requestFocus();
				System.out.println(board.getGameState().toString());
			}
			
		});

		
		simple.setText("Simple Game");
		general.setText("General Game");
		recordGame.setText("Record Game");



		newGameButton.setText("New Game");
		gameType.add(simple);
		gameType.add(general);
		gameOptions.add(title);
		gameOptions.add(simple);
		gameOptions.add(general);
		gameOptions.add(sizeFieldTxt);
		gameOptions.add(tf);
		gameOptions.add(recordGame);
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
		
		B_Human.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				enableNewGameButton();
			}
			
		});
		
		B_Computer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				enableNewGameButton();
			}
			
		});
		
		Blue_S.setText("S");
		Blue_O.setText("O");
		
		B_Human.setText("Human");
		B_Computer.setText("Computer");
		
		
		SOS_Buttons_B.add(Blue_S);
		SOS_Buttons_B.add(Blue_O);
		
		B_PlayerType.add(B_Human);
		B_PlayerType.add(B_Computer);
		
		bluePlayerOptions.add(bluePlayerTxt);
		bluePlayerOptions.add(B_Human);
		bluePlayerOptions.add(Blue_S);
		bluePlayerOptions.add(Blue_O);
		bluePlayerOptions.add(B_Computer);
		
		
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
		
		R_Human.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				enableNewGameButton();
			}
			
		});
		
		R_Computer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				enableNewGameButton();
			}
			
		});
		
		
		Red_S.setText("S");
		Red_O.setText("O");
		R_Human.setText("Human");
		R_Computer.setText("Computer");
		
		
		SOS_Buttons_R.add(Red_S);
		SOS_Buttons_R.add(Red_O);
		
		R_PlayerType.add(R_Human);
		R_PlayerType.add(R_Computer);
		
		redPlayerOptions.add(redPlayerTxt);
		redPlayerOptions.add(R_Human);
		redPlayerOptions.add(Red_S);
		redPlayerOptions.add(Red_O);
		redPlayerOptions.add(R_Computer);
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
						recordMove(rowSelected, colSelected);
						updateTurns(board.getPlayerColor());
						//check if next player is a computer
						computerTurn();
						
					}
					else {
						board.beginSetup();
						board.InitializeGrid();
						gameStatusText.setForeground(Color.BLACK);
						gameStatusText.setText("Current Turn: ");
						
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
			if(board.getGameState() == GameState.SETUP) {
				//System.out.println("In Setup Game")
				newGameButton.setEnabled(false);
				recordGame.setEnabled(false);
				
				
				gameStatusText.setForeground(Color.BLACK);
				gameStatusText.setText("Select game mode, board size, and human/computer");
				//enable game modes, board size, and human/computer buttons
				simple.setEnabled(true);
				simple.setSelected(true);
				general.setEnabled(true);
				tf.setEnabled(true);
				B_Human.setEnabled(true);
				B_Human.setSelected(false);
				
				B_Computer.setEnabled(true);
				B_Computer.setSelected(false);
				
				R_Human.setEnabled(true);
				R_Human.setSelected(false);
				
				R_Computer.setEnabled(true);
				R_Computer.setSelected(false);
				

				//disable S and O for both red and blue players
				Red_S.setEnabled(false);
				Red_O.setEnabled(false);
				Blue_S.setEnabled(false);
				Blue_O.setEnabled(false);

			}
			else if(board.getGameState() == GameState.PLAYING) {
				gameStatusText.setForeground(Color.BLACK);
				updateTurns(board.getPlayerColor());
				computerTurn();
			}
			else if(board.getGameState() == GameState.DRAW) {
				gameStatusText.setForeground(Color.MAGENTA);
				gameStatusText.setText("It's a Draw! Click to play again.");
				recordResult(board.getGameState());
				createFile();

			}
			else if(board.getGameState() == GameState.RED_WON) {
				gameStatusText.setForeground(Color.RED);
				gameStatusText.setText("Red Player Won! Click to play again.");
				recordResult(board.getGameState());
				createFile();

			}
			else if(board.getGameState() == GameState.BLUE_WON) {
				gameStatusText.setForeground(Color.BLUE);
				gameStatusText.setText("Blue Player Won! Click to play again.");
				recordResult(board.getGameState());
				createFile();

			}
		}

	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new SOS_GUI(new Board()); 
			}
		});
	}

	public Boolean getIsFileCreated() {
		return isFileCreated;
	}

	public void setIsFileCreated(Boolean isFileCreated) {
		this.isFileCreated = isFileCreated;
	}


}
