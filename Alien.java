
public class Alien extends Sprite{
	
	private final int INITIAL_X = 400;
	
	public Alien(int x, int y) {
		super(x, y);
		initAlien();
	}
	
	private void initAlien() {
		loadImage("src/alien.png");
		getImageDimensions();
	}
	
	//aliens return to screen on the right after they have left the screen
	public void move() {
		if (x < 0) {
			x = INITIAL_X;
		}
		
		x -= 1;
	}
	

}
