package sprint5_test;

import static org.junit.Assert.assertEquals;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sprint5_product.Board;
import sprint5_product.Board.GameState;
import sprint5_product.SOS_GUI;

public class TestRecordSimpleGame {
	private Board board;
	private Boolean is_Red_Human = false;
	private Boolean is_Blue_Human = false;
	private Boolean record = false;
	private ArrayList<String> fileContent = new ArrayList<String>();
	private FileWriter myWriter;
	
	
	@Before
	public void setUp() {
		this.board = new Board();
		this.board.setBoardsize(5);
		this.board.setGameMode("Simple Game");
		this.setIs_Red_Human(true);
		this.setIs_Blue_Human(true);
		this.setRecord(true);
		
	}
	
	@After
	public void writeFile() {
		LocalDateTime date = LocalDateTime.now();
		DateTimeFormatter date_format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		String formatted_date = date.format(date_format);
		try {
			myWriter = new FileWriter("GameRecord.txt", true);
			myWriter.write(formatted_date + "\n");
			for(String s : this.fileContent) {
				myWriter.write(s + "\n");
				System.out.println("writing to file");
			}
			System.out.println("closed file");
			myWriter.write("\n");
			myWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void TestHumanVHuman() {
		String gameSettings = "gm=";
			//add game mode to settings string
			if(board.getGameMode() == "Simple Game") {
				gameSettings += "s;";
			}
			else {
				gameSettings += "g;";
			}
			//add board size to settings string
			gameSettings += ("bs=" + board.getBoardsize() + ";");
			
			//add red player to settings string
			gameSettings += "rp=";
			if(this.is_Red_Human) {
				gameSettings += "h;";
			}	
			else {
				gameSettings += "c;";
			}
			//add blue player to settings string
			gameSettings += "bp=";
			if(this.is_Blue_Human) {
				gameSettings += "h;";
			}
			else{
				gameSettings += "c;";
			}
			this.fileContent.add(gameSettings);
			
		//red's move
		board.setPlayerSymbol('S');
		board.makeMove(0, 1);
		String move = "plr=r;sym=S;row=0;col=1;";
		this.fileContent.add(move);

		
		//blue's turn
		board.setPlayerSymbol('O');
		board.makeMove(0, 0);
		String move2 = "plr=b;sym=O;row=0;col=0;";
		this.fileContent.add(move2);
		
		
		
		//red's turn
		board.setPlayerSymbol('O');
		board.makeMove(0, 2);
		String move3 = "plr=r;sym=O;row=0;col=2;";
		this.fileContent.add(move3);
		
		//blue's turn
		board.setPlayerSymbol('S');
		board.makeMove(3, 2);
		String move4 = "plr=b;sym=S;row=3;col=2;";
		this.fileContent.add(move4);
		
		//red's turn
		board.setPlayerSymbol('S');
		board.makeMove(0, 3);
		String move5 = "plr=r;sym=S;row=0;col=3;";
		this.fileContent.add(move5);
		
		String result = "res=r";
		this.fileContent.add(result);
		
		assertEquals("", board.getGameState(), GameState.RED_WON);
		new SOS_GUI(this.board);
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	public Boolean getIs_Red_Human() {
		return is_Red_Human;
	}

	public void setIs_Red_Human(Boolean is_Red_Human) {
		this.is_Red_Human = is_Red_Human;
	}

	public Boolean getIs_Blue_Human() {
		return is_Blue_Human;
	}

	public void setIs_Blue_Human(Boolean is_Blue_Human) {
		this.is_Blue_Human = is_Blue_Human;
	}

	public Boolean getRecord() {
		return record;
	}

	public void setRecord(Boolean record) {
		this.record = record;
	}


}
