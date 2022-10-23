package sprint0_product;

import java.awt.event.*;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

import java.awt.*;



public class SOS_GAME_GUI{
	public static void main(String[] args) {
		JFrame frame = new JFrame("SOS_GAME_WINDOW");
		frame.setSize(600, 600);
		JCheckBox c1 = new JCheckBox("Record Game");
		JRadioButton rbtn1 = new JRadioButton("Human");
		//rbtn1.setBounds(500, 500, 10, 10);
		JRadioButton rbtn2 = new JRadioButton("Computer");
		ButtonGroup g = new ButtonGroup();
		g.add(rbtn1);
		g.add(rbtn2);
		JLabel gameLabel = new JLabel("SOS Game");
		BoardPanel panel = new BoardPanel();
		panel.add(c1);
		panel.add(rbtn1);
		panel.add(rbtn2);
		panel.add(gameLabel);
		frame.add(panel);
		frame.setVisible(true);
		
	}
	

}
