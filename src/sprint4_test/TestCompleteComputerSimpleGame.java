package sprint4_test;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import sprint4_product.Board;
import sprint4_product.Board.GameState;
import sprint4_product.SOS_GUI;

public class TestCompleteComputerSimpleGame {
	private Board board;
	private SOS_GUI gui;
	@Before
	public void setUp() throws Exception{
		board = new Board();	
	}
	@Test
	public void TestComputer() {
		gui = new SOS_GUI(board);
		gui.returnTextField().setText("5");
		gui.returnSimpleGameButton().doClick();
		gui.returnRedComputerButton().doClick();
		gui.returnBlueComputerButton().doClick();
		gui.returnNewGameButton().doClick();
		assertTrue("", board.getGameState() == GameState.BLUE_WON || 
				board.getGameState() == GameState.RED_WON ||
				board.getGameState() == GameState.DRAW);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
