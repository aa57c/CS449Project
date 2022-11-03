package sprint3_product;

public class Interactive_Board {
	public enum GameState {PLAYING, DRAW, RED_WON, BLUE_WON}
	private String gameMode = "";
	private GameState currentGameState;
	private MyPair[][] grid;
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
		this.grid = new MyPair[size][size];
		InitializeGrid();
	}
	
	public int getBoardsize() {
		return this.boardsize;
	}
	
	
	public Interactive_Board() {
		grid = new MyPair[this.boardsize][this.boardsize];
		InitializeGrid();
	}
	
	public void InitializeGrid() {
		for(int row = 0; row < this.boardsize; row++ ) {
			for(int col = 0; col < this.boardsize; col++) {
				this.grid[row][col] = new MyPair();
			}
		}
		this.currentGameState = GameState.PLAYING;
		
	}
	
	
	public int getCell(int row, int column) {
		if (row >= 0 && row < this.boardsize && column >= 0 && column < this.boardsize)
			return this.grid[row][column].getSym();
		else
			return -1;
	}
	
	public String getCellClr(int row, int column) {
		if (row >= 0 && row < this.boardsize && column >= 0 && column < this.boardsize)
			return this.grid[row][column].getClr();
		else
			return "None";
		
	}
	
	public void makeMove(int row, int column) {
		if (row >= 0 && row < this.boardsize && column >= 0 && column < this.boardsize
				&& this.grid[row][column].getSym() == 0) {
			//save player's color and symbol to the grid
			this.grid[row][column].setMyPair(this.player_symbol == 'S'? 1 : 2, this.player_color);
			//set next player's turn
			updateGameState(this.player_symbol, row, column);
			this.player_color = (this.player_color == "red")? "blue" : "red";
		}
	}
	private void updateGameState(char sym, int row, int col) {
		if(hasWon(sym, row, col)) {
			currentGameState = (this.player_color == "red") ? GameState.RED_WON : GameState.BLUE_WON;
		}
		else if(isDraw()) {
			currentGameState = GameState.DRAW;
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
	private boolean hasWon(char sym, int row, int col) {
		
		
		return false;
	}
	public GameState getGameState() {
		return currentGameState;
	}
	
}