package fr.sma.pacman;

import java.awt.Color;

import fr.sma.core.Agent;
import fr.sma.core.Environment;

public class Defender extends Agent {

	public Defender(Environment environment, int posX, int posY) {
		super(environment, posX, posY, 0, 0);
		this.color = Color.blue;
	}

	@Override
	public void decide() {
		if(environment.getAgent(this.posX, this.posY) instanceof Avatar) {
			Avatar a = (Avatar) environment.getAgent(this.posX, this.posY);
			a.setDefend();
			environment.removeAgent(this.posX, this.posY);
		}
	}

}
