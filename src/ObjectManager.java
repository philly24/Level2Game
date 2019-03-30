
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager {
	Segment head;
	int blueScore = 0;
	int redScore = 0;
	ArrayList<Segment> tailArray = new ArrayList<Segment>();
	String direction = "down";
	int lives = 14;
	String direction2 = "down2";
	int foodW = 40;
	int foodL = 40;
	Random rand = new Random();
	int xLocation = rand.nextInt(1960);
	int yLocation = rand.nextInt(860) + 100;
	Random rand2 = new Random();
	int xLocation2 = rand2.nextInt(1960);
	int yLocation2 = rand2.nextInt(860) + 100;
	int scoreP1 = 0;
	int scoreP2 = 0;
	int headWidth = 30;
	int headLenght = 30;

	ObjectManager() {
		head = new Segment(50, 50, headWidth, headLenght);

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
	}

	void draw(Graphics graphics) {
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 0, Snake.WIDTH, Snake.HEIGHT);
		head.draw(graphics);
		drawTail(graphics);
		drawFood(graphics);
		drawFood2(graphics);
		scoreBoard(graphics);
	}

	void scoreBoard(Graphics g) {
		g.setColor(Color.GRAY);
		g.drawRect(0, 0, 2000, 100);
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
		// Draw a 10 by 10 rectangle for each Segment in your snake ArrayList.
		for (Segment ta : tailArray) {
			g.drawRect(ta.getX(), ta.getY(), 30, 30);
		}

	}

	void drawFood(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(xLocation, yLocation, foodL, foodW);
	}

	void drawFood2(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(xLocation2, yLocation2, foodL, foodW);
	}

	void manageTail() {

		tailArray.add(new Segment(head.getX(), head.getY(), 30, 30));

		while (tailArray.size() > lives) {
			tailArray.remove(0);
		}

	}

	void purgeObjects() {
		/*
		 * for (int i = 0; i < aliens.size(); i++) { if (aliens.get(i).isAlive==false) {
		 * aliens.remove(aliens.get(i)); score++; }
		 */
	}

	void checkCollision() {

		if (head.x < xLocation + foodW && head.x > xLocation - headWidth && head.y < yLocation + foodL
				&& head.y > yLocation - headLenght) {
			redScore++;
			xLocation = rand.nextInt(1960);
			yLocation = rand.nextInt(860) + 100;

		}

		if (head.x < xLocation2 + foodW && head.x > xLocation2 - headWidth && head.y < yLocation2 + foodL
				&& head.y > yLocation2 - headLenght) {
			blueScore++;
			xLocation2 = rand2.nextInt(1960);
			yLocation2 = rand2.nextInt(860) + 100;

		}

	}

	public int getScoreBlue() {
		return blueScore;

	}

	public int getScoreRed() {
		return redScore;
	}

}
