import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

//This uses thread animation

public class Board5 extends JPanel implements Runnable{
	
	private final int B_WIDTH = 350;
	private final int B_HEIGHT = 350;
	private final int INITIAL_X = -40;
	private final int INITIAL_Y = -40;
	private final int DELAY = 25;
	
	private Image groot;
	private Thread animator;
	private int x, y;
	
	public Board5() {
		initBoard();
	}
	
	private void loadImage() {
		ImageIcon ii = new ImageIcon("src/BabyGroot.jpg");
		groot = ii.getImage();
	}
	
	private void initBoard() {
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
		
		loadImage();
		
		x = INITIAL_X;
		y = INITIAL_Y;
	}
	
	//This is used for initialization
	@Override
	public void addNotify() {
		super.addNotify();
		
		animator = new Thread(this);
		animator.start();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		drawGroot(g);
	}
	
	private void drawGroot(Graphics g) {
		g.drawImage(groot, x, y, this);
		Toolkit.getDefaultToolkit().sync();
	}
	
	private void cycle() {
		
		x += 1;
		y += 1;
		
		if (y > B_HEIGHT) {
			y = INITIAL_Y;
			x = INITIAL_X;
		}
	}
	
	@Override
	public void run() {
		long beforeTime, timeDiff, sleep;
		
		beforeTime = System.currentTimeMillis();
		
		//compute system time so that cycle and repaint methods run at constant speeds
		
		while(true) {
			cycle();
			repaint();
			
			timeDiff = System.currentTimeMillis() - beforeTime;
			sleep = DELAY - timeDiff;
			
			if(sleep < 0) {
				sleep = 2;
			}
			
			try {
				Thread.sleep(sleep);
			} catch (InterruptedException e) {
				String msg = String.format("Thread interrupted: %s", e.getMessage());
				
				JOptionPane.showMessageDialog(this,  msg, "Error", JOptionPane.ERROR_MESSAGE);
			}
			
			beforeTime = System.currentTimeMillis();
		}
	}

}
