package fr.sma.pacman;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import fr.sma.core.Agent;
import fr.sma.core.Environment;

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
					int value = path[this.posX + i - 1][this.posY + j - 1];
					if(value < minValue && value != -1) {
						p = new Point(this.posX + i - 1, this.posY + j - 1);
						minValue = value;
					}
				} catch(ArrayIndexOutOfBoundsException e) {
					
				}
			}
		}
		
		this.pasX = (int) (p.getX() - this.posX);
		this.pasY = (int) (p.getY() - this.posY);
		move();
	}
	
	private void move() {
		this.environment.removeAgent(this.posX, this.posY);
		this.posX += pasX;
		this.posY += pasY;
		this.environment.addAgent(this, this.posX, this.posY);
	}

}
