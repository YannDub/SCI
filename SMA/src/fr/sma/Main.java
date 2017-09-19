package fr.sma;

public class Main {
	
	public static void main(String[] argv) {
		SMA sma = new SMA(10);
		Vue vue = new Vue();
		sma.addObserver(vue);
		sma.run();
	}
}
