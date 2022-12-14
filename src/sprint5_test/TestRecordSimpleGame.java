package sprint5_test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sprint5_product.Board;
import sprint5_product.Board.GameState;
import sprint5_product.SOS_GUI;

public class TestRecordSimpleGame {
	private Board board;
	private ArrayList<String> fileContent = new ArrayList<String>();
	
	
	@Before
	public void setUp() {
		this.board = new Board();
		this.board.setGameMode("Simple Game");
		this.board.setBoardsize(5);
		
	}

	
	
	
	@Test
	public void TestHumanVHuman() {
		//record game settings
		String gameSettings = "gm=s;";
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
		
		assertEquals("", board.getGameState(), GameState.RED_WON);
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
			FileWriter fileWriter = new FileWriter("src/sprint5_test/TestGameRecords/SimpleGameRecords.txt");
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
