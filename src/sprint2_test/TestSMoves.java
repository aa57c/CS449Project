package sprint2_test;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sprint2_product.Interactive_Board;

public class TestSMoves {
	private Interactive_Board board;

	@Before
	public void setUp() throws Exception {
		board = new Interactive_Board();
	}

	@After
	public void tearDown() throws Exception {
	}

	// acceptance criterion 2.1
	@Test
	public void testCrossTurnMoveVacantCell() {
		board.makeMove(0, 0);
		assertEquals("", board.getCell(0, 0), 1);
		assertEquals("", board.getPlayerSymbol(), 'O');
	}

	// acceptance criterion 2.2
	@Test
	public void testCrossTurnMoveNonVacantCell() {
		board.makeMove(0, 0);
		board.makeMove(1, 0);
		assertEquals("", board.getCell(1, 0), 2);
		assertEquals("", board.getPlayerSymbol(), 'X');
		board.makeMove(0, 0);
		assertEquals("", board.getPlayerSymbol(), 'X');
	}

	// acceptance criterion 2.3 - 1
	@Test
	public void testCrossTurnInvalidRowMove() {
		board.makeMove(this.board.getBoardsize() + 1, 0);
		assertEquals("", board.getPlayerSymbol(), 'X');
	}

	// acceptance criterion 2.3 - 2
	@Test
	public void testCrossTurnInvalidColumnMove() {
		board.makeMove(0, this.board.getBoardsize() + 1);
		assertEquals("", board.getPlayerSymbol(), 'X');
	}

}
