package fr.sma.wator;

import java.util.Random;

import fr.sma.core.Agent;
import fr.sma.core.Environment;
import fr.sma.core.utils.Properties;

public class Fishs extends Agent {
	
	private int breedTime;
	
	public Fishs(Environment environment, int posX, int posY) {
		super(environment, posX, posY, 0, 0);
		this.breedTime = Integer.parseInt(Properties.getProperty("FishBreedTime"));
	}

	@Override
	public void decide() {
		
		Random rand = new Random();
		pasX = rand.nextInt(3) - 1;
		pasY = rand.nextInt(3) - 1;
		if(pasX == 0) {
			pasY = rand.nextBoolean() ? -1 : 1;
		}
		if(this.environment.getAgent(this.posX + pasX, this.posY + pasY) == null) {
			this.breedTime--;
			if(this.breedTime <= 0) {
				this.bornToBeAlive();
			}
			move();
		}
	}
	
	private void move() {
		this.environment.removeAgent(this.posX, this.posY);
		this.posX += pasX;
		this.posY += pasY;
		this.environment.addAgent(this, this.posX, this.posY);
	}
	
	private void bornToBeAlive() {
		this.environment.addAgent(new Fishs(this.environment, this.posX, this.posY), this.posX, this.posY);
		this.breedTime = Integer.parseInt(Properties.getProperty("FishBreedTime"));
	}

}
