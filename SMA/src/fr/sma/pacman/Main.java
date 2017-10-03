package fr.sma.pacman;

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
		
		for(int i = 0; i < gridSizeX; i++) {
			for(int j = 0; j < gridSizeY; j++) {
				Wall wall = new Wall(e, i, j);
				e.addAgent(wall, i, j);
				agents.add(wall);
			}
		}
		
		float nbWall = gridSizeX * gridSizeY;
		while(nbWall / (gridSizeX * gridSizeY) * 100 > Integer.parseInt(Properties.getProperty("WallsPercent"))) {			
			int x = rand.nextInt(gridSizeX);
			int y = rand.nextInt(gridSizeY);
			if(e.getAgent(x, y) instanceof Wall) {
				e.addAgent(null, x, y);					
				agents.remove(e.getAgent(x, y));
				nbWall--;
			}
		}
		
		
		
		int x = rand.nextInt(gridSizeX);
		int y = rand.nextInt(gridSizeY);
		Avatar a = new Avatar(e, x, y, 0, 0);
		agents.add(a);
		e.addAgent(a, x, y);
		
		Vue vue = new Vue();
		vue.getPanel().addKeyListener(a);
		sma.addObserver(vue);
		sma.run();
	}
}
