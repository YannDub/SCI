package fr.sma.pacman;

import fr.sma.core.Environment;
import fr.sma.core.utils.Properties;

public class Dijkstra {
	
	public static int[][] path;
	private static int gridSizeX = Integer.parseInt(Properties.getProperty("gridSizeX"));
	private static int gridSizeY = Integer.parseInt(Properties.getProperty("gridSizeY"));
	
	public static void compute(Environment e, int startX, int startY, int startValue) {
		path = new int[gridSizeX][gridSizeY];
		if(startX >= 0 && startY >= 0 && startX < gridSizeX && startY < gridSizeY && !(e.getAgent(startX, startY) instanceof Wall)) {
			path[startX][startY] = startValue;
			compute(e, startX - 1, startY - 1, startValue + 1);
			compute(e, startX, startY - 1, startValue + 1);
			compute(e, startX + 1, startY - 1, startValue + 1);
			compute(e, startX - 1, startY, startValue + 1);
			compute(e, startX + 1, startY, startValue + 1);
			compute(e, startX - 1, startY + 1, startValue + 1);
			compute(e, startX, startY + 1, startValue + 1);
			compute(e, startX + 1, startY + 1, startValue + 1);
		}
	}
}
