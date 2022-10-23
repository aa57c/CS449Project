package sprint2_product;

public class Interactive_Board {
	private int[][] grid;
	private char turn = 'S';
	private int boardsize;
	
	
	

	
	
	public void setBoardsize(int size) {
		this.boardsize = size;
		this.grid = new int[this.boardsize][this.boardsize];
	}
	
	
	public int getBoardsize() {
		return this.boardsize;
	}
	
	
	public Interactive_Board() {
		grid = new int[this.boardsize][this.boardsize];
	}
	
	
	public int getCell(int row, int column) {
		if (row >= 0 && row < this.boardsize && column >= 0 && column < this.boardsize)
			return grid[row][column];
		else
			return -1;
	}

	public char getTurn() {
		return turn;
	}
	

	/*
	 * Note for tomorrow (10/23/2022)
	 * create setter, getter for symbol
	 * create setter, getter for color
	 * change makeMove to put symbol on board based on COLOR
	 * 
	 * 
	 * 
	 * 
	 * */
	public void makeMove(int row, int column) {
		if (row >= 0 && row < this.boardsize && column >= 0 && column < this.boardsize
				&& grid[row][column] == 0) {
			grid[row][column] = (turn == 'S')? 1 : 2; 
			turn = (turn == 'S')? 'O' : 'S';
		}
	}




}
