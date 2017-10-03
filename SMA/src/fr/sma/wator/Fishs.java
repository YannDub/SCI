package fr.sma.wator;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fr.sma.core.Agent;
import fr.sma.core.Environment;
import fr.sma.core.utils.Properties;

public class Fishs extends Agent {
	
	private int breedTime;
	
	public Fishs(Environment environment, int posX, int posY) {
		super(environment, posX, posY, 0, 0);
		this.breedTime = Integer.parseInt(Properties.getProperty("FishBreedTime"));
		this.color = Color.blue;
	}

	@Override
	public void decide() {
		Random rand = new Random();
		List<Point> water = new ArrayList<Point>();
		
		if(this.color == Color.green)
			this.color = Color.blue;
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				try {
					Agent neighbour = this.environment.getAgent(this.posX + i - 1, this.posY + j - 1);
					Point p = new Point(this.posX + i - 1, this.posY + j - 1);
					if(neighbour == null) {
						water.add(p);
					}
				} catch(ArrayIndexOutOfBoundsException e) {
					
				}
			}
		}
		
		if(!water.isEmpty()) {
			Point p = water.get(rand.nextInt(water.size()));
			this.pasX = (int) (p.getX() - this.posX);
			this.pasY = (int) (p.getY() - this.posY);			
			int currentX = this.posX;
			int currentY = this.posY;
			move();
			this.breedTime--;
			if(this.breedTime <= 0) {
				this.bornToBeAlive(currentX, currentY);
			}
		}
	}
	
	private void move() {
		this.environment.removeAgent(this.posX, this.posY);
		this.posX += pasX;
		this.posY += pasY;
		this.environment.addAgent(this, this.posX, this.posY);
	}
	
	private void bornToBeAlive(int x, int y) {
		Fishs fish = new Fishs(this.environment, x, y);
		fish.color = Color.green;
		this.environment.addAgent(fish, x, y);
		this.breedTime = Integer.parseInt(Properties.getProperty("FishBreedTime"));
		if(Boolean.parseBoolean(Properties.getProperty("trace")))
			System.out.println("Fish; Born");
	}

}
