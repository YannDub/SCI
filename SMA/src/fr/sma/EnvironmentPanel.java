package fr.sma;

import java.awt.Graphics;

import javax.swing.JPanel;

import fr.sma.agents.Particle;
import fr.sma.utils.Properties;

public class EnvironmentPanel extends JPanel {
	
	private Environment e;
	private int gridSizeX = Integer.parseInt(Properties.getProperty("gridSizeX"));
	private int gridSizeY = Integer.parseInt(Properties.getProperty("gridSizeY"));
	private int cellWidth, cellHeight;
	
	public EnvironmentPanel(int width, int height) {
		cellWidth = width / gridSizeX;
		cellHeight = height / gridSizeY;
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(int i = 0; i < gridSizeX; i++) {
			for(int j = 0; j < gridSizeY; j++) {
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
