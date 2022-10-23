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
		board.makeMove(1, 1);	// CROSS move, then it is NOUGHT's turn
	}

	// acceptance criterion 3.1
	@Test
	public void testNoughtTurnMoveVacantCell() {
		board.makeMove(0, 0); // NOUGHT move
		assertEquals("", board.getCell(0, 0), 2);
		assertEquals("", board.getPlayerSymbol(), 'X');
	}

	// acceptance criterion 3.2
	@Test
	public void testNoughtTurnMoveNonVacantCell() {
		board.makeMove(0, 0); // NOUGHT move
		board.makeMove(1, 0); // CROSS move
		assertEquals("", board.getPlayerSymbol(), 'O');
		board.makeMove(1, 0); // invalid NOUGHT move
		assertEquals("", board.getPlayerSymbol(), 'O');
	}

	// acceptance criterion 3.3 - 1
	@Test
	public void testNoughtTurnInvalidRowMove() {
		board.makeMove(4, 0); 
		assertEquals("", board.getPlayerSymbol(), 'O');
	}
	
	// acceptance criterion 3.3 - 1
	@Test
	public void testNoughtTurnInvalidColumnMove() {
		board.makeMove(0, 4); 
		assertEquals("", board.getPlayerSymbol(), 'O');
	}

}
