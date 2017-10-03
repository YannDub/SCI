package fr.sma.core;

import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;

import fr.sma.core.utils.Properties;

public class Vue implements Observer {

	private JFrame window;
	private EnvironmentPanel panel;
	private int canvasSizeX = Integer.parseInt(Properties.getProperty("canvasSizeX"));
	private int canvasSizeY = Integer.parseInt(Properties.getProperty("canvasSizeY"));
	private int refresh = Integer.parseInt(Properties.getProperty("refresh"));
	
	public Vue() {
		panel = new EnvironmentPanel(canvasSizeX, canvasSizeY);
		panel.setPreferredSize(new Dimension(canvasSizeX, canvasSizeY));
		
		this.window = new JFrame("SMA");
		window.add(panel);
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		SMA sma = (SMA) o;
		panel.setEnvironment(sma.getEnvironment());
		if(sma.getTick() % refresh == 0) panel.repaint();
	}
	
	public JPanel getPanel() {
		return this.panel;
	}

}
