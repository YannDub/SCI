package fr.sma.pacman;

import fr.sma.core.Environment;
import fr.sma.core.utils.Properties;

public class Dijkstra {

	public static int[][] path;
	private static int gridSizeX = Integer.parseInt(Properties.getProperty("gridSizeX"));
	private static int gridSizeY = Integer.parseInt(Properties.getProperty("gridSizeY"));

	public static void init() {
		path = new int[gridSizeX][gridSizeY];
		for (int i = 0; i < gridSizeX; i++) {
			for (int j = 0; j < gridSizeY; j++) {
				path[i][j] = -1;
			}
		}
	}

	public static void compute(Environment e, int startX, int startY) {
		for(int i = 0; i < gridSizeX; i++) {
			for(int j = 0; j < gridSizeY; j++) {
//				int minX = Math.abs(startX - i);
//				int minY = Math.abs(startY - j);
//				if(Boolean.parseBoolean(Properties.getProperty("torus"))) {
//					minX = Math.min(minX, Math.abs(gridSizeX + startX - i));
//					minY = Math.min(minY, Math.abs(gridSizeY + startY - j));
//				}
//				if(!(e.getAgent(i, j) instanceof Wall)) {					
//					if(minX == minY) path[i][j] = Math.min(minX, minY);
//					else path[i][j] = Math.max(minX, minY);
//				}
				int posX = Math.abs(startX - i);
				int posY = Math.abs(startY - j);
				if(Boolean.parseBoolean(Properties.getProperty("torus"))) {
					posX = Math.min(posX, Math.abs(gridSizeX + startX - i)) % gridSizeX;
					posY = Math.min(posY, Math.abs(gridSizeY + startY - j)) % gridSizeY;
				}
				int dist = posX * posX + posY * posY;
				if(!(e.getAgent(i, j) instanceof Wall)) {
					path[i][j] = dist;
				}
			}
		}
	}
}
