package fr.sma.pacman;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import fr.sma.core.Agent;
import fr.sma.core.Environment;
import fr.sma.core.utils.Properties;

public class Hunter extends Agent {

	public Hunter(Environment environment, int posX, int posY, int pasX, int pasY) {
		super(environment, posX, posY, pasX, pasY);
		this.color = Color.red;
	}

	@Override
	public void decide() {
		int[][] path = Dijkstra.path;
		Point p = new Point(posX, posY);
		
		int minValue = path[posX][posY];
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				try {
					int x = this.posX + i - 1;
					int y = this.posY + j - 1;
					if(Boolean.parseBoolean(Properties.getProperty("torus"))) {
						x = x % path.length;
						y = y % path[0].length;
					}
					int value = path[x][y];
					if(value < minValue && value != -1 && path[x][y] != -1 && (environment.getAgent(x, y) == null || environment.getAgent(x, y) instanceof Avatar)) {
						p = new Point(x, y);
						minValue = value;
					}
				} catch(ArrayIndexOutOfBoundsException e) {
					
				}
			}
		}
		
		this.pasX = (int) (p.getX() - this.posX);
		this.pasY = (int) (p.getY() - this.posY);
		
		if(environment.getAgent(posX + pasX, posY + pasY) instanceof Avatar) {
			JOptionPane.showMessageDialog(null, "You're Die");
			System.exit(0);
		} else
			move();
	}
	
	private void move() {
		this.environment.removeAgent(this.posX, this.posY);
		this.posX += pasX;
		this.posY += pasY;
		this.environment.addAgent(this, this.posX, this.posY);
	}

}
