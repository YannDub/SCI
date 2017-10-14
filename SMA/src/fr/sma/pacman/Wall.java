package fr.sma.pacman;

import java.awt.Color;

import fr.sma.core.Agent;
import fr.sma.core.Environment;

public class Wall extends Agent {
	
	public Wall(Environment environment, int posX, int posY) {
		super(environment, posX, posY, 0, 0);
		this.color = Color.black;
	}

	@Override
	public void decide(int tick) {
		
	}

}
