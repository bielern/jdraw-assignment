package jdraw.figures;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import jdraw.bielern.RectangularHandle;

@SuppressWarnings("serial")
public abstract class AbstractRectangularFigure extends AbstractFigure {

	
	protected Rectangle bounds = new Rectangle();
	
	protected AbstractRectangularFigure(){
		for (int i = 0; i < 8; i++){
			handles.add(new RectangularHandle(this, i));
		}
	}
	
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
}
