import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Timer;//must be a util timer!
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

//Uses utility timer to animate

public class Board4 extends JPanel{
	
	private final int B_WIDTH = 350;
	private final int B_HEIGHT = 350;
	private final int INITIAL_X = -40;
	private final int INITIAL_Y = -40;
	private final int INITIAL_DELAY = 100;
	private final int PERIOD_INTERVAL = 25;
	
	private Image star;
	private Timer timer;
	private int x, y;
	
	public Board4() {
		initBoard();
	}
	
	private void loadImage() {
		ImageIcon ii = new ImageIcon("src/star.png");
		star = ii.getImage();
	}
	
	private void initBoard() {
		
		setBackground(Color.PINK);
		setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
		
		loadImage();
		
		x = INITIAL_X;
		y = INITIAL_Y;
		
		timer = new Timer();
		timer.scheduleAtFixedRate(new ScheduleTask(), INITIAL_DELAY, PERIOD_INTERVAL);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		drawStar(g);
	}
	
	private void drawStar(Graphics g) {
		g.drawImage(star, x, y, this);
		Toolkit.getDefaultToolkit().sync();
	}
	
	//has class, calls the class which calls super run method every 10 ms
	//Allows greater control over animation than swing timer, better oop handling
	
	private class ScheduleTask extends TimerTask{
		
		@Override
		public void run() {
			x += 1;
			y += 1;
			
			if(y > B_HEIGHT) {
				y = INITIAL_Y;
				x = INITIAL_X;
			}
			
			repaint();
		}
	}

}
