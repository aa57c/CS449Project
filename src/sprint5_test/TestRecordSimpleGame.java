package sprint5_test;

import static org.junit.Assert.assertTrue;

import java.awt.Desktop;
import java.awt.Desktop.Action;
import java.io.File;


import org.junit.Before;
import org.junit.Test;


import sprint5_product.Board;
import sprint5_product.Board.GameState;
import sprint5_product.SOS_GUI;

public class TestRecordSimpleGame {
	private Board board;
	private SOS_GUI gui;
	
	@Before
	public void setUp() {
		this.board = new Board();	
		
	}

	@Test
	public void TestHumanVHuman() {
		gui = new SOS_GUI(board);
		gui.returnTextField().setText("5");
		gui.returnSimpleGameButton().doClick();
		gui.returnRedHumanButton().doClick();
		gui.returnBlueHumanButton().doClick();
		gui.returnNewGameButton().doClick();
		
		gui.returnRedS().doClick();
		board.makeMove(0, 0);
		gui.updateTurns(board.getPlayerColor());
		gui.recordMove(0, 0);
		gui.computerTurn();
		
		gui.returnBlueO().doClick();
		board.makeMove(1, 2);
		gui.updateTurns(board.getPlayerColor());
		gui.recordMove(1, 2);
		gui.computerTurn();
		
		
		
		
		
		
		
		assertTrue("", board.getGameState() == GameState.BLUE_WON || 
				board.getGameState() == GameState.RED_WON ||
				board.getGameState() == GameState.DRAW);
		try {
			Thread.sleep(10000);
			File directory = new File("GameRecords.txt");
			if(Desktop.getDesktop().isSupported(Action.BROWSE_FILE_DIR) && directory.exists()) {
				Desktop.getDesktop().browseFileDirectory(directory);
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}
