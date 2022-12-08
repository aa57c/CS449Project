package sprint5_test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sprint5_product.Board;
import sprint5_product.Board.GameState;
import sprint5_product.SOS_GUI;


public class TestRecordGeneralGame {
	private Board board;
	private ArrayList<String> fileContent = new ArrayList<String>();
	
	
	@Before
	public void setUp() {
		this.board = new Board();
		this.board.setGameMode("General Game");
		this.board.setBoardsize(5);
		
	}

	
	
	
	@Test
	public void TestHumanVHuman() {
		//record game settings
		String gameSettings = "gm=g;";
		gameSettings += "bs=" + board.getBoardsize() + ";rp=h;bp=h;\n";
		this.fileContent.add(gameSettings);
		
		//red move
		String move1 = "plr=r;";
		board.setPlayerSymbol('S');
		board.makeMove(1, 1);
		move1 += "sym=S;row=1;col=1;\n";
		this.fileContent.add(move1);
		
		//blue move
		String move2 = "plr=b;";
		board.setPlayerSymbol('S');
		board.makeMove(3, 3);
		move2 += "sym=S;row=3;col=3;\n";
		this.fileContent.add(move2);
		
		//red move
		String move3 = "plr=r;";
		board.setPlayerSymbol('O');
		board.makeMove(1, 2);
		move3 += "sym=O;row=1;col=2;\n";
		this.fileContent.add(move3);
		
		//blue move
		String move4 = "plr=b;";
		board.setPlayerSymbol('O');
		board.makeMove(2, 3);
		move4 += "sym=O;row=2;col=3;\n";
		this.fileContent.add(move4);
		
		//red move
		String move5 = "plr=r;";
		board.setPlayerSymbol('S');
		board.makeMove(1, 3);
		move5 += "sym=S;row=1;col=3;\n";
		this.fileContent.add(move5);
		

		for(int r = 0; r < board.getBoardsize(); r++) {
			for(int c = 0; c < board.getBoardsize(); c++) {
				if(board.getCellSym(r, c) != 0) {
					continue;
				}
				else {
					if(board.getPlayerSymbol() == 'S') {
						board.setPlayerSymbol('O');
					}
					else if(board.getPlayerSymbol() == 'O') {
						board.setPlayerSymbol('S');
					}
					board.makeMove(r, c);
					String move = "plr=" + board.getCellClr(r, c).charAt(0) + ";sym=" + 
					(board.getCellSym(r, c) == 1 ? "S;" : "O;") + "row=" + r + ";col=" + c + ";\n";
					this.fileContent.add(move);
				}
			}
		}
		
		
		assertTrue(board.getGameState() == GameState.BLUE_WON || 
				board.getGameState() == GameState.RED_WON ||
				board.getGameState() == GameState.DRAW);

		assertNotEquals("", fileContent.size(), 0);
		new SOS_GUI(this.board);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		
		
	}
	
	@After
	public void writeFile() {
		try {
			FileWriter fileWriter = new FileWriter("src/sprint5_test/TestGameRecords/GeneralGameRecords.txt");
			for(String s : this.fileContent) {
				fileWriter.write(s);
			}
			fileWriter.write("res=r;\n");
			fileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
