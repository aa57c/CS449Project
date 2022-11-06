package sprint3_test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import sprint3_product.Interactive_Board;
import sprint3_product.Interactive_Board.GameState;
import sprint3_product.SOS_GUI;

public class TestCompleteSimpleGame {
	private Interactive_Board board;
	
	@Before
	public void setUp() throws Exception{
		board = new Interactive_Board();
		board.setBoardsize(5);
		board.setGameMode("Simple Game");
	}
	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testRedWon() {
		//red move
		board.setPlayerSymbol('S');
		board.makeMove(2, 2);
		//blue move
		board.setPlayerSymbol('O');
		board.makeMove(3, 3);
		//red move
		board.setPlayerSymbol('O');
		board.makeMove(1, 2);
		//blue move
		board.setPlayerSymbol('S');
		board.makeMove(3, 0);
		//red move
		board.setPlayerSymbol('S');
		board.makeMove(0, 2);
		assertEquals("", board.getGameState(), GameState.RED_WON);
		new SOS_GUI(board);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	
	}
	public void testBlueWon() {
		//red move
		board.setPlayerSymbol('O');
		board.makeMove(1, 0);
		//blue move
		board.setPlayerSymbol('S');
		board.makeMove(3, 1);
		//red move
		board.setPlayerSymbol('S');
		board.makeMove(0, 0);
		//blue move
		board.setPlayerSymbol('O');
		board.makeMove(2, 2);
		//red move
		board.setPlayerSymbol('S');
		board.makeMove(0, 1);
		//blue move
		board.setPlayerSymbol('S');
		board.makeMove(1, 3);
		assertEquals("", board.getGameState(), GameState.BLUE_WON);
		new SOS_GUI(board);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void testDraw() {
		for(int r = 0; r < board.getBoardsize(); r++) {
			for(int c = 0; c < board.getBoardsize(); c++) {
				board.makeMove(r, c);
			}
		}
		assertEquals("", board.getGameState(), GameState.DRAW);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	
}


