package sprint2_test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import sprint2_product.Interactive_Board;

public class TestOMoves {
	private Interactive_Board board;

	@Before
	public void setUp() throws Exception {
		board = new Interactive_Board();
		board.makeMove(1, 1);	
	}


	@Test
	public void testOTurnMoveVacantCell() {
		board.makeMove(0, 0); 
		assertEquals("", board.getCell(0, 0), 2);
		assertEquals("", board.getPlayerSymbol(), 'X');
	}


	@Test
	public void testOTurnMoveNonVacantCell() {
		board.makeMove(0, 0); // NOUGHT move
		board.makeMove(1, 0); // CROSS move
		assertEquals("", board.getPlayerSymbol(), 'O');
		board.makeMove(1, 0); // invalid NOUGHT move
		assertEquals("", board.getPlayerSymbol(), 'O');
	}


	@Test
	public void testOTurnInvalidRowMove() {
		board.makeMove(this.board.getBoardsize() + 1, 0); 
		assertEquals("", board.getPlayerSymbol(), 'O');
	}
	

	@Test
	public void testOInvalidColumnMove() {
		board.makeMove(0, this.board.getBoardsize() + 1); 
		assertEquals("", board.getPlayerSymbol(), 'O');
	}

}
