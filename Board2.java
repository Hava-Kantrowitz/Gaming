import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

//THIS BOARD USES AN IMAGE

public class Board2 extends JPanel{
	
	private Image groot;
	
	public Board2() {//in constructor, create board setup
		initBoard();
	}
	
	private void initBoard() {
		loadImage();//get the image
		
		int w = groot.getWidth(this);
		int h = groot.getHeight(this);
		setPreferredSize(new Dimension(w, h));//draws the image on the window
	}
	
	private void loadImage() {//create the image icon
		ImageIcon ii = new ImageIcon("src/BabyGroot.jpg");
		groot = ii.getImage();
	}
	
	@Override
	public void paintComponent(Graphics g) {//this is necessary to actually paint the loaded image
		g.drawImage(groot, 0, 0, null);//get an image from the image icon
	}
}
