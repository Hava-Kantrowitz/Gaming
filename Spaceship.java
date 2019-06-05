import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

//controls the image and coordinates, and regulates movement

public class Spaceship extends Sprite{
	
	private int dx;
	private int dy;
	private List<Missile> missiles;
	
	public Spaceship(int x, int y) {
		super(x, y);
		initSpaceship();
	}
	
	private void initSpaceship() {
		missiles = new ArrayList<>();
		
		loadImage("src/craft.png");
		getImageDimensions();
	}
	
	public void move() {
		x += dx;
		y += dy;
	}
	
	public List<Missile> getMissiles(){
		return missiles;
	}
	
	//key pressed and key released controlled with key event
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_LEFT) {
			dx = -1;
		}
		
		if(key == KeyEvent.VK_RIGHT) {
			dx = 1;
		}
		
		if(key == KeyEvent.VK_UP) {
			dy = -1;
		}
		
		if(key == KeyEvent.VK_DOWN) {
			dy = 1;
		}
		
		if(key == KeyEvent.VK_SPACE) {
			fire();
		}
	}
	
	public void fire() {
		missiles.add(new Missile(x+width, y+height/2));
	}
	
	//spacecraft will stop moving when key is released
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_LEFT) {
			dx = 0;
		}
		
		if(key == KeyEvent.VK_RIGHT) {
			dx = 0;
		}
		
		if(key == KeyEvent.VK_UP) {
			dy = 0;
		}
		
		if(key == KeyEvent.VK_DOWN) {
			dy = 0;
		}
	}

}
