package jdraw.bielern;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseEvent;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;

public interface HandleState {

	public Point getLocation(Figure figure);
	
	public Cursor getCursor();
	
	public void startInteraction(Figure figure, int x, int y, MouseEvent e, DrawView v);

	public void dragInteraction(Figure figure, int x, int y, MouseEvent e, DrawView v);

	public void stopInteraction(Figure figure, int x, int y, MouseEvent e, DrawView v);
}
