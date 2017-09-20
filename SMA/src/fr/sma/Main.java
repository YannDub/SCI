package fr.sma;

import java.util.Timer;
import java.util.TimerTask;

import fr.sma.utils.Properties;

public class Main {
	
	public static void main(String[] argv) {
		SMA sma = new SMA();
		Vue vue = new Vue();
		sma.addObserver(vue);
		sma.run();
	}
}
