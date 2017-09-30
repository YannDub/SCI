package fr.sma.particules;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fr.sma.core.Agent;
import fr.sma.core.Environment;
import fr.sma.core.SMA;
import fr.sma.core.Vue;
import fr.sma.core.utils.Properties;

public class Main {
	
	private static int gridSizeX = Integer.parseInt(Properties.getProperty("gridSizeX"));
	private static int gridSizeY = Integer.parseInt(Properties.getProperty("gridSizeY"));
	
	public static void main(String[] argv) {
		Random rand = new Random();
		Environment e = new Environment(gridSizeX, gridSizeY);
		List<Agent> agents = new ArrayList<Agent>();
		SMA sma = new SMA(agents, e);
		e.setSMA(sma);
		
		for (int i = 0; i < Integer.parseInt(Properties.getProperty("nbParticules")); i++) {
			int posX, posY;
			do {
				posX = rand.nextInt(gridSizeX);
				posY = rand.nextInt(gridSizeY);
			} while (e.getAgent(posX, posY) != null);
			int pasX = rand.nextInt(3) - 1;
			int pasY = rand.nextInt(3) - 1;
			Agent p = new Particle(e, posX, posY, pasX, pasY);
			agents.add(p);
			e.addAgent(p, posX, posY);
		}
		
		Vue vue = new Vue();
		sma.addObserver(vue);
		sma.run();
	}
}
