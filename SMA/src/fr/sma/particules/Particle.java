package fr.sma.particules;

import java.awt.Color;

import fr.sma.core.Agent;
import fr.sma.core.Environment;
import fr.sma.core.SMA;
import fr.sma.core.utils.Properties;

public class Particle extends Agent {

	protected Color color;
	
	public Particle(Environment environment, int posX, int posY, int pasX, int pasY) {
		super(environment, posX, posY, pasX, pasY);
		this.color = Color.gray;
	}
	
	public void decide(int tick) {		
		try {
			Particle p = (Particle) this.environment.getAgent(this.posX + this.pasX, this.posY + this.pasY);
			if(p != null) this.bounceParticle(p);
			else this.step();
		} catch(ArrayIndexOutOfBoundsException e) {
			this.bounceBorder();
		}
	}
	
	private void step() {
		environment.removeAgent(this.posX, this.posY);
		this.posX += this.pasX;
		this.posY += this.pasY;
		environment.addAgent(this, this.posX, this.posY);
	}
	
	private void bounceParticle(Particle agent) {
		int currentPasX = this.pasX;
		int currentPasY = this.pasY;
		this.pasX = agent.pasX;
		this.pasY = agent.pasY;
		agent.pasX = currentPasX;
		agent.pasY = currentPasY;
		this.turnRed();
		agent.turnRed();
		ParticlePrinter.nbCollisions++;
	}
	
	private void bounceBorder() {
		int gridSizeX = Integer.parseInt(Properties.getProperty("gridSizeX"));
		int gridSizeY = Integer.parseInt(Properties.getProperty("gridSizeY"));
		if(this.posX + this.pasX >= gridSizeX || this.posX + this.pasX < 0)
			this.pasX = -this.pasX;
		else if (this.posY + this.pasY >= gridSizeY || this.posY + this.pasY < 0) 
			this.pasY = -this.pasY;
		else {
			this.pasX = -this.pasX;
			this.pasY = -this.pasY;
		}
		this.turnRed();
	}
	
	private void turnRed() {
		this.color = Color.red;
	}
	
	public Color getColor() {
		return this.color;
	}
}
