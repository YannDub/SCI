package fr.sma.pacman;

import java.awt.Canvas;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fr.sma.core.Agent;
import fr.sma.core.Environment;
import fr.sma.core.SMA;
import fr.sma.core.Vue;
import fr.sma.core.utils.Properties;

public class Main extends Canvas {
	
	private static int gridSizeX = Integer.parseInt(Properties.getProperty("gridSizeX"));
	private static int gridSizeY = Integer.parseInt(Properties.getProperty("gridSizeY"));
	
	public Main() {
		Random rand = new Random();
		Environment e = new Environment(gridSizeX, gridSizeY);
		List<Agent> agents = new ArrayList<Agent>();
		SMA sma = new SMA(agents, e);
		e.setSMA(sma);
		
		int x = rand.nextInt(gridSizeX);
		int y = rand.nextInt(gridSizeY);
		Avatar a = new Avatar(e, x, y, 0, 0);
		agents.add(a);
		e.addAgent(a, x, y);
		
		Vue vue = new Vue();
		sma.addObserver(vue);
		sma.run();
	}
	
	public static void main(String[] argv) {
		Main main = new Main();
	}
}
