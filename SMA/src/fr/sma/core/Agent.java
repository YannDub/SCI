package fr.sma.core;

public abstract class Agent {
	protected int posX, posY;
	protected int pasX, pasY;
	protected Environment environment;
	
	public Agent(Environment environment, int posX, int posY, int pasX, int pasY) {
		this.environment = environment;
		this.posX = posX;
		this.posY = posY;
		this.pasX = pasX;
		this.pasY = pasY;
	}
	
	public void update() {
		
	}
	
	public abstract void decide();
}
