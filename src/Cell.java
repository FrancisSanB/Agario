import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Cell {
	
	private int x, y;
	private double vx, vy;
	private int rad;
	private double k;
	private Color color;
	private int vmag, theta;
	Rectangle word = new Rectangle(0, 0, 1000, 1000);
	
	public Cell() {
		rad = 20;
		vx = 0;
		vy = 0;
		x = 400;
		y = 300;
		
		//generate random color
		int r = (int)(Math.random()*256);
		int g = (int)(Math.random()*256);
		int b = (int)(Math.random()*256);
		color = new Color(r, g, b);
		
	}
	
	public void paint(Graphics g) {
		update();
		g.setColor(color);
		g.fillOval(x, y, rad, rad);
		
		/* have the enemy object bounce off
		 * using helper methods
		 */
		collideWorld();
	}
	
	public void update() {
		x += vx;
		y += vy;
	}
	
	public void collideWorld() {
		if (x >= word.getMaxX() - rad) {
			vx *= -1;
		}
		if (x <= word.getMinX()) {
			vx *= -1;
		}
		if (y >= word.getMaxY() - rad) {
			vy *= -1;
		}
		if (x <= word.getMinY()) {
			vy *= -1;
		}
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getRad() {
		return rad;
	}
}
