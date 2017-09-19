package fr.sma;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import fr.sma.agents.Agent;
import fr.sma.agents.Particle;

public class SMA {
	
	protected List<Agent> agents = new ArrayList<Agent>();
	protected int gridSizeX = 100;
	protected int gridSizeY = 100;
	
	public SMA(int nbAgent) {
		Random rand = new Random();
		Environment e = new Environment(this.gridSizeX, this.gridSizeY);
		for (int i = 0; i < nbAgent; i++) {
			int posX, posY;
			do {
				posX = rand.nextInt(this.gridSizeX);
				posY = rand.nextInt(this.gridSizeY);
			} while (e.getAgent(posX, posY) != null);
			Particle p = new Particle(e, posX, posY, rand.nextInt(3) - 1, rand.nextInt(3) - 1);
			this.agents.add(p);
			e.addAgent(p, posX, posY);
		}
	}
	
	public void run() {
		while(true) {
			Collections.shuffle(agents);
			for(Agent a : this.agents){
				a.decide();
			}
		}
	}	
}
