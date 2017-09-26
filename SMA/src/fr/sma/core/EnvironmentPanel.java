package fr.sma.core;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import fr.sma.core.utils.Properties;
import fr.sma.particules.Particle;

public class EnvironmentPanel extends JPanel {
	
	private Environment e;
	private int gridSizeX = Integer.parseInt(Properties.getProperty("gridSizeX"));
	private int gridSizeY = Integer.parseInt(Properties.getProperty("gridSizeY"));
	private boolean grid = Boolean.parseBoolean(Properties.getProperty("grid"));
	private int cellWidth, cellHeight;
	
	
	public EnvironmentPanel(int width, int height) {
		cellWidth = width / gridSizeX;
		cellHeight = height / gridSizeY;
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(int i = 0; i < gridSizeX; i++) {
			for(int j = 0; j < gridSizeY; j++) {
				if(grid) {
					g.setColor(Color.black);
					g.drawRect(i * cellWidth, j * cellHeight, cellWidth, cellHeight);
				}
				
				Particle p = (Particle) e.getAgent(i, j);
				if(p != null) {
					g.setColor(p.getColor());
					g.fillRect(i * cellWidth, j * cellHeight, cellWidth, cellHeight);
				}
			}
		}
	}
	
	public void setEnvironment(Environment e) {
		this.e = e;
	}
}
