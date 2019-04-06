import java.awt.Color;
import java.awt.Graphics;

public class Segment extends GameObject {
	Segment(int x, int y, int width, int height) {
		super(x, y, width, height);

		// TODO Auto-generated constructor stub
	}

	int speed;
	// TODO Auto-generated constructor stub

	void update() {
		super.update();
		checkBoundaries();
	}

	void checkBoundaries() {

		if (x > Snake.WIDTH) {

			x = 0;
		}
		if (x < 0) {
		
			x = Snake.WIDTH;
		}
		if (y >= Snake.HEIGHT) {
			
			y = 100;
		}
		if (y < 100) {
			
			y = Snake.HEIGHT-height;
		}
	}

	void draw(Graphics graphics) {
		graphics.setColor(Color.WHITE);
		graphics.fillRect(x, y, width, height);
	}

	int getX() {
		return x;
	}

	int getY() {
		return y;
	}

	void setX(int newX) {
		x = newX;
	}

	void setY(int newY) {
		y = newY;
	}

}
