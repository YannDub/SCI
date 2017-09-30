package fr.sma.wator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fr.sma.core.Agent;
import fr.sma.core.Environment;
import fr.sma.core.SMA;
import fr.sma.core.Vue;
import fr.sma.core.utils.Properties;
import fr.sma.particules.Particle;

public class Main {
	
	private static int gridSizeX = Integer.parseInt(Properties.getProperty("gridSizeX"));
	private static int gridSizeY = Integer.parseInt(Properties.getProperty("gridSizeY"));
	
	public static void main(String[] argv) {
		Random rand = new Random();
		Environment e = new Environment(gridSizeX, gridSizeY);
		List<Agent> agents = new ArrayList<Agent>();
		
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
		
		SMA sma = new SMA(agents, e);
		Vue vue = new Vue();
		sma.addObserver(vue);
		sma.run();
	}
}
