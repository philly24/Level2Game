import java.awt.Graphics;
import java.awt.Rectangle;

public class GameObject {
	int x;
	int y;
	int width;
	int height;
	boolean isAlive = true;
	Graphics graphics;
	Rectangle collisionBox;

	GameObject(int x, int y, int width, int height) {
		collisionBox = new Rectangle();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	void update() {
		collisionBox.setBounds(x, y, width, height);
	}

	void draw(Graphics graphics) {

	}
}
