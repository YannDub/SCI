package fr.sma.pacman;

import java.awt.Color;

import fr.sma.core.Agent;
import fr.sma.core.Environment;

public class Hunter extends Agent {

	public Hunter(Environment environment, int posX, int posY, int pasX, int pasY) {
		super(environment, posX, posY, pasX, pasY);
		this.color = Color.red;
	}

	@Override
	public void decide() {
		
	}

}
