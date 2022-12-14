package sprint0_product;

import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class BoardPanel extends JPanel{
	static final int cols = 10;
	static final int rows = 10;
	static final int originX = 23;
	static final int originY = 37;
	static final int cellSide = 59;
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(int i = 0; i < rows + 1; i++) {
			g.drawLine(originX, originY + i * cellSide, originX + cols * cellSide, originY + i * cellSide);
			
		}
		for(int i = 0; i < cols + 1; i++) {
			g.drawLine(originX + i * cellSide, originY, originX + i * cellSide, originY + rows * cellSide);
		}
		
	}
	
}
