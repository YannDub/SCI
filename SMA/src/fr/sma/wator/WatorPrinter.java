package fr.sma.wator;

import java.util.List;

import fr.sma.core.Agent;
import fr.sma.core.SMAPrinter;

public class WatorPrinter extends SMAPrinter {
	
	public int nbSharks = 0;
	public int nbFishs = 0;
	
	@Override
	public void print(List<Agent> agents) {
		for(Agent a : agents) {
			if(a instanceof Fishs)
				nbFishs++;
			else
				nbSharks++;
		}
		System.out.println("Tick;" + nbFishs + ";" + nbSharks + ";" + nbFishs/nbSharks);
		nbFishs = 0;
		nbSharks = 0;
	}

}
