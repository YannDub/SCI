package fr.sma.pacman;

import java.awt.Point;
import java.util.LinkedList;
import java.util.Queue;

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
				path[i][j] = Integer.MAX_VALUE;
			}
		}
	}

	public static void compute(Environment e, int startX, int startY) {
		Queue<Point> queue = new LinkedList<Point>();
		path[startX][startY] = 0;
		queue.add(new Point(startX + 1, startY));
		queue.add(new Point(startX - 1, startY));
		queue.add(new Point(startX, startY + 1));
		queue.add(new Point(startX, startY - 1));

		while (!queue.isEmpty()) {
			Point p = queue.poll();
			try {
				if (!(e.getAgent(p.x, p.y) instanceof Wall)) {
					Point l = new Point(p.x - 1, p.y);
					Point r = new Point(p.x + 1, p.y);
					Point t = new Point(p.x, p.y - 1);
					Point b = new Point(p.x, p.y + 1);
					
					if(Boolean.getBoolean(Properties.getProperty("torus"))) {
						if(l.x < 0) l.x = gridSizeX + l.x;
						if(l.y < 0) l.y = gridSizeY + l.y;
						l.x = l.x % gridSizeX;
						l.y = l.y % gridSizeY;
						
						if(r.x < 0) r.x = gridSizeX + r.x;
						if(r.y < 0) r.y = gridSizeY + r.y;
						r.x = r.x % gridSizeX;
						r.y = r.y % gridSizeY;
						
						if(t.x < 0) t.x = gridSizeX + t.x;
						if(t.y < 0) t.y = gridSizeY + t.y;
						t.x = t.x % gridSizeX;
						t.y = t.y % gridSizeY;
						
						if(b.x < 0) b.x = gridSizeX + b.x;
						if(b.y < 0) b.y = gridSizeY + b.y;
						b.x = b.x % gridSizeX;
						b.y = b.y % gridSizeY;
					}
					
					int h = Integer.MAX_VALUE;
					
					try {
						if(!(e.getAgent(l.x, l.y) instanceof Wall) && path[l.x][l.y] == Integer.MAX_VALUE && !queue.contains(l)) queue.add(l);
						h = path[l.x][l.y];
					} catch(ArrayIndexOutOfBoundsException ex) {
						
					}
					try {
						if(!(e.getAgent(r.x, r.y) instanceof Wall) && path[r.x][r.y] == Integer.MAX_VALUE && !queue.contains(r)) queue.add(r);
						h = Math.min(h, path[r.x][r.y]);
					} catch(ArrayIndexOutOfBoundsException ex) {
						
					}
					try {
						if(!(e.getAgent(t.x, t.y) instanceof Wall) && path[t.x][t.y] == Integer.MAX_VALUE && !queue.contains(t)) queue.add(t);
						h = Math.min(h, path[t.x][t.y]);
					} catch(ArrayIndexOutOfBoundsException ex) {
						
					}
					try {
						if(!(e.getAgent(b.x, b.y) instanceof Wall) && path[b.x][b.y] == Integer.MAX_VALUE && !queue.contains(b)) queue.add(b);
						h = Math.min(h, path[b.x][b.y]);
					} catch(ArrayIndexOutOfBoundsException ex) {
						
					}
					h += 1;
					path[p.x][p.y] = h;
				}
			} catch (ArrayIndexOutOfBoundsException ex) {

			}
		}
	}
}
