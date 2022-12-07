package sprint5_product;

public class Board {
	public enum GameState {SETUP, PLAYING, DRAW, RED_WON, BLUE_WON, REPLAY}
	private String gameMode = "";
	private GameState currentGameState;
	private Cell[][] grid;
	private char player_symbol = ' ';
	private String player_color = "red";
	private int redWins = 0;
	private int blueWins = 0;
	private int boardsize;
	
	
	public Cell[][] returnGrid(){
		return this.grid;
	}
	
	public void resetWins() {
		this.redWins = 0;
		this.blueWins = 0;
	}
	public void setGameMode(String mode) {
		this.gameMode = mode;
	}
	public String getGameMode() {
		return this.gameMode;
	}
	
	public void setPlayerSymbol(char symbol) {
		this.player_symbol = symbol;
	}
	
	
	public void setPlayerColor(String color) {
		this.player_color = color;
	}
	
	public String getPlayerColor() {
		return this.player_color;
	}
	
	public char getPlayerSymbol() {
		return this.player_symbol;
	}

	
	
	public void setBoardsize(int size) {
		this.boardsize = size;
		this.grid = new Cell[size][size];
		this.currentGameState = GameState.PLAYING;
		InitializeGrid();
	}
	
	public int getBoardsize() {
		return this.boardsize;
	}
	public void beginReplay(int size) {
		this.boardsize = size;
		this.grid = new Cell[size][size];
		this.currentGameState = GameState.REPLAY;
		InitializeGrid();
	}
	public void beginSetup() {
		this.currentGameState = GameState.SETUP;
	}
	public Board() {
		//grid = new Cell[this.boardsize][this.boardsize];
		this.currentGameState = GameState.SETUP;
		//InitializeGrid();
	}
	
	public void InitializeGrid() {
		for(int row = 0; row < this.boardsize; row++ ) {
			for(int col = 0; col < this.boardsize; col++) {
				this.grid[row][col] = new Cell();
			}
		}
		
	}
	
	
	public int getCellSym(int row, int column) {
		if (row >= 0 && row < this.boardsize && column >= 0 && column < this.boardsize) {
			return this.grid[row][column].getSym();
		}
		else {
			return -1;
		}

	}
	public String getCellClr(int row, int column) {
		if (row >= 0 && row < this.boardsize && column >= 0 && column < this.boardsize)
			return this.grid[row][column].getClr();
		else
			return "None";
		
	}
	public Cell getCellContents(int row, int column) {
		if (row >= 0 && row < this.boardsize && column >= 0 && column < this.boardsize) {
			return this.grid[row][column];
		}
		else {
			return null;
		}
		
	}
	public void makeMove(int row, int column) {
		if (row >= 0 && row < this.boardsize && column >= 0 && column < this.boardsize
				&& this.grid[row][column].getSym() == 0) {
			//place move on board
			this.grid[row][column].setMyPair(this.player_symbol == 'S' ? 1 : 2, this.player_color);
			updateGameState(this.grid[row][column], row, column);
			//update turns
			this.player_color = (this.player_color == "red")? "blue" : "red";
			//this is just for testing purposes (I don't know if this actually matters while playing the game,
			//since it still alternates regardless
			//this.player_symbol = (this.player_symbol == 'S') ? 'O' : 'S';
		}
	}

	private void updateGameState(Cell turn, int row, int column) {
		//if there is a win from either player AND the game mode is simple
		if(hasWon(turn, row, column) && this.gameMode == "Simple Game") {
			//verifies whether red or blue won
			this.currentGameState = (turn.getClr() == "red") ? GameState.RED_WON : GameState.BLUE_WON;
			//resets the player symbol and player color
			setPlayerSymbol(' ');
			setPlayerColor("red");
		}
		//if there is win from either player AND the game mode is general
		else if(hasWon(turn, row, column) && this.gameMode == "General Game") {
			//count red wins depending on the turn just played
			this.redWins += (turn.getClr() == "red") ? 1 : 0;
			//count blue wins depending on the turn just played
			this.blueWins += (turn.getClr() == "blue") ? 1 : 0;
		}
		//if the board is full AND the game mode is simple, the game is a draw
		else if(isBoardFull() && this.gameMode == "Simple Game") {
			this.currentGameState = GameState.DRAW;
			setPlayerSymbol(' ');
			setPlayerColor("red");
		}
		//if the board is full AND the game mode is general
		else if(isBoardFull() && this.gameMode == "General Game") {
			//if red has more wins than blue player, red has won
			if(this.redWins > this.blueWins) {
				this.currentGameState = GameState.RED_WON;
				setPlayerSymbol(' ');
				setPlayerColor("red");
				resetWins();
			}
			//if blue has more wins than the red player, blue has won
			else if(this.blueWins > this.redWins) {
				this.currentGameState = GameState.BLUE_WON;
				setPlayerSymbol(' ');
				setPlayerColor("red");
				resetWins();
			}
			//if blue and red have the same amount of wins, the game is a draw
			else if(this.redWins == this.blueWins) {
				this.currentGameState = GameState.DRAW;
				setPlayerSymbol(' ');
				setPlayerColor("red");
				resetWins();
			}
		}
	}
	private boolean isBoardFull() {
		for (int row = 0; row < this.boardsize; row++) {
			for (int col = 0; col < this.boardsize; col++) {
				if (this.grid[row][col].getSym() == 0) {
					return false; // an empty cell found, not draw
				}
			}
		}
		return true;
	}
	private boolean isValidCell(int r, int c) {
		//checks to see if the neighbors of the turn just played are within the range of the board
		return (r >= 0 && r < this.boardsize && c >= 0 && c < this.boardsize);
		
	}
	private boolean isMatch(int r, int c, int sym, String clr) {
		//checks to see if second or third cell in pattern matches the symbol and color of the turn just played
		return(this.grid[r][c].getSym() > 0 && this.grid[r][c].getSym() == sym && this.grid[r][c].getClr() == clr);
	}
	private int getThirdCell(int start, int stop, int sym) {
		int thirdCell = 0;
		int Change;
		//if symbol is S, then find third cell in the same direction because S is last symbol in pattern
		if(sym == 1) {
			//finding the direction of pattern
			Change = start - stop;
			//apply change to second cell to find third cell
			thirdCell = start + Change;
		}
		//if symbol is O, then find third cell in the reverse direction because O is middle symbol in pattern
		else if(sym == 2) {
			//finding the direction of pattern (negative is reverse)
			Change = (start - stop) * -2;
			//apply change to second cell to find third cell
			thirdCell = start + Change;
		}
		return thirdCell;
	}
	//function stub for determining wins
	private boolean hasWon(Cell turn, int row, int col) {
		//set second symbol and color based on turn (the target is the first space found after turn is made)
		int SecondSym = turn.getSym() == 1 ? 2 : 1;
		String SecondClr = turn.getClr() == "red" ? "red" : "blue";
		int turnSym = this.grid[row][col].getSym();
		
		
		//testing printing statements
		/*
		 * //String turnClr = this.grid[row][col].getClr();
		System.out.println("Player's Turn: (" + row + "," + col + ")");
		if(turnSym == 1) {
			System.out.println("Player's Color: " + turnClr + ", Player's Symbol: S");
		}
		else {
			System.out.println("Player's Color: " + turnClr + ", Player's Symbol: O");
		}
		System.out.println("Neighbors of Player...");
		*/
		//loop through all neighbor cells
		for(int r = row - 1; r <= row + 1; r++) {
			for(int c = col - 1; c <= col + 1; c++) {
				//if the neighbor is the same as turn, then skip
				if(r == row && c == col) {
					continue;
				}
				//if the neighbor is within range of board
				else if(isValidCell(r, c)) {
					//System.out.print("(" + r + "," + c + ")");
					//if the space the neighbor is in is occupied and it matches the target, found the second symbol after turn is placed
					if(isMatch(r, c, SecondSym, SecondClr)) {
						//System.out.println("Found Second Symbol");
						//if the current symbol (current turn) is S, then find change of target coord and current coord
						//1 represents symbol S
						//2 represent symbol O
						
						int thirdCellRow = getThirdCell(r, row, turnSym);
						int thirdCellCol = getThirdCell(c, col, turnSym);
						//if the third cell is within range of board
						if(isValidCell(thirdCellRow, thirdCellCol)) {
							if(isMatch(thirdCellRow, thirdCellCol, 1, SecondClr)) {
								//System.out.println("(" + thirdCellRow + "," + thirdCellCol + "), found winner!);
								return true;
									
							}
						}
							
					}
				}
			}
		}
		return false;

	}
	public GameState getGameState() {
		return currentGameState;
	}

}
