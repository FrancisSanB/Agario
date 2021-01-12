import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Enemy {
	
	private int x, y;
	private double vx, vy;
	private int rad;
	private double k;
	private Color color;
	private int vmag, theta;
	Rectangle word = new Rectangle(-500, -500, 2000, 2000);
	//Rectangle word = new Rectangle(200, 200, 750, 750);
	
	public Enemy() {

		//random radius
		rad = (int)(Math.random()*(60-15+1)+1);
		
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
		g.setColor(Color.black);
		g.drawRect(word.x, word.x, word.width, word.height);
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
		if (y <= word.getMinY()) {
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
