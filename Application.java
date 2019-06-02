
import java.awt.EventQueue;

import javax.swing.JFrame;

public class Application extends JFrame{
	
	public Application() {
		initUI();//creates the UI
	}
	
	private void initUI() {
		add(new Board2());//entry point of game
		
		pack();//puts the board in center of JFrame container, THIS IS FOR IMAGES
		
		//setSize(330, 330);//put the board in center of JFrame container, THIS IS FOR PAINTED
		
		setTitle("Groot");//sets title of frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//exits the screen when closed
		setLocationRelativeTo(null);
	}
	
	public static void main(String[] args) {//makes code visible onscreen
		EventQueue.invokeLater(() ->{
			Application ex = new Application();
			ex.setVisible(true);
		});
	}

}
