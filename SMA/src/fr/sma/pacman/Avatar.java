package fr.sma.pacman;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import fr.sma.core.Agent;
import fr.sma.core.Environment;

public class Avatar extends Agent implements KeyListener {

	private int lastKeyPressed;
	
	public Avatar(Environment environment, int posX, int posY, int pasX, int pasY) {
		super(environment, posX, posY, pasX, pasY);
		this.color = Color.green;
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		this.lastKeyPressed = e.getKeyCode();
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void decide() {
		switch(this.lastKeyPressed) {
		case KeyEvent.VK_RIGHT:
			this.setPas(1, 0);
			break;
		case KeyEvent.VK_LEFT:
			this.setPas(-1, 0);
			break;
		case KeyEvent.VK_UP:
			this.setPas(0, -1);
			break;
		case KeyEvent.VK_DOWN:
			this.setPas(0, 1);
			break;
		default:
			this.setPas(0, 0);
		}
		
		List<Point> points = new ArrayList<Point>();
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				try {					
					Agent neighbour = this.environment.getAgent(this.posX + i - 1, this.posY + j - 1);
					Point p = new Point(this.posX + i - 1, this.posY + j - 1);
					if(neighbour == null)
						points.add(p);
				} catch(ArrayIndexOutOfBoundsException e) {
					
				}
			}
		}
		
		if(!points.isEmpty()) {
			Point p = new Point(this.posX + this.pasX, this.posY + this.pasY);
			if(points.contains(p))
				this.move();
		}
	}
	
	private void setPas(int x, int y) {
		this.pasX = x;
		this.pasY = y;
	}
	
	private void move() {
		this.environment.removeAgent(this.posX, this.posY);
		this.posX += pasX;
		this.posY += pasY;
		this.environment.addAgent(this, this.posX, this.posY);
	}

}
