package sprint2_test;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sprint2_product.Interactive_Board;

public class TestEmptyBoard {
	private Interactive_Board board = new Interactive_Board();

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testNewBoard() {
		for (int row = 0; row < this.board.getBoardsize(); row++) {
			for (int col = 0; col < this.board.getBoardsize(); col++) {
				assertEquals("", board.getCell(row, col), 0); 
			}
		}
	}

	@Test
	public void testInvalidRow() {
		assertEquals("", board.getCell(this.board.getBoardsize(), 0), -1); 
	}

	@Test
	public void testInvalidColumn() {
		assertEquals("", board.getCell(0, this.board.getBoardsize()), -1); 
	}

}
