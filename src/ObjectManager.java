
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import java.applet.Applet;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.awt.*;

public class ObjectManager {
	Segment head;
	int blueScore = 100;
	int redScore = 100;
	ArrayList<Segment> tailArray = new ArrayList<Segment>();
	String direction = "down";
	int lives = 14;
	String direction2 = "up";
	int foodW = 50;
	int foodL = 50;
	Random rand = new Random();
	int xLocation = rand.nextInt(1200);
	int yLocation = rand.nextInt(610) + 100;
	Random rand2 = new Random();
	int xLocation2 = rand2.nextInt(1200);
	int yLocation2 = rand2.nextInt(610) + 100;
	int headWidth = 30;
	int headLenght = 30;
	int frameCount = 0;
	BufferedImage whiteUp;
	BufferedImage whiteDown;
	BufferedImage whiteRight;
	BufferedImage whiteLeft;
	BufferedImage blueUp;
	BufferedImage blueDown;
	BufferedImage blueRight;
	BufferedImage blueLeft;
	BufferedImage redUp;
	BufferedImage redDown;
	BufferedImage redRight;
	BufferedImage redLeft;
	BufferedImage cad;
	BufferedImage apple;
	BufferedImage apple2;
	BufferedImage trophy;
	static BufferedImage Head;
	int widthArrow = 30;
	int heightArrow = 30;
	int yUp = 1;
	int yDown = 68;
	int yRightLeft = 33;
	Font Font;

	ObjectManager() {
		head = new Segment(710, 385, headWidth, headLenght);
		Font = new Font("Arial", Font.PLAIN, 30);
		String path = Paths.get("").toAbsolutePath().toString();
		try {
			Head = ImageIO.read(new File(path+ "/src/snake head.png"));
			apple = ImageIO.read(new File(path+ "/src/apple.png"));
			apple2 = ImageIO.read(new File(path+ "/src/apple 2.png"));
			cad = ImageIO.read(new File(path+ "/src/Caduceus.png"));
			trophy = ImageIO.read(new File(path+ "/src/trophy.png"));
			whiteUp = ImageIO.read(new File(path + "/src/whiteUp.png"));
			whiteDown = ImageIO.read(new File(path + "/src/white down.png"));
			whiteRight = ImageIO.read(new File(path + "/src/white right.png"));
			whiteLeft = ImageIO.read(new File(path + "/src/white left.png"));
			redUp = ImageIO.read(new File(path + "/src/red Up.png"));
			redDown = ImageIO.read(new File(path + "/src/red down.png"));
			redRight = ImageIO.read(new File(path + "/src/red right.png"));
			redLeft = ImageIO.read(new File(path + "/src/red left.png"));
			blueUp = ImageIO.read(new File(path + "/src/blue up.png"));
			blueDown = ImageIO.read(new File(path + "/src/blue down.png"));
			blueRight = ImageIO.read(new File(path + "/src/blue right.png"));
			blueLeft = ImageIO.read(new File(path + "/src/blue left.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}

	public void setdirection(String value) {
		direction = value;

	}

	public void setdirection2(String value2) {
		direction2 = value2;

	}

	public String getdirection() {
		return this.direction;
	}

	public String getdirection2() {
		return this.direction2;
	}

	void update() {
		move();
		head.update();
		manageTail();
		checkCollision();
		frameCount++;
		if (frameCount % 40 == 0) {
			blueScore--;
			redScore--;
		}
		endGame();
	}

	void draw(Graphics graphics) {
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 0, Snake.WIDTH, Snake.HEIGHT);
		head.draw(graphics);
		drawTail(graphics);
		drawFood(graphics);
		drawFood2(graphics);
		scoreBoard(graphics);
		arrows(graphics);
	}

	void arrows(Graphics g) {

		if (direction2.equals("up")) {
			g.drawImage(blueUp, 607, yUp, widthArrow, heightArrow, null);
			g.drawImage(whiteDown, 607, yDown, widthArrow, heightArrow, null);
			g.drawImage(whiteRight, 640, yRightLeft, widthArrow, heightArrow, null);
			g.drawImage(whiteLeft, 574, yRightLeft, widthArrow, heightArrow, null);
		} else if (direction2.equals("down")) {
			g.drawImage(whiteUp, 607, yUp, widthArrow, heightArrow, null);
			g.drawImage(blueDown, 607, yDown, widthArrow, heightArrow, null);
			g.drawImage(whiteRight, 640, yRightLeft, widthArrow, heightArrow, null);
			g.drawImage(whiteLeft, 574, yRightLeft, widthArrow, heightArrow, null);
		} else if (direction2.equals("right")) {
			g.drawImage(whiteUp, 607, yUp, widthArrow, heightArrow, null);
			g.drawImage(whiteDown, 607, yDown, widthArrow, heightArrow, null);
			g.drawImage(blueRight, 640, yRightLeft, widthArrow, heightArrow, null);
			g.drawImage(whiteLeft, 574, yRightLeft, widthArrow, heightArrow, null);
		} else if (direction2.equals("left")) {
			g.drawImage(whiteUp, 607, yUp, widthArrow, heightArrow, null);
			g.drawImage(whiteDown, 607, yDown, widthArrow, heightArrow, null);
			g.drawImage(whiteRight, 640, yRightLeft, widthArrow, heightArrow, null);
			g.drawImage(blueLeft, 574, yRightLeft, widthArrow, heightArrow, null);
		}
		if (direction.equals("up")) {
			g.drawImage(redUp, 813, yUp, widthArrow, heightArrow, null);
			g.drawImage(whiteDown, 813, yDown, widthArrow, heightArrow, null);
			g.drawImage(whiteRight, 846, yRightLeft, widthArrow, heightArrow, null);
			g.drawImage(whiteLeft, 780, yRightLeft, widthArrow, heightArrow, null);
		} else if (direction.equals("down")) {
			g.drawImage(whiteUp, 813, yUp, widthArrow, heightArrow, null);
			g.drawImage(redDown, 813, yDown, widthArrow, heightArrow, null);
			g.drawImage(whiteRight, 846, yRightLeft, widthArrow, heightArrow, null);
			g.drawImage(whiteLeft, 780, yRightLeft, widthArrow, heightArrow, null);
		} else if (direction.equals("right")) {
			g.drawImage(whiteUp, 813, yUp, widthArrow, heightArrow, null);
			g.drawImage(whiteDown, 813, yDown, widthArrow, heightArrow, null);
			g.drawImage(redRight, 846, yRightLeft, widthArrow, heightArrow, null);
			g.drawImage(whiteLeft, 780, yRightLeft, widthArrow, heightArrow, null);
		} else if (direction.equals("left")) {
			g.drawImage(whiteUp, 813, yUp, widthArrow, heightArrow, null);
			g.drawImage(whiteDown, 813, yDown, widthArrow, heightArrow, null);
			g.drawImage(whiteRight, 846, yRightLeft, widthArrow, heightArrow, null);
			g.drawImage(redLeft, 780, yRightLeft, widthArrow, heightArrow, null);
		}
	}

	void scoreBoard(Graphics g) {
		g.setFont(Font);
		g.setColor(Color.BLUE);
		String blue = "BLUE SCORE " + blueScore;
		g.drawString(blue, 315, 50);
		g.setColor(Color.GRAY);
		g.drawRect(0, 0, 1450, 100);
		g.drawRect(0, 0, 725, 100);
		g.setColor(Color.RED);
		String red =  redScore + " RED SCORE";
		g.drawString(red, 906, 50);
	}

	void move() {
		int vel = 20;
		int xVel = 0;
		int yVel = 0;
		switch (direction) {
		case "up":
			yVel -= vel;
			break;
		case "down":
			yVel += vel;
			break;
		case "left":
			xVel -= vel;
			break;
		case "right":
			xVel += vel;
			break;
		}
		switch (direction2) {
		case "up":
			yVel -= vel;
			break;
		case "down":
			yVel += vel;
			break;
		case "left":
			xVel -= vel;
			break;
		case "right":
			xVel += vel;
			break;
		}
		head.setX(head.getX() + xVel);
		head.setY(head.getY() + yVel);
	}

	void drawTail(Graphics g) {
		
		for (Segment ta : tailArray) {
			g.drawRect(ta.getX(), ta.getY(), 30, 30);
		}

	}

	void drawFood(Graphics g) {
		g.drawImage(apple, xLocation, yLocation, foodL, foodW, null);
	}

	void drawFood2(Graphics g) {
		g.drawImage(apple2, xLocation2, yLocation2, foodL, foodW, null);
	}

	void manageTail() {

		tailArray.add(new Segment(head.getX(), head.getY(), 30, 30));

		while (tailArray.size() > lives) {
			tailArray.remove(0);
		}

	}

	void checkCollision() {

		if (head.x < xLocation + foodW && head.x > xLocation - headWidth && head.y < yLocation + foodL
				&& head.y > yLocation - headLenght) {
			redScore += 2;
			xLocation = rand.nextInt(1100);
			yLocation = rand.nextInt(610) + 100;

		}

		if (head.x < xLocation2 + foodW && head.x > xLocation2 - headWidth && head.y < yLocation2 + foodL
				&& head.y > yLocation2 - headLenght) {
			blueScore += 2;
			xLocation2 = rand2.nextInt(1200);
			yLocation2 = rand2.nextInt(610) + 100;

		}

	}

	void endGame() {
		if (redScore <= 0 && blueScore > 0) {
			GamePanel.currentState = GamePanel.END_STATE;
			GamePanel.loser = 3;
			GamePanel.winner = "BLUE";
		}
		if (blueScore <= 0 && redScore > 0) {
			GamePanel.currentState = GamePanel.END_STATE;
			GamePanel.loser = 3;
			GamePanel.winner = "RED";
		}
		if (blueScore <= 0 && redScore <= 0) {
			blueScore += 10;
			redScore += 10;
		}
	}

	public int getScoreBlue() {
		return blueScore;

	}

	public int getScoreRed() {
		return redScore;
	}

}
