package sprint2_product;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
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
	//private TextField sizeField;
	private Interactive_Board board;
	//private Game_Options gameOptions;
	
	
	
	
	
	
	public SOS_GUI(Interactive_Board board) {
		this.board = board;
		setContentPane();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack(); 
		setTitle("SOS Game");
		setVisible(true);  
	}
	
	public Interactive_Board getBoard() {
		return board;
	}
	


	
	private void setContentPane(){
		
		//
		//Panel for top of game window (game options)
		JPanel gameOptions = new JPanel(new FlowLayout());
		JTextField tf = new JTextField(3);
		tf.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//set the board size and repaint grid canvas
				board.setBoardsize(Integer.parseInt(tf.getText()));
				canvas.paintComponent(canvas.getGraphics());
				//resize JFrame (window) according to contents
				pack();
				//removes focus from text box
				tf.getRootPane().requestFocus();
			}
			
		});
		JLabel sizeFieldTxt = new JLabel("Board Size");
		ButtonGroup gameType = new ButtonGroup();
		JRadioButton simple = new JRadioButton();
		JRadioButton general = new JRadioButton();
		simple.setText("Simple Game");
		general.setText("General Game");
		gameType.add(simple);
		gameType.add(general);
		JLabel title = new JLabel("SOS");
		gameOptions.add(title);
		gameOptions.add(simple);
		gameOptions.add(general);
		gameOptions.add(sizeFieldTxt);
		gameOptions.add(tf);
		//
		//
		
		//
		//Panel for left side of game window (blue player controls)
		JPanel bluePlayerOptions = new JPanel();
		bluePlayerOptions.setLayout(new BoxLayout(bluePlayerOptions, BoxLayout.Y_AXIS));
		bluePlayerOptions.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		//creates label for blue player buttons
		JLabel bluePlayerTxt = new JLabel("Blue Player");
		//button group for S and O buttons
		ButtonGroup SOS_Buttons_B = new ButtonGroup();
		
		JRadioButton S = new JRadioButton();
		JRadioButton O = new JRadioButton();
		
		S.setText("S");
		O.setText("O");
		
		SOS_Buttons_B.add(S);
		SOS_Buttons_B.add(O);
		
		bluePlayerOptions.add(bluePlayerTxt);
		bluePlayerOptions.add(S);
		bluePlayerOptions.add(O);
		
		
		//
		//Panel for right of game window (red player controls)
		JPanel redPlayerOptions = new JPanel();
		redPlayerOptions.setLayout(new BoxLayout(redPlayerOptions, BoxLayout.Y_AXIS));
		redPlayerOptions.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		
		//creates label for blue player buttons
		JLabel redPlayerTxt = new JLabel("Red Player");
		//button group for S and O buttons
		ButtonGroup SOS_Buttons_R = new ButtonGroup();
		
		JRadioButton Red_s = new JRadioButton();
		JRadioButton Red_o = new JRadioButton();
		Red_s.setText("S");
		Red_o.setText("O");
		
		SOS_Buttons_R.add(Red_s);
		SOS_Buttons_R.add(Red_o);
		
		redPlayerOptions.add(redPlayerTxt);
		redPlayerOptions.add(Red_s);
		redPlayerOptions.add(Red_o);
		//
		//
		
		
		//
		//Panel for bottom of game window
		JPanel currentTurn = new JPanel(new FlowLayout());
		JLabel currTurnTitle = new JLabel("Current Turn:");
		JTextField currTurnTxt = new JTextField();
		currentTurn.add(currTurnTitle);
		currentTurn.add(currTurnTxt);
		//
		//
		//Panel for center of game window (game board)
		canvas = new Canvas();
		
		/*
		CANVAS_WIDTH = CELL_SIZE * board.getBoardsize();  
		CANVAS_HEIGHT = CELL_SIZE * board.getBoardsize();
		canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
		*/
		//
		//

		//
		//Container for entire game window
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		//add board to center of window and game options to top of window
		contentPane.add(canvas, BorderLayout.CENTER);
		contentPane.add(gameOptions, BorderLayout.NORTH);
		contentPane.add(bluePlayerOptions, BorderLayout.WEST);
		contentPane.add(redPlayerOptions, BorderLayout.EAST);
		contentPane.add(currentTurn, BorderLayout.SOUTH);
		//
		//
	}
	

	class Canvas extends JPanel {
		
		Canvas(){
			addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {  
						int rowSelected = e.getY() / CELL_SIZE;
						int colSelected = e.getX() / CELL_SIZE;
						board.makeMove(rowSelected, colSelected);
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
					if (board.getCell(row,col) == 1) {
						g2d.setColor(Color.RED);
						int y2 = (row + 1) * CELL_SIZE - CELL_PADDING;
						g2d.setFont(new Font("TimesRoman", Font.BOLD, CELL_SIZE));
						g2d.drawString("S", x1, y2);
					} else if (board.getCell(row,col) == 2) {
						g2d.setColor(Color.BLUE);
						g2d.drawOval(x1, y1, SYMBOL_SIZE, SYMBOL_SIZE);
					}
				}
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


	
	

	
	


