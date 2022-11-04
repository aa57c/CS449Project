package sprint3_product;

public class Interactive_Board {
	//public enum Cell {EMPTY, S, O}
	public enum GameState {PLAYING, DRAW, RED_WON, BLUE_WON}
	private String gameMode = "";
	private GameState currentGameState;
	private Cell[][] grid;
	private char player_symbol = ' ';
	private String player_color = "red";
	private int boardsize;
	
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
		InitializeGrid();
	}
	
	public int getBoardsize() {
		return this.boardsize;
	}
	
	
	public Interactive_Board() {
		grid = new Cell[this.boardsize][this.boardsize];
		InitializeGrid();
	}
	
	public void InitializeGrid() {
		for(int row = 0; row < this.boardsize; row++ ) {
			for(int col = 0; col < this.boardsize; col++) {
				this.grid[row][col] = new Cell();
			}
		}
		this.currentGameState = GameState.PLAYING;
		
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
			this.grid[row][column].setMyPair(this.player_symbol == 'S' ? 1 : 2, this.player_color);
			updateGameState(this.grid[row][column], row, column);
			this.player_color = (this.player_color == "red")? "blue" : "red";
		}
	}

	private void updateGameState(Cell turn, int row, int column) {
		hasWon(turn, row, column);
		/*
		if(hasWon(turn, row, column)) {
			currentGameState = (turn.getClr() == "red") ? GameState.RED_WON : GameState.BLUE_WON;
			this.player_symbol = ' ';
		}
		*/
		/*else*/ if(isDraw()) {
			currentGameState = GameState.DRAW;
			this.player_symbol = ' ';
		}
	}
	private boolean isDraw() {
		for (int row = 0; row < this.boardsize; row++) {
			for (int col = 0; col < this.boardsize; col++) {
				if (this.grid[row][col].getSym() == 0) {
					return false; // an empty cell found, not draw
				}
			}
		}
		return true;
	}
	//function stub for determining wins
	private void hasWon(Cell turn, int row, int col) {
		//set target symbol and color based on turn (the target is the first space found after turn is made)
		int targetSym = turn.getSym() == 1 ? 2 : 1;
		String targetClr = turn.getClr() == "red" ? "red" : "blue";
		//testing printing statements
		System.out.println("Player's Turn: (" + row + "," + col + ")");
		if(turn.getSym() == 1) {
			System.out.println("Player's Color: " + turn.getClr() + ", Player's Symbol: S");
		}
		else {
			System.out.println("Player's Color: " + turn.getClr() + ", Player's Symbol: O");
		}
		System.out.println("Neighbors of Player...");
		//loop through all neighbor cells
		for(int r = row - 1; r <= row + 1; r++) {
			for(int c = col - 1; c <= col + 1; c++) {
				//if the neighbor is the same as turn, then skip
				if(r == row && c == col) {
					continue;
				}
				//if the neighbor is within range of board
				else if(r >= 0 && r < this.boardsize && c >= 0 && c < this.boardsize) {
					System.out.println("(" + r + "," + c + ")");
					//if the space the neighbor is in is occupied and it matches the target, found the second symbol after turn is placed
					if(this.grid[r][c].getSym() > 0 && this.grid[r][c].getSym() == targetSym && this.grid[r][c].getClr() == targetClr) {
						System.out.println("Found Second Symbol");
						//if()
					}
				}
			}
		}
		
		/*
		//check red wins
		if(turn.getClr() == "red") {
			//1 corresponds with the symbol 'S'
			if(turn.getSym() == 1) {
				if(this.grid[row][col + 1].getSym() == 2 ||
						this.grid[row + 1][col + 1].getSym() == 2 ||
						this.grid[row + 1][col].getSym() == 2 ||
						this.grid[row + 1][col - 1].getSym() == 2 ||
						this.grid[row][col - 1].getSym() == 2 ||
						this.grid[row - 1][col - 1].getSym() == 2 ||
						this.grid[row - 1][col].getSym() == 2) {
					
				}
			}
		}
		*/
		//return false;
		
		

	}
	public GameState getGameState() {
		return currentGameState;
	}
	
}