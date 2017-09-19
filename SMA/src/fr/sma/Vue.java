package fr.sma;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

import fr.sma.agents.Particle;
import fr.sma.utils.Properties;

public class Vue extends Canvas implements Observer {

	private static final long serialVersionUID = 1L;
	private JFrame window;
	private int cellWidth, cellHeight;
	private int canvasSizeX = Integer.parseInt(Properties.getProperty("canvasSizeX"));
	private int canvasSizeY = Integer.parseInt(Properties.getProperty("canvasSizeY"));
	private int gridSizeX = Integer.parseInt(Properties.getProperty("gridSizeX"));
	private int gridSizeY = Integer.parseInt(Properties.getProperty("gridSizeY"));
	
	public Vue() {
		this.setSize(canvasSizeX, canvasSizeY);
		
		this.window = new JFrame("SMA");
		window.add(this);
		window.pack();
		window.setSize(canvasSizeX, canvasSizeY);
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setIgnoreRepaint(true);
		
		this.cellHeight = canvasSizeY / gridSizeX;
		this.cellWidth = canvasSizeX / gridSizeY;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		Graphics g = this.getGraphics();
		SMA sma = (SMA) o;
		
		g.setColor(Color.white);
		g.clearRect(0, 0, canvasSizeX, canvasSizeY);
		
		for(int i = 0; i < gridSizeX; i++) {
			for(int j = 0; j < gridSizeY; j++) {
				Particle p = (Particle) sma.e.getAgent(i, j);
				if(p != null) {
					g.setColor(p.getColor());
					g.fillRect(i * cellWidth, j * cellHeight, cellWidth, cellHeight);
				}
			}
		}
		g.dispose();
		System.out.println("test");
	}

}
