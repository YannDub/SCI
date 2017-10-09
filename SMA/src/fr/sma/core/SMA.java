package fr.sma.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import fr.sma.core.utils.Properties;
import fr.sma.wator.Fishs;

public class SMA extends Observable {

	protected List<Agent> agents = new ArrayList<Agent>();
	protected List<Agent> removedAgent;

	protected String scheduling = Properties.getProperty("scheduling");
	protected int tick = 0;
	public static int nbCollisions = 0;
	private SMAPrinter printer;

	protected Environment e;

	public SMA(List<Agent> agents, Environment e) {
		this.agents = agents;
		this.removedAgent = new ArrayList<Agent>();
		this.e = e;
	}
	
	public int getTick() {
		return this.tick;
	}
	
	public Environment getEnvironment() {
		return this.e;
	}
	
	public void addSMAPrinter(SMAPrinter printer) {
		this.printer = printer;
	}
	
	private void printAgents() {
		printer.print(agents);
	}

	public void run() {
		int delay = Integer.parseInt(Properties.getProperty("delay"));
		final int nbTicks = Integer.parseInt(Properties.getProperty("nbTicks"));
		
		TimerTask task = new TimerTask() {
			private int ticks = nbTicks;
			@Override
			public void run() {
				Random rand = new Random();
				removedAgent = new ArrayList<Agent>();
				if (ticks == 0 || ticks != 1) {
					if(!Boolean.parseBoolean(Properties.getProperty("trace")) && printer != null)
						printAgents();
					if(scheduling == "equitable")
						Collections.shuffle(agents);
					for (Agent a : agents) {
						if(scheduling == "aleat") {
							Agent ag = agents.get(rand.nextInt(agents.size()));
							if(!removedAgent.contains(ag)) ag.decide();
						} else 
							if(!removedAgent.contains(a)) a.decide();
					}
					tick = ticks;
					if (ticks > 1)
						ticks--;
					setChanged();
					notifyObservers();
					
					int width = Integer.parseInt(Properties.getProperty("gridSizeX"));
					int height = Integer.parseInt(Properties.getProperty("gridSizeY"));
					agents = new ArrayList<Agent>();
					for(int i = 0; i < width; i++) {
						for(int j = 0; j < height; j++) {
							if(e.getAgent(i, j) != null)
								agents.add(e.getAgent(i, j));
						}
					}
				}
			}
		};
		new Timer().scheduleAtFixedRate(task, delay, delay);
	}
}
