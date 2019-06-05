import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.Timer;

//This board is how you do collisions!

public class CollisionBoard extends JPanel implements ActionListener{
	
	private Timer timer;
	private Spaceship ship;
	private List<Alien> aliens;
	private boolean ingame;
	private final int ICRAFT_X = 40;
	private final int ICRAFT_Y = 60;
	private final int B_WIDTH = 400;
	private final int B_HEIGHT = 300;
	private final int DELAY = 15;
	
	//initial positions of the alien ships
	private final int[][] pos = {
			{2380, 29}, {2500, 59}, {1380, 89},
	        {780, 109}, {580, 139}, {680, 239},
	        {790, 259}, {760, 50}, {790, 150},
	        {980, 209}, {560, 45}, {510, 70},
	        {930, 159}, {590, 80}, {530, 60},
	        {940, 59}, {990, 30}, {920, 200},
	        {900, 259}, {660, 50}, {540, 90},
	        {810, 220}, {860, 20}, {740, 180},
	        {820, 128}, {490, 170}, {700, 30}	
	};
	
	public CollisionBoard() {
		initBoard();
	}
	
	private void initBoard() {
		addKeyListener(new TAdapter());
		setFocusable(true);
		setBackground(Color.BLACK);
		ingame = true;
		
		setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
		ship = new Spaceship(ICRAFT_X, ICRAFT_Y);
		
		initAliens();
		timer = new Timer(DELAY, this);
		timer.start();
	}
	
	//Creates a list of alien objects, taking initial positions from array
	public void initAliens() {
		
		aliens = new ArrayList<>();
		
		for(int[] p : pos) {
			aliens.add(new Alien(p[0], p[1]));
		}
	}
	
	//Draw the sprites or game over message, depending on ingame variable
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(ingame) {
			drawObjects(g);
		}
		
		else {
			drawGameOver(g);
		}
		
		Toolkit.getDefaultToolkit().sync();
	}
	
	private void drawObjects(Graphics g) {
		if(ship.isVisible()) {
			g.drawImage(ship.getImage(), ship.getX(), ship.getY(), this);
		}
		
		List<Missile> ms = ship.getMissiles();
		
		for(Missile missile : ms) {
			if(missile.isVisible()) {
				g.drawImage(missile.getImage(), missile.getX(), missile.getY(), this);
			}
		}
		
		for(Alien alien : aliens) {
			if(alien.isVisible()) {
				g.drawImage(alien.getImage(), alien.getX(), alien.getY(), this);
			}
		}
		
		g.setColor(Color.WHITE);
		g.drawString("Aliens left: " + aliens.size(), 5, 15);
	}
	
	private void drawGameOver(Graphics g) {
		String msg = "Game Over";
		Font small = new Font("Helvetica", Font.BOLD, 14);
		FontMetrics fm = getFontMetrics(small);
		
		g.setColor(Color.white);
		g.setFont(small);
		g.drawString(msg, (B_WIDTH - fm.stringWidth(msg))/2, B_HEIGHT/2);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		inGame();
		
		updateShip();
		updateMissiles();
		updateAliens();
		
		checkCollisions();
		
		repaint();
	}
	
	private void inGame() {
		if(!ingame) {
			timer.stop();
		}
	}
	
	private void updateShip() {
		if(ship.isVisible()) {
			ship.move();
		}
	}
	
	private void updateMissiles() {
		List<Missile> ms = ship.getMissiles();
		for(int i = 0; i < ms.size(); i++) {
			Missile m = ms.get(i);
			if(m.isVisible()) {
				m.move();
			}
			else {
				ms.remove(i);
			}
		}
	}
	
	//game is finished if all aliens are dead
	//Move all aliens, destroyed aliens are removed from list
	private void updateAliens() {
		if(aliens.isEmpty()) {
			ingame = false;
			return;
		}
		
		for(int i = 0; i < aliens.size(); i++) {
			Alien al = aliens.get(i);
			
			if(al.isVisible()) {
				al.move();
			}
			else {
				aliens.remove(i);
			}
		}
	}
	
	//get the rectangles of the objects with the get bounds method
	//intersects method checks if two rectangles intersect
	//if they intersect, make it invisible
	//if it is invisible, it will be removed in the update methods
	public void checkCollisions() {
		Rectangle r3 = ship.getBounds();
		
		for(Alien alien : aliens) {
			Rectangle r2 = alien.getBounds();
			if(r3.intersects(r2)) {
				ship.setVisible(false);
				alien.setVisible(false);
				ingame = false;
			}
		}
		
		List<Missile> ms = ship.getMissiles();
		for(Missile mis : ms) {
			Rectangle r1 = mis.getBounds();
			for(Alien alien : aliens) {
				Rectangle r2 = alien.getBounds();
				if(r1.intersects(r2)) {
					mis.setVisible(false);
					alien.setVisible(false);
				}
			}
		}
	}
	
	private class TAdapter extends KeyAdapter{
		@Override
		public void keyReleased(KeyEvent e) {
			ship.keyReleased(e);
		}
		
		@Override
		public void keyPressed(KeyEvent e) {
			ship.keyPressed(e);
		}
	}
	
	

}
