package fr.sma.wator;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fr.sma.core.Agent;
import fr.sma.core.Environment;
import fr.sma.core.utils.Properties;

public class Sharks extends Agent {
	
	private int breedTime;
	private int starveTime;
	private boolean trace;
	
	public Sharks(Environment environment, int posX, int posY) {
		super(environment, posX, posY, 0, 0);
		Random rand = new Random();
		this.breedTime = 1 + rand.nextInt(Integer.parseInt(Properties.getProperty("SharkBreedTime")));
		this.starveTime = Integer.parseInt(Properties.getProperty("SharkStarveTime"));
		this.trace = Boolean.parseBoolean(Properties.getProperty("trace"));
		this.color = Color.red;
	}
	
	public Sharks(Environment environment, int posX, int posY, Color color) {
		this(environment, posX, posY);
		this.color = color;
	}

	@Override
	public void decide(int tick) {
		Random rand = new Random();		
		List<Point> fishs = new ArrayList<Point>();
		List<Point> water = new ArrayList<Point>();
		
		if(this.color == Color.pink)
			this.color = Color.red;
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				try {
					Agent neighbour = this.environment.getAgent(this.posX + i - 1, this.posY + j - 1);
					Point p = new Point(this.posX + i - 1, this.posY + j - 1);
					if(neighbour != null && neighbour instanceof Fishs) {
						fishs.add(p);
					} else if(neighbour == null) {
						water.add(p);
					}
				} catch(ArrayIndexOutOfBoundsException e) {
					
				}
			}
		}
		int currentX = this.posX;
		int currentY = this.posY;
		
		if(!fishs.isEmpty()) {
			Point p = fishs.get(rand.nextInt(fishs.size()));
			this.eatFish(p);
			this.breedTime--;
			if(this.breedTime <= 0) {
				this.bornToBeAlive(currentX, currentY);
			}
		} else if(!water.isEmpty()){
			Point p = water.get(rand.nextInt(water.size()));
			this.pasX = (int) (p.getX() - this.posX);
			this.pasY = (int) (p.getY() - this.posY);
			if(this.starveTime == 0) {
				this.die();
			} else {
				move();
				this.breedTime--;
				if(this.breedTime <= 0) {
					this.bornToBeAlive(currentX, currentY);
				}
			}
			this.starveTime--;
		} else {
			if(this.starveTime == 0) {
				this.die();
			}
			this.starveTime--;
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
		this.environment.removeAgent(this.posX + this.pasX, this.posY + this.pasY);
		this.move();
		this.starveTime = Integer.parseInt(Properties.getProperty("SharkStarveTime"));
		if(this.trace) System.out.println("Fish; Eat");
	}
	
	private void bornToBeAlive(int x, int y) {
		Sharks shark = new Sharks(this.environment, x, y);
		shark.color = Color.pink;
		this.environment.addAgent(shark, x, y);
		this.breedTime = Integer.parseInt(Properties.getProperty("SharkBreedTime"));
		if(this.trace) System.out.println("Shark; Born");
	}
	
	private void die() {
		this.environment.removeAgent(this.posX, this.posY);
		if(this.trace) System.out.println("Shark; Die");
	}

}
