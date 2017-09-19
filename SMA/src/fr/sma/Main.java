package fr.sma;

import java.util.Random;

public class Main {
	
	public static void main(String[] argv) {
		Random rand = new Random();
		for(int i = 0; i < 20; i++)
			System.out.println(rand.nextInt(3));
	}
}
