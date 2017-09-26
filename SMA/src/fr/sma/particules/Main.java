package fr.sma.particules;

import fr.sma.core.Vue;

public class Main {
	
	public static void main(String[] argv) {
		SMA sma = new SMA(Particle.class);
		Vue vue = new Vue();
		sma.addObserver(vue);
		sma.run();
	}
}
