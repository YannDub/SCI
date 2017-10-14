package fr.sma.pacman;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fr.sma.core.Agent;
import fr.sma.core.Environment;
import fr.sma.core.utils.Properties;

public class Avatar extends Agent implements KeyListener {

	private int lastKeyPressed;
	private int defendTime;
	private int gridSizeX = Integer.parseInt(Properties.getProperty("gridSizeX"));
	private int gridSizeY = Integer.parseInt(Properties.getProperty("gridSizeY"));
	
	public Avatar(Environment environment, int posX, int posY, int pasX, int pasY) {
		super(environment, posX, posY, pasX, pasY);
		this.color = Color.green;
		Dijkstra.init();
		Dijkstra.path[posX][posY] = 0;
		Dijkstra.compute(environment, posX, posY);
		for(int j = 0; j < Dijkstra.path[0].length; j++) {
			for(int i = 0; i < Dijkstra.path.length; i++) {
				System.out.print(Dijkstra.path[i][j] + " ");
			}
			System.out.println();
		}
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
					if(neighbour == null || neighbour instanceof Defender)
						points.add(p);
				} catch(ArrayIndexOutOfBoundsException e) {
					
				}
			}
		}
		
		if(!points.isEmpty()) {
			Point p = new Point(this.posX + this.pasX, this.posY + this.pasY);
			if(points.contains(p)) {
				try {					
					if(environment.getAgent(p.x, p.y) instanceof Defender)
						this.setDefend();
				} catch (ArrayIndexOutOfBoundsException ex) {}
				this.move();
			}
		}
		
		if(isDefended()) {
			this.defendTime--;
			Dijkstra.invert();
		}
		
		Random rand = new Random();
		if(rand.nextFloat() <= 0.01) {
			int x = rand.nextInt(gridSizeX);
			int y = rand.nextInt(gridSizeY);
			while(environment.getAgent(x, y) != null) {
				x = rand.nextInt(gridSizeX);
				y = rand.nextInt(gridSizeY);
			}
			environment.addAgent(new Defender(environment, x, y), x, y);
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
		Dijkstra.init();
		Dijkstra.compute(environment, this.posX, this.posY);
	}
	
	public boolean isDefended() {
		return this.defendTime > 0;
	}
	
	public void setDefend() {
		this.defendTime = Integer.parseInt(Properties.getProperty("DefendLive"));
	}

}
