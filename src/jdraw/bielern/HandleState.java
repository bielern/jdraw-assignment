package jdraw.bielern;

import java.awt.Cursor;
import java.awt.Point;

import jdraw.framework.Figure;

public interface HandleState {

	public Point getLocation(Figure figure);
	
	public Cursor getCursor();
}
