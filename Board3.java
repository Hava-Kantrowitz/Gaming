import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;//must be a swing timer!!

//Uses swing timer to animate

public class Board3 extends JPanel implements ActionListener{
	
	//Puts star in bottom right corner
	private final int B_WIDTH = 350;
	private final int B_HEIGHT = 350;
	private final int INITIAL_X = -40;
	private final int INITIAL_Y = -40;
	private final int DELAY = 25;//speed of animation in ms
	
	private Image star;
	private Timer timer;
	private int x, y;
	
	public Board3() {
		initBoard();
	}
	
	//Create timer class and start it
	//In order to use action performed, must implement action method
	
	private void loadImage() {
		ImageIcon ii = new ImageIcon("src/star.png");
		star = ii.getImage();
	}
	
	private void initBoard() {
		
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
		
		loadImage();
		
		x = INITIAL_X;
		y = INITIAL_Y;
		
		timer = new Timer(DELAY, this);
		timer.start();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		drawStar(g);
	}
	
	//Always delegate the actual drawing!! Good oop practice
	private void drawStar(Graphics g) {
		g.drawImage(star, x, y, this);
		Toolkit.getDefaultToolkit().sync();//synchronises the painting on systems
		//that buffer graphics events -- smooths animation
	}
	
	//timer repeatedly calls this method, increase x and y values and repaint
	@Override
	public void actionPerformed(ActionEvent e) {
		x += 1;
		y += 1;
		
		if (y > B_HEIGHT) {
			y = INITIAL_Y;
			x = INITIAL_X;
		}
		
		repaint();
	}
}
