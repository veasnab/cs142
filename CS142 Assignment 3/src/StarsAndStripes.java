import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

// Bun, Veasna

public class StarsAndStripes {
	public static void drawFlag(int stars, int stripes, java.awt.Graphics g, int x, int y, int width, int height) {
		// Fill this in according to the assignment!
		
		//whiteBase.
		g.setColor(Color.white);
		g.fillRect(x, y, width, height);
		
		int yAxis = y;
		//redStripes
		//the number of Stripes is half the size of Stripes.
		//y is increasing by height divided by stripes; multiply by two to leave a white section showing.
		for (int totalStripes = 1; totalStripes != (int)Math.ceil(stripes/2.0); totalStripes = totalStripes + 1) {
			g.setColor(Color.red);
			g.fillRect(x, yAxis, width, (height/ stripes));	
			yAxis = yAxis + (2 * height/stripes);
		//last red Stripes require a special condition so its on its own
			if (totalStripes + 1 == (int)Math.ceil(stripes/2.0)) {
				g.fillRect(x, yAxis, width, (height/ stripes) + 5);		
			}
		}
		//starField
		//height should be equal to the red stripe height; multiply by the number of red stripes.
		int starFieldHeight = (height/stripes) * (int)Math.ceil(stripes/2.0);
		// width should be proportional to the flag (star field height × base width / base height).
		int starFieldWidth = ((starFieldHeight * width) / height);
		g.setColor(Color.blue);
		g.fillRect(x, y, starFieldWidth, starFieldHeight);
		
		//drawStar
		// counting the amount of stars in one rows.
		// total amount of stars in a is calculated so that we can later place the largest value of the multiply in the column. 
		int countStars = stars;
		if (countStars % 2 == 0) {
			int countEvenRows = 2;
			while (countEvenRows < countStars) {
				countStars = countStars / 2;
				countEvenRows = countEvenRows + 2;
				if (countEvenRows % 2 == 1) {
					return;
				}
			}
		}
		
		else if (countStars % 3 == 0) {
			int countOddRows = 3;
			while (countOddRows <= countStars) {
				countStars = countStars / 3;
				countOddRows = countOddRows + 3;
				if (countOddRows % 3 == 1) {
					return;
				}	
			}
		}
		// the largest number will be in the column while the smallest number will be use for the rows; starPerSet is measure in column.
		int starPerColumn = 0;
		starPerColumn = starPerColumn + stars / countStars; 
		if (starPerColumn > countStars) {
			starPerColumn = starPerColumn + 0;
		}
		else starPerColumn = countStars;
		
		//find the starSize by comparing the width or height of the starfield to the amount in the row or height. looking for the smallest
		int starSize = 0;
		int areaWidth =  (starFieldWidth)/((starPerColumn) * 5);
		int areaHeight = (starFieldHeight)/((stars/starPerColumn) * 5); 
		starSize = areaHeight;
		
		
		
		

		//looping the stars. 	
		int xPosition = x;
		int yPosition = y;
		int columnOfStars= starPerColumn;
		int rowOfStars = stars/starPerColumn;
		if (stars != 0) {
			while (columnOfStars != 0) {
				drawStar(g, xPosition, y, starSize);
				xPosition = xPosition  + starSize * 5;
				columnOfStars = columnOfStars - 1; 
			}
		}
		//rows and column reset to x at orgin. 
			while (rowOfStars != 1) {
				xPosition = x;
				columnOfStars = starPerColumn; 
				yPosition = yPosition + starSize * 5;
				while (columnOfStars != 0) {
					drawStar(g, xPosition, yPosition, starSize);
					xPosition = xPosition + starSize * 5;
					columnOfStars = columnOfStars - 1; 
				}
				rowOfStars = rowOfStars - 1;
			}
			 
}
		
	public static void drawStar(java.awt.Graphics g, int x, int y, int size) {
		// Fill this in according to the assignment!
		g.setColor(Color.white);
		g.drawLine(x + size * 1, y + size * 5, x + size * 5/2, y + size * 0);
		g.drawLine(x + size * 5/2, y + size * 0, x + size * 4, y + size * 5);
		g.drawLine(x + size * 4, y + size * 5, x + size * 0, y + size * 2);
		g.drawLine(x + size * 0, y + size * 2, x + size * 5, y + size * 2);
		g.drawLine(x + size * 5, y + size * 2, x + size * 1, y + size * 5);
	
		
		
	}

	// Only alter the "drawFlag" part of the paintComponent
	// code to call it in different ways. You can also test
	// drawing multiple flags at once!
	public static void main(String[] args) {
		JFrame window = new JFrame("Graphics window");
		window.setLocationByPlatform(true);
		final JLabel coords = new JLabel(" ");
		@SuppressWarnings("serial")
		final JPanel panel = new JPanel() {

			protected void paintComponent(Graphics gx) {
				coords.setText(" ");
				Graphics2D g = (Graphics2D) gx;
				int width = getWidth();
				int height = getHeight();
				g.setBackground(Color.GREEN); // To make sure you cover the base rectangle!
				g.clearRect(0, 0, width, height);
				g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g.setColor(Color.BLACK);

				// You could alter this code to try different flags!
				drawFlag(48, 16, g, width/2, height/2, width/2, height/2);
				drawFlag(24, 15, g, 0, height/2, width/2, height/2);
				drawFlag(20, 14, g, width/2, 0, width/2, height/2);
				drawFlag(15, 13, g, 0, 0, width/2, height/2);
			}
		};
		panel.addMouseMotionListener(new MouseMotionListener() {


			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				coords.setText(e.getX()+", "+e.getY());				
			}
			
		});
		window.setLayout(new BorderLayout());
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		window.setSize(d.width / 2, d.height / 2);

		JPanel coordPanel = new JPanel();
		coordPanel.setLayout(new BorderLayout());
		coordPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
		window.add(coordPanel, BorderLayout.SOUTH);
		coordPanel.add(coords, BorderLayout.WEST);
		
		window.setBackground(Color.WHITE); // To make sure you cover the base rectangle!		
		panel.setBackground(Color.BLACK);
		window.add(panel, BorderLayout.CENTER);
		//window.setContentPane(panel);
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
