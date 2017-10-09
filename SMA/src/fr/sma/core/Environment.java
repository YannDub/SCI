package fr.sma.core;

import fr.sma.core.utils.Properties;

public class Environment {
	
	private Agent[][] grid;
	private int width, height;
	private SMA sma;
	
	public Environment(int width, int height) {
		this.grid = new Agent[width][height];
		this.width = width;
		this.height = height;
	}
	
	public Agent getAgent(int x, int y) {
		if(Boolean.parseBoolean(Properties.getProperty("torus"))) {
			int posX = x;
			int posY = y;
			if(x < 0) posX = width + x;
			if(y < 0) posY = height + y;
			return this.grid[posX % width][posY % height];
		}
		return this.grid[x][y];
	}
	
	public void removeAgent(int x, int y) {
		int posX = x, posY = y;
		if(Boolean.parseBoolean(Properties.getProperty("torus"))) {
			if(x < 0) posX = width + x;
			if(y < 0) posY = height + y;
			posX = posX % width;
			posY = posY % height;
		}
		sma.removedAgent.add(this.getAgent(posX, posY));
		this.grid[posX][posY] = null;
	}
	
	public void addAgent(Agent agent, int x, int y) {
		int posX = x, posY = y;
		if(Boolean.parseBoolean(Properties.getProperty("torus"))) {
			if(x < 0) posX = width + x;
			if(y < 0) posY = height + y;
			posX = posX % width;
			posY = posY % height;
		}
		
		if(sma.removedAgent.contains(this.getAgent(posX, posY))) sma.removedAgent.remove(this.getAgent(posX, posY));
		this.grid[posX][posY] = agent;
	}
	
	public void setSMA(SMA sma) {
		this.sma = sma;
	}
}
