import java.awt.Graphics;
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
	
	public void paint(Graphics g) {
		
		super.paintComponent(g);
		//g.fillOval(30, 30, 50, 50);
		
		//call each Enemy to paint themselves
		for(Enemy e: enemies) {
			e.paint(g);
		}
		
		isColliding();
	}

	public Driver() {
		JFrame frame = new JFrame("Agar.io");
		frame.setSize(800, 600);
		frame.add(this);
		
		/* add 50  Enemies */
		for(int i =0 ; i < 50; i++) {
			enemies.add( new Enemy() );
		}	
		
		Timer t = new Timer(16, this);
		t.start();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
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
	
	public void isColliding() {
		int size = enemies.size();
		
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (i == j) {
					continue;
				}
				
				if ((int)distance(i, j) <= enemies.get(i).getRad() + enemies.get(j).getRad()) {
					if (enemies.get(i).getRad() >= enemies.get(j).getRad()) {
						enemies.remove(j);
						size--;
					} else {
						enemies.remove(i);
						size--;
					}
				}
			}
		}
	}
	
	public double distance(int i, int j) {
		return Math.sqrt(Math.pow((double)(enemies.get(i).getX()+enemies.get(i).getRad() - enemies.get(j).getX())+enemies.get(j).getRad(), 2.0) + 
							Math.pow((double)(enemies.get(i).getY()+enemies.get(i).getRad() - enemies.get(j).getY())+enemies.get(j).getRad(), 2.0));
		
	}
}
