import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Food {
	
	private int x, y;
	private int rad;
	private double vx, vy;
	private Color color;
	Rectangle word = new Rectangle(-500, -500, 2000, 2000);
	
	public Food() {
		//radius
		rad = 10;
		
		//spawn the food randomly anywhere on 800x600 screen
		x = (int)(Math.random()*word.getMaxX() - rad + 1);
		y = (int)(Math.random()*word.getMaxY() - rad + 1);
		
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
		double distance = Math.sqrt( (double)(Math.pow(disX, 2)) + (double)(Math.pow(disY, 2)) );

		//return if the distance is smaller than total radius
		return distance < totalRad;
	}
	
	public boolean isColliding(Cell e) {
		//get the x distance and y distance between enemies and total radius
		int disX = Math.abs(e.getCenterX() - x);
		int disY = Math.abs(e.getCenterY() - y);
		int totalRad = e.getRad() + rad;
		
		//calculate distance
		double distance = Math.sqrt( (double)(Math.pow(disX, 2)) + (double)(Math.pow(disY, 2)) );

		//return if the distance is smaller than total radius
		return distance < totalRad;
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
