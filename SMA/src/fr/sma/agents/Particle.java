package fr.sma.agents;

import java.awt.Color;

import fr.sma.Environment;

public class Particle extends Agent {

	protected Color color = Color.gray;
	
	public Particle(Environment environment, int posX, int posY, int pasX, int pasY) {
		super(environment, posX, posY, pasX, pasY);
	}
	
	public void decide() {		
		try {
			Particle p = (Particle) this.environment.getAgent(this.posX + this.pasX, this.posY + this.posY);
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
	}
	
	private void bounceBorder() {
		this.pasX = -this.pasX;
		this.pasY = -this.pasY;
		this.turnRed();
	}
	
	private void turnRed() {
		this.color = Color.red;
	}
	
	public Color getColor() {
		return this.color;
	}
}
