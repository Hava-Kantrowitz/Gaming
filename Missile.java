

public class Missile extends Sprite{
	private final int BOARD_WIDTH = 390;
	private final int MISSILE_SPEED = 2;
	
	public Missile(int x, int y) {
		super(x, y);
		
		initMissile();
	}
	
	private void initMissile() {
		loadImage("src/missile.png");
		getImageDimensions();
	}
	
	//missile moves at a constant speed, when it hits the right border it disappears
	public void move() {
		x += MISSILE_SPEED;
		if(x > BOARD_WIDTH) {
			visible = false;
		}
	}
}