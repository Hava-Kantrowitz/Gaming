import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

import javax.swing.JPanel;

//THIS BOARD CREATES AN IMAGE

public class Board extends JPanel{
	@Override
	public void paintComponent(Graphics g) {//does the painting
		super.paintComponent(g);
		drawDonut(g);//draws a donut -- created method
	}
	
	private void drawDonut(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;//cast the graphics into 2d graphics
		
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);//RH makes graphics
		//look better by enabling hints, turning antialias on makes things look more lifelike but code slower, antialias off is
		//less lifelike but faster performance
		
		rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g2d.setRenderingHints(rh);//sets the 2d graphics to the rendering style created
		
		Dimension size = getSize();
		double w = size.getWidth();
		double h = size.getHeight();//height and width are needed to center shape on window
		
		Ellipse2D e = new Ellipse2D.Double(0, 0, 80, 130);//create a 2d ellipse at the given coordinates
		g2d.setStroke(new BasicStroke(1));//Stroking a Shape is like tracing its outline with a marking pen of the appropriate size and shape. 
		//The area where the pen would place ink is the area enclosed by the outline Shape.
		g2d.setColor(Color.gray);
		
		//moves around the full circle to create ellipse
		for(double deg = 0; deg < 360; deg += 5) {
			AffineTransform at = AffineTransform.getTranslateInstance(w/2, h/2);//creates a new donut line
			//affine transform preserves all points and lines -- think purely linear transformation
			
			at.rotate(Math.toRadians(deg));//performs rotation
			g2d.draw(at.createTransformedShape(e));//draws the ellipse
		}
		
	}
}
