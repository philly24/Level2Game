
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager {
	Segment head;
	ArrayList<Segment> tailArray = new ArrayList<Segment>();
	String direction = "down";
	int lives = 7;

	ObjectManager() {
		head = new Segment(50, 50, 10, 10);
	}

	public void setdirection(String value) {
		direction = value;

	}

	public String getdirection() {
		return this.direction;
	}

	void update() {
		move();
		head.update();

	}

	void draw(Graphics graphics) {
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 0, Snake.WIDTH, Snake.HEIGHT);
		head.draw(graphics);
		
	}

	void move() {
		switch (direction) {
		case "up":
			head.setY(head.getY() - 10);
			break;
		case "down":
			head.setY(head.getY() + 10);

			break;
		case "left":
			head.setX(head.getX() - 10);

			break;
		case "right":
			head.setX(head.getX() + 10);

			break;
		}
	}

	void drawTail(Graphics g) {
		// Draw a 10 by 10 rectangle for each Segment in your snake ArrayList.
		for (Segment ta : tailArray) {
			g.drawRect(ta.getX(), ta.getY(), 10, 10);
		}

	}

	void manageTail() {

		tailArray.add(new Segment(head.getX(), head.getY(), 10, 10));

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
		/*
		 * for(Aliens a: this.aliens){
		 * 
		 * if(rocket.collisionBox.intersects(a.collisionBox)){
		 * 
		 * rocket.isAlive = false; } for (Projectile p : projectile) { if
		 * (a.collisionBox.intersects(p.collisionBox)) { a.isAlive = false; p.isAlive =
		 * false; } } }
		 */

	}
	// public int getscore() {
	// return score;
	// }

}
