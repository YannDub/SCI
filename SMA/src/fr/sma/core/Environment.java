package fr.sma.core;

public class Environment {
	
	private Agent[][] grid;
	private SMA sma;
	
	public Environment(int width, int height) {
		this.grid = new Agent[width][height];
	}
	
	public Agent getAgent(int x, int y) {
		return this.grid[x][y];
	}
	
	public void removeAgent(int x, int y) {
		sma.removedAgent.add(this.getAgent(x, y));
		this.grid[x][y] = null;
	}
	
	public void addAgent(Agent agent, int x, int y) {
		if(sma.removedAgent.contains(this.getAgent(x, y))) sma.removedAgent.remove(this.getAgent(x, y));
		this.grid[x][y] = agent;
	}
	
	public void setSMA(SMA sma) {
		this.sma = sma;
	}
}
