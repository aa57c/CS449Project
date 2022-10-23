package sprint2_test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sprint2_product.Interactive_Board;
import sprint2_product.SOS_GUI;

public class TestSOS_GUI {
	private Interactive_Board board;
	
	@Before
	public void setUp() throws Exception {
		board = new Interactive_Board();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testEmptyBoard() {	
		new SOS_GUI(board); 
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testNonEmptyBoard() {
		board.makeMove(0, 0);
		board.makeMove(1, 1);		
		new SOS_GUI(board); 
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


}
