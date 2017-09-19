package fr.sma;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Random;

import fr.sma.agents.Agent;
import fr.sma.agents.Particle;
import fr.sma.utils.Properties;

public class SMA extends Observable {
	
	protected List<Agent> agents = new ArrayList<Agent>();

	protected int gridSizeX = Integer.parseInt(Properties.getProperty("gridSizeX"));
	protected int gridSizeY = Integer.parseInt(Properties.getProperty("gridSizeY"));

	protected Environment e;
	
	public SMA() {
		Random rand = new Random();
		this.e = new Environment(this.gridSizeX, this.gridSizeY);
		for (int i = 0; i < Integer.parseInt(Properties.getProperty("nbParticules")); i++) {
			int posX, posY;
			do {
				posX = rand.nextInt(this.gridSizeX);
				posY = rand.nextInt(this.gridSizeY);
			} while (e.getAgent(posX, posY) != null);
			int pasX = rand.nextInt(3) - 1;
			int pasY = rand.nextInt(3) - 1;
			Particle p = new Particle(e, posX, posY, pasX, pasY);
			this.agents.add(p);
			e.addAgent(p, posX, posY);
		}
	}
	
	public void run() {
		int nbTicks = Integer.parseInt(Properties.getProperty("nbTicks")); 
		do {
			Collections.shuffle(agents);
			for(Agent a : this.agents){
				a.decide();
			}
			if(nbTicks > 1) nbTicks--;
			this.setChanged();
			this.notifyObservers();
		} while(nbTicks == 0 || nbTicks != 1); 
	}
}
