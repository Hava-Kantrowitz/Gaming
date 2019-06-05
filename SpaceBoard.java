import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.Timer;

public class SpaceBoard extends JPanel implements ActionListener{
	
	private Timer timer;
	private Spaceship ship;
	private final int DELAY = 10;
	private final int ICRAFT_X = 40;
	private final int ICRAFT_Y = 60;
	
	public SpaceBoard() {
		initBoard();
	}
	
	private void initBoard() {
		addKeyListener(new TAdapter());
		setBackground(Color.BLACK);
		setFocusable(true);
		
		ship = new Spaceship(ICRAFT_X, ICRAFT_Y);
		timer = new Timer(DELAY, this);
		timer.start();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		doDrawing(g);
		
		Toolkit.getDefaultToolkit().sync();
	}
	
	//draws the ship using coordinates from the ship class
	private void doDrawing(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(ship.getImage(), ship.getX(), ship.getY(), this);
		
		List<Missile> missiles = ship.getMissiles();
		for(Missile missile : missiles) {
			g2d.drawImage(missile.getImage(), missile.getX(), missile.getY(), this);
		}
	}
	
	//ACTION PERFORMED IS CALLED EVERY DELAY MS
	@Override
	public void actionPerformed(ActionEvent e) {
		updateMissiles();
		updateSpaceship();
		repaint();
	}
	
	private void updateMissiles() {
		List<Missile> missiles = ship.getMissiles();
		
		for(int i = 0; i < missiles.size(); i++) {
			Missile missile = missiles.get(i);
			if(missile.isVisible()) {
				missile.move();
			}
			else {
				missiles.remove(i);
			}
		}
	}
	
	private void updateSpaceship() {
		ship.move();
	}
	
	//listen for key events, which are delegated to ship class
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
