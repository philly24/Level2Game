
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	Timer timer;
	GameObject gameobject;

	Font titleFont;
	Segment segment;
	final int MENU_STATE = 0;

	final int GAME_STATE = 1;

	final int END_STATE = 2;
	int currentState = MENU_STATE;
	ObjectManager objectmanager;

	GamePanel(ObjectManager object) {
		timer = new Timer(1000 / 60, this);
		titleFont = new Font("Arial", Font.PLAIN, 48);
		segment = new Segment(0, 0, 10, 10);
		objectmanager = object;
	}

	void updateMenuState() {
		// tells the code when in menu state
	}

	void updateGameState() {
		// checks for collisions and updates it
		objectmanager.update();

		objectmanager.checkCollision();
		objectmanager.purgeObjects();
		if (segment.isAlive == false) {
			currentState = END_STATE;
		}
	}

	void updateEndState() {
		// ends the game and restarts it

	}

	void drawMenuState(Graphics graphics) {
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 0, WIDTH, HEIGHT);
		graphics.setColor(Color.YELLOW);
		graphics.setFont(titleFont);
		graphics.drawString("Battle Snake ", 80, 100);
		graphics.drawString("press enter to play", 60, 400);

	}

	void drawGameState(Graphics graphics) {
		objectmanager.draw(graphics);
	}

	void drawEndState(Graphics graphics) {
		graphics.setColor(Color.RED);
		graphics.fillRect(0, 0, WIDTH, HEIGHT);
		graphics.setFont(titleFont);
		graphics.setColor(Color.BLACK);
		graphics.drawString("Player # wins", 80, 80);

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

			if (currentState > END_STATE) {

				currentState = MENU_STATE;

			} else if (currentState == END_STATE) {

			} else {
				currentState++;
			}

		}

		if (e.getKeyChar() == KeyEvent.VK_SPACE) {

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

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
