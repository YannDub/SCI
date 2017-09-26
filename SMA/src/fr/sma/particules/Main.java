package fr.sma.particules;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fr.sma.core.Agent;
import fr.sma.core.Environment;
import fr.sma.core.SMA;
import fr.sma.core.Vue;
import fr.sma.core.utils.Properties;
import fr.sma.wator.Fishs;
import fr.sma.wator.Sharks;

public class Main {
	
	private static int gridSizeX = Integer.parseInt(Properties.getProperty("gridSizeX"));
	private static int gridSizeY = Integer.parseInt(Properties.getProperty("gridSizeY"));
	private static int nbSharks = Integer.parseInt(Properties.getProperty("nbSharks"));
	private static int nbFishes = Integer.parseInt(Properties.getProperty("nbFishes"));
	
	public static void main(String[] argv) {
		Random rand = new Random();
		Environment e = new Environment(gridSizeX, gridSizeY);
		List<Agent> agents = new ArrayList<Agent>();
		
		for (int i = 0; i < nbSharks; i++) {
			int posX, posY;
			do {
				posX = rand.nextInt(gridSizeX);
				posY = rand.nextInt(gridSizeY);
			} while (e.getAgent(posX, posY) != null);
			Sharks p = new Sharks(e, posX, posY);
			agents.add(p);
			e.addAgent(p, posX, posY);
		}
		
		for (int i = 0; i < nbFishes; i++) {
			int posX, posY;
			do {
				posX = rand.nextInt(gridSizeX);
				posY = rand.nextInt(gridSizeY);
			} while (e.getAgent(posX, posY) != null);
			Fishs p = new Fishs(e, posX, posY);
			agents.add(p);
			e.addAgent(p, posX, posY);
		}
		
		SMA sma = new SMA(agents);
		Vue vue = new Vue();
		sma.addObserver(vue);
		sma.run();
	}
}
