import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Food {
	
	private int x, y;
	private int rad;
	private double vx, vy;
	private Color color;
	Rectangle word = new Rectangle(0, 0, 5000, 5000);
	
	public Food() {
		//radius
		rad = 5;
		
		//spawn the food randomly anywhere on 800x600 screen
		x = (int)(Math.random()*(word.getMaxX() - rad - word.getMinX() + 1) + word.getMinX());
		y = (int)(Math.random()*(word.getMaxX() - rad- word.getMinX() + 1) + word.getMinX());
		
		//generate random color
		int r = (int)(Math.random()*256);
		int g = (int)(Math.random()*256);
		int b = (int)(Math.random()*256);
		color = new Color(r, g, b);
		
	}
	
	public void paint(Graphics g) {
		update();
		g.setColor(color);
		g.fillOval(x, y, rad*2, rad*2);
		
	}
	
	public void update() {
		x += vx;
		y += vy;
	}
	
	public boolean isColliding(Enemy e) {
		//get the x distance and y distance between enemies and total radius
		int disX = Math.abs(e.getCenterX() - x);
		int disY = Math.abs(e.getCenterY() - y);
		int totalRad = e.getRad() + rad;
		
		//calculate distance
		double distance = Math.sqrt(Math.pow(disX, 2) + Math.pow(disY, 2));

		//return if the distance is smaller than total radius
		return distance < totalRad;
	}
	
	public boolean isColliding(Cell e) {
		//get the x distance and y distance between enemies and total radius
		int disX = Math.abs(e.getCenterX() - x);
		int disY = Math.abs(e.getCenterY() - y);
		int totalRad = e.getRad() + rad;
		
		//calculate distance
		double distance = Math.sqrt(Math.pow(disX, 2) + Math.pow(disY, 2));

		//return if the distance is smaller than total radius
		return distance < totalRad;
	}
	
	public void collideWorld(int xmin, int ymin, int xmax, int ymax) {
		if (x >= xmax - rad) {
			x = xmax - rad;
		}
		if (x <= xmin) {
			x = xmin;
		}
		if (y >= ymax - rad) {
			y = ymax - rad;
		}
		if (y <= ymin) {
			y = ymin;
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
	
	public void setVx(double paramVx) {
		vx = paramVx;
	}
	
	public void setVy(double paramVy) {
		vy = paramVy;
	}
}
