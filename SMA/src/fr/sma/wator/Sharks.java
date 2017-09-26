package fr.sma.wator;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fr.sma.core.Agent;
import fr.sma.core.Environment;

public class Sharks extends Agent {
	
	private int breedTime;
	private int starveTime;
	
	public Sharks(Environment environment, int posX, int posY, int pasX, int pasY) {
		super(environment, posX, posY, pasX, pasY);
	}

	@Override
	public void decide() {
		Agent[][] neighbours = {
				{environment.getAgent(posX - 1, posY - 1), environment.getAgent(posX, posY - 1), environment.getAgent(posX + 1, posY - 1)},
				{environment.getAgent(posX - 1, 0), environment.getAgent(posX, posY), environment.getAgent(posX + 1, posY)},
				{environment.getAgent(posX - 1, posY + 1), environment.getAgent(posX, posY + 1), environment.getAgent(posX + 1, posY + 1)}
		};
		Random rand = new Random();		
		List<Point> fishs = new ArrayList<Point>();
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if(neighbours[i][j] != null && neighbours[i][j] instanceof Fishs) {
					Point p = new Point(this.posX + i - 1, this.posY + j - 1);
					fishs.add(p);
				}
			}
		}
		
		if(!fishs.isEmpty()) {
			Point p = fishs.get(rand.nextInt(fishs.size()));
			this.eatFish(p);
		} else {
			pasX = rand.nextInt(3) - 1;
			pasY = rand.nextInt(3) - 1;
			if(pasX == 0) {
				pasY = rand.nextBoolean() ? -1 : 1;
			}
			if(this.environment.getAgent(this.posX + pasX, this.posY + pasY) == null) {
				this.breedTime--;
				if(this.breedTime == 0) {
					this.bornToBeAlive();
				}
				move();
			}
		}
	}
	
	private void move() {
		this.environment.removeAgent(this.posX, this.posY);
		this.posX += pasX;
		this.posY += pasY;
		this.environment.addAgent(this, this.posX, this.posY);
	}
	
	private void eatFish(Point p) {
		this.pasX = (int) (p.getX() - this.posX);
		this.pasY = (int) (p.getY() - this.posY);
		this.environment.removeAgent((int)p.getX(), (int)p.getY());
		this.move();
	}
	
	private void bornToBeAlive() {
		this.environment.addAgent(new Fishs(this.environment, this.posX, this.posY), this.posX, this.posY);
	}

}
