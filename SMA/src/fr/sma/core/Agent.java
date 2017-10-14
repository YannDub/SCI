package fr.sma.core;

import java.awt.Color;

public abstract class Agent {
	protected int posX, posY;
	protected int pasX, pasY;
	protected Environment environment;
	protected Color color;
	
	public Agent(Environment environment, int posX, int posY, int pasX, int pasY) {
		this.environment = environment;
		this.posX = posX;
		this.posY = posY;
		this.pasX = pasX;
		this.pasY = pasY;
	}
	
	public void update() {
		
	}
	
	public abstract void decide(int tick);
	
	public Color getColor() {
		return this.color;
	}
}
