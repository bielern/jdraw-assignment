package jdraw.figures;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.List;

import jdraw.framework.FigureHandle;

@SuppressWarnings("serial")
public abstract class AbstractRectangularFigure extends AbstractFigure {

	//protected LinkedList<RectangularHandle> = new LinkedList<RectangularHandle>();
	
	protected Rectangle bounds = new Rectangle();
	
	@Override
	public abstract void draw(Graphics g);

	@Override
	public void move(int dx, int dy){
		bounds.setLocation(bounds.x + dx, bounds.y + dy);
		_move(dx, dy);
		notifyListeners();
	}
	protected abstract void _move(int dx, int dy);

	@Override
	public boolean contains(int x, int y){
		return bounds.contains(x, y);
	}

	@Override
	public void setBounds(Point origin, Point corner){
		bounds.setFrameFromDiagonal(origin, corner);
		_setBounds(origin, corner);
		notifyListeners();
	}
	protected abstract void _setBounds(Point origin, Point corner);
	
	@Override
	public Rectangle getBounds(){
		return bounds;
	}
	
	@Override
	public List<FigureHandle> getHandles(){
		return null;
	}
}
