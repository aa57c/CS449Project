package sprint3_product;

public class Interactive_Board {
	public enum Cell {EMPTY, S, O}
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
				this.grid[row][col] = Cell.EMPTY;
			}
		}
		this.currentGameState = GameState.PLAYING;
		
	}
	
	
	public Cell getCell(int row, int column) {
		if (row >= 0 && row < this.boardsize && column >= 0 && column < this.boardsize) {
			return this.grid[row][column];
		}
		else {
			return null;
		}

	}

	public void makeMove(int row, int column) {
		if (row >= 0 && row < this.boardsize && column >= 0 && column < this.boardsize
				&& this.grid[row][column] == Cell.EMPTY) {
			this.grid[row][column] = (this.player_symbol == 'S') ? Cell.S : Cell.O;
			updateGameState(row, column);
			this.player_color = (this.player_color == "red")? "blue" : "red";
		}
	}

	private void updateGameState(int row, int column) {
		if(hasWon(row, column)) {
			currentGameState = (this.player_color == "red") ? GameState.RED_WON : GameState.BLUE_WON;
		}
		else if(isDraw()) {
			currentGameState = GameState.DRAW;
		}
	}
	private boolean isDraw() {
		for (int row = 0; row < this.boardsize; row++) {
			for (int col = 0; col < this.boardsize; col++) {
				if (this.grid[row][col] == Cell.EMPTY) {
					return false; // an empty cell found, not draw
				}
			}
		}
		return true;
	}
	private boolean hasWon(int row, int col) {
		return(this.grid[row][col] == Cell.S &&     //horizontal SOS (3 in a row)
				this.grid[row][col + 1] == Cell.O &&
				this.grid[row][col + 2] == Cell.S);
	}
	public GameState getGameState() {
		return currentGameState;
	}
	
}