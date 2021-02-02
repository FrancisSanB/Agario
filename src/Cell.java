import java.awt.Color;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Rectangle;

public class Cell {
	private int x, y;
	private double v;
	private int rad;
	private Color color;
	
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
		update();
		
		g.setColor(color);
		g.fillOval(x - rad, y - rad, rad*2, rad*2);
		g.drawRect(x - rad, y - rad, rad*2, rad*2);
	}
	
	public void update() {
		if (x > 400) {
			x = 400;
		}
		if (x < 400) {
			x = 400;
		}
		if (y > 300) {
			y = 300;
		}
		if (y < 300) {
			y = 300;
		}
		if (rad > 250) {
			rad = 250;
		}
	}

	public int iscollideWorldX(int xmin, int xmax) {
		if (x + rad > xmax) {
			return 1;
		}
		if (x - rad < xmin) {
			return 2;
		}
		return 0;
	}
	
	public int iscollideWorldY(int ymin, int ymax) {
		if (y + rad > ymax) {
			return 1;
		}
		if (y - rad < ymin) {
			return 2;
		}
		return 0;
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

	public void addRad(int paramRad) {
		rad += paramRad;
	}
	
	public int getCenterX() {
		return x;
	}
	
	public int getCenterY() {
		return y;
	}
}
