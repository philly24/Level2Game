
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	Timer timer;
	GameObject gameobject;

	Font titleFont;
	Segment segment;
	final static int MENU_STATE = 0;

	final int GAME_STATE = 1;

	final static int END_STATE = 2;
	static int currentState = MENU_STATE;
	static int loser;
	static String winner;
	BufferedImage cad;
	ObjectManager objectmanager;
	
	GamePanel(){
		String path = Paths.get("").toAbsolutePath().toString();
		try {
			
			cad = ImageIO.read(new File(path+ "/src/Caduceus.png"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
	GamePanel(ObjectManager object) {
		timer = new Timer(1000 / 40, this);
		titleFont = new Font("Arial", Font.PLAIN, 48);
		segment = new Segment(0, 0, 10, 10);
		objectmanager = object;
		
	}

	void updateMenuState() {

	}

	void updateGameState() {

		objectmanager.update();

		objectmanager.checkCollision();

		if (loser == 3) {
			currentState = END_STATE;

		}
	}

	void updateEndState() {

	}

	void drawMenuState(Graphics graphics) {
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 0, Snake.WIDTH, Snake.HEIGHT);
		graphics.setColor(Color.RED);
		graphics.setFont(titleFont);
		graphics.drawString("Caduceus", 600, 100);
		graphics.drawString("press enter to play", 520, 200);
		graphics.drawString("press spacebar for instructions",400,300);
		graphics.drawImage(cad,600,350,250,400,null);
	}

	void drawGameState(Graphics graphics) {
		objectmanager.draw(graphics);
	}

	void drawEndState(Graphics graphics) {
		graphics.setColor(Color.RED);
		graphics.fillRect(0, 0, Snake.WIDTH, Snake.HEIGHT);
		graphics.setColor(Color.BLACK);
		graphics.setFont(titleFont);
		graphics.drawString(winner + " WINS", 600, 300);

	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		repaint();

		if (currentState == MENU_STATE) {

			updateMenuState();

		} else if (currentState == GAME_STATE) {

			updateGameState();

		} else if (currentState == END_STATE) {

			updateEndState();

		}
	}

	void startGame() {
		timer.start();
	}

	@Override

	public void paintComponent(Graphics graphics) {

		if (currentState == MENU_STATE) {

			drawMenuState(graphics);

		} else if (currentState == GAME_STATE) {

			drawGameState(graphics);

		} else if (currentState == END_STATE) {

			drawEndState(graphics);

		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyChar() == KeyEvent.VK_ENTER) {

			currentState++;

			if (currentState > END_STATE) {

				currentState = MENU_STATE;

			}

		}

		if (e.getKeyChar() == KeyEvent.VK_SPACE) {
			String menu = "";
			JFrame frame = new JFrame();

			frame.setVisible(true);
			frame.setDefaultCloseOperation(frame.getDefaultCloseOperation());
			JPanel panel = new JPanel();	
			frame.add(panel);
			JTextArea text = new JTextArea(); 
			JTextArea text2 = new JTextArea(); 
			JTextArea text3 = new JTextArea(); 
			text.setText("blue team controls snake with WASD keys ");
			text2.setText("red team controls snake with arrow keys");
			text3.setText("first one to run out of time loses");
			panel.add(text);
			panel.add(text2);
			panel.add(text3);
			frame.pack();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			if (!objectmanager.getdirection().equals("down")) {
				objectmanager.setdirection("up");
			}
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			if (!objectmanager.getdirection().equals("up")) {
				objectmanager.setdirection("down");
			}

		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			if (!objectmanager.getdirection().equals("right")) {
				objectmanager.setdirection("left");
			}

		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (!objectmanager.getdirection().equals("left")) {
				objectmanager.setdirection("right");
			}

		}
		if (e.getKeyCode() == KeyEvent.VK_W) {
			if (!objectmanager.getdirection2().equals("down")) {
				objectmanager.setdirection2("up");
			}
		} else if (e.getKeyCode() == KeyEvent.VK_S) {
			if (!objectmanager.getdirection2().equals("up")) {
				objectmanager.setdirection2("down");
			}

		} else if (e.getKeyCode() == KeyEvent.VK_A) {
			if (!objectmanager.getdirection2().equals("right")) {
				objectmanager.setdirection2("left");
			}

		} else if (e.getKeyCode() == KeyEvent.VK_D) {
			if (!objectmanager.getdirection2().equals("left")) {
				objectmanager.setdirection2("right");
			}

		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
