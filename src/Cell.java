import java.awt.Color;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Rectangle;

public class Cell {
	private int x, y;
	private double v;
	private int rad;
	private Color color;
	Rectangle word = new Rectangle(-500, -500, 2000, 2000);
	
	public Cell() {
		rad = 20;
		v = 100/rad + 1;
		x = 400;
		y = 300;
		
		//generate random color
		int r = (int)(Math.random()*256);
		int g = (int)(Math.random()*256);
		int b = (int)(Math.random()*256);
		color = new Color(r, g, b);
		
	}
	
	public void paint(Graphics g) {
		g.setColor(color);
		g.fillOval(x - rad/2, y - rad/2, rad, rad);
		//g.fillOval(x, y, rad, rad);
		
		/* have the enemy object bounce off
		 * using helper methods
		 */
		//collideWorld();
	}
	
	public void collideWorld() {
		if (x >= word.getMaxX() - rad) {
			x = (int) (word.getMaxX() - rad);
		}
		if (x <= word.getMinX()) {
			x = (int) word.getMinX();
		}
		if (y >= word.getMaxY() - rad) {
			y = (int) (word.getMaxY() - rad);
		}
		if (x <= word.getMinY()) {
			y = (int) word.getMinY();
		}
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public double getV() {
		return v;
	}
	
	public int getRad() {
		return rad;
	}
	
	public void setRad(int paramRad) {
		rad = paramRad;
	}
	
	public int getCenterX() {
		return x;
	}
	
	public int getCenterY() {
		return y;
	}
	
	public void addRad(int paramRad) {
		rad += paramRad;
	}
}
