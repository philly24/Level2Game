
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Snake {
	JFrame frame;
	static final int WIDTH = 1450;
	static final int HEIGHT = 800;
	GamePanel gamepanel;

	Snake() {
		frame = new JFrame();
	}

	public void setup() {
		gamepanel = new GamePanel(new ObjectManager());
		frame.add(gamepanel);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setSize(WIDTH, HEIGHT);
		frame.getContentPane().setPreferredSize(new Dimension(WIDTH, HEIGHT));
		frame.pack();
		gamepanel.startGame();
		frame.addKeyListener(gamepanel);
	}

	public static void main(String[] args) {
		Snake snake = new Snake();
		snake.setup();

	}
}
