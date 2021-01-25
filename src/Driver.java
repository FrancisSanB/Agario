import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Rectangle;
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
		updateBorder(g);
		
		//call each object to paint themselves
		for (Food f: foods) {
			f.paint(g);
			f.setVx(vx);
			f.setVy(vy);
			
		}
		
		for (Enemy e: enemies) {
			e.paint(g);
			e.addVx(vx);
			e.addVy(vy);
		}
		
		p.paint(g);
		
		//collision
		collision();
		collisionBorder();
		
		//paint words
		g.setColor(Color.black);
		g.setFont(verdana);
		g.drawString("Mass: " + p.getRad(), 0, 550);
		g.drawString("Enemies left: " + enemies.size(), 0, 30);
		g.drawString("Food left: " + foods.size(), 0, 60);
		
	}

	public Driver() {
		JFrame frame = new JFrame("Agar.io");
		frame.setSize(800, 600);
		frame.add(this);
		
		/* add 50  Enemies */
		for (int i = 0; i < 25; i++) {
			enemies.add(new Enemy());
		}
		
		for (int i = 0; i < 500; i++) {
			foods.add(new Food());
		}
		
		p = new Cell();
		
		Timer t = new Timer(16, this);
		t.start();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public void collision() {
		for (Food f: foods) {
			//player-food collision
			if (f.isColliding(p) ) {
				System.out.println("player hit food");
				p.addRad(1);
				foods.remove(f);
				break;
			}
		}
		
		for (Enemy e: enemies) {
			//enemy-food collision
			for (Food f: foods) {
				if (f.isColliding(e)) {
					System.out.println("enemy hit food");
					e.addRad(1);
					foods.remove(f);
					break;
				}
			}
			
			//enemy-player collision
			if (e.isColliding(p)) {
				System.out.println("enemy hit player");
				
				//which is larger
				if (e.getRad() > p.getRad()) {
					p.setRad(20);
					//e.addRad(p.getRad());
				} else {
					enemies.remove(e);
					p.addRad(e.getRad());
				}
			}
			/*
			//enemy-enemy collision
			for (Enemy e2: enemies) {
				if (e == e2) {
					break;
				}
				
				if (e2.isColliding(e)) {
					System.out.println("enemy hit enemy");
					
					//which enemy is larger
					if (e.getRad() < e2.getRad()) {
						e2.addRad(e.getRad());
						enemies.remove(e);
					} else {
						e.addRad(e2.getRad());
						enemies.remove(e2);
					}
				}
			}*/
			
		}
	}
	
	//collision with the world
	public void collisionBorder() {
		//p.collideWorld(x, y, x + 2000, y + 2000);
		
		for (Enemy e: enemies) {
			e.collideWorld(x, y, x + 2000, y + 2000);
		}
		
		for (Food f: foods) {
			f.collideWorld(x, y, x + 2000, y + 2000);
		}
	}
	
	//update global x and y to render border
	public void updateBorder(Graphics g) {
		x += vx;
		y += vy;
		
		g.drawRect(x, y, 2000, 2000);
		g.setColor(Color.red);
		g.drawLine(400, 0, 400, 600);
		g.drawLine(0, 300, 800, 300);
	}
	
	//set vx and vy to get things ready to move
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
		
		//border collision
		if (p.iscollideWorldX(x, x + 2000) == 1 && distanceX > 0) {
			vx = 0;
		} else if (p.iscollideWorldX(x, x + 2000) == 2 && distanceX < 0) {
			vx = 0;
		} else {
			vx = Math.sin(theta)*p.getV();
		}
		
		if (p.iscollideWorldY(y, y + 2000) == 1 && distanceY > 0) {
			vy = 0;
		} else if (p.iscollideWorldY(y, y + 2000) == 2 && distanceY < 0) {
			vy = 0;
		} else {
			vy = Math.cos(theta)*p.getV();
		}
		
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
