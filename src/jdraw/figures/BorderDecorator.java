package jdraw.figures;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import jdraw.framework.Figure;

@SuppressWarnings("serial")
public class BorderDecorator extends DecoratorFigure {
	
	static final private  double padding = 5;

	public BorderDecorator(Figure fig) {
		super(fig);
	}

	@Override
	public void draw(Graphics g) {
		Rectangle bounds = getBounds();
		int x = (int) bounds.getX();
		int y = (int) bounds.getY();
		int w = (int) bounds.getWidth();
		int h = (int) bounds.getHeight();
		g.draw3DRect(x, y, w, h, true);
		super.draw(g);
	}

	@Override
	public void move(int dx, int dy) {
		super.move(dx, dy);
	}

	@Override
	public boolean contains(int x, int y) {
		return super.contains(x, y);
	}

	@Override
	public void setBounds(Point origin, Point corner) {
		super.setBounds(origin, corner);
	}

	@Override
	public Rectangle getBounds() {
		Rectangle fb  = super.getBounds();
		double x = fb.getX() - padding;
		double y = fb.getY() - padding;
		double w = fb.getWidth() + 2*padding;
		double h = fb.getHeight() + 2*padding;
		fb.setFrame(x, y, w, h);
		return fb;
	}

	@Override
	public Object clone() {
		return super.clone();
	}

}
