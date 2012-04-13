package jdraw.figures;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.List;

import jdraw.framework.Figure;
import jdraw.framework.FigureEvent;
import jdraw.framework.FigureHandle;
import jdraw.framework.FigureListener;

@SuppressWarnings("serial")
public abstract class AbstractFigure implements Figure {
	LinkedList<FigureListener> listeners = new LinkedList<FigureListener>();
	
	/**
	 * Notify the listeners, that the figure did change
	 */
	protected void notifyListeners(){
		FigureEvent event = new FigureEvent(this);
		for (FigureListener l: listeners){
			l.figureChanged(event);
		}
	}

	@Override
	public abstract void draw(Graphics g);

	@Override
	public abstract void move(int dx, int dy);

	@Override
	public abstract boolean contains(int x, int y);

	@Override
	public abstract void setBounds(Point origin, Point corner);

	@Override
	public abstract Rectangle getBounds();

	@Override
	public List<FigureHandle> getHandles() {
		return null;
	}

	@Override
	public void addFigureListener(FigureListener listener) {
		listeners.add(listener);
	}

	@Override
	public void removeFigureListener(FigureListener listener) {
		listeners.remove(listener);
	}

	@Override
	public Object clone() {
		return null;
	}
}
