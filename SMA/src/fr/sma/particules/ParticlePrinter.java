package fr.sma.particules;

import java.util.List;

import fr.sma.core.Agent;
import fr.sma.core.SMAPrinter;

public class ParticlePrinter extends SMAPrinter{

	public static int nbCollisions = 0;
	
	@Override
	public void print(List<Agent> agents) {
		System.out.println("Tick; " + nbCollisions);
		nbCollisions = 0;
	}

}
