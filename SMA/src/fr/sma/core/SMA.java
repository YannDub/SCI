package fr.sma.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import fr.sma.core.utils.Properties;

public class SMA extends Observable {

	protected List<Agent> agents = new ArrayList<Agent>();

	protected String scheduling = Properties.getProperty("scheduling");
	protected int tick = 0;
	public static int nbCollisions = 0;

	protected Environment e;

	public SMA(List<Agent> agents) {
		this.agents = agents;
	}
	
	public int getTick() {
		return this.tick;
	}
	
	public Environment getEnvironment() {
		return this.e;
	}

	public void run() {
		int delay = Integer.parseInt(Properties.getProperty("delay"));
		final int nbTicks = Integer.parseInt(Properties.getProperty("nbTicks"));
		
		TimerTask task = new TimerTask() {
			private int ticks = nbTicks;
			@Override
			public void run() {
				Random rand = new Random();
				if (ticks == 0 || ticks != 1) {
					SMA.nbCollisions = 0;
					if(scheduling == "equitable")
						Collections.shuffle(agents);
					for (Agent a : agents) {
						if(scheduling == "aleat") {
							Agent ag = agents.get(rand.nextInt(agents.size()));
							ag.decide();
						} else 
							a.decide();
					}
					tick = ticks;
					if (ticks > 1)
						ticks--;
					setChanged();
					notifyObservers();
					if(Boolean.parseBoolean(Properties.getProperty("trace")))
						System.out.println("Tick;"+SMA.nbCollisions);
				}
			}
		};
		new Timer().scheduleAtFixedRate(task, delay, delay);
	}
}
