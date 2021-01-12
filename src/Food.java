import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Food {
	
	private int x, y;
	private int rad;
	private Color color;
	Rectangle word = new Rectangle(-500, -500, 2000, 2000);
	
	public Food() {

		//random radius
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
		g.setColor(color);
		g.fillOval(x, y, rad, rad);
		
		/* have the enemy object bounce off
		 * using helper methods
		 */
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
