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
	
	protected AbstractRectangularFigure(AbstractRectangularFigure other){
		this();
		bounds = new Rectangle(other.getBounds());
	}
	
	@Override
	public abstract void draw(Graphics g);

	@Override
	public void move(int dx, int dy){
		if (dx != 0 || dy != 0){
			bounds.setLocation(bounds.x + dx, bounds.y + dy);
			_move(dx, dy);
			notifyListeners();
		}
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
	
	protected void setBounds(Rectangle r){
		this.setBounds(r.getLocation(), new Point((int) r.getMaxX(), (int) r.getMaxY()));
	}
	
	@Override
	public Rectangle getBounds(){
		return bounds;
	}
	
}
