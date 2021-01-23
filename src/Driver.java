import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Driver extends JPanel implements MouseListener, ActionListener {
	
	//Create ArrayList for enemies
	ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	ArrayList<Food> foods = new ArrayList<Food>();
	Cell p = new Cell();
	Font verdana = new Font("Verdana", Font.BOLD, 30);
	
	public double vx, vy;
	public int x = 0;
	public int y = 0;
	
	public void paint(Graphics g) {
		super.paintComponent(g);
		
		//set vx and vy to get things ready to move
		move();
		
		//word border
		update();
		g.drawRect(x, y, 2000, 2000);
		g.setColor(Color.red);
		g.drawLine(400, 0, 400, 600);
		g.drawLine(0, 300, 800, 300);
		
		//call each object to paint themselves
		for (Food f: foods) {
			f.paint(g);
			f.setVx(vx);
			f.setVy(vy);
			
			if (f.isColliding(p) ) {
				System.out.println("player hit food");
				p.addRad(1);
				foods.remove(f);
			}
			
		}
		
		for (Enemy e: enemies) {
			e.paint(g);
			e.addVx(vx);
			e.addVy(vy);
			
			//enemy-player collision
			/*if (e.isColliding(p)) {
				System.out.print("enemy hit player");
				e.addRad(1);
				p.setRad(20);
				

				//which is larger
				if (e.getRad() > p.getRad()) {
					p.setRad(20);
					e.addRad(p.getRad());
				} else {
					enemies.remove(e);
					p.addRad(e.getRad());
				}
			}*/
			
			//enemy-food collision
			/*for (Food f: foods) {
				if (f.isColliding(e)) {
					System.out.println("enemy hit food");
					e.addRad(1);
					foods.remove(f);
				}
			}*/
			
			/*//enemy-enemy collision
			for (Enemy f: enemies) {
				if (f.isColliding(e)) {
					System.out.println("enemy hit enemy");
					e.addRad(1);
					foods.remove(f);
					
					//which enemy is larger
					if (e.getRad() > f.getRad()) {
						enemies.remove(e);
						f.addRad(1);
					} else {
						enemies.remove(f);
						e.addRad(1);
					}
				}
			}*/
			
		}
		p.paint(g);
		
		//paint words
		g.setColor(Color.black);
		g.setFont(verdana);
		g.drawString("Mass: " + p.getRad(), 0, 550);
		g.drawString("Players left: " + enemies.size(), 0, 30);
		
	}

	public Driver() {
		JFrame frame = new JFrame("Agar.io");
		frame.setSize(800, 600);
		frame.add(this);
		
		/* add 50  Enemies */
		for (int i = 0; i < 25; i++) {
			enemies.add(new Enemy());
		}
		
		for (int i = 0; i < 250; i++) {
			foods.add(new Food());
		}
		p = new Cell();
		
		Timer t = new Timer(16, this);
		t.start();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public void removeEnemy(Enemy e) {
		enemies.remove(e);
	}
	
	public void update() {
		x += vx;
		y += vy;
	}
	
	public void move() {
		double mouseX = MouseInfo.getPointerInfo().getLocation().getX();
		double mouseY = MouseInfo.getPointerInfo().getLocation().getY();
		double px = p.getX();
		double py = p.getY();
		double distanceX = mouseX - px;
		double distanceY = mouseY - py;
		double theta;
		
		if (distanceY == 0) {
			theta = 0;
		} else {
			theta = Math.atan(distanceX / distanceY);
		}
		
		vx = Math.sin(theta)*p.getV();
		vy = Math.cos(theta)*p.getV();
		
		if (distanceY > 0) {
			vx *= -1;
			vy *= -1;
		}
	}
	
	public static void main(String[] arg) {
		Driver d = new Driver();
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("here");
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		repaint();
	}
}
