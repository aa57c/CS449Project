package sprint0_product;

import java.awt.event.*;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

import java.awt.*;


@SuppressWarnings("serial")
public class SOS_GAME_GUI{
	public static void main(String[] args) {
		JFrame frame = new JFrame("SOS_GAME_WINDOW");
		frame.setSize(600, 600);
		JCheckBox c1 = new JCheckBox("Record Game");
		JRadioButton rbtn1 = new JRadioButton();
		JRadioButton rbtn2 = new JRadioButton();
		rbtn1.setText("Human");
		rbtn2.setText("Computer");
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
