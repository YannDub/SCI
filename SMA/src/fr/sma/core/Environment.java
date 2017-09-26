package fr.sma.core;

public class Environment {
	
	private Agent[][] grid;
	
	public Environment(int width, int height) {
		this.grid = new Agent[width][height];
	}
	
	public Agent getAgent(int x, int y) {
		if(x >= grid.length || x < 0 || y >= grid[0].length || y < 0)
			return null;
		return this.grid[x][y];
	}
	
	public void removeAgent(int x, int y) {
		this.grid[x][y] = null;
	}
	
	public void addAgent(Agent agent, int x, int y) {
		this.grid[x][y] = agent;
	}
}
