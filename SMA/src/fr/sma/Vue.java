package fr.sma;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

import fr.sma.agents.Particle;

public class Vue extends Canvas implements Observer {

	private static final long serialVersionUID = 1L;
	private JFrame window;
	private int cellWidth, cellHeight;
	
	public Vue() {
		this.setSize(1080, 720);
		
		this.window = new JFrame("SMA");
		window.add(this);
		window.pack();
		window.setSize(1080, 720);
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		this.setIgnoreRepaint(true);
		
		this.cellHeight = 720 / 100;
		this.cellWidth = 1080 / 100;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		Graphics g = this.getGraphics();
		SMA sma = (SMA) o;
		
		g.setColor(Color.white);
		g.clearRect(0, 0, 1080, 720);
		for(int i = 0; i < 100; i++) {
			for(int j = 0; j < 100; j++) {
				Particle p = (Particle) sma.e.getAgent(i, j);
				if(p != null) {
					g.setColor(p.getColor());
					g.fillRect(i * cellWidth, j * cellHeight, cellWidth, cellHeight);
				}
			}
		}
		g.dispose();
	}

}
