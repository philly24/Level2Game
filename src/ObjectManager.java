
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager {
	Segment head;
	Segment head2;
	ArrayList<Segment> tailArray = new ArrayList<Segment>();
	String direction = "down";
	int lives = 14;
	String direction2= "down2";
	ObjectManager() {
		head = new Segment(50, 50, 30, 30);
		head2 = new Segment(50,50,30,30);
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
		move2();
		head.update();
		head2.update();
		manageTail();
	
	}

	void draw(Graphics graphics) {
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 0, Snake.WIDTH, Snake.HEIGHT);
		head.draw(graphics);
		drawTail(graphics);
	}

	void move() {
		switch (direction) {
		case "up":
			head.setY(head.getY() - 30);
			break;
		case "down":
			head.setY(head.getY() + 30);

			break;
		case "left":
			head.setX(head.getX() - 30);

			break;
		case "right":
			head.setX(head.getX() + 30);

			break;
		}
	}
	void move2() {
		switch (direction2) {
		case "up":
			head.setY(head.getY() - 30);
			break;
		case "down":
			head.setY(head.getY() + 30);

			break;
		case "left":
			head.setX(head.getX() - 30);

			break;
		case "right":
			head.setX(head.getX() + 30);

			break;
		}
	}

	void drawTail(Graphics g) {
		// Draw a 10 by 10 rectangle for each Segment in your snake ArrayList.
		for (Segment ta : tailArray) {
			g.drawRect(ta.getX(), ta.getY(), 30, 30);
		}

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
