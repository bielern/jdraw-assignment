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
	
	protected List<FigureHandle> handles = new LinkedList<FigureHandle>();

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
	public List<FigureHandle> getHandles(){
		return handles;
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
	public abstract Object clone();
	/**
	 * Reorders the points
	 */
	class RightRect {
		public int x, y, width, height;
		public RightRect(int x1, int y1, int x2, int y2){
			x = x1 < x2 ? x1 : x2;
			y = y1 < y2 ? y1 : y2;
			width = Math.abs(x1 - x2);
			height = Math.abs(y1 - y2);
		}
		public RightRect(Point p1, Point p2){
			this(p1.x, p1.y, p2.x, p2.y);
		}
		Point lu(){
			return new Point(this.x, this.y);
		}
		Point rl(){
			return new Point(this.x + this.width, this.y + this.height);
		}
	}
}
