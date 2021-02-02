import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Enemy {
	private int x, y;
	private double vx, vy;
	private double addVx, addVy;
	private int rad;
	private Color color;
	private int vmag, theta;
	Rectangle word = new Rectangle(0, 0, 2000, 2000);
	
	public Enemy() {

		//random radius
		rad = (int) ((Math.random()*(30 - 20))+20);
		
		vmag = (int)(7-rad/10);
		theta = (int)(Math.random()*(360-0+1)+0);
		
		//set vx, vy to non-zero value between -3, 3
		vx = (int)(vmag*(Math.cos(theta)));
		vy = (int)(vmag*(Math.sin(theta)));
		
		//spawn the enemy randomly anywhere on 800x600 screen
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
		
		//g.drawLine(getCenterX(), 0, getCenterX(), 600);
		//g.drawLine(0, getCenterY(), 800, getCenterY());
	}
	
	public void update() {
		x += vx + addVx;
		y += vy + addVy;
		
		if (rad > 250) {
			rad = 250;
		}
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
	
	public void collideWorld(int xmin, int ymin, int xmax, int ymax) {
		if (x >= xmax - rad) {
			vx *= -1;
			x = xmax - rad;
		}
		if (x <= xmin) {
			vx *= -1;
			x = xmin;
		}
		if (y >= ymax - rad) {
			vy *= -1;
			y = ymax - rad;
		}
		if (y <= ymin) {
			vy *= -1;
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
	
	public int getCenterX() {
		return x + rad;
	}
	
	public int getCenterY() {
		return y + rad;
	}
	
	public void addVx(double paramVx) {
		addVx = paramVx;
	}
	
	public void addVy(double paramVy) {
		addVy = paramVy;
	}

	public void addRad(int paramRad) {
		rad += paramRad;
	}
}
