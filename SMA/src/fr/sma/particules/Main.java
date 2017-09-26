package fr.sma.particules;

public class Main {
	
	public static void main(String[] argv) {
		SMA sma = new SMA(Particle.class);
		Vue vue = new Vue();
		sma.addObserver(vue);
		sma.run();
	}
}
